package com.voatads.flights.repository;

import com.voatads.flights.model.Voo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface VooRepository extends JpaRepository<Voo, UUID> {

    List<Voo> findByAeroportoOrigemAndAeroportoDestino(String aeroportoOrigem, String aeroportoDestino);

    Voo findByCodigoVoo(String codigoVoo);
}
