package com.example.payrollmanagement.service;

import com.example.payrollmanagement.Dto.AttendanceDTO;
import java.util.List;

public interface AttendanceService {

    AttendanceDTO markAttendance(Long employeeId, AttendanceDTO attendanceDTO);
    List<AttendanceDTO> getAttendanceByEmployee(Long employeeId);
    List<AttendanceDTO> getAllAttendance();
}
