package com.voatads.customer.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressDTO {
    private UUID id;
    private String street;
    private String complement;
    private String zip;
    private String city;
    private String state;
}