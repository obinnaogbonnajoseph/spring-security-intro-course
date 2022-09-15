package com.obinna.springsecurity.service;

import static com.obinna.springsecurity.util.AuthenticationUtil.getUsername;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.obinna.springsecurity.entity.Portfolio;
import com.obinna.springsecurity.entity.Transaction;
import com.obinna.springsecurity.model.CryptoCurrencyDto;
import com.obinna.springsecurity.model.ListTransactionsDto;
import com.obinna.springsecurity.model.PortfolioPositionsDto;
import com.obinna.springsecurity.model.PositionDto;
import com.obinna.springsecurity.model.TransactionDetailsDto;
import com.obinna.springsecurity.repository.PortfolioRepository;

@Service
public class PortfolioQueryServiceNoSql implements PortfolioQueryService {

    private final CurrencyQueryService currencyService;
    private final PortfolioRepository portfolioRepository;
    private final PricingService pricingService;

    public PortfolioQueryServiceNoSql(CurrencyQueryService currencyService, PortfolioRepository portfolioRepository,
            PricingService pricingService) {
        this.currencyService = currencyService;
        this.portfolioRepository = portfolioRepository;
        this.pricingService = pricingService;
    }

    @Override
    public PortfolioPositionsDto getPortfolioPositions() {
        var cryptos = currencyService.getSupportedCryptoCurrencies();
        if (!portfolioRepository.existsByUsername(getUsername())) {
            portfolioRepository.save(new Portfolio(getUsername(), Collections.EMPTY_LIST));
        }
        var portfolio = portfolioRepository.findByUsername(getUsername());
        var positions = calculatePositions(cryptos, portfolio);
        var cryptoMap = convertCryptoListToMap(cryptos);
        return new PortfolioPositionsDto("", "", positions, cryptoMap);
    }

    @Override
    @PreAuthorize("hasPermission(#id, 'com.obinna.springsecurity.entity.Portfolio', 'READ')")
    public PortfolioPositionsDto getPortfolioPositions(String id) {
        var cryptos = currencyService.getSupportedCryptoCurrencies();
        var portfolio = portfolioRepository.findById(id).get();
        var positions = calculatePositions(cryptos, portfolio);
        var cryptoMap = convertCryptoListToMap(cryptos);
        return new PortfolioPositionsDto("", "", positions, cryptoMap);
    }

    @Override
    public ListTransactionsDto getPortfolioTransactions() {
        Portfolio portfolio = portfolioRepository.findByUsername(getUsername());
        return createListTransactionsResponse(getUsername(), portfolio.getTransactions());
    }

    @Override
    public List<String> getPortfolioIds() {
        var portfolios = portfolioRepository.findAll();
        return portfolios.stream()
                .map(Portfolio::getId)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("@isPortfolioOwnerOrAdmin.check(#username)")
    public PortfolioPositionsDto getPortfolioPositionsForUser(String username) {
        var cryptos = currencyService.getSupportedCryptoCurrencies();
        var portfolio = portfolioRepository.findByUsername(username);
        var positions = calculatePositions(cryptos, portfolio);
        var cryptoMap = convertCryptoListToMap(cryptos);
        return new PortfolioPositionsDto("", "", positions, cryptoMap);
    }

    private List<PositionDto> calculatePositions(List<CryptoCurrencyDto> cryptos, Portfolio portfolio) {
        List<PositionDto> positions = new ArrayList<>();
        for (CryptoCurrencyDto crypto : cryptos) {
            List<Transaction> cryptoTransactions = portfolio.getTransactionsForCoin(crypto.symbol());
            BigDecimal quantity = calculatePositionQuantity(cryptoTransactions);
            BigDecimal currentPrice = getCurrentPriceForCrypto(crypto);
            BigDecimal positionValue = calculatePositionValue(quantity, currentPrice);
            positions.add(new PositionDto(crypto, quantity, positionValue));
        }
        return positions;
    }

    private BigDecimal getCurrentPriceForCrypto(CryptoCurrencyDto crypto) {
        return pricingService.getCurrentPriceForCrypto(crypto.symbol());
    }

    private BigDecimal calculatePositionValue(BigDecimal quantity, BigDecimal currentPrice) {
        return currentPrice.multiply(quantity);
    }

    private Map<String, String> convertCryptoListToMap(List<CryptoCurrencyDto> cryptos) {
        Map<String, String> cryptoMap = new HashMap<>();
        for (CryptoCurrencyDto crypto : cryptos) {
            cryptoMap.put(crypto.symbol(), crypto.name());
        }
        return cryptoMap;
    }

    private BigDecimal calculatePositionQuantity(List<Transaction> cryptoTransactions) {
        var quantity = BigDecimal.ZERO;
        for (Transaction transaction : cryptoTransactions) {
            switch (transaction.getType()) {
                case BUY -> quantity = quantity.add(transaction.getQuantity());
                case SELL -> quantity = quantity.subtract(transaction.getQuantity());
                default -> quantity = quantity.add(BigDecimal.ZERO);
            }
        }
        return quantity;
    }

    private ListTransactionsDto createListTransactionsResponse(String username, List<Transaction> transactions) {
        List<TransactionDetailsDto> transactionDetails = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionDetails.add(new TransactionDetailsDto(transaction));
        }
        return new ListTransactionsDto(username, transactionDetails);
    }
}