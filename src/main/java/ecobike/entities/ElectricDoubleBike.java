package ecobike.entities;

public class ElectricDoubleBike extends Bike{
    private int battery;
    private int maximum_usage_time;
    public ElectricDoubleBike(int bikeCode, String type, String name, String country, String date_of_manufacture, String color, int price, String license_place_number, int slot_id, int park_id, boolean is_rented, int battery, int maximum_usage_time) {
        super(bikeCode, type, name, country, date_of_manufacture, color, price, license_place_number, slot_id, park_id, is_rented);
        this.battery = battery;
        this.maximum_usage_time = maximum_usage_time;
    }
}


