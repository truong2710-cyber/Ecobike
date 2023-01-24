package ecobike.entities;

public class Rental {
    private int rentee_id;
    private int bike_id;

    public Rental(int rentee_id, int bike_id) {
        this.rentee_id = rentee_id;
        this.bike_id = bike_id;
    }

    public int getRentee_id() {
        return rentee_id;
    }

    public void setRentee_id(int rentee_id) {
        this.rentee_id = rentee_id;
    }

    public int getBike_id() {
        return bike_id;
    }

    public void setBike_id(int bike_id) {
        this.bike_id = bike_id;
    }
}
