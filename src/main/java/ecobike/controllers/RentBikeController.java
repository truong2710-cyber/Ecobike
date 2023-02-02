package ecobike.controllers;

import ecobike.databaseservices.BikeDA;
import ecobike.databaseservices.CardDA;
import ecobike.databaseservices.EventDA;
import ecobike.databaseservices.PaymentTransactionDA;
import ecobike.databaseservices.RentalDA;
import ecobike.entities.Card;
import ecobike.entities.ParkingLot;
import ecobike.subsystems.barcodesubsystem.BarcodeConverterController;
import ecobike.subsystems.interbanksubsystem.IInterbank;
import ecobike.subsystems.interbanksubsystem.InterbankSubsysController;
import ecobike.views.box.NotificationBox;
import ecobike.views.Main;
import ecobike.views.box.ConfirmBox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RentBikeController {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private ParkingLot parkingLot;
    private String bikeID;

    public String getBikeID() {
        return bikeID;
    }
    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public RentBikeController() {

    }

    public RentBikeController(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public boolean handleRentBike(String barcode) {
        BarcodeConverterController barcodeConverterController = new BarcodeConverterController();
        boolean RentConfirmation = false;
        if (barcode.isEmpty()) {
            NotificationBox.display("NotificationBox", "Enter bike's barcode!");
        } else {
            int bikecode = barcodeConverterController.convertBarcodeToBikeCode(barcode);
            bikeID = String.valueOf(bikecode);
            ArrayList<ArrayList<String>> bikes = BikeDA.getAllBikesByID(bikeID);
            if (bikes.isEmpty()) {
                NotificationBox.display("NotificationBox", "Bike does not exist!");
            } else if (bikes.get(0).get(12).equals("1")) {
                NotificationBox.display("NotificationBox", "Bike is currently rented!");
            } else {
                if (bikes.get(0).get(11).equals(parkingLot.getID())) {
                    RentConfirmation = ConfirmBox.display("ConfirmBox", "Proceed to rent bike " + barcode + "?");
                } else {
                    RentConfirmation = ConfirmBox.display("ConfirmBox", "Bike " + barcode + " is in another parking lot. Do you want to proceed?");
                }
            }
        }
        return RentConfirmation;
    }

    public boolean checkCardOnRental(String cardCode){
        ArrayList<ArrayList<String>> events = EventDA.getAllEvents();

        Set<String> ongoingRentals = new HashSet<>();
        for (ArrayList<String> event : events) {
            if (event.get(3).equals("start")) {
                ongoingRentals.add(event.get(1));
            } else if (event.get(3).equals("end")) {
                ongoingRentals.remove(event.get(1));
            }
        }
        if (ongoingRentals.size() == 0){
            return false;
        }

        ArrayList<ArrayList<String>> ongoingRentalsDB = RentalDA.getOnGoingRentals(ongoingRentals);
        if (ongoingRentalsDB == null){
            return false;
        }

        boolean cardCurrentlyOnRental = false;
        for (ArrayList<String> ongoingRentalDB : ongoingRentalsDB) {
            if (ongoingRentalDB.get(3).equals(cardCode)) {
                cardCurrentlyOnRental = true;
            }
        }
        return cardCurrentlyOnRental;
    }

    public String handlePayment(String cardCode, String owner, String cvv, String amount, String expiredDate) {
        try {
            SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            expiredDate = myFormat.format(fromUser.parse(expiredDate));

            IInterbank interbank = new InterbankSubsysController();
            Card card = new Card(cardCode, owner, cvv, expiredDate);
            String respondCode = interbank.processTransaction(card, Integer.parseInt(amount), "deposit", "Refund transaction");

            if (respondCode.equals("00")) {
                CardDA.saveCardInfo(cardCode, owner, cvv, expiredDate);
                RentalDA.saveRental(Integer.toString(Main.user_id), bikeID, cardCode);
                String rentalID = RentalDA.getLastestRentalID();
                EventDA.saveEvent(rentalID, "start");
                PaymentTransactionDA.savePaymentTransaction(rentalID, Integer.parseInt(amount), "deposit");
                BikeDA.updateBikeRentalStatus(bikeID, "1");
            }
            return respondCode;

        } catch (Exception e) {
            e.printStackTrace();
            return "08";
        }
    }
}
