package com.methodia.prices.model.prices;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {
    @Query("select p from Prices p where product.id = :productId and p.startDate <= :today AND p.endDate >= :today")
    Prices findCurrentProductPriceByProductId(long productId, LocalDate today);

    List<Prices> findAllByProductId(long productId);
}
