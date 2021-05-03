package com.arsatapathy.springboot.demo.exceptions;

public class DuplicateEmployeePresentException extends RuntimeException {

    private long id;

    public DuplicateEmployeePresentException(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
