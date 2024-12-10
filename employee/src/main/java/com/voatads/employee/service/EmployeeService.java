package com.voatads.employee.service;

import com.voatads.employee.dto.CreateEmployeeDTO;
import com.voatads.employee.dto.EmployeeDTO;
import com.voatads.employee.dto.UpdateEmployeeDTO;
import com.voatads.employee.model.Employee;
import com.voatads.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Employee getEmployee(UUID id){
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    @Transactional
    public Employee createEmployee(CreateEmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employeeRepository.save(employee);
        return employeeRepository.findById(employee.getId()).orElse(null);
    }
    public Employee saveEmployee(Employee employee){
        employeeRepository.save(employee);
        return employeeRepository.findById(employee.getId()).orElse(null);
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Transactional
    public Employee updateEmployee(UUID id, UpdateEmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(employeeDTO.getName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setNumber(employeeDTO.getNumber());
            employeeRepository.save(employee);
            return employee;
        }
        return null;
    }

    @Transactional
    public Employee updateEmployeeFields(UUID id, EmployeeDTO updates) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            if (updates.getName() != null) {
                employee.setName(updates.getName());
            }
            if (updates.getEmail() != null) {
                employee.setEmail(updates.getEmail());
            }
            if (updates.getNumber() != null) {
                employee.setNumber(updates.getNumber());
            }
            employeeRepository.save(employee);
            return employee;
        }
        return null;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Transactional
    public String updateEmployeeStatus(UUID id, String status) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setStatus(status);
            employeeRepository.save(employee);
            return "Employee status updated successfully";
        } else {
            return "Employee not found";
        }
    }
}
