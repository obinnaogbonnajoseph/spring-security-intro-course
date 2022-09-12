package com.obinna.springsecurity.model;

public class AddTransactionToPortfolioDto {

    private String cryptoSymbol;
    private String quantity;
    private String price;
    private String type;
    private String username;

    public AddTransactionToPortfolioDto() {
    }

    public AddTransactionToPortfolioDto(String cryptoSymbol, String quantity, String price) {
        this.cryptoSymbol = cryptoSymbol;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCryptoSymbol() {
        return this.cryptoSymbol;
    }

    public void setCryptoSymbol(String cryptoSymbol) {
        this.cryptoSymbol = cryptoSymbol;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}