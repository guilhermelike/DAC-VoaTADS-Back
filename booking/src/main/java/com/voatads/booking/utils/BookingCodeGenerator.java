package com.voatads.booking.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class BookingCodeGenerator {
    
    public String generateCodBooking() {
        String upperCasePart = RandomStringUtils.randomAlphabetic(3).toUpperCase();
        String numericPart = RandomStringUtils.randomNumeric(3);
        return upperCasePart + numericPart;
    }
}
