package com.example.byggforetag.Repository;

import com.example.byggforetag.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByUserId(Long userId);

    @Query("SELECT j FROM Job j JOIN j.jobAssignments ja WHERE ja.employee.id = :employeeId")
    List<Job> findJobsByEmployeeId(@Param("employeeId") Long employeeId);

}
