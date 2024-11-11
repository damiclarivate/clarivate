package com.clarivate.service;

import com.clarivate.model.*;
import lombok.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Wrapper class to enable building a subclass of the Trade object using a builder pattern.
 */
@Builder
public class TradeBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    int quantity;
    String transactionType;
    String securityType;
    double price;

    /**
     * Builds the subclass of Trade
     *
     * @return The new subclassed Trade
     */
    public Trade build() {
        // Get subclass
        Trade trade = getNewTrade();

        // If illegal data, throw exception
        if (this.quantity < 0) {
            throw new IllegalArgumentException(String.format("Quantity '%d' is not allowed because it is less than 0 ", this.quantity));
        }
        if (this.price < 0.0) {
            throw new IllegalArgumentException(String.format("Price '%f' is not allowed because it is less than 0.0 ", this.price));
        }
        trade.setQuantity(this.quantity);
        trade.setPrice(this.price);

        trade.setTransactionType(TransactionType.valueOf(this.transactionType));

        LOGGER.info("Created {},transaction={}, quantity={}, price={}"
                , trade.toString(), trade.getTransactionType(), trade.getQuantity(), trade.getPrice());
        return trade;
    }


    /**
     * Factory method to create a Trade subclass using the value of the SecurityType enum.
     *
     * @return The subclass.
     */
    // TODO move this to a Factory class
    private Trade getNewTrade() {

        // TODO consider creating a map of SecurityType string values to corresponding subclassed Trade objects
        if (this.securityType.equals(SecurityType.STO.toString())) {
            return new StockTrade();
        } else if (this.securityType.equals(SecurityType.BON.toString())) {
            return new BondTrade();
        } else if (this.securityType.equals(SecurityType.FX.toString())) {
            return new ForexTrade();
        } else {
            throw new IllegalArgumentException(String.format("Security type '%s' is not a valid type.", this.securityType.toString()));
        }
    }
}
