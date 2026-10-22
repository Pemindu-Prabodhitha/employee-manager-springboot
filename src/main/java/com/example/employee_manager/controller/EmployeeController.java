package com.example.employee_manager.controller;

import com.example.employee_manager.model.Employee;
import com.example.employee_manager.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // READ - show form + table
    @GetMapping
    public String list(Model model) {
        if (!model.containsAttribute("employee")) {
            model.addAttribute("employee", new Employee());
        }
        model.addAttribute("employees", service.findAll());
        return "employees";
    }

    // CREATE
    @PostMapping
    public String create(@Valid @ModelAttribute("employee") Employee employee,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirect) {
        if (service.emailExists(employee.getEmail(), null)) {
            result.rejectValue("email", "duplicate", "This email is already registered");
        }
        if (result.hasErrors()) {
            model.addAttribute("employees", service.findAll());
            return "employees";
        }
        service.save(employee);
        redirect.addFlashAttribute("success", "Employee saved successfully");
        return "redirect:/employees";
    }

    // EDIT - show edit form
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("employee", service.findById(id));
        return "edit-employee";
    }

    // UPDATE
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("employee") Employee employee,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirect) {
        if (service.emailExists(employee.getEmail(), id)) {
            result.rejectValue("email", "duplicate", "This email is already registered");
        }
        if (result.hasErrors()) {
            employee.setId(id);
            return "edit-employee";
        }
        employee.setId(id);
        service.save(employee);
        redirect.addFlashAttribute("success", "Employee updated successfully");
        return "redirect:/employees";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        service.deleteById(id);
        redirect.addFlashAttribute("success", "Employee deleted");
        return "redirect:/employees";
    }
}