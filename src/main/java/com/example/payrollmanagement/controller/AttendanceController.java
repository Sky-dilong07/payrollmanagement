package com.example.payrollmanagement.controller;


import com.example.payrollmanagement.Dto.AttendanceDTO;
import com.example.payrollmanagement.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    // 1. Attendance for a specific employee
    @PostMapping("/{employeeId}")
    public ResponseEntity<AttendanceDTO> markAttendance(
            @PathVariable Long employeeId,
            @RequestBody AttendanceDTO attendanceDTO
    ) {
        AttendanceDTO savedAttendance = attendanceService.markAttendance(employeeId, attendanceDTO);
        return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
    }

    // 2. Get all records
    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    // 3. Get attendance by ID
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByEmployee(@PathVariable Long employeeId) {
        List<AttendanceDTO> attendanceList = attendanceService.getAttendanceByEmployee(employeeId);
        return ResponseEntity.ok(attendanceList);
    }

}
