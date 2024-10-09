package com.voatads.customer.dto;

import com.voatads.customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDTO {
        private Long id;

        @NotBlank
        private String name;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        private String cpf;

        private double miles;

        private AddressDTO address;
}