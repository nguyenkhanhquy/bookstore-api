package com.bookstore.api.dto;

import com.bookstore.api.entity.Employee;

import java.util.List;

public class EmployeeDTO {

    // define fields
    private boolean error;
    private String message;
    private List<Employee> result;

    // define constructors
    public EmployeeDTO() {

    }

    public EmployeeDTO(boolean error, String message, List<Employee> result) {
        this.error = error;
        this.message = message;
        this.result = result;
    }

    // define getters/setters
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Employee> getResult() {
        return result;
    }

    public void setResult(List<Employee> result) {
        this.result = result;
    }
}
