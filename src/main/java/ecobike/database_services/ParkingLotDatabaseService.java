package ecobike.database_services;

import ecobike.database_connection.MySQLConnector;
import ecobike.entities.Bike;
import ecobike.entities.BikeFactory;
import ecobike.entities.ParkingLot;

import java.util.ArrayList;

public class ParkingLotDatabaseService {
    public static ArrayList<ParkingLot> getAllParkingLotsWithBike(){
        String command = "SELECT p.* " +
                "FROM (parkinglot AS p " +
                "JOIN " +
                "(SELECT DISTINCT park_id FROM bike WHERE is_rented = 0) AS q " +
                "ON p.id = q.park_id) ";
        ArrayList<ArrayList<String>> result = MySQLConnector.query(command);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        assert result != null;
        for (ArrayList<String> row : result){
            ParkingLot parkingLot = new ParkingLot(row.get(0), row.get(1), row.get(2), Integer.parseInt(row.get(3)));
            parkingLots.add(parkingLot);
        }
        /* TODO: getBike? */
        return parkingLots;
    }

    public static ArrayList<ParkingLot> getAllParkingLotsNotFull(){
        String command = "SELECT parkinglot.* FROM parkinglot JOIN " +
                "(SELECT parkinglot.id, COUNT(bike.id) AS num_bike FROM " +
                "(parkinglot LEFT JOIN bike ON parkinglot.id = bike.park_id) GROUP BY parkinglot.id) AS b " +
                "ON parkinglot.id = b.id " +
                "WHERE parkinglot.total_slots > b.num_bike";
        ArrayList<ArrayList<String>> result = MySQLConnector.query(command);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        assert result != null;
        for (ArrayList<String> row : result){
            ParkingLot parkingLot = new ParkingLot(row.get(0), row.get(1), row.get(2), Integer.parseInt(row.get(3)));
            parkingLots.add(parkingLot);
        }
        /* TODO: getBike? */
        return parkingLots;
    }

    public static ArrayList<Bike> getBikesInParkingLot(String ID){
        String command = "SELECT bike.* " +
                "FROM bike JOIN parkinglot " +
                "ON bike.park_id = parkinglot.id " +
                "WHERE park_id = " + ID;
        ArrayList<ArrayList<String>> result = MySQLConnector.query(command);
        ArrayList<Bike> bikes = new ArrayList<>();
        assert result != null;
        for (ArrayList<String> row : result){
            Bike bike = BikeFactory.getBike(row);
            bikes.add(bike);
        }
        return bikes;
    }
    public static ParkingLot getParkingLotByID(String ID) {
        String command = "SELECT * FROM parkinglot where id  = " + ID;
        ArrayList<ArrayList<String>> result = MySQLConnector.query(command);
        assert result != null;
        ArrayList<String> row = result.get(0);
        ParkingLot parkingLot = new ParkingLot(row.get(0), row.get(1), row.get(2), Integer.parseInt(row.get(3)));
        return parkingLot;
    }

    public static ArrayList<Bike> getAvailableBikeInParkingLot(String ID) {
        String command = "SELECT bike.* " +
                "FROM bike JOIN parkinglot " +
                "ON bike.park_id = parkinglot.id " +
                "WHERE park_id = " + ID +
                " and bike.is_rented = 0";
        ArrayList<ArrayList<String>> result = MySQLConnector.query(command);
        ArrayList<Bike> bikes = new ArrayList<>();
        assert result != null;
        for (ArrayList<String> row : result){
            Bike bike = BikeFactory.getBike(row);
            bikes.add(bike);
        }
        return bikes;
    }

    public static boolean getBoolean(String value)
    {
        return !value.equals("0");
    }
}
