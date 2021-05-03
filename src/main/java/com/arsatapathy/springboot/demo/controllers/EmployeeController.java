package com.arsatapathy.springboot.demo.controllers;

import com.arsatapathy.springboot.demo.exceptions.DuplicateEmployeePresentException;
import com.arsatapathy.springboot.demo.exceptions.EmployeeNotFoundException;
import com.arsatapathy.springboot.demo.exceptions.NoEmployeeFoundException;
import com.arsatapathy.springboot.demo.models.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    private final Map<Long, Employee> employeeCache = new HashMap<>();

    {
        employeeCache.put((long)123, new Employee(123, "Ashish", "Ranjan", "Satapathy", "123456789"));
        employeeCache.put((long)456, new Employee(456, "Snigdha", "Rani", "Sahoo", "123456789"));
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<Map<Long, Employee>> get() {
        if (employeeCache.isEmpty()) {
            throw new NoEmployeeFoundException();
        } else {
            return new ResponseEntity<>(employeeCache, HttpStatus.FOUND);
        }
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> get(@PathVariable int id) {
        if (employeeCache.containsKey((long)id)) {
            return new ResponseEntity<>( employeeCache.get((long)id), HttpStatus.FOUND);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<Employee> get(@RequestParam("id") long id) {
        if (employeeCache.containsKey(id)) {
             return new ResponseEntity<>(employeeCache.get(id), HttpStatus.FOUND);
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
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.PUT)
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        if (employeeCache.containsKey(employee.getEmployeeId())) {
            return new ResponseEntity<>(employeeCache.put(employee.getEmployeeId(), employee), HttpStatus.OK);
        } else {
            throw new EmployeeNotFoundException(employee.getEmployeeId());
        }
    }

    @RequestMapping(value = "employee/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> delete(@RequestParam("id") long id) {
        if (employeeCache.containsKey(id)) {
            return new ResponseEntity<>(employeeCache.remove(id), HttpStatus.OK);

        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

}
