package com.nst.facture.billing.controllers.product;


import com.nst.facture.billing.models.Product;
import com.nst.facture.billing.payload.Dto.ProductDto;
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
        return productService.getAllProducts();

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
     *
     * @param productDto
     * @return
     */
    @PostMapping("/addproduct")
    public Product createProduct(@RequestBody ProductDto productDto) {

        return productService.addProductFromDTO(productDto);
    }

    /**
     * This function about update a product
     * @param id
     * @param product
     * @return
     */
    @PutMapping("updateproduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){

        Product p = productService.updateProduct(id, product);
        return new ResponseEntity<Product>(p, HttpStatus.OK);
    }

    /**
     * This function about delete a product
     * @param id
     * @return
     */
    @DeleteMapping("/deleteproduct/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {

        productService.deleteProduct(id);
    }

    @DeleteMapping("/deleteall")
    public void deleteAllProducts() {

        productService.deleteAll();
    }




}