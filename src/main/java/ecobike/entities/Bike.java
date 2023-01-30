package ecobike.entities;

public class Bike {
    protected int bikeCode;
    protected String type;
    protected String name;
    protected String country;
    protected String date_of_manufacture;
    protected String color;
    protected int price;
    protected String license_place_number;
    protected int slot_id;
    protected int park_id;
    protected boolean is_rented;
    public Bike(int bikeCode, String type, String name, String country, String date_of_manufacture, String color, int price, String license_place_number, int slot_id, int park_id, boolean is_rented) {
        this.bikeCode = bikeCode;
        this.type = type;
        this.name = name;
        this.country = country;
        this.date_of_manufacture = date_of_manufacture;
        this.color = color;
        this.price = price;
        this.license_place_number = license_place_number;
        this.slot_id = slot_id;
        this.park_id = park_id;
        this.is_rented = is_rented;
    }
    public int getBikeCode() {
        return bikeCode;
    }

    public void setBikeCode(int bikeCode) {
        this.bikeCode = bikeCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate_of_manufacture() {
        return date_of_manufacture;
    }

    public void setDate_of_manufacture(String date_of_manufacture) {
        this.date_of_manufacture = date_of_manufacture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLicense_place_number() {
        return license_place_number;
    }

    public void setLicense_place_number(String license_place_number) {
        this.license_place_number = license_place_number;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public boolean isIs_rented() {
        return is_rented;
    }

    public void setIs_rented(boolean is_rented) {
        this.is_rented = is_rented;
    }

    public int getDeposit(){
        return 4 * getPrice() / 10;
    }

    public String convertBikeCodeToBarCode(int bikeCode){
        if (bikeCode%100<10)
            return "X0"+bikeCode%100;
        return "X"+ bikeCode%100;
    }
    public String getGeneralInfo(){
        return convertBikeCodeToBarCode(bikeCode) + " - " + getType();
    }
}
