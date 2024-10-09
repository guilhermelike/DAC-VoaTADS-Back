package com.voatads.customer.dto;

import lombok.Data;

@Data
public class UpdateCustomerDTO {
    private String name;
    private String email;
    private String cpf;
    private Double miles;
    private AddressDTO address;
}