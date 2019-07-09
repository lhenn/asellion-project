package com.asellionassignment.asellion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class AsellionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsellionApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ProductRepository productRepository) {
		return args -> {
			productRepository.save(new Product("milk", new BigDecimal("2.00"), new Date()));
			productRepository.save(new Product("eggs", new BigDecimal("2.50"), new Date()));
			productRepository.save(new Product("donuts", new BigDecimal("5.00"), new Date()));
			productRepository.save(new Product("bicycle", new BigDecimal("200.00"), new Date()));
		};
	}

}
