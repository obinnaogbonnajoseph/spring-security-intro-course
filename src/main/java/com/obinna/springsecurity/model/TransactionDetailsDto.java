package com.obinna.springsecurity.model;

import com.obinna.springsecurity.entity.Transaction;

public record TransactionDetailsDto(String id, String symbol, String type, String quantity, String price) {

    public TransactionDetailsDto(Transaction transaction) {
        this(transaction.getId(), transaction.getCryptoCurrency().getSymbol(), transaction.getType().toString(),
                transaction.getQuantity().toString(), transaction.getPrice().toString());
    }

}