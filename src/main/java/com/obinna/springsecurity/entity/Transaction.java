package com.obinna.springsecurity.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Transaction {
    @Id
    private String id;
    @DBRef
    private final CryptoCurrency cryptoCurrency;
    private final Type type;
    private final BigDecimal quantity;
    private final BigDecimal price;
    private final long timestamp;

    public Transaction(CryptoCurrency cryptoCurrency, Type type, BigDecimal quantity, BigDecimal price,
            long timestamp) {
        this.cryptoCurrency = cryptoCurrency;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getId() {
        return this.id;
    }

    public CryptoCurrency getCryptoCurrency() {
        return this.cryptoCurrency;
    }

    public Type getType() {
        return this.type;
    }

    public BigDecimal getQuantity() {
        return this.quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaction other = (Transaction) obj;
        if (cryptoCurrency == null) {
            if (other.cryptoCurrency != null)
                return false;
        } else if (!cryptoCurrency.equals(other.cryptoCurrency))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (timestamp != other.timestamp)
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cryptoCurrency == null) ? 0 : cryptoCurrency.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", cryptoCurrency='" + getCryptoCurrency() + "'" +
                ", type='" + getType() + "'" +
                ", quantity='" + getQuantity() + "'" +
                ", price='" + getPrice() + "'" +
                ", timestamp='" + getTimestamp() + "'" +
                "}";
    }

}