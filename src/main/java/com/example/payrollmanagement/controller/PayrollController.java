package com.example.payrollmanagement.controller;

import com.example.payrollmanagement.Dto.PayrollDTO;
import com.example.payrollmanagement.service.PayrollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // 1️. Generate Payroll for a specific employee
    @PostMapping("/generate/{employeeId}")
    public ResponseEntity<PayrollDTO> generatePayroll(@PathVariable Long employeeId) {
        PayrollDTO payroll = payrollService.generatePayroll(employeeId);
        return new ResponseEntity<>(payroll, HttpStatus.CREATED);
    }

    // 2️. Get all Payroll records
    @GetMapping
    public ResponseEntity<List<PayrollDTO>> getAllPayrolls() {
        List<PayrollDTO> payrolls = payrollService.getAllPayrolls();
        return ResponseEntity.ok(payrolls);
    }

    // 3️. Get Payroll by ID
    @GetMapping("/{id}")
    public ResponseEntity<PayrollDTO> getPayrollById(@PathVariable Long id) {
        PayrollDTO payroll = payrollService.getPayrollById(id);
        return ResponseEntity.ok(payroll);
    }
}
