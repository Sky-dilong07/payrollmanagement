package com.example.payrollmanagement.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private boolean present;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Attendance() {}

    public Attendance(LocalDate date, boolean present, Employee employee) {
        this.date = date;
        this.present = present;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", date=" + date +
                ", present=" + present +
                ", employee=" + employee +
                '}';
    }
}
