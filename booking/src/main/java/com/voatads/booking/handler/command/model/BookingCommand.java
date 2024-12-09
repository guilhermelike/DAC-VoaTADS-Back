package com.voatads.booking.handler.command.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking", schema = "bookingcommand")
public class BookingCommand {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column
    private String codBooking;
    @Column
    private String codFlight;
    @Column(name = "timeBooking", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime timeBooking;
    @Column
    private String status;
    @Column
    private UUID idUser;

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

    public void setIdUser(UUID idUser) {
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
