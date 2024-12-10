package com.voatads.employee.service;

import com.voatads.employee.dto.CreateEmployeeDTO;
import com.voatads.employee.dto.EmployeeDTO;
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
    public Employee updateEmployee(UUID id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = getEmployee(id);
        if (existingEmployee != null) {
            existingEmployee.setName(employeeDTO.getName());
            existingEmployee.setCpf(employeeDTO.getCpf());
            existingEmployee.setEmail(employeeDTO.getEmail());
            existingEmployee.setNumber(employeeDTO.getNumber());

            return saveEmployee(existingEmployee);
        }
        return null;
    }

    @Transactional
    public Employee updateEmployeeFields(UUID id, EmployeeDTO updates) {
        Employee existingEmployee = getEmployee(id);
        if (existingEmployee != null) {
            if (updates.getName() != null) existingEmployee.setName(updates.getName());
            if (updates.getEmail() != null) existingEmployee.setEmail(updates.getEmail());
            if (updates.getCpf() != null) existingEmployee.setCpf(updates.getCpf());
            if (updates.getNumber() != null) existingEmployee.setNumber(updates.getNumber());
            return saveEmployee(existingEmployee);
        }
        return null;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public String removeEmployee(UUID id){
        employeeRepository.deleteById(id);
        return "Employee removed with the id: " + id;
    }
}
