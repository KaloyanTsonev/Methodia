package com.methodia.prices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.methodia.prices.model.prices.client.PricesDTO;
import com.methodia.prices.services.PricesService;
import com.methodia.prices.services.ProductService;


@RestController
@RequestMapping("/prices")
public class PricesController {

    private final PricesService pricesService;

    private final ProductService productService;

    @Autowired
    public PricesController(PricesService pricesService, ProductService productService) {

        this.pricesService = pricesService;
        this.productService = productService;
    }

    @GetMapping("/currentPrice/{productCategory}")
    public ResponseEntity<PricesDTO> getCurrentProductPrice(@PathVariable("productCategory") String productCategory) {

        Long productId = productService.findProductIdByCategory(productCategory);
        return new ResponseEntity<>(pricesService.getCurrentProductPrice(productId), HttpStatus.OK);
    }

    @GetMapping("/{productCategory}")
    public ResponseEntity<List<PricesDTO>> getProductPrices(@PathVariable("productCategory") String productCategory) {

        Long productId = productService.findProductIdByCategory(productCategory);
        return new ResponseEntity<>(pricesService.getProductPrices(productId), HttpStatus.OK);
    }


}
