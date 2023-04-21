package com.methodia.prices.services;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import com.methodia.prices.exceptions.ValidationException;
import com.methodia.prices.model.product.ProductCategory;
import com.methodia.prices.model.product.ProductRepository;

@Service
public class ProductService {

    private static final String INVALID_PRODUCT_CATEGORY = "Product category is missing";
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public long findProductIdByCategory(String productCategory){
        if( !EnumUtils.isValidEnum(ProductCategory.class, productCategory)){
            throw new ValidationException(INVALID_PRODUCT_CATEGORY);
        }
        return repository.findProductIdByCategory(ProductCategory.valueOf(productCategory));
    }
}
