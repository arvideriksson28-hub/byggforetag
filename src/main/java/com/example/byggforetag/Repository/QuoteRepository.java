package com.example.byggforetag.Repository;

import com.example.byggforetag.Model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Optional<Quote> findQuoteByJobId(Long jobId);
}
