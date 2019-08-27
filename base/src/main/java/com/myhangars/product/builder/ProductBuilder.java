package com.myhangars.product.builder;

import com.myhangars.product.dto.ProductDto;
import com.myhangars.product.model.Product;

public class ProductBuilder {

    private Product product;

    public ProductBuilder(ProductDto productDto) {

        this.product = new Product();
        this.product.setName(productDto.getName());
        this.product.setDescription(productDto.getDescription());
        this.product.setPrices(productDto.getPrices());

    }

    public Product getProduct() {
        return product;
    }
}
