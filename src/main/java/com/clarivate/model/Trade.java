package com.clarivate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    int quantity;
    TransactionType transactionType;
    double price;

    protected double getTradeAmount() {
        return quantity * price;
    }

    public double calculateCommission() {
        return 0;
    }
}


