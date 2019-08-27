package com.myhangars.product.service;

import com.myhangars.price.model.Price;
import com.myhangars.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
   public Page<Product> getAll(Pageable pageable);
   public Product getById(long id);
   public Product getByName(String name);
   public Product insert(Product product);
   public Product insertPrices(long id, List<Price> prices);
   public Product insertPrice(long id, float price);
}
