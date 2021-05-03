package com.arsatapathy.springboot.demo.controllers;

import com.arsatapathy.springboot.demo.exceptions.DuplicateEmployeePresentException;
import com.arsatapathy.springboot.demo.exceptions.EmployeeNotFoundException;
import com.arsatapathy.springboot.demo.exceptions.NoEmployeeFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionController {
    @ExceptionHandler(NoEmployeeFoundException.class)
    public ResponseEntity<Object> noEmployeeFound(NoEmployeeFoundException employee) {
        return new ResponseEntity<>("No employee found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFound(EmployeeNotFoundException employee) {
        return new ResponseEntity<>("Employee id " + employee.getId() + " not found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEmployeePresentException.class)
    public ResponseEntity<Object> duplicateEmployee(DuplicateEmployeePresentException employee) {
        return new ResponseEntity<>("Duplicate employee id " + employee.getId() + " present!", HttpStatus.CONFLICT);
    }
}
