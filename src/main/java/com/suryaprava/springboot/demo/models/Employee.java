package com.suryaprava.springboot.demo.models;

public class Employee {

    private long employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;

    public Employee() {
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public Employee(long employeeId, String firstName, String middleName, String lastName, String phoneNumber) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
