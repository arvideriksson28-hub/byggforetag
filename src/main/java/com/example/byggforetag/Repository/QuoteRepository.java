package com.example.byggforetag.Repository;

import com.example.byggforetag.Model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Optional<Quote> findQuoteByJobId(Long jobId);
}
