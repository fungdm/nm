package com.dava.maintaince.controller;

import com.dava.maintaince.model.Employee;
import com.dava.maintaince.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*") // Enable Cross-Origin Requests
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Add a new employee
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.insertEmployee(employee);
    }

    // Remove an employee by ID
    @DeleteMapping("/remove/{id}")
    public String removeEmployee(@PathVariable String id) {
        employeeService.removeEmployee(id);
        return "Employee removed with ID: " + id;
    }

    // Get all employees
    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
}
