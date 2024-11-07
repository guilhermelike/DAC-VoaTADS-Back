package com.voatads.booking.handler.command.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voatads.booking.handler.command.model.BookingCommand;

@Repository
public interface BookingCommandRepository extends JpaRepository<BookingCommand, UUID>{

    
}