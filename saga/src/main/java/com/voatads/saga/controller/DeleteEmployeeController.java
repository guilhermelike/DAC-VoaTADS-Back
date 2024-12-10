package com.voatads.saga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.voatads.saga.dto.DeleteEmployeeDTO;
import com.voatads.saga.producer.DeleteEmployeeProducer;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class DeleteEmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(DeleteEmployeeController.class);

    @Autowired
    private DeleteEmployeeProducer deleteEmployeeProducer;

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable UUID id) {
        try {
            DeleteEmployeeDTO deleteEmployeeDTO = new DeleteEmployeeDTO();
            deleteEmployeeDTO.setId(id);
            deleteEmployeeDTO.setStatus("Desativado");
            deleteEmployeeProducer.deleteEmployee(deleteEmployeeDTO);
            return new ResponseEntity<>("Employee deletion initiated successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error initiating employee deletion", e);
            return new ResponseEntity<>("Error initiating employee deletion", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}