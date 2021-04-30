package com.suryaprava.springboot.demo.controllers;

import com.suryaprava.springboot.demo.exceptions.DuplicateEmployeePresentException;
import com.suryaprava.springboot.demo.exceptions.EmployeeNotFoundException;
import com.suryaprava.springboot.demo.exceptions.NoEmployeeFoundException;
import com.suryaprava.springboot.demo.models.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    private Map<Long, Employee> employeeCache = new HashMap<Long, Employee>();

    {
        employeeCache.put(Long.valueOf(123), new Employee(123, "Ashish", "Ranjan", "Satapathy", "123456789"));
        employeeCache.put(Long.valueOf(456), new Employee(456, "Snigdha", "Rani", "Sahoo", "123456789"));
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<Map<Long, Employee>> get() {
        if (employeeCache.isEmpty()) {
            throw new NoEmployeeFoundException();
        } else {
            return new ResponseEntity<Map<Long, Employee>>(employeeCache, HttpStatus.FOUND);
        }
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> get(@PathVariable int id) {
        if (employeeCache.containsKey(Long.valueOf(id))) {
            return new ResponseEntity<Employee>( employeeCache.get(Long.valueOf(id)), HttpStatus.FOUND);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<Employee> get(@RequestParam("id") long id) {
        if (employeeCache.containsKey(id)) {
             return new ResponseEntity<Employee>(employeeCache.get(id), HttpStatus.FOUND);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    @RequestMapping(value = "employee/add", method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        if (employeeCache.containsKey(employee.getEmployeeId())) {
            throw new DuplicateEmployeePresentException(employee.getEmployeeId());
        } else {
            employeeCache.put(employee.getEmployeeId(), employee);
            return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.PUT)
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        if (employeeCache.containsKey(employee.getEmployeeId())) {
            return new ResponseEntity<Employee>(employeeCache.put(employee.getEmployeeId(), employee), HttpStatus.OK);
        } else {
            throw new EmployeeNotFoundException(employee.getEmployeeId());
        }
    }

    @RequestMapping(value = "employee/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> delete(@RequestParam("id") long id) {
        if (employeeCache.containsKey(id)) {
            return new ResponseEntity<Employee>(employeeCache.remove(id), HttpStatus.OK);

        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

}
