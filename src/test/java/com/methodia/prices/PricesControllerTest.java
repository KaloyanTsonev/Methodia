package com.methodia.prices;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PricesControllerTest {

    private static final String URL = "http://localhost:8081/prices";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenPricesForProductCategoryIsPresent_then200andPriceIsReceived() throws Exception {
        //given
        String validProductCategory = "Y";

        //when valid category with prices presented-> then 200 and price 1.01.
        this.mockMvc.perform(get(URL + "/" + validProductCategory)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1.01")));

    }

    @Test
    void whenCurrentPriceForProductCategoryIsPresent_then200andPriceIsReceived() throws Exception {
        //given
        String validProductCategory = "Y";

        //when valid category with current price presented-> then 200 and price 1.10.
        this.mockMvc.perform(get(URL + "/currentPrice/" + validProductCategory)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1.10")));

    }

    @Test
    void whenInvalidProductCategory_then400IsReceived_andProductCategoryIsMissingText() throws Exception {
        //given
        String invalidProductCategory = "I";

        //when invalid category -> then 400, product category is missing
        this.mockMvc.perform(get(URL + "/" + invalidProductCategory)).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Product category is missing")));

    }

    @Test
    void whenNoCurrentPriceForProductCategory_then400IsReceived_andNoCurrentPriceForProductCategoryText()
            throws Exception {
        //given
        String validProductCategory = "X";

        //when valid category, but no current price for it -> then 400, No current price for product category.
        this.mockMvc.perform(get(URL + "/currentPrice/" + validProductCategory)).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("No current price for product category.")));

    }

    @Test
    void whenNotInitializedProductCategory_then400IsReceived_andProductCategoryExistsButNotInitializedText()
            throws Exception {
        //given
        String validProductCategoryNoDataForIt = "Z";

        //when product category exists but not initialized in DB -> then 400, Product category exists but not initialized in DB.
        this.mockMvc.perform(get(URL + "/" + validProductCategoryNoDataForIt)).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Product category exists but not initialized in DB.")));

    }
}
