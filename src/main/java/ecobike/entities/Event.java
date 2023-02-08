package ecobike.entities;

import java.time.LocalDateTime;

public class Event {
    private String rentalId;
    private LocalDateTime time;
    private String type;

    public Event(String rentalId, LocalDateTime time, String type) {
        this.rentalId = rentalId;
        this.time = time;
        this.type = type;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
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
