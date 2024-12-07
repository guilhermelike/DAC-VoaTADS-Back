package com.voatads.saga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.voatads.saga.dto.CreateEmployeeDTO;
import com.voatads.saga.producer.CreateEmployeeProducer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class CreateEmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(CreateEmployeeDTO.class);

    @Autowired
    CreateEmployeeProducer createEmployeeProducer;

    @PostMapping("/employees/create")
    public ResponseEntity<CreateEmployeeDTO> createEmployee(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        try {
            System.out.println("Esta em controller: ");
            createEmployeeProducer.createEmployee(createEmployeeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createEmployeeDTO);
        } catch (Exception e) {
            logger.error("Erro ao criar funcionário: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}