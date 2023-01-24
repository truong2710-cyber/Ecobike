package ecobike.entities;

public class DoubleBike extends Bike{
    public DoubleBike(int bikeCode, String type, String name, String country, String date_of_manufacture, String color, int price, String license_place_number, int slot_id, int park_id, boolean is_rented) {
        super(bikeCode, type, name, country, date_of_manufacture, color, price, license_place_number, slot_id, park_id, is_rented);
    }
}
