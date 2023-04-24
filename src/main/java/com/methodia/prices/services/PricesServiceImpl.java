package com.methodia.prices.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.methodia.prices.exceptions.ValidationException;
import com.methodia.prices.model.prices.Prices;
import com.methodia.prices.model.prices.PricesRepository;
import com.methodia.prices.model.prices.client.PricesDTO;

@Service
public class PricesServiceImpl implements PricesService {

    private static final String NO_DATA_FOR_PRODUCT_CATEGORY = "No data for product category.";

    private static final String NO_CURRENT_PRICE_FOR_PRODUCT_CATEGORY = "No current price for product category.";
    private final PricesRepository repository;

    public PricesServiceImpl(PricesRepository repository) {

        this.repository = repository;
    }

    public PricesDTO getCurrentProductPrice(Long id) {

        Prices prices = repository.findCurrentProductPriceByProductId(id, LocalDate.now());
        if (ObjectUtils.isEmpty(prices)) {
            throw new ValidationException(NO_CURRENT_PRICE_FOR_PRODUCT_CATEGORY);
        }
        PricesDTO pricesDTO = new PricesDTO();
        pricesDTO.setId(prices.getId());
        pricesDTO.setProductCategory(prices.getProduct().getCategory());
        pricesDTO.setPrice(prices.getPrice());
        pricesDTO.setStartDate(prices.getStartDate());
        pricesDTO.setEndDate(prices.getEndDate());

        return pricesDTO;
    }

    public List<PricesDTO> getProductPrices(Long id) {

        List<Prices> prices = repository.findAllByProductId(id);
        if (prices == null || prices.isEmpty()) {
            throw new ValidationException(NO_DATA_FOR_PRODUCT_CATEGORY);
        }
        List<PricesDTO> pricesDTO = prices.stream()
                .map(price -> new PricesDTO(price.getId(), price.getProduct().getCategory(), price.getPrice(),
                        price.getStartDate(), price.getEndDate())).collect(
                        Collectors.toList());
        return pricesDTO;
    }
}
