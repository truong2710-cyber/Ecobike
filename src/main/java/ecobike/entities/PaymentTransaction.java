package ecobike.entities;
import java.time.LocalDateTime;
public class PaymentTransaction {
    private String id;
    private String rentalId;
    private int amount;
    private LocalDateTime time;
    private String type;

    public PaymentTransaction(String id, String rentalId, int amount, LocalDateTime time, String type) {
        this.id = id;
        this.rentalId = rentalId;
        this.amount = amount;
        this.time = time;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rental_id) {
        this.rentalId = rental_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
