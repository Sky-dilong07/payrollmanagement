package com.example.payrollmanagement.repository;

import com.example.payrollmanagement.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepository extends JpaRepository<Payroll,Long> {

}
