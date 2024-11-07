package com.voatads.customer.dto;

import lombok.Data;

@Data
public class CustomerDTO {
        private String cpf;
        private String name;
        private String email;
        private String street;
        private String number;
        private String complement;
        private String zipCode;
        private String city;
        private String state;
        private Double miles;
}

