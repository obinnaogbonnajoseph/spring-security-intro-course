package com.obinna.springsecurity.model;

import java.util.List;

public record ListTransactionsDto(String username, List<TransactionDetailsDto> transactions) {

}