package com.voatads.employee.controller;

import com.voatads.employee.dto.EmployeeDTO;
import com.voatads.employee.model.Employee;
import com.voatads.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        try{
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable UUID id){
        try{
            Employee employee = employeeService.getEmployee(id);
            if (employee != null){
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {
        try {
            Employee employee = employeeService.getEmployeeByEmail(email);
            if (employee != null) {
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error("Erro ao obter funcionário com email {}: ", email, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        logger.debug("Iniciando método createEmployee");

        try{
            logger.debug("Recebido EmployeeDTO: {}", employeeDTO);

            if (employeeDTO == null) {
                logger.error("EmployeeDTO é nulo.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            logger.debug("Chamando employeeService.createEmployee");
            Employee createdEmployee = employeeService.createEmployee(employeeDTO);

            logger.debug("Funcionário criado com sucesso: {}", createdEmployee);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
        } catch (Exception e){
            logger.error("Erro ao criar funcionário: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> saveEmployee(@PathVariable UUID id, @RequestBody EmployeeDTO updatedEmployeeDTO){
        try{
            Employee updatedEmployee = employeeService.updateEmployee(id, updatedEmployeeDTO);
            if (updatedEmployee != null) {
                return ResponseEntity.ok(updatedEmployee);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeFields(@PathVariable UUID id, @RequestBody EmployeeDTO updates) {
        try {
            Employee updatedEmployee = employeeService.updateEmployeeFields(id, updates);
            if (updatedEmployee != null) {
                return ResponseEntity.ok(updatedEmployee);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeEmployee(@PathVariable UUID id){
        try{
            String message = employeeService.removeEmployee(id);
            return ResponseEntity.ok(message);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

