package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Product;

import java.util.List;

/**
 * This interface of product service
 */
public interface ProductService {
    List<Product> getProducts();

    Product createProduct(Product product);

    Product updateProduct(long id, Product product);

    void deleteProduct(long id);

    Product getProductById(long id);
}