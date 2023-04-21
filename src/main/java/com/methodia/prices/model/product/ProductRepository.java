package com.methodia.prices.model.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
     @Query("select p.id from Product p where p.category = :productCategory")
     long findProductIdByCategory(@Param("productCategory") ProductCategory productCategory);


}
