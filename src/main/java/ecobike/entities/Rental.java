package ecobike.entities;

public class Rental {
    private String renteeId;
    private String bikeId;
    private String cardCode;

    public Rental(String renteeId, String bikeId, String cardCode) {
        this.renteeId = renteeId;
        this.bikeId = bikeId;
        this.cardCode = cardCode;
    }

    public String getRenteeId() {
        return renteeId;
    }

    public void setRenteeId(String renteeId) {
        this.renteeId = renteeId;
    }

    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }
}
