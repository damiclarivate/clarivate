package com.clarivate.model;

import lombok.Data;


@Data
public class ForexTrade extends Trade {

    /**
     * Calculates commission based on rules for an FX trade.
     *
     * @return The commission for this trade.
     */
    @Override
    public double calculateCommission() {
        double com = 0;
        double amount = this.getTradeAmount();

        if (transactionType.equals(TransactionType.BUY)) {
            com = (amount * 0.01) / 100;
        } else if (transactionType.equals(TransactionType.SELL)) {
            if (amount > 1000000.00) {
                com = 1000;
            } else if (amount > 10000.00) {
                com = 100;
            }
        }

        return com;
    }
}
