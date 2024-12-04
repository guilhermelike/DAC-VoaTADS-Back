package com.voatads.saga.dto;

import java.io.Serializable;
import java.util.UUID;

public class CreateBookingDTO implements Serializable {
    private UUID id;
    private String codBooking;
    private String codFlight;
    private String timeBooking;
    private String status;
    private String idUser;
    
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int qtdPassagens;
    private int qtdMilhas;

    public int getQtdPassagens() {
        return qtdPassagens;
    }

    public void setQtdPassagens(int qtdPassagens) {
        this.qtdPassagens = qtdPassagens;
    }

    public int getQtdMilhas() {
        return qtdMilhas;
    }

    public void setQtdMilhas(int qtdMilhas) {
        this.qtdMilhas = qtdMilhas;
    }

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
    
}
