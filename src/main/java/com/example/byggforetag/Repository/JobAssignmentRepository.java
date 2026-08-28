package com.example.byggforetag.Repository;


import com.example.byggforetag.Model.JobAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobAssignmentRepository extends JpaRepository<JobAssignment, Long> {

    List<JobAssignment> findAllByJobId(Long jobId);
    List<JobAssignment> findAllByEmployeeId(Long employeeId);

    @Query("SELECT ja FROM JobAssignment ja JOIN ja.employee e JOIN e.user u WHERE u.email = :email")
    List<JobAssignment> findAllByEmployeeEmail(@Param("email") String email);
}
