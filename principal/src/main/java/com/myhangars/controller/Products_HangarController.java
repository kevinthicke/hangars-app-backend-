package com.myhangars.controller;


import com.myhangars.product.builder.ProductBuilder;
import com.myhangars.product.builder.ProductDtoBuilder;
import com.myhangars.product.dto.ProductDto;
import com.myhangars.product.model.Product;
import com.myhangars.product.service.ProductService;
import com.myhangars.products_hangar.model.Products_Hangar;
import com.myhangars.products_hangar.service.Products_HangarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class Products_HangarController {

    @Autowired
    private Products_HangarService products_hangarService;

    @Autowired
    private ProductService productService;

    /*
    @GetMapping("/products_hangar/hangar")
    public List<Products_Hangar> loadListProducts_HangarByHangarId(@RequestParam(value = "id", required = true) long id) {
        return this.products_hangarService.getListProducts_HangarsByHangarId(id);
    }
    */

   @GetMapping("/hangars/products/{id}")
   public ResponseEntity<List> loadHangarProductByHangarId(@PathVariable("id") long id) {

       List<ProductDto> productDtos = new ArrayList<ProductDto>();

       this.products_hangarService
               .getHangarProductsByHangarId(id)
               .forEach(product -> {
                   productDtos.add(new ProductDtoBuilder(product).getProductDto());
               });


       return new ResponseEntity<List>(
               productDtos,
               HttpStatus.OK
       );
   }

   @GetMapping("/hangars/products/by-name")
    public ResponseEntity<List> loadHangarProductByHangarName(@RequestParam("name") @NotEmpty String name) {

       List<ProductDto> productDtoList = this.products_hangarService.getHangarProductByHangarName(name)
                .stream()
                .map(product -> new ProductDtoBuilder(product).getProductDto())
                .collect(Collectors.toList());

        return new ResponseEntity<List>(
                productDtoList,
                HttpStatus.OK
        );
   }

   @PostMapping("/hangars/{id}/products")
    public ResponseEntity<Products_Hangar> saveProductInHangarByHangarId(@PathVariable("id") @Min(1) long id,
                                                                    @RequestBody @Valid ProductDto productDto) {
       Product product = new ProductBuilder(productDto).getProduct();
       this.productService.insert(product);

        return new ResponseEntity<Products_Hangar>(
                this.products_hangarService.includeProductInHangarByHangarId(product, id),
                HttpStatus.CREATED
        );
   }
}
