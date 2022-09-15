package com.obinna.springsecurity.service;

import static com.obinna.springsecurity.util.AuthenticationUtil.getUsername;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import com.obinna.springsecurity.entity.CryptoCurrency;
import com.obinna.springsecurity.entity.Portfolio;
import com.obinna.springsecurity.entity.Transaction;
import com.obinna.springsecurity.entity.Type;
import com.obinna.springsecurity.model.AddTransactionToPortfolioDto;
import com.obinna.springsecurity.repository.CryptoCurrencyRepository;
import com.obinna.springsecurity.repository.PortfolioRepository;

@Service
public class PortfolioCommandServiceNoSql implements PortfolioCommandService {

    private final PortfolioRepository portfolioRepository;
    private final CryptoCurrencyRepository currencyRepository;

    public PortfolioCommandServiceNoSql(PortfolioRepository portfolioRepository,
            CryptoCurrencyRepository currencyRepository) {
        this.portfolioRepository = portfolioRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void addTransactionToPortfolio(AddTransactionToPortfolioDto request) {
        Portfolio portfolio = portfolioRepository.findByUsername(getUsername());
        Transaction transaction = createTransactionEntity(request);
        portfolio.addTransaction(transaction);
        portfolioRepository.save(portfolio);
    }

    @PreFilter("hasRole('ADMIN') or filterObject.username === authentication.username")
    public void addTransactionToPortfolio(List<AddTransactionToPortfolioDto> transactionsDto) {
        Portfolio portfolio = portfolioRepository.findByUsername(getUsername());
        for (AddTransactionToPortfolioDto transactionDto : transactionsDto) {
            Transaction transaction = createTransactionEntity(transactionDto);
            portfolio.addTransaction(transaction);
        }
        portfolioRepository.save(portfolio);
    }

    @Override
    public void removeTransactionFromPortfolio(String transactionId) {
        Portfolio portfolio = portfolioRepository.findByUsername(getUsername());
        Transaction transaction = portfolio.getTransactionById(transactionId);
        portfolio.deleteTransaction(transaction);
        portfolioRepository.save(portfolio);
    }

    @Override
    public void createNewPortfolio(String username) {
        portfolioRepository.save(new Portfolio(username, new ArrayList<>()));
    }

    @Override
    public boolean userHasAportfolio(String username) {
        return portfolioRepository.existsByUsername(username);
    }

    private Transaction createTransactionEntity(AddTransactionToPortfolioDto request) {
        CryptoCurrency cryptoCurrency = currencyRepository.findBySymbol(request.getCryptoSymbol());
        Type type = Type.valueOf(request.getType());
        BigDecimal quantity = new BigDecimal(request.getQuantity());
        BigDecimal price = new BigDecimal(request.getPrice());
        return new Transaction(cryptoCurrency, type, quantity, price, System.currentTimeMillis());
    }

}