package com.obinna.springsecurity.service;

import java.util.List;

import com.obinna.springsecurity.model.CryptoCurrencyDto;

public interface CurrencyQueryService {

    List<CryptoCurrencyDto> getSupportedCryptoCurrencies();

    CryptoCurrencyDto getCryptoCurrency(String symbol);

}