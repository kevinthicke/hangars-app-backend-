package com.myhangars.product.builder;

import com.myhangars.product.model.Product;
import com.myhangars.product.dto.ProductExtendedDto;

public class ProductExtendedDtoBuilder {

    private ProductExtendedDto productExtendedDto;

    public ProductExtendedDtoBuilder(Product product, int quantity) {
        productExtendedDto = new ProductExtendedDto();

        this.productExtendedDto.setName(product.getName());
        this.productExtendedDto.setPrices(product.getPrices());
        this.productExtendedDto.setDescription(product.getDescription());
        this.productExtendedDto.setQuantity(quantity);
    }

    public ProductExtendedDto getProductExtended() {
        return this.productExtendedDto;
    }
}
