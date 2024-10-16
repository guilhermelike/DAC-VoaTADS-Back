package com.voatads.flights.repository;

import com.voatads.flights.model.Voo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VooRepository extends JpaRepository<Voo, UUID> {
}
