package com.voatads.customer.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String street;
    private String complement;
    private String zip;
    private String city;
    private String state;
}