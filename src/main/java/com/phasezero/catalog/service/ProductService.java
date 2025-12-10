package com.phasezero.catalog.service;

import com.phasezero.catalog.model.Product;
import java.util.List;

public interface ProductService {
	Product addProduct(Product product);
	List<Product> getAllProducts();
	Product getProduct(String partNumber);
	List<Product> searchByName(String text);
	List<Product> filterByCategory(String category);
	List<Product> sortByPrice();
	double getInventoryValue();

}
