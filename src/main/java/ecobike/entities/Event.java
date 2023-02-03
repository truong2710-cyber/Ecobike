package ecobike.entities;

import java.time.LocalDateTime;

public class Event {
    private String id;
    private String rentalId;
    private LocalDateTime time;
    private String type;

    public Event(String id, String rentalId, LocalDateTime time, String type) {
        this.id = id;
        this.rentalId = rentalId;
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
