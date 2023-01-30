package ecobike.databaseservices;

import ecobike.databaseconnection.MySQLDB;
import ecobike.entities.Bike;
import ecobike.entities.BikeFactory;
import ecobike.entities.ParkingLot;

import java.util.ArrayList;

public class ParkingLotDA {
    public static ArrayList<ParkingLot> getAllParkingLots(){
        String command = "SELECT * FROM parkinglot";
        ArrayList<ArrayList<String>> result = MySQLDB.query(command);
        ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
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
        ArrayList<ArrayList<String>> result = MySQLDB.query(command);
        ArrayList<Bike> bikes = new ArrayList<>();
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