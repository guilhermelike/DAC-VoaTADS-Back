package com.voatads.saga.dto;

import java.io.Serializable;
import java.util.UUID;

public class BookingDTO implements Serializable {
    private UUID id;
    private String codBooking;
    private String codFlight;
    private String timeBooking;
    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getCodBooking() {
        return codBooking;
    }

    public void setCodBooking(String codBooking) {
        this.codBooking = codBooking;
    }
    
    public String getCodFlight() {
        return codFlight;
    }
    
    public void setCodFlight(String codFlight) {
        this.codFlight = codFlight;
    }
    
    public String getTimeBooking() {
        return timeBooking;
    }
    
    public void setTimeBooking(String timeBooking) {
        this.timeBooking = timeBooking;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookingCommand{" +
            "id=" + id +
            ", codBooking='" + codBooking + '\'' +
            ", codFlight='" + codFlight + '\'' +
            ", timeBooking='" + timeBooking + '\'' +
            ", status='" + status + '\'' +
            '}';
}
}
