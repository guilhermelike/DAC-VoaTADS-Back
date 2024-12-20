package com.voatads.booking.handler.query.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voatads.booking.handler.query.model.BookingQuery;
import java.util.List;

@Repository
public interface BookingQueryRepository extends JpaRepository<BookingQuery, UUID> {
    
    List<BookingQuery> findByIdUser(UUID idUser);
}