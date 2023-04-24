package com.methodia.prices;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.methodia.prices.controllers.PricesController;

@SpringBootTest
class PricesApplicationTests {

    @Autowired
    private PricesController pricesController;


    @Test
    void contextLoads() {

        assertThat(pricesController).isNotNull();
    }
}
