package ecobike.controllers;

import ecobike.database_services.BikeDatabaseService;
import ecobike.database_services.CardDatabaseService;
import ecobike.database_services.EventDatabaseService;
import ecobike.database_services.PaymentTransactionDatabaseService;
import ecobike.database_services.RentalDatabaseService;
import ecobike.entities.*;
import ecobike.subsystems.barcode_subsystem.BarcodeConverterController;
import ecobike.subsystems.interbank_subsystem.IInterbank;
import ecobike.subsystems.interbank_subsystem.InterbankController;
import ecobike.validators.BarcodeValidator;
import ecobike.views.box.ErrorBox;
import ecobike.views.Main;
import ecobike.views.box.ConfirmBox;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RentBikeController {
    private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String INPUT_DATE_FORMAT = "dd/MM/yyyy";
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
            ErrorBox.show("Error", "Bạn chưa nhập barcode!");
        } else if (!BarcodeValidator.validateBarcode(barcode)) {
            ErrorBox.show("Error", "Barcode không hợp lệ!");
        } else {
            int bikecode = barcodeConverterController.convertBarcodeToBikeCode(barcode);
            bikeID = String.valueOf(bikecode);
            ArrayList<ArrayList<String>> bikes = BikeDatabaseService.getAllBikesByID(bikeID);
            if (bikes.isEmpty()) {
                ErrorBox.show("Error", "Xe này không tồn tại!");
            } else if (bikes.get(0).get(12).equals("1")) {
                ErrorBox.show("Error", "Xe này đang được thuê rồi!");
            } else {
                if (bikes.get(0).get(11).equals(parkingLot.getID())) {
                    RentConfirmation = ConfirmBox.show("ConfirmBox", "Bạn có chắc chắn muốn thê xe có mã " + barcode + " không ạ?");
                } else {
                    RentConfirmation = ConfirmBox.show("ConfirmBox", "Xe " + barcode + " đang ở một bãi khác. Bạn vẫn muốn thuê chứ?");
                }
            }
        }
        return RentConfirmation;
    }

    public boolean checkCardOnRental(String cardCode){
        ArrayList<ArrayList<String>> events = EventDatabaseService.getAllEvents();

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

        ArrayList<ArrayList<String>> ongoingRentalsDB = RentalDatabaseService.getOnGoingRentals(ongoingRentals);
        if (ongoingRentalsDB == null){
            return false;
        }

        boolean cardCurrentlyOnRental = false;
        for (ArrayList<String> ongoingRentalDB : ongoingRentalsDB) {
            if (ongoingRentalDB.get(3).equals(cardCode)) {
                cardCurrentlyOnRental = true;
                break;
            }
        }
        return cardCurrentlyOnRental;
    }

    public String handlePayment(Card card, String amount) {
        try {
//            SimpleDateFormat fromUser = new SimpleDateFormat(INPUT_DATE_FORMAT);
//            SimpleDateFormat myFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
//            String expiredDate = String.valueOf(card.getExpiredDate());

            IInterbank interbank = new InterbankController();
            InterbankTransaction interbankTransaction = new InterbankTransaction(card, "deposit", "Deposit transaction", Integer.parseInt(amount), LocalDateTime.now());
            String respondCode = interbank.processTransaction(interbankTransaction);

            if (respondCode.equals("00")) {
                CardDatabaseService.saveCardInfo(card.getCardCode(), card.getOwner(), card.getCVV(), String.valueOf(card.getExpiredDate()));
                RentalDatabaseService.saveRental(Integer.toString(Main.user_id), bikeID, card.getCardCode());
                String rentalID = RentalDatabaseService.getLastestRentalID();
                Rental rental = new Rental(rentalID, bikeID, card.getCardCode());
                Event event = new Event(rentalID, LocalDateTime.now(), "start");
                EventDatabaseService.saveEvent(event.getRentalId(), event.getType());
                PaymentTransactionDatabaseService.savePaymentTransaction(event.getRentalId(), (long) interbankTransaction.getAmount(), interbankTransaction.getCommand());
                BikeDatabaseService.updateBikeRentalStatus(bikeID, "1");
            }
            return respondCode;

        } catch (Exception e) {
            e.printStackTrace();
            return "08";
        }
    }
}
