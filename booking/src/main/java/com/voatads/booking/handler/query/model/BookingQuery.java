package com.voatads.booking.handler.query.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking", schema = "bookingquery")
public class BookingQuery {

    @Id
    // @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column
    private String codBooking;
    @Column
    private String codFlight;
    @Column
    private String timeBooking;
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

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }
}
