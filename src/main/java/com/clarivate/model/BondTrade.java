package com.clarivate.model;

import lombok.Data;


@Data
public class BondTrade extends Trade {

    /**
     * Calculates commission based on rules for a BON trade.
     *
     * @return The commission for this trade.
     */
    @Override
    public double calculateCommission() {

        double com = 0;
        double amount = this.getTradeAmount();

        if (transactionType.equals(TransactionType.BUY)) {
            com = (amount * 0.02) / 100;
        } else if (transactionType.equals(TransactionType.SELL)) {
            com = (amount * 0.01) / 100;
        }

        return com;
    }
}
