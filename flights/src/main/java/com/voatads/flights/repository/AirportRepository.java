package com.voatads.flights.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voatads.flights.model.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, UUID> {
    
}
