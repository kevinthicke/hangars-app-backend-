package com.myhangars.product.builder;


import com.myhangars.product.dto.ProductDto;
import com.myhangars.product.model.Product;

public class ProductDtoBuilder {

    private ProductDto productDto;

    public ProductDtoBuilder(Product product) {

        this.productDto = new ProductDto();

        this.productDto.setId(product.getId());
        this.productDto.setName(product.getName());
        this.productDto.setPrices(product.getPrices());
        this.productDto.setDescription(product.getDescription());

    }

    public ProductDto getProductDto() {
        return productDto;
    }
}
