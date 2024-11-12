package com.voatads.flights.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VooDTO {

    private UUID id;
    private String codigoVoo;
    private LocalDateTime dataVoo;
    private String aeroportoOrigem;
    private String aeroportoDestino;
    private BigDecimal valorPassagem;
    private BigDecimal valorMilhas;
    private Integer totalPoltronas;
    private Integer qtdPoltronasOcupadas;
    private String statusVoo;
}
