package com.obinna.springsecurity.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CryptoCurrency {
    @Id
    private String id;
    @Indexed(unique = true)
    private final String symbol;
    private final String name;

    public CryptoCurrency(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", symbol='" + getSymbol() + "'" +
                ", name='" + getName() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CryptoCurrency other = (CryptoCurrency) obj;
        if (symbol == null) {
            if (other.symbol != null)
                return false;
        } else if (!symbol.equals(other.symbol))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
        return result;
    }

}