package com.example.payrollmanagement.service.impl;

import com.example.payrollmanagement.Dto.PayrollDTO;
import com.example.payrollmanagement.model.Employee;
import com.example.payrollmanagement.model.Payroll;
import com.example.payrollmanagement.repository.EmployeeRepository;
import com.example.payrollmanagement.repository.PayrollRepository;
import com.example.payrollmanagement.service.PayrollService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;

    public PayrollServiceImpl(PayrollRepository payrollRepository, EmployeeRepository employeeRepository) {
        this.payrollRepository = payrollRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public PayrollDTO generatePayroll(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));

        double grossSalary = employee.getSalary();
        double tax = grossSalary * 0.1;
        double netSalary = grossSalary - tax;

        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setTotalSalary(netSalary);
        payroll.setPayDate(LocalDate.now());

        Payroll saved = payrollRepository.save(payroll);

        return new PayrollDTO(
                saved.getId(),
                employee.getId(),
                employee.getName(),
                saved.getTotalSalary(),
                saved.getPayDate()
        );
    }

    @Override
    public List<PayrollDTO> getAllPayrolls() {
        return payrollRepository.findAll()
                .stream()
                .map(pay -> new PayrollDTO(
                        pay.getId(),
                        pay.getEmployee().getId(),
                        pay.getEmployee().getName(),
                        pay.getTotalSalary(),
                        pay.getPayDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public PayrollDTO getPayrollById(Long id) {
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found with ID: " + id));

        return new PayrollDTO(
                payroll.getId(),
                payroll.getEmployee().getId(),
                payroll.getEmployee().getName(),
                payroll.getTotalSalary(),
                payroll.getPayDate()
        );
    }
}
