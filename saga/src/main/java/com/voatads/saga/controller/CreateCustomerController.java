package com.voatads.saga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.voatads.saga.dto.CreateCustomerDTO;
import com.voatads.saga.producer.CreateCustomerProducer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class CreateCustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CreateCustomerDTO.class);

    @Autowired
    CreateCustomerProducer createCustomerProducer;

    @PostMapping("/customers/create")
    public ResponseEntity<CreateCustomerDTO> createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        try {
            System.out.println("Esta em controller: ");
            createCustomerProducer.createCustomer(createCustomerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createCustomerDTO);
        } catch (Exception e) {
            logger.error("Erro ao criar cliente: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}