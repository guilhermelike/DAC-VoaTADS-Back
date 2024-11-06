package com.voatads.booking.dto;

import java.util.UUID;

public class BookingDTO {
    private UUID id;
    private String cod_booking;
    private String cod_flight;
    private String time_booking;
    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getCodBooking() {
        return cod_booking;
    }

    public void setCodBooking(String cod_booking) {
        this.cod_booking = cod_booking;
    }
    
    public String getCodFlight() {
        return cod_flight;
    }
    
    public void setCodFlight(String cod_flight) {
        this.cod_flight = cod_flight;
    }
    
    public String getTimeBooking() {
        return time_booking;
    }
    
    public void setTimeBooking(String time_booking) {
        this.time_booking = time_booking;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    } 
}
