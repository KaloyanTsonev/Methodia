package com.methodia.prices.services;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import com.methodia.prices.exceptions.ValidationException;
import com.methodia.prices.model.product.ProductCategory;
import com.methodia.prices.model.product.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String INVALID_PRODUCT_CATEGORY = "Product category is missing";

    private static final String NOT_INITIALIZED_PRODUCT_CATEGORY = "Product category exists but not initialized in DB.";
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    public Long findProductIdByCategory(String productCategory){
        if( !EnumUtils.isValidEnum(ProductCategory.class, productCategory)){
            throw new ValidationException(INVALID_PRODUCT_CATEGORY);
        }
        Object result = repository.findProductIdByCategory(ProductCategory.valueOf(productCategory));
        if(null == result){
            throw new ValidationException(NOT_INITIALIZED_PRODUCT_CATEGORY);
        }
        return (Long)result;
    }
}
