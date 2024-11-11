package com.clarivate.model;

import com.clarivate.service.TradeBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BondTradeTest {

    @Test
    void testCalculateCommission() {
        var trade = TradeBuilder.builder()
                .quantity(10)
                .securityType(SecurityType.BON.toString())
                .transactionType(TransactionType.BUY.toString())
                .price(50.0)
                .build().build();
        var commission = trade.calculateCommission();
        assertEquals(0.1, commission);
    }
}