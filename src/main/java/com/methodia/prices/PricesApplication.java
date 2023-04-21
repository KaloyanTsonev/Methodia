package com.methodia.prices;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.methodia.prices.model.prices.Prices;
import com.methodia.prices.model.prices.PricesRepository;
import com.methodia.prices.model.product.Product;
import com.methodia.prices.model.product.ProductCategory;
import com.methodia.prices.model.product.ProductRepository;

@SpringBootApplication
public class PricesApplication {

	private static final Logger log = LoggerFactory.getLogger(PricesApplication.class);
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void main(String[] args) {
		SpringApplication.run(PricesApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadProductsAndPrices(ProductRepository productRepository, PricesRepository pricesRepository) {

		return (args) -> {
			Product productX = productRepository.save(new Product(ProductCategory.X));
			Product productY = productRepository.save(new Product(ProductCategory.Y));

			log.info("Products found with findAll():");
			log.info("-------------------------------");
			for (Product customer : productRepository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			pricesRepository.save(new Prices(productX, new BigDecimal("2.34"), LocalDate.parse("2023-01-01", formatter), LocalDate.parse("2023-01-31", formatter)));
			pricesRepository.save(new Prices(productY, new BigDecimal("1.01"), LocalDate.parse("2023-01-01", formatter), LocalDate.parse("2023-01-01", formatter)));
			pricesRepository.save(new Prices(productX, new BigDecimal("2.50"), LocalDate.parse("2023-02-01", formatter), LocalDate.parse("2023-03-31", formatter)));
			pricesRepository.save(new Prices(productY, new BigDecimal("1.10"), LocalDate.parse("2023-02-01", formatter), LocalDate.parse(LocalDate.now().toString(), formatter)));

			log.info("Prices found with findAll():");
			log.info("-------------------------------");
			for (Prices prices : pricesRepository.findAll()) {
				log.info(prices.toString());
			}
			log.info("");
		};
	}
}
