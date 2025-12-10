package com.phasezero.catalog.service;

import com.phasezero.catalog.model.Product;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product getProduct(String partNumber);     // <-- add this
    List<Product> getAllProducts();
    List<Product> searchByName(String text);
    List<Product> filterByCategory(String category);
    List<Product> sortByPrice();
    double getInventoryValue();
}
