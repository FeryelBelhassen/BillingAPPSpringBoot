package com.nst.facture.billing.controllers.product;


import com.nst.facture.billing.models.Product;
import com.nst.facture.billing.payload.Dto.ProductDto;
import com.nst.facture.billing.payload.response.ApiResponse;
import com.nst.facture.billing.service.ProductService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
@Api("Product Controller API")
/**
 * This class describes a productController
 */
public class ProductController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;


    /**
     * This function displays the list of products
     * @return
     */
    /*@GetMapping("/products")
    public List<ProductDto> getProducts() {

        return productService.getProducts().stream().map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }*/
    @GetMapping("/products")
    public List<Product> allProducts(){
        return productService.getProducts();

    }

    /**
     * This function for get a product
     * @param id
     * @return
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> geProductById(@PathVariable(name = "id") Long id) {
        Product product = productService.getProductById(id);

        // convert entity to DTO
        ProductDto productResponse = modelMapper.map(product, ProductDto.class);

        return ResponseEntity.ok().body(productResponse);
    }

    /**
     * This function about create a product
     * @param productDto
     * @return
     */
    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {

        // convert DTO to entity
        Product productRequest = modelMapper.map(productDto, Product.class);

        Product product = productService.createProduct(productRequest);

        // convert entity to DTO
        ProductDto productResponse = modelMapper.map(product, ProductDto.class);

        return new ResponseEntity<ProductDto>(productResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO

    /**
     * This function about update a product
     * @param id
     * @param productDto
     * @return
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable long id, @RequestBody ProductDto productDto) {

        // convert DTO to Entity
        Product productRequest = modelMapper.map(productDto, Product.class);

        Product product = productService.updateProduct(id, productRequest);

        // entity to DTO
        ProductDto productResponse = modelMapper.map(product, ProductDto.class);

        return ResponseEntity.ok().body(productResponse);
    }

    /**
     * This function about delete a product
     * @param id
     * @return
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Product deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }



}