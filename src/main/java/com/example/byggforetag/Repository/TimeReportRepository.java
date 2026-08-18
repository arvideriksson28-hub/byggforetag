package com.example.byggforetag.Repository;

import com.example.byggforetag.Model.TimeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeReportRepository extends JpaRepository<TimeReport, Long> {

    List<TimeReport> findAllByEmployeeId(Long employeeId);
    List<TimeReport> findAllByJobId(Long jobId);
}
