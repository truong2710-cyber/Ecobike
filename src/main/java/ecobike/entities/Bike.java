package ecobike.entities;

public class Bike {
    protected int bikeCode;
    protected String type;
    protected String name;
    protected String country;
    protected String dateOfManufacture;
    protected String color;
    protected int price;
    protected String licensePlateNumber;
    protected int slotId;
    protected int parkId;
    protected boolean isRented;
    public Bike(int bikeCode, String type, String name, String country, String dateOfManufacture, String color, int price, String licensePlateNumber, int slotId, int parkId, boolean isRented) {
        this.bikeCode = bikeCode;
        this.type = type;
        this.name = name;
        this.country = country;
        this.dateOfManufacture = dateOfManufacture;
        this.color = color;
        this.price = price;
        this.licensePlateNumber = licensePlateNumber;
        this.slotId = slotId;
        this.parkId = parkId;
        this.isRented = isRented;
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

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
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

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean isRented) {
        this.isRented = isRented;
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
        return getLicensePlateNumber() + " - " + getType();
    }
}
