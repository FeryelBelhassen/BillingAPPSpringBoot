package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Product;
import com.nst.facture.billing.repository.ProductRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long id, Product productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product"));

        product.setCode(productRequest.getCode());
        product.setDesignation(productRequest.getDesignation());
        product.setQuantity(productRequest.getQuantity());
        product.setSupplier(productRequest.getSupplier());
        product.setPrice(productRequest.getPrice());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product"));

        productRepository.delete(product);
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> result = productRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("Product ");
        }
    }
}

