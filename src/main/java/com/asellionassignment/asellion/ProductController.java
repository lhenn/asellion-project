package com.asellionassignment.asellion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(path="/products", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getProducts() {
        Map<String, Object> dto = new LinkedHashMap<>();
        List<Map<String,Object>> products = productRepository.findAll()
               .stream()
                .map(product -> productDTO(product))
                .collect(Collectors.toList());
        dto.put("products", products);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @RequestMapping(path="/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createNewProduct(
            @RequestParam String name, @RequestParam BigDecimal currentPrice
    ) {
        System.out.println(name);
        System.out.println(currentPrice);
        productRepository.save(new Product(name, currentPrice, new Date()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Map<String, Object> productDTO(Product product) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", product.getId());
        dto.put("name",product.getName());
        dto.put("price",product.getCurrentPrice());
        dto.put("lastUpdated",product.getLastUpdate());
        return dto;
    }
}
