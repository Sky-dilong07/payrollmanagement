package com.example.payrollmanagement.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalSalary;
    private LocalDate payDate;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Payroll() {}

    public Payroll(double totalSalary, LocalDate payDate, Employee employee) {
        this.totalSalary = totalSalary;
        this.payDate = payDate;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "id=" + id +
                ", totalSalary=" + totalSalary +
                ", payDate=" + payDate +
                ", employee=" + employee +
                '}';
    }
}
