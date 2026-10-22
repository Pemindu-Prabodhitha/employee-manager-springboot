package com.example.employee_manager.repository;

import com.example.employee_manager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // Add this import
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    boolean existsByEmail(String email);
    
    // Add this line:
    List<Employee> findByFullNameContainingIgnoreCase(String keyword);
}