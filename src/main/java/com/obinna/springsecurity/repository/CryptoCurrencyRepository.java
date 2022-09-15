package com.obinna.springsecurity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.obinna.springsecurity.entity.CryptoCurrency;

public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, String> {
    CryptoCurrency findBySymbol(String symbol);
}