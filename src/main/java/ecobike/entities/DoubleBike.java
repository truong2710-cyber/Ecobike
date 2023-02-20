package ecobike.entities;

public class DoubleBike extends Bike{
    public DoubleBike(int bikeCode, String name, String country, String dateOfManufacture, String color, int price, String licensePlateNumber, int slotId, int parkId, boolean isRented) {
        super(bikeCode, name, country, dateOfManufacture, color, price, licensePlateNumber, slotId, parkId, isRented);
    }

    @Override
    public String getType() {
        return "Double Bike";
    }

    @Override
    public String getGeneralInfo() {
        return getLicensePlateNumber() + " - " + "Double Bike";
    }
}
