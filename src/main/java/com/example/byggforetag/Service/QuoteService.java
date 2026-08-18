package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.QuoteDto;
import com.example.byggforetag.Enums.QuoteStatus;
import com.example.byggforetag.Exception.JobNotFoundException;
import com.example.byggforetag.Exception.QuoteNotFoundException;
import com.example.byggforetag.Model.Job;
import com.example.byggforetag.Model.Quote;
import com.example.byggforetag.Repository.JobRepository;
import com.example.byggforetag.Repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final JobRepository jobRepository;

    public QuoteService(QuoteRepository quoteRepository, JobRepository jobRepository) {
        this.quoteRepository = quoteRepository;
        this.jobRepository = jobRepository;
    }

    public QuoteDto getQuoteByJobId(Long jobId){
        return QuoteDto.fromEntity(quoteRepository.findQuoteByJobId(jobId)
                .orElseThrow(()-> new QuoteNotFoundException(jobId)));
    }

    public QuoteDto updateQuoteStatus(Long id, QuoteStatus status){
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(()-> new QuoteNotFoundException(id));
        quote.setQuoteStatus(status);
        return QuoteDto.fromEntity(quoteRepository.save(quote));
    }

    public QuoteDto createQuote(Long jobId, QuoteDto quoteDto){
        Job job = jobRepository.findById(jobId)
                .orElseThrow(()-> new JobNotFoundException(jobId));
        Quote quote = quoteRepository.save(quoteDto.toEntity(job));
        return QuoteDto.fromEntity(quote);
    }

    public List<QuoteDto> getAllQuotes(){
        return quoteRepository.findAll().stream()
                .map(QuoteDto::fromEntity)
                .toList();
    }

    public QuoteDto updateQuote(Long id, QuoteDto quoteDto){
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(()-> new QuoteNotFoundException(id));
        if (quoteDto.getQuoteStatus() != null){
            quote.setQuoteStatus(quoteDto.getQuoteStatus());
        }
        if (quoteDto.getRotDeduction() != null){
            quote.setRotDeduction(quoteDto.getRotDeduction());
        }
        if (quoteDto.getTotalPrice() != null){
            quote.setTotalPrice(quoteDto.getTotalPrice());
        }
        if (quoteDto.getTravelFee() != null){
            quote.setTravelFee(quoteDto.getTravelFee());
        }
        return QuoteDto.fromEntity(quoteRepository.save(quote));
    }

    public void deleteQuote(Long id){
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(()-> new QuoteNotFoundException(id));
        quoteRepository.delete(quote);
    }
}
