package com.myhangars.product.service;

import com.myhangars.exception.GenericException;
import com.myhangars.price.model.Price;
import com.myhangars.product.dao.ProductDao;
import com.myhangars.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public Page<Product> getAll(Pageable pageable) {

        Page<Product> products = this.productDao.findAll(pageable);

        if(products.isEmpty())
            throw new GenericException.NotFound();

        return products;
    }

    public Product getById(long id) {

        return this.productDao
                .findById(id)
                .orElseThrow(() -> new GenericException.NotFound(id));
    }

    @Override
    public Product getByName(String name) {
        return this.productDao
                .findByName(name)
                .orElseThrow(() -> new GenericException.NotFound(name));
    }

    @Override
    public Product insert(Product product) {
        return this.productDao.save(product);
    }

    @Override
    public Product insertPrices(long id, List<Price> prices) {
        Product product = this.getById(id);

        product.setPrices(prices);

        return this.insert(product);

    }

    @Override
    public Product insertPrice(long id, float price) {
        Product product = this.getById(id);

        List<Price> productPrice = product.getPrices();
        productPrice.add(new Price(price));

        return this.productDao.save(product);
    }

}
