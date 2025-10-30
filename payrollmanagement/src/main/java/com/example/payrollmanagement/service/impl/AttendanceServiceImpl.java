package com.example.payrollmanagement.service.impl;

import com.example.payrollmanagement.Dto.AttendanceDTO;
import com.example.payrollmanagement.exception.ResourceNotFoundException;
import com.example.payrollmanagement.model.Attendance;
import com.example.payrollmanagement.model.Employee;
import com.example.payrollmanagement.repository.AttendanceRepository;
import com.example.payrollmanagement.repository.EmployeeRepository;
import com.example.payrollmanagement.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public AttendanceDTO markAttendance(Long employeeId, AttendanceDTO attendanceDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setDate(attendanceDTO.getDate());
        attendance.setPresent("Present".equalsIgnoreCase(attendanceDTO.getStatus()));

        Attendance saved = attendanceRepository.save(attendance);
        return mapToDTO(saved);
    }

    @Override
    public List<AttendanceDTO> getAttendanceByEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        return employee.getAttendances()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ------------------ Mapping ------------------

    private AttendanceDTO mapToDTO(Attendance attendance) {
        return new AttendanceDTO(
                attendance.getId(),
                attendance.getEmployee().getId(),
                attendance.getEmployee().getName(),
                attendance.getDate(),
                attendance.isPresent() ? "Present" : "Absent"
        );
    }
}
