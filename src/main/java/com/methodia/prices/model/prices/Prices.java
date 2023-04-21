package com.methodia.prices.model.prices;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.methodia.prices.model.product.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Prices {
    /*Tried manually SQL script for no overlapping periods on my local DB
    CREATE TABLE public.prices ( prices_id serial PRIMARY key , p_id integer not null , price numeric, startDate date , endDate date , EXCLUDE USING gist (int4range(p_id, p_id, '[]') WITH =, tsrange(startDate, endDate) WITH &&))
    */

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    public Prices(Product product, BigDecimal price, LocalDate startDate, LocalDate endDate) {

        this.product = product;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
