package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Product;
import com.nst.facture.billing.payload.Dto.ProductDto;

import java.util.List;

/**
 * This interface of product service
 */
public interface ProductService {
    List<Product> getAllProducts();

    Product addProductFromDTO(ProductDto productDto);
    Product updateProduct(Long id, Product product);

    void deleteProduct( Long id);

    void deleteAll();

    Product getProductById(long id);

}