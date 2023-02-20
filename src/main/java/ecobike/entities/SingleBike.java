package ecobike.entities;

public class SingleBike extends Bike{
    public SingleBike(int bikeCode, String name, String country, String dateOfManufacture, String color, int price, String licensePlateNumber, int slotId, int parkId, boolean isRented) {
        super(bikeCode, name, country, dateOfManufacture, color, price, licensePlateNumber, slotId, parkId, isRented);
    }

    @Override
    public String getType() {
        return "Single Bike";
    }

    @Override
    public String getGeneralInfo() {
        return getLicensePlateNumber() + " - " + "Single Bike";
    }
}
