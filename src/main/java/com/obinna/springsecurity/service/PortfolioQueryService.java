package com.obinna.springsecurity.service;

import java.util.List;

import com.obinna.springsecurity.model.ListTransactionsDto;
import com.obinna.springsecurity.model.PortfolioPositionsDto;

public interface PortfolioQueryService {

    PortfolioPositionsDto getPortfolioPositions();

    PortfolioPositionsDto getPortfolioPositions(String id);

    ListTransactionsDto getPortfolioTransactions();

    List<String> getPortfolioIds();

    PortfolioPositionsDto getPortfolioPositionsForUser(String username);

}