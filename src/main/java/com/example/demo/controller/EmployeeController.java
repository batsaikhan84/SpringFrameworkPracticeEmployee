package com.example.demo.controller;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Value("$sprint.application.name")
    String appName;
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
    @GetMapping("/employees")
    public Page<Employee> getAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") long id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with given id: " + id));
        return ResponseEntity.ok().body(employee);
    }
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
}
