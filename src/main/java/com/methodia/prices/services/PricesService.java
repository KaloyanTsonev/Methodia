package com.methodia.prices.services;

import java.util.List;

import com.methodia.prices.model.prices.client.PricesDTO;


public interface PricesService {

    PricesDTO getCurrentProductPrice(Long id);

    List<PricesDTO> getProductPrices(Long id);
}
