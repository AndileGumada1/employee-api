package com.andile.employee.service;

import com.andile.employee.exception.UserNotFoundException;
import com.andile.employee.model.Employee;
import com.andile.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    /**
     * method to add an employee
     * @param employee
     * @return
     */
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * method used to list all the employees
     * @return List objects
     */
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * method used to update employee
     * @param employeeDetails represents the employee object
     * @return Employee object
     */
    public Employee updateEmployee(Employee employeeDetails,long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee not exist with id :" + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        return employeeRepository.save(employee);
    }

    /**
     * method used to find an Employee by id
     * @param id represents employeeId
     * @return Employee object
     */
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by " + id + "id not found"));
    }

    /**
     * method used to delete an employee
     * @param id
     */
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
