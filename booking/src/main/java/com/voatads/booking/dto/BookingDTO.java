package com.voatads.booking.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class BookingDTO implements Serializable {
    private UUID id;
    private String codBooking;
    private String codFlight;
    private LocalDateTime timeBooking;
    private String status;
    private UUID idUser;
    
//     "codFlight": "TADS103"
// "idUser": "e1c347    private Timestamp timeBooking;cc-056b-4a76-b371-262eae7140b0"
// "qtdMilhas": 0
// "qtdPassagens": 1

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
    
    public LocalDateTime getTimeBooking() {
        return timeBooking;
    }

    public void setTimeBooking(LocalDateTime timeBooking) {
        this.timeBooking = timeBooking;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUSer(UUID idUser) {
        this.idUser = idUser;
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
