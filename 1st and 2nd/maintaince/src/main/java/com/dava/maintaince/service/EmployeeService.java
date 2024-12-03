package com.dava.maintaince.service;

import com.dava.maintaince.model.Employee;
import com.dava.maintaince.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Insert a new employee
    public Employee insertEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Remove employee by ID
    public void removeEmployee(String id) {
        employeeRepository.deleteById(id);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
