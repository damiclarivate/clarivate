package com.clarivate.tradeservice.controller;

import com.clarivate.model.TradeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TradeControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCalculate() throws Exception {

        TradeDTO trade1 = new TradeDTO();
        trade1.setTransactionType("SELL");
        trade1.setSecurityType("STO");
        trade1.setPrice(100);
        trade1.setQuantity(100);
        trade1.setTradeTime("01/01/2019 10:00:00");


        TradeDTO trade2 = new TradeDTO();
        trade2.setTransactionType("SELL");
        trade2.setSecurityType("FX");
        trade2.setPrice(100);
        trade2.setQuantity(10);
        trade2.setTradeTime("01/15/2019 10:00:00");

        var listTrades = Arrays.asList(trade1, trade2);

        double expectedCommission = 5.0;
        String expectedMsg = String.format("Total commission for %s trades is $%f",
                listTrades.size(), expectedCommission);

        mockMvc.perform(post("/trades/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(listTrades)))
                .andExpect(content().string(expectedMsg));
    }

    @Test
    void testCalculateEmptyRequest() throws Exception {
        mockMvc.perform(post("/trades/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is4xxClientError());
    }

}