package ecobike.databaseservices;

import ecobike.databaseconnection.MySQLDB;
import ecobike.entities.Bike;
import ecobike.entities.BikeFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BikeDA {
    public static void updateBikeParkAndStatus(int bike_id, String park_id){
        String command = String.format("UPDATE bike " +
                "SET park_id = '%1$s', is_rented = 0 " +
                "WHERE id = '%2$s'", park_id, Integer.toString(bike_id));
        MySQLDB.execute(command);
    }

    public static void updateBikeRentalStatus(String bike_id, String rental_id){
        String command = String.format("UPDATE bike " +
                "SET is_rented = '%1$s' WHERE id = '%2$s'"
                , rental_id, bike_id);
        MySQLDB.execute(command);
    }

    public static ArrayList<ArrayList<String>> getRentingBikes() {
        String command = "SELECT * FROM bike WHERE is_rented = 1";
        ArrayList<ArrayList<String>> result = MySQLDB.query(command);
        return result;
    }

    public static Bike getBikeByID(String bikeID) {
        String command = "SELECT * FROM bike WHERE id = " + bikeID;
        ArrayList<String> result = MySQLDB.query(command).get(0);

        return BikeFactory.getBike(result);
    }

    public static String getRentalID(int bikeID){
        String command = "SELECT rental.id " +
                "FROM bike JOIN rental " +
                "ON rental.bike_id = bike.id " +
                "WHERE bike.id = " + bikeID;
        String rental_id = MySQLDB.query(command).get(0).get(0);

        return rental_id;
    }
//    public static void main(String[] args){
//        updateBikeParkAndStatus(5, "1");
//    }
}
