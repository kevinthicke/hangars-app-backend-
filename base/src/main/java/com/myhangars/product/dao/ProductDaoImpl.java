package com.myhangars.product.dao;


import com.myhangars.product.model.Product;
import com.myhangars.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {

        return this.productRepository.findAllWithTrueState(pageable);
    }

    @Override
    public Optional<Product> findById(long id) {
        return this.productRepository.findByIdWithTrueState(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByNameWithTrueState(name);
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }
}
