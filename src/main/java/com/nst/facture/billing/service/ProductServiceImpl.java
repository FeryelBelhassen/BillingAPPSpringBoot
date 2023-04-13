package com.nst.facture.billing.service;

import com.nst.facture.billing.models.Product;
import com.nst.facture.billing.payload.Dto.ProductDto;
import com.nst.facture.billing.repository.ProductRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
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
    public List<Product> getAllProducts() {

        return (List<Product>) productRepository.findAll();
    }

    @Override
     public Product addProductFromDTO(ProductDto productDto){
            Product toAdd = new Product();
            BeanUtils.copyProperties(productDto, toAdd);
            return productRepository.save(toAdd);
    }


    @Override
    public Product updateProduct(Long id, Product product) {
        Product productDB =getProductById(id);
        productDB.setCode(product.getCode());
        productDB.setDesignation(product.getDesignation());
        productDB.setQuantity(product.getQuantity());
        productDB.setPrice(product.getPrice());
        productDB.setSupplier(product.getSupplier());
        Product updatedProduct = getProductById(id);

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(u -> {
            productRepository.delete(u);

        });
    }

    @Override
    public void deleteAll(){
        productRepository.deleteAll();
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

