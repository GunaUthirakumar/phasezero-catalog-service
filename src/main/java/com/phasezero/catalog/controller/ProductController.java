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


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = service.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }


    @GetMapping("/{partNumber}")
    public ResponseEntity<Product> getProduct(@PathVariable String partNumber) {
        Product p = service.getProduct(partNumber);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    @GetMapping("/search")
    public List<Product> searchByName(@RequestParam(name = "q", required = false) String q) {
        return service.searchByName(q);
    }

    // GET: filter by category
    @GetMapping("/category/{category}")
    public List<Product> filterByCategory(@PathVariable String category) {
        return service.filterByCategory(category);
    }

    // GET: sort by price (ascending)
    @GetMapping("/sort/price")
    public List<Product> sortByPrice() {
        return service.sortByPrice();
    }

    // GET: total inventory value (price * stock)
    @GetMapping("/value")
    public ResponseEntity<Double> getInventoryValue() {
        double value = service.getInventoryValue();
        return ResponseEntity.ok(value);
    }
}
