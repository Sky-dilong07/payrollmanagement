package com.example.payrollmanagement.service;

import com.example.payrollmanagement.Dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDto);
    EmployeeDTO getEmployeeById(Long id);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDto);
    void deleteEmployee(Long id);

}
