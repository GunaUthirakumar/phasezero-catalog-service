package com.phasezero.catalog.repository;

import com.phasezero.catalog.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<String, Product> store = new ConcurrentHashMap<>();

    @Override
    public Product save(Product product) {
        store.put(product.getPartNumber(), product);
        return product;
    }

    @Override
    public Optional<Product> findByPartNumber(String partNumber) {
        return Optional.ofNullable(store.get(partNumber));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }
}
