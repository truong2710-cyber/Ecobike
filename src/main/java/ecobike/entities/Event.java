package ecobike.entities;

import java.time.LocalDateTime;

public class Event {
    private String id;
    private String rental_id;
    private LocalDateTime time;
    private String type;

    public Event(String id, String rental_id, LocalDateTime time, String type) {
        this.id = id;
        this.rental_id = rental_id;
        this.time = time;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRental_id() {
        return rental_id;
    }

    public void setRental_id(String rental_id) {
        this.rental_id = rental_id;
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
