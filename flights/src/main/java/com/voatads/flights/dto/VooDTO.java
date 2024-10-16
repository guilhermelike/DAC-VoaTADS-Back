package com.voatads.flights.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class VooDTO {

    private UUID id;
    private String codigoVoo;
    private LocalDate dataVoo;
    private LocalTime horarioVoo;
    private String aeroportoOrigem;
    private String aeroportoDestino;
    private BigDecimal valorPassagem;
    private BigDecimal valorMilhas;
    private Integer qtdPoltronas;
}
