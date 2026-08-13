package com.example.byggforetag.Repository;

import com.example.byggforetag.Model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    Optional<List<LeaveRequest>> findByEmployeeId(Long employeeId);
}
