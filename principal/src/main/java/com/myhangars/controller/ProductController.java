package com.myhangars.controller;


import com.myhangars.exception.ArgumentException;
import com.myhangars.price.model.Price;
import com.myhangars.product.builder.ProductBuilder;
import com.myhangars.product.builder.ProductDtoBuilder;
import com.myhangars.product.dto.ProductDto;
import com.myhangars.product.model.Product;
import com.myhangars.product.service.ProductServiceImpl;
import com.myhangars.products_hangar.service.Products_HangarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Validated
@RequestMapping(value = "/api")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;


    @GetMapping(value = "/products")
    ResponseEntity<List> loadProducts() {

        List<ProductDto> productDtos = this.productService
                .getAll(null)
                .stream()
                .map(hangar -> new ProductDtoBuilder(hangar).getProductDto())
                .collect(Collectors.toList());

        return new ResponseEntity<List>(
                productDtos,
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/products", params = { "page", "size" })
    ResponseEntity<Page> loadProducts(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<ProductDto> productDtos = this.productService
                .getAll(pageable)
                .map(product -> new ProductDtoBuilder(product).getProductDto());

        return new ResponseEntity<Page>(
                productDtos,
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/products/{id}")
    ResponseEntity<ProductDto> loadProductById(@PathVariable("id") @Min(1) long id) {

        Product product = this.productService.getById(id);

        return new ResponseEntity<ProductDto>(
                new ProductDtoBuilder(product).getProductDto(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/products/")
    public ResponseEntity<ProductDto> loadProductByName(@RequestParam String name) {

        final Product product = this.productService.getByName(name);

        return new ResponseEntity<ProductDto>(
                new ProductDtoBuilder(product).getProductDto(),
                HttpStatus.OK
        );

    }

    @PostMapping(value = "/products")
    ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productDto) {

        Product product = new ProductBuilder(productDto).getProduct();
        Product _product = this.productService.insert(product);

        return new ResponseEntity<ProductDto>(
                new ProductDtoBuilder(_product).getProductDto(),
                HttpStatus.CREATED
        );

    }

    @PutMapping(value = "products/{id}")
    ResponseEntity<ProductDto> savePrices(@PathVariable("id") @Min(1) long id,
                                          @RequestBody @NotEmpty List<Price> prices) {

        Product product = this.productService.insertPrices(id, prices);

        return new ResponseEntity<ProductDto>(
                new ProductDtoBuilder(product).getProductDto(),
                HttpStatus.CREATED
        );
    }

    @PostMapping(value = "products/new-price/{id}")
    ResponseEntity<ProductDto> insertPrice(@PathVariable("id") @Min(1) long id,
                                           @RequestBody float price) {
        if(price<=0)
            throw new ArgumentException.PriceMustBePositive();

        Product product = this.productService.insertPrice(id, price);

        return new ResponseEntity<ProductDto>(
                new ProductDtoBuilder(product).getProductDto(),
                HttpStatus.CREATED
        );
    }

}
