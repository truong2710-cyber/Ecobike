package ecobike.databaseservices;

import ecobike.databaseconnection.MySQLDB;
import ecobike.entities.Bike;
import ecobike.entities.BikeFactory;

import java.util.ArrayList;

public class RentalDA {
    public static Bike getRentalBike(String rental_id){
        String command = "SELECT bike.* " +
                "FROM bike JOIN rental " +
                "ON bike.id = rental.bike_id " +
                "WHERE rental.id = " + rental_id;
        ArrayList<String> result = MySQLDB.query(command).get(0);
        Bike bike = BikeFactory.getBike(result);
        return bike;
    }
    public static long getRefund(String rental_id){
        String command = String.format("SELECT amount " +
                "FROM paymenttransaction JOIN rental " +
                "ON paymenttransaction.rental_id = rental.id " +
                "WHERE rental.id = '%1$s' AND type = 'refund'", rental_id);
        long refundAmount = Long.parseLong(MySQLDB.query(command).get(0).get(0));
        return refundAmount;
    }
    public static long getDeposit(String rental_id){
        String command = String.format("SELECT amount " +
                "FROM paymenttransaction JOIN rental " +
                "ON paymenttransaction.rental_id = rental.id " +
                "WHERE rental.id = '%1$s' AND type = 'deposit'", rental_id);
        long refundAmount = Long.parseLong(MySQLDB.query(command).get(0).get(0));
        return refundAmount;
    }
    public static String getRentee(String rental_id){
        String command = "SELECT owner " +
                "FROM rental JOIN card " +
                "ON rental.cardcode = card.cardcode " +
                "WHERE rental.id = " + rental_id;
        String rentee = MySQLDB.query(command).get(0).get(0);
        return rentee;
    }
    public static String getRentTime(String rental_id){
        String command = String.format("SELECT time " +
                "FROM rental JOIN event " +
                "ON rental.id = event.rental_id " +
                "WHERE rental.id = '%1$s' AND type = 'start'", rental_id);
        String startTime = MySQLDB.query(command).get(0).get(0);
        return startTime;
    }
    public static String getReturnTime(String rental_id){
        String command = String.format("SELECT time " +
                "FROM rental JOIN event " +
                "ON rental.id = event.rental_id " +
                "WHERE rental.id = '%1$s' AND type = 'end'", rental_id);
        String endTime = MySQLDB.query(command).get(0).get(0);
        return endTime;
    }
    public static void saveRental(String rentee_id, String bike_id, String cardcode){
        String command = String.format("INSERT INTO rental(rentee_id, bike_id, cardcode) " +
                "VALUES (%1$s, '%2$s', '%3$s')", rentee_id, bike_id, cardcode);
        MySQLDB.execute(command);
    }
}
