package com.example.payrollmanagement.service;

import com.example.payrollmanagement.Dto.PayrollDTO;
import java.util.List;

public interface PayrollService {

    PayrollDTO generatePayroll(Long employeeId);

    List<PayrollDTO> getAllPayrolls();

    PayrollDTO getPayrollById(Long id);
}
