package com.quotes.api.backapi.controllers;


import com.quotes.api.backapi.entities.Quote;
import com.quotes.api.backapi.respostories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/quotes")
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping("/")
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable("id") String id) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        if (quote != null) {
            return ResponseEntity.ok(quote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
        Quote savedQuote = quoteRepository.save(quote);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable("id") String id, @RequestBody Quote quote) {
        Quote existingQuote = quoteRepository.findById(id).orElse(null);
        if (existingQuote != null) {
            existingQuote.setText(quote.getText());
            existingQuote.setAuthor(quote.getAuthor());
            Quote savedQuote = quoteRepository.save(existingQuote);
            return ResponseEntity.ok(savedQuote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable("id") String id) {
        Quote existingQuote = quoteRepository.findById(id).orElse(null);
        if (existingQuote != null) {
            quoteRepository.delete(existingQuote);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}