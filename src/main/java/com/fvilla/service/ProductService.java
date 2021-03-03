package com.fvilla.service;

import com.fvilla.exception.RecordNotFoundException;
import com.fvilla.model.ProductEntity;
import com.fvilla.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductEntity> getAllProducts(){
        List<ProductEntity> productEntityList = productRepository.findAll();
        if(productEntityList.isEmpty()){
            return new ArrayList<ProductEntity>();
        }else{
            return productEntityList;
        }
    }

    public ProductEntity addOrUpdateProductById(ProductEntity productEntity) throws RecordNotFoundException {
        if(productEntity.getId() != null){
            Optional<ProductEntity> optionalProductEntity =  productRepository.findById(productEntity.getId());

            if(optionalProductEntity.isPresent()){
                ProductEntity newEntity = optionalProductEntity.get();
                newEntity.setName(productEntity.getName());

                newEntity = productRepository.save(newEntity);
                return newEntity;
            }else{
                productEntity = productRepository.save(productEntity);
                return  productEntity;
            }
        }else{
            productEntity = productRepository.save(productEntity);
            return productEntity;
        }
    }
}
