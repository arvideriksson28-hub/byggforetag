package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.TimeReportDto;
import com.example.byggforetag.Service.TimeReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timereports")
public class TimeReportController {

    private final TimeReportService timeReportService;

    public TimeReportController(TimeReportService timeReportService) {
        this.timeReportService = timeReportService;
    }

    @PostMapping("/{employeeId}/{jobId}")
    public ResponseEntity<TimeReportDto> createTimeReport(@RequestBody TimeReportDto timeReportDto, @PathVariable Long employeeId, @PathVariable Long jobId){
        return ResponseEntity.status(HttpStatus.CREATED).body(timeReportService.createTimeReport(timeReportDto, employeeId, jobId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<TimeReportDto>> getAllTimeReports(@PathVariable Long employeeId){
        return ResponseEntity.ok(timeReportService.getTimeReports(employeeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeReport(@PathVariable Long id){
        timeReportService.deleteTimeReport(id);
        return ResponseEntity.noContent().build();
    }
}
