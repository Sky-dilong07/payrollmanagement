package com.example.payrollmanagement.service.impl;

import com.example.payrollmanagement.Dto.EmployeeDTO;
import com.example.payrollmanagement.exception.ResourceNotFoundException;
import com.example.payrollmanagement.model.Employee;
import com.example.payrollmanagement.repository.EmployeeRepository;
import com.example.payrollmanagement.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDto){
        Employee employee = mapToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return mapToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update. Employee not found with id: " + id));

        existing.setName(employeeDTO.getName());
        existing.setDepartment(employeeDTO.getDepartment());
        existing.setEmail(employeeDTO.getEmail());
        existing.setSalary(employeeDTO.getSalary());

        Employee updated = employeeRepository.save(existing);
        return mapToDTO(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot delete. Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    // Mapping (Entity <-> DTO)

    private Employee mapToEntity(EmployeeDTO dto) {
        Employee emp = new Employee();
        emp.setId(dto.getId());
        emp.setName(dto.getName());
        emp.setDepartment(dto.getDepartment());
        emp.setEmail(dto.getEmail());
        emp.setSalary(dto.getSalary());
        return emp;
    }

    private EmployeeDTO mapToDTO(Employee emp) {
        return new EmployeeDTO(emp.getId(), emp.getName(), emp.getDepartment(), emp.getEmail(), emp.getSalary());
    }

}
