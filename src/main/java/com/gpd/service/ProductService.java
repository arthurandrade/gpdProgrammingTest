package com.gpd.service;

import java.util.List;

import com.gpd.model.product.Product;

public interface ProductService {

    Product findBy(String id);

    List<Product> findAllProducts();
}
