package com.phasezero.catalog.repository;

import com.phasezero.catalog.model.Product;
import java.util.*;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findByPartNumber(String partNumber);

    List<Product> findAll();
}
