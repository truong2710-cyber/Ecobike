package ecobike.entities;

public class ElectricDoubleBike extends Bike{
    private int battery;
    private int maximumUsageTime;
    public ElectricDoubleBike(int bikeCode, String type, String name, String country, String dateOfManufacture, String color, int price, String licensePlateNumber, int slotId, int parkId, boolean isRented, int battery, int maximumUsageTime) {
        super(bikeCode, type, name, country, dateOfManufacture, color, price, licensePlateNumber, slotId, parkId, isRented);
        this.battery = battery;
        this.maximumUsageTime = maximumUsageTime;
    }
}


