package com.voatads.booking.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voatads.booking.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    

}
