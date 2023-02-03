package ecobike.entities;

public class Rental {
    private int renteeId;
    private int bikeId;

    public Rental(int renteeId, int bikeId) {
        this.renteeId = renteeId;
        this.bikeId = bikeId;
    }

    public int getRenteeId() {
        return renteeId;
    }

    public void setRenteeId(int renteeId) {
        this.renteeId = renteeId;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }
}
