package com.obinna.springsecurity.service;

import com.obinna.springsecurity.model.AddTransactionToPortfolioDto;

public interface PortfolioCommandService {

    void addTransactionToPortfolio(AddTransactionToPortfolioDto request);

    void removeTransactionFromPortfolio(String transactionId);

    void createNewPortfolio(String username);

    boolean userHasAportfolio(String username);
}