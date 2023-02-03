package ecobike.database_services;

import ecobike.database_connection.MySQLConnector;
import ecobike.entities.Bike;
import ecobike.entities.BikeFactory;

import java.util.ArrayList;

public class BikeDatabaseService {
    public static void updateBikeParkAndStatus(int bike_id, String park_id){
        String command = String.format("UPDATE bike " +
                "SET park_id = '%1$s', is_rented = 0 " +
                "WHERE id = '%2$s'", park_id, Integer.toString(bike_id));
        MySQLConnector.execute(command);
    }

    public static void updateBikeRentalStatus(String bike_id, String rental_id){
        String command = String.format("UPDATE bike " +
                "SET is_rented = '%1$s' WHERE id = '%2$s'"
                , rental_id, bike_id);
        MySQLConnector.execute(command);
    }

    public static ArrayList<Bike> getRentingBikes(int user_id) {
        String command = "SELECT * FROM bike JOIN " +
                "(SELECT bike_id FROM rental WHERE id IN " +
                "(SELECT rental_id FROM event " +
                "WHERE rental_id NOT IN (SELECT rental_id FROM event WHERE type = 'end')) AND rentee_id = " + Integer.toString(user_id)  +") AS r ON bike.id = r.bike_id";
        ArrayList<ArrayList<String>> result = MySQLConnector.query(command);
        ArrayList<Bike> bikes = new ArrayList<>();
        for (ArrayList<String> row: result){
            Bike bike = BikeFactory.getBike(row);
            bikes.add(bike);
        }
        return bikes;
    }

    public static Bike getBikeByID(String bikeID) {
        String command = "SELECT * FROM bike WHERE id = " + bikeID;
        ArrayList<String> result = MySQLConnector.query(command).get(0);

        return BikeFactory.getBike(result);
    }

    public static ArrayList<ArrayList<String>> getAllBikesByID(String bikeID) {
        String command = "SELECT * FROM bike WHERE id = " + bikeID;
        ArrayList<ArrayList<String>> result = MySQLConnector.query(command);

        return result;
    }

    public static String getRentalID(int bikeID){
        String command = "SELECT rental.id " +
                "FROM bike JOIN rental " +
                "ON rental.bike_id = bike.id " +
                "WHERE bike.id = " + bikeID +
                " AND rental.id NOT IN " +
                "(SELECT rental.id FROM rental JOIN event ON rental.id = event.rental_id WHERE event.type = 'end')";
        String rental_id = MySQLConnector.query(command).get(0).get(0);

        return rental_id;
    }
//    public static void main(String[] args){
//        System.out.println(getRentalID(1));
//    }
}
