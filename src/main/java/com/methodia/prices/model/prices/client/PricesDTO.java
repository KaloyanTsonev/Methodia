package com.methodia.prices.model.prices.client;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.methodia.prices.model.product.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PricesDTO {

    private Long id;

    private ProductCategory productCategory;

    private BigDecimal price;

    private LocalDate startDate;

    private LocalDate endDate;

}
