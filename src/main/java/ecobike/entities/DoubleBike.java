package ecobike.entities;

public class DoubleBike extends Bike{
    public DoubleBike(int bikeCode, String type, String name, String country, String dateOfManufacture, String color, int price, String licensePlateNumber, int slotId, int parkId, boolean isRented) {
        super(bikeCode, type, name, country, dateOfManufacture, color, price, licensePlateNumber, slotId, parkId, isRented);
    }
}
