package ecobike.database_services;

import ecobike.database_connection.MySQLConnector;
import ecobike.entities.Bike;
import ecobike.entities.BikeFactory;

import java.util.ArrayList;
import java.util.Set;

public class RentalDatabaseService {
    public static Bike getRentalBike(String rental_id){
        String command = "SELECT bike.* " +
                "FROM bike JOIN rental " +
                "ON bike.id = rental.bike_id " +
                "WHERE rental.id = " + rental_id;
        ArrayList<String> result = MySQLConnector.query(command).get(0);
        Bike bike = BikeFactory.getBike(result);
        return bike;
    }
    public static long getRefund(String rental_id){
        String command = String.format("SELECT amount " +
                "FROM paymenttransaction JOIN rental " +
                "ON paymenttransaction.rental_id = rental.id " +
                "WHERE rental.id = '%1$s' AND type = 'refund'", rental_id);
        long refundAmount = Long.parseLong(MySQLConnector.query(command).get(0).get(0));
        return refundAmount;
    }
    public static long getDeposit(String rental_id){
        String command = String.format("SELECT amount " +
                "FROM paymenttransaction JOIN rental " +
                "ON paymenttransaction.rental_id = rental.id " +
                "WHERE rental.id = '%1$s' AND type = 'deposit'", rental_id);
        long refundAmount = Long.parseLong(MySQLConnector.query(command).get(0).get(0));
        return refundAmount;
    }
    public static String getRentee(String rental_id){
        String command = "SELECT owner " +
                "FROM rental JOIN card " +
                "ON rental.cardcode = card.cardcode " +
                "WHERE rental.id = " + rental_id;
        String rentee = MySQLConnector.query(command).get(0).get(0);
        return rentee;
    }
    public static String getRentTime(String rental_id){
        String command = String.format("SELECT time " +
                "FROM rental JOIN event " +
                "ON rental.id = event.rental_id " +
                "WHERE rental.id = '%1$s' AND type = 'start'", rental_id);
        String startTime = MySQLConnector.query(command).get(0).get(0);
        return startTime;
    }
    public static String getReturnTime(String rental_id){
        String command = String.format("SELECT time " +
                "FROM rental JOIN event " +
                "ON rental.id = event.rental_id " +
                "WHERE rental.id = '%1$s' AND type = 'end'", rental_id);
        String endTime = MySQLConnector.query(command).get(0).get(0);
        return endTime;
    }
    public static void saveRental(String rentee_id, String bike_id, String cardcode){
        String command = String.format("INSERT INTO rental(rentee_id, bike_id, cardcode) " +
                "VALUES (%1$s, '%2$s', '%3$s')", rentee_id, bike_id, cardcode);
        MySQLConnector.execute(command);
    }
    public static ArrayList<ArrayList<String>> getOnGoingRentals(Set<String> ongoingRentals){
        StringBuilder command = new StringBuilder("SELECT * FROM rental WHERE id in (");
        for (String ongoingRental : ongoingRentals) {
            command.append("\"").append(ongoingRental).append("\"").append(", ");
        }
        command = new StringBuilder(command.substring(0, command.length() - 2));
        command.append(")");
        ArrayList<ArrayList<String>> ongoingRentalsDB = MySQLConnector.query(command.toString());
        return ongoingRentalsDB;
    }
    public static String getLastestRentalID(){
        String command = "SELECT * FROM rental ORDER BY id DESC LIMIT 1";
        String rentalID = MySQLConnector.query(command).get(0).get(0);
        return rentalID;
    }

}
