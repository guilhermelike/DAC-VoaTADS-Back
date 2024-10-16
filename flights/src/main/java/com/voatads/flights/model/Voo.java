package com.voatads.flights.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "voo")
public class Voo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "codigo_voo", nullable = false)
    private String codigoVoo;

    @Column(name = "data_voo", nullable = false)
    private LocalDate dataVoo;

    @Column(name = "horario_voo", nullable = false)
    private LocalTime horarioVoo;

    @Column(name = "aeroporto_origem", nullable = false)
    private String aeroportoOrigem;

    @Column(name = "aeroporto_destino", nullable = false)
    private String aeroportoDestino;

    @Column(name = "valor_passagem", nullable = false)
    private BigDecimal valorPassagem;

    @Column(name = "valor_milhas", nullable = false)
    private BigDecimal valorMilhas;

    @Column(name = "qtd_poltronas", nullable = false)
    private Integer qtdPoltronas;
}
