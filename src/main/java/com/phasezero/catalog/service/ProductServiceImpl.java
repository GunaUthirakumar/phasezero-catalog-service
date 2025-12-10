package com.phasezero.catalog.service;

import com.phasezero.catalog.exception.InvalidProductException;
import com.phasezero.catalog.exception.ProductAlreadyExistsException;
import com.phasezero.catalog.model.Product;
import com.phasezero.catalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product addProduct(Product product) {
        // basic null checks
        if (product == null) {
            throw new InvalidProductException("Product payload is required");
        }
        if (product.getPartNumber() == null || product.getPartNumber().isBlank()) {
            throw new InvalidProductException("partNumber is required");
        }
        if (product.getPartName() == null || product.getPartName().isBlank()) {
            throw new InvalidProductException("partName is required");
        }
        if (product.getCategory() == null || product.getCategory().isBlank()) {
            throw new InvalidProductException("category is required");
        }
        if (product.getPrice() < 0) {
            throw new InvalidProductException("price cannot be negative");
        }
        if (product.getStock() < 0) {
            throw new InvalidProductException("stock cannot be negative");
        }

        // duplicate check
        if (repo.findByPartNumber(product.getPartNumber()).isPresent()) {
            throw new ProductAlreadyExistsException("partNumber already exists: " + product.getPartNumber());
        }

        // normalize partName to lowercase (Product.setPartName already does this)
        product.setPartName(product.getPartName());

        return repo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public List<Product> searchByName(String text) {
        if (text == null || text.isBlank()) return List.of();
        String q = text.toLowerCase();
        return repo.findAll().stream()
                .filter(p -> p.getPartName() != null && p.getPartName().contains(q))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> filterByCategory(String category) {
        if (category == null || category.isBlank()) return List.of();
        return repo.findAll().stream()
                .filter(p -> p.getCategory() != null && p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByPrice() {
        return repo.findAll().stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public double getInventoryValue() {
        return repo.findAll().stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
                .sum();
    }
    
    @Override
    public Product getProduct(String partNumber) {
        if (partNumber == null || partNumber.isBlank()) {
            throw new InvalidProductException("partNumber is required");
        }

        return repo.findByPartNumber(partNumber)
                .orElseThrow(() -> new InvalidProductException(
                        "Product not found: " + partNumber
                ));
    }

}
