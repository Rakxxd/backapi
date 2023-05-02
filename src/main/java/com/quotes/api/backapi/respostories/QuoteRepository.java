package com.quotes.api.backapi.respostories;

import com.quotes.api.backapi.entities.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends MongoRepository<Quote,String> {
}
