package com.example.payrollmanagement.Dto;

import java.time.LocalDate;

public class PayrollDTO {

    private Long id;
    private Long employeeId;
    private String employeeName;
    private double totalSalary;
    private LocalDate payDate;

    public PayrollDTO() {}

    public PayrollDTO(Long id, Long employeeId, String employeeName, double totalSalary, LocalDate payDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.totalSalary = totalSalary;
        this.payDate = payDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }
}
