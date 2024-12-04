package com.voatads.flights.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateBookingVooDTO implements Serializable  {
    private String codFlight;
    private int qtdPassagens;
}
