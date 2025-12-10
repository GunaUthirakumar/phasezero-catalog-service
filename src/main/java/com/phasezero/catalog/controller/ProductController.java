package com.phasezero.catalog.controller;

import com.phasezero.catalog.model.Product;
import com.phasezero.catalog.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // 1) Add product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = service.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 2) List all products
    @GetMapping
    public List<Product> getAllProducts(@RequestParam(value = "category", required = false) String category,
                                        @RequestParam(value = "q", required = false) String q,
                                        @RequestParam(value = "sort", required = false) String sort) {
        // Simple routing convenience: /products?category=... or ?q=... or ?sort=price
        if (q != null && !q.isBlank()) {
            return service.searchByName(q);
        }
        if (category != null && !category.isBlank()) {
            return service.filterByCategory(category);
        }
        if ("price".equalsIgnoreCase(sort)) {
            return service.sortByPrice();
        }
        return service.getAllProducts();
    }

    // 3) Get product by partNumber
    @GetMapping("/{partNumber}")
    public ResponseEntity<Product> getProduct(@PathVariable String partNumber) {
        Product p = service.getProduct(partNumber);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    // 4) Search by name (explicit endpoint)
    @GetMapping("/search")
    public List<Product> searchByName(@RequestParam(name = "q", required = true) String q) {
        return service.searchByName(q);
    }

    // 5) Filter by category (explicit endpoint)
    @GetMapping("/filter")
    public List<Product> filterByCategory(@RequestParam(name = "category", required = true) String category) {
        return service.filterByCategory(category);
    }

    // 6) Sort by price (explicit endpoint)
    @GetMapping("/sort/price")
    public List<Product> sortByPrice() {
        return service.sortByPrice();
    }

    // 7) Inventory value
    @GetMapping("/inventory/value")
    public ResponseEntity<Double> getInventoryValue() {
        double value = service.getInventoryValue();
        return ResponseEntity.ok(value);
    }
}
