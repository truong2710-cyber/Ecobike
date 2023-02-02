package ecobike.controllers;

import ecobike.databaseconnection.MySQLDB;
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
import ecobike.views.DepositScreenController;
import ecobike.views.Main;
import ecobike.views.box.ConfirmBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RentBikeController {
    private ParkingLot parkingLot;
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    public RentBikeController() {

    }

    public RentBikeController(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void handleRentBike(String barcode) {
        BarcodeConverterController barcodeConverterController = new BarcodeConverterController();
        if (barcode.isEmpty()) {
            NotificationBox.display("NotificationBox", "Enter bike's barcode!");
        } else {
            int bikecode = barcodeConverterController.convertBarcodeToBikeCode(barcode);
            ArrayList<ArrayList<String>> bikes = BikeDA.getAllBikesByID(String.valueOf(bikecode));
            if (bikes.isEmpty()) {
                NotificationBox.display("NotificationBox", "Bike does not exist!");
            } else if (bikes.get(0).get(12).equals("1")) {
                NotificationBox.display("NotificationBox", "Bike is currently rented!");
            } else {
                boolean RentConfirmation;
                if (!bikes.get(0).get(11).equals(parkingLot.getID())) {
                    RentConfirmation = ConfirmBox.display("ConfirmBox", "Bike " + barcode + " is in another parking lot. Do you want to proceed?");
                } else {
                    RentConfirmation = ConfirmBox.display("ConfirmBox", "Proceed to rent bike " + barcode + "?");
                }

                if (RentConfirmation) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/DepositScreen.fxml"));
                        Parent root = loader.load();

                        DepositScreenController depositScreenController = loader.getController();
                        depositScreenController.setBikeID(bikes.get(0).get(0));
            
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(new Scene(root));
                        stage.setTitle("Deposit Screen");
                        stage.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void handlePayment(String text1, String text2, String text3, String text4, String text5, String bikeID) {
        if (text1.isEmpty() || text2.isEmpty() || text3.isEmpty()) {
            NotificationBox.display("NotificationBox", "Fill mandatory fields!");
        } else {
            // call interbank API -> cardInfoMatched
            boolean cardInfoMatched = true;
            if (cardInfoMatched) {
                ArrayList<ArrayList<String>> events = EventDA.getAllEvents();

                Set<String> ongoingRentals = new HashSet<>();
                for (ArrayList<String> event : events) {
                    if (event.get(3).equals("start")) {
                        ongoingRentals.add(event.get(1));
                    } else if (event.get(3).equals("end")) {
                        ongoingRentals.remove(event.get(1));
                    }
                }

                ArrayList<ArrayList<String>> ongoingRentalsDB = RentalDA.getOnGoingRentals(ongoingRentals);

                boolean cardCurrentlyOnRental = false;
                for (ArrayList<String> ongoingRentalDB : ongoingRentalsDB) {
                    if (ongoingRentalDB.get(3).equals(text1)) {
                        cardCurrentlyOnRental = true;
                    }
                }

                if (cardCurrentlyOnRental) {
                    NotificationBox.display("NotificationBox", "Card currently on rental");
                } else {
                    boolean DepositConfirmation = ConfirmBox.display("ConfirmBox", "Proceed to deposit?");
                    if (DepositConfirmation) {
                        NotificationBox.display("NotificationBox", "Checking balance... Placing deposit");
                        IInterbank interbank = new InterbankSubsysController();
                        Card card = new Card(text1, text2, text3, text5);
                        String respondCode = interbank.processTransaction(card, Integer.parseInt(text4), "deposit", "Refund transaction");

                        if (respondCode.equals("00")) {
                            NotificationBox.display("NotificationBox", "Rent request successful!");

                            RentalDA.saveRental(Integer.toString(Main.user_id), bikeID, text1);
                            String rentalID = RentalDA.getLastestRentalID();
                            EventDA.saveEvent(rentalID, "start");
                            PaymentTransactionDA.savePaymentTransaction(rentalID, Integer.parseInt(text4), "deposit");
                            BikeDA.updateBikeRentalStatus(bikeID, "1");
                            CardDA.saveCardInfo(text1, text2, text3, text5);
                        } else {
                            NotificationBox.display("NotificationBox", "Not enough balance");
                            //TODO: handle other respond codes?
                        }
                    }
                }
            } else {
                NotificationBox.display("NotificationBox", "Wrong card info!");
            }
        }
    }
}
