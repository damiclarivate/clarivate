package com.clarivate.model;

import lombok.Data;


@Data
public class StockTrade extends Trade {

    /**
     * Calculates commission based on rules for a STO trade.
     *
     * @return The commission for this trade.
     */
    @Override
    public double calculateCommission() {
        double amount = this.getTradeAmount();
        double com = (amount * 0.05) / 100;

        if (transactionType.equals(TransactionType.SELL)) {
            if (amount > 100000) {
                com += 500;
            }
        }

        return com;
    }
}
