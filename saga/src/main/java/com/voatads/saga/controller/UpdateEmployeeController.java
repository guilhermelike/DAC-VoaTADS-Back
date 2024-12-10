package com.voatads.saga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.voatads.saga.dto.UpdateEmployeeDTO;
import com.voatads.saga.producer.UpdateEmployeeProducer;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class UpdateEmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(UpdateEmployeeController.class);

    @Autowired
    private UpdateEmployeeProducer updateEmployeeProducer;

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable UUID id, @RequestBody UpdateEmployeeDTO updateEmployeeDTO) {
        try {
            updateEmployeeDTO.setId(id);
            updateEmployeeProducer.updateEmployee(updateEmployeeDTO);
            logger.info("Requisição de atualização de funcionário enviada: {}", updateEmployeeDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Requisição de atualização de funcionário enviada com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao enviar requisição de atualização de funcionário: {}", updateEmployeeDTO, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar requisição de atualização de funcionário.");
        }
    }
}