package com.voatads.customer.controller;

import com.voatads.customer.dto.CustomerDTO;
import com.voatads.customer.dto.UpdateCustomerDTO;
import com.voatads.customer.model.Customer;
import com.voatads.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem
public class CustomerController {
    @Autowired
    CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            logger.error("Erro ao obter todos os clientes: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable UUID id) {
        try {
            Customer customer = customerService.getCustomer(id);
            if (customer != null) {
                return ResponseEntity.ok(customer);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error("Erro ao obter cliente com ID {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customerDTO) {
        logger.debug("Iniciando método createCustomer");
        try {
            logger.debug("Recebido CustomerDTO: {}", customerDTO);

            if (customerDTO == null) {
                logger.error("CustomerDTO é nulo.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Customer createdCustomer = customerService.createCustomer(customerDTO);
            logger.debug("Cliente criado com sucesso: {}", createdCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer); // Retorna 201 Created
        } catch (Exception e) {
            logger.error("Erro ao criar cliente: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> saveCustomer(@PathVariable UUID id, @RequestBody CustomerDTO updatedCustomerDTO) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, updatedCustomerDTO);
            if (updatedCustomer != null) {
                return ResponseEntity.ok(updatedCustomer);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error("Erro ao atualizar cliente com ID {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomerFields(@PathVariable UUID id, @RequestBody UpdateCustomerDTO updates) {
        try {
            Customer updatedCustomer = customerService.updateCustomerFields(id, updates);
            if (updatedCustomer != null) {
                return ResponseEntity.ok(updatedCustomer);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error("Erro ao atualizar campos do cliente com ID {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCustomer(@PathVariable UUID id) {
        try {
            String message = customerService.removeCustomer(id);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            logger.error("Erro ao remover cliente com ID {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
