package ecobike.controllers;

import ecobike.databaseconnection.MySQLDB;
import ecobike.entities.ParkingLot;
import ecobike.subsystems.barcodesubsystem.BarcodeConverterController;
import ecobike.views.box.NotificationBox;
import ecobike.views.DepositScreenController;
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
            String command = "SELECT * FROM bike WHERE id = " + bikecode;
            ArrayList<ArrayList<String>> bikes = MySQLDB.query(command);
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

                if(RentConfirmation) {
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

    public void handlePayment(String text1, String text2, String text3, String text4, String bikeID) {
        if (text1.isEmpty() || text2.isEmpty() || text3.isEmpty()) {
            NotificationBox.display("NotificationBox", "Fill mandatory fields!");
        } else {
            String command = "SELECT * FROM card";
            ArrayList<ArrayList<String>> cards = MySQLDB.query(command);
            boolean cardInfoMatched = false;
            for (ArrayList<String> card : cards) {
                if (card.get(0).equals(text1) && card.get(1).equals(text2) && card.get(2).equals(text3)) {
                    cardInfoMatched = true;
                    command = "SELECT * FROM event";
                    ArrayList<ArrayList<String>> events = MySQLDB.query(command);

                    Set<String> ongoingRentals = new HashSet<String> ();
                    for (ArrayList<String> event : events) {
                        if (event.get(3).equals("start")) {
                            ongoingRentals.add(event.get(1));
                        } else if (event.get(3).equals("end")) {
                            ongoingRentals.remove(event.get(1));
                        }
                    }

                    command = "SELECT * FROM rental WHERE id in (";
                    for (String ongoingRental : ongoingRentals) {
                        command += "\"" + ongoingRental + "\"" + ", ";
                    }
                    command = command.substring(0, command.length() - 2);
                    command += ")";
                    ArrayList<ArrayList<String>> ongoingRentalsDB = MySQLDB.query(command);

                    boolean cardCurrentlyOnRental = false;
                    for (ArrayList<String> ongoingRentalDB : ongoingRentalsDB) {
                        if (ongoingRentalDB.get(3).equals(card.get(0))) {
                            cardCurrentlyOnRental = true;
                        }
                    }

                    if (cardCurrentlyOnRental) {
                        NotificationBox.display("NotificationBox", "Card currently on rental");
                    } else {
                        boolean DepositConfirmation = ConfirmBox.display("ConfirmBox", "Proceed to deposit?");
                        if (DepositConfirmation) {
                            NotificationBox.display("NotificationBox", "Checking balance... Placing deposit");
                            boolean depositResult = true;
                            if (depositResult) {
                                NotificationBox.display("NotificationBox", "Rent request successful!");

                                command = "SELECT * FROM rental";
                                ArrayList<ArrayList<String>> rentals = MySQLDB.query(command);
                                String rentee_id = "1";
                                command = "INSERT INTO rental VALUES (" + (rentals.size() + 1) + ", " + rentee_id + ", " + bikeID + ", '" + card.get(0) + "')";
                                MySQLDB.execute(command);
                                command = "INSERT INTO event VALUES (" + (events.size() + 1) + ", " + (rentals.size() + 1) + ", '" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN)) + "', 'start')";
                                MySQLDB.execute(command);
                                command = "SELECT * FROM paymenttransaction";
                                ArrayList<ArrayList<String>> paymenttransactions = MySQLDB.query(command);
                                command = "INSERT INTO paymenttransaction VALUES (" + (paymenttransactions.size() + 1) + ", " + (rentals.size() + 1) + ", " + text4 + ", '" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN)) + "', 'deposit')";
                                MySQLDB.execute(command);
                                command = "UPDATE bike SET is_rented = 1 WHERE id = " + bikeID + "";
                                MySQLDB.execute(command);
                            } else {
                                NotificationBox.display("NotificationBox", "Not enough balance");
                            }
                        }
                    }
                }
            }
            if (!cardInfoMatched) {
                NotificationBox.display("NotificationBox", "Card not found or CVV wrong!");
            }
        }
    }
}
