package com.example.byggforetag.Repository;

import com.example.byggforetag.Model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {
    List<Certification> findAllCertificationByEmployeeId(Long employeeId);
}
