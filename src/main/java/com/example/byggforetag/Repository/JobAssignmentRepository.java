package com.example.byggforetag.Repository;


import com.example.byggforetag.Model.JobAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobAssignmentRepository extends JpaRepository<JobAssignment, Long> {

    List<JobAssignment> findAllByJobId(Long jobId);
    List<JobAssignment> findAllByEmployeeId(Long employeeId);
}
