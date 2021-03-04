package com.fvilla.controller;

import com.fvilla.exception.RecordNotFoundException;
import com.fvilla.model.ProductEntity;
import com.fvilla.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductEntity>> getAllProducts(){
        List<ProductEntity> productEntityList = productService.getAllProducts();
        return new ResponseEntity<List<ProductEntity>>(productEntityList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductEntity> createOrUpdateStudent(@Valid @RequestBody ProductEntity productEntity)
            throws RecordNotFoundException {
        ProductEntity updated = productService.addOrUpdateProductById(productEntity);
        return new ResponseEntity<ProductEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProductById(@PathVariable("id") Long id) throws RecordNotFoundException{
        productService.deleteProductById(id);
        return HttpStatus.FORBIDDEN;
    }
}
