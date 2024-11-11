package com.clarivate.tradeservice;

import com.clarivate.controller.TradeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class ApplicationTest {

    @Autowired
    private TradeController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}