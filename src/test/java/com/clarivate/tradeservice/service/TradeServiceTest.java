package com.clarivate.tradeservice.service;


import com.clarivate.model.TradeDTO;
import com.clarivate.service.TradeService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TradeServiceTest {

    TradeService tradeService = new TradeService();

    @Test
    void testStockBuyTrade() throws IllegalArgumentException {

        TradeDTO actual = new TradeDTO();
        actual.setTransactionType("BUY");
        actual.setSecurityType("STO");
        actual.setPrice(12);
        actual.setQuantity(1000);
        actual.setTradeTime("01/01/2019 10:00:00");

        var commission = tradeService.getTotalCommission(Arrays.asList(actual));
        assertEquals(6, commission);
    }

    @Test
    void testStockSellTrade() throws IllegalArgumentException {

        TradeDTO actual = new TradeDTO();
        actual.setTransactionType("SELL");
        actual.setSecurityType("STO");
        actual.setPrice(1200);
        actual.setQuantity(100000);
        actual.setTradeTime("01/01/2019 10:00:00");

        var commission = tradeService.getTotalCommission(Arrays.asList(actual));
        assertEquals(60500, commission);
    }

    @Test
    void testBondTrades() throws IllegalArgumentException {

        TradeDTO trade1 = new TradeDTO();
        trade1.setTransactionType("BUY");
        trade1.setSecurityType("BON");
        trade1.setPrice(12);
        trade1.setQuantity(1000);
        trade1.setTradeTime("01/05/2019 10:00:00");

        TradeDTO trade2 = new TradeDTO();
        trade2.setTransactionType("SELL");
        trade2.setSecurityType("BON");
        trade2.setPrice(100);
        trade2.setQuantity(1000);
        trade2.setTradeTime("01/08/2019 10:00:00");

        var commission = tradeService.getTotalCommission(Arrays.asList(trade1, trade2));

        assertEquals(12.4, commission);
    }

    @Test
    void testForexTrade() throws IllegalArgumentException {
        TradeDTO trade1 = new TradeDTO();
        trade1.setTransactionType("SELL");
        trade1.setSecurityType("FX");
        trade1.setPrice(1000);
        trade1.setQuantity(100000000);
        trade1.setTradeTime("01/05/2019 10:00:00");
        var commission = tradeService.getTotalCommission(Arrays.asList(trade1));
        assertEquals(1000, commission);
    }

    @Test
    void testNullTrade() throws IllegalArgumentException {
        var commission = tradeService.getTotalCommission(new ArrayList<>());
        assertEquals(0, commission);
    }

    @Test
    void testErrorTrade() {
        TradeDTO trade1 = new TradeDTO();
        trade1.setTransactionType("SELL");
        trade1.setSecurityType("ABC");
        trade1.setPrice(100);
        trade1.setQuantity(1);
        trade1.setTradeTime("01/08/2019 10:00:00");

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            tradeService.getTotalCommission(Arrays.asList(trade1));
        });

    }

}