package com.obinna.springsecurity.service;

import java.math.BigDecimal;

public interface PricingService {

    BigDecimal getCurrentPriceForCrypto(String symbol);

}