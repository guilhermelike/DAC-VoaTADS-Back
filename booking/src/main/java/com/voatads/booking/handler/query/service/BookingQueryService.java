package com.voatads.booking.handler.query.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voatads.booking.dto.BookingDTO;
import com.voatads.booking.handler.query.model.BookingQuery;
import com.voatads.booking.handler.query.repository.BookingQueryRepository;

@Service
public class BookingQueryService {

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    BookingQueryRepository bookingQueryRepository;

    public List<BookingQuery> getAllBookings() {
        return bookingQueryRepository.findAll();
    }

    public BookingQuery saveBooking(BookingDTO bookingDTO) {
        BookingQuery bookingQuery = modelMapper.map(bookingDTO, BookingQuery.class);
        BookingQuery bookingCreated = bookingQueryRepository.save(bookingQuery);
        return bookingCreated;
    }

}
