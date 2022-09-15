package com.obinna.springsecurity.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.obinna.springsecurity.entity.CryptoCurrency;
import com.obinna.springsecurity.model.CryptoCurrencyDto;
import com.obinna.springsecurity.repository.CryptoCurrencyRepository;

@Service
public class CurrencyQueryServiceNoSql implements CurrencyQueryService {

    private final CryptoCurrencyRepository cryptoRepository;
    private Map<String, CryptoCurrencyDto> cryptoCurrencies = null;

    public CurrencyQueryServiceNoSql(CryptoCurrencyRepository cryptorRepository) {
        this.cryptoRepository = cryptorRepository;
    }

    @Override
    public List<CryptoCurrencyDto> getSupportedCryptoCurrencies() {
        if (this.cryptoCurrencies == null) {
            this.cryptoCurrencies = new HashMap<>();
            for (CryptoCurrency currency : cryptoRepository.findAll()) {
                this.cryptoCurrencies.put(currency.getSymbol(),
                        new CryptoCurrencyDto(currency.getSymbol(), currency.getName()));
            }
        }
        return new ArrayList<>(cryptoCurrencies.values());
    }

    @Override
    public CryptoCurrencyDto getCryptoCurrency(String symbol) {
        return cryptoCurrencies.get(symbol);
    }

}