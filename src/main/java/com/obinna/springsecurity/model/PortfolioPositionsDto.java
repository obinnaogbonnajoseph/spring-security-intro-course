package com.obinna.springsecurity.model;

import java.util.List;
import java.util.Map;

public record PortfolioPositionsDto(String firstName, String lastname, List<PositionDto> positions,
        Map<String, String> cryptocurrencies) {

    public PositionDto getPositionForCryPositionDto(CryptoCurrencyDto crypto) {
        PositionDto position = null;
        for (PositionDto pos : positions) {
            if (pos.cryptoCurrency().equals(crypto)) {
                position = pos;
                break;
            }
        }
        return position;
    }

}