package com.voatads.employee.repository;

import com.voatads.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
        Employee findByEmail(String email);
}
