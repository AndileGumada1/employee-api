package com.andile.employee.controller;

import com.andile.employee.model.Employee;
import com.andile.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * this end-point is used to get all employees
     * @return LIst of objects
     */
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.findAllEmployees();
    }

    /**
     * this end-point is used to create employee
     * @param employee
     * @return Employee object
     */
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        log.info("Adding a new employee object {}:",employee);
        return employeeService.addEmployee(employee);
    }

    /**
     * this end-point is used to get employee by id
     * @param id
     * @return ResponseEntity object
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        log.info("Getting a employee by id {}:",employee,id);
        return ResponseEntity.ok(employee);
    }

    /**
     * this end-point is used update employee
     * @param id
     * @param employeeDetails
     * @return
     */
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee employee = employeeService.findEmployeeById(id);

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeService.updateEmployee(employee,id);
        log.info("Updating an employee object {}:",employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * This end point is used to delete employee rest api
     * @param id
     * @return ResponseEntity object
     */
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        log.info("Deleting an employee object by id {}:",id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
