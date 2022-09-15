package com.obinna.springsecurity.service;

import java.util.List;

import com.obinna.springsecurity.entity.CryptoCurrency;
import com.obinna.springsecurity.entity.Portfolio;
import com.obinna.springsecurity.model.AddTransactionToPortfolioDto;
import com.obinna.springsecurity.model.ListTransactionsDto;
import com.obinna.springsecurity.model.PortfolioPositionsDto;

public interface PortfolioService {

    List<CryptoCurrency> getSupportedCryptoCurrencies();

    Portfolio getPortfolioForUsername(String username);

    PortfolioPositionsDto getPortfolioPositions(String username);

    void addTransactionToPortfolio(AddTransactionToPortfolioDto request);

    ListTransactionsDto getPortfolioTransactions(String username);

    void removeTransactionFromPortfolio(String username, String transactionId);

}