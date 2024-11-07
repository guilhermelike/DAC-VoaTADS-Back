package com.voatads.booking.handler.query.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.booking.handler.query.model.BookingQuery;
import com.voatads.booking.handler.query.repository.BookingQueryRepository;

@Service
public class BookingQueryService {
    
    @Autowired
    BookingQueryRepository bookingRepository;

    public List<BookingQuery> getAllBookings() {
        return bookingRepository.findAll();
    }

}
