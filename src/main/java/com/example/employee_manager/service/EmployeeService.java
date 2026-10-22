package com.example.employee_manager.service;

import com.example.employee_manager.model.Employee;
import com.example.employee_manager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> findAll() {
        return repo.findAll();
    }

    // --- ADD THIS NEW METHOD ---
    public List<Employee> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repo.findAll(); // Return all if search is empty
        }
        return repo.findByFullNameContainingIgnoreCase(keyword);
    }
    // ---------------------------

    public Employee findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
    }

    public boolean emailExists(String email, Long excludeId) {
        return repo.findByEmail(email)
                .filter(e -> !e.getId().equals(excludeId))
                .isPresent();
    }

    public void save(Employee employee) {
        repo.save(employee);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}