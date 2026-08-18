package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.QuoteDto;
import com.example.byggforetag.Enums.QuoteStatus;
import com.example.byggforetag.Service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<QuoteDto> getQuoteByJobId(@PathVariable Long jobId){
        return ResponseEntity.ok(quoteService.getQuoteByJobId(jobId));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<QuoteDto> updateQuoteStatus(@PathVariable Long id, @RequestBody QuoteStatus status){
        return ResponseEntity.ok(quoteService.updateQuoteStatus(id, status));
    }



}
