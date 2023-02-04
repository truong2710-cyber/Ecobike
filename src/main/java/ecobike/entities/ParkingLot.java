package ecobike.entities;

import ecobike.database_services.ParkingLotDatabaseService;
import java.util.ArrayList;

public class ParkingLot {
    private String ID;
    private String name;
    private String location;
    private int totalSlots;
    private ArrayList<Bike> bikes;

    public ParkingLot(String ID, String location, String name, int totalSlots) {
        this.ID = ID;
        this.name = name;
        this.location = location;
        this.totalSlots = totalSlots;
        this.bikes = this.getBikes();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public ArrayList<Bike> getBikes() {
        bikes = ParkingLotDatabaseService.getBikesInParkingLot(ID);
        return bikes;
    }

    public int getNumberOfBikes() {
        return ParkingLotDatabaseService.getBikesInParkingLot(ID).size();
    }

    public int getNumberOfAvailableBikes() {
        return ParkingLotDatabaseService.getAvailableBikeInParkingLot(ID).size();
    }
    public void setBikes(ArrayList<Bike> bikes) {
        this.bikes = bikes;
    }

    public String getGeneralInfo(){
        return String.format(" %-2s - %s", name, location);
    }
}
