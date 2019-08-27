package com.myhangars.products_hangar.service;

import com.myhangars.exception.GenericException;
import com.myhangars.hangar.service.HangarService;
import com.myhangars.product.dao.ProductDao;
import com.myhangars.product.model.Product;
import com.myhangars.products_hangar.builder.Product_HangarBuilder;
import com.myhangars.products_hangar.dao.Products_HangarDao;
import com.myhangars.products_hangar.model.Products_Hangar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.Global.undefined;

@Service
public class Products_HangarServiceImpl implements Products_HangarService {

    @Autowired
    private Products_HangarDao products_hangarDao;

    //TODO: replace ProductDao by ProductService :)
    @Autowired
    private ProductDao productDao;

    @Autowired
    private HangarService hangarService;

    @Override
    public List<Products_Hangar> getListProducts_HangarsByHangarId(long id) {
        return this.products_hangarDao.findListProducts_HangarByHangarId(id);
    }

    @Override
    public List<Products_Hangar> getListProducts_HangarsByProductId(long id) {
        return this.products_hangarDao.findListProducts_HangarByProductId(id);
    }

    @Override
    public List<Product> getHangarProductsByHangarId(long id) {

        return this.products_hangarDao
                .findListProducts_HangarByHangarId(id)
                .stream()
                .map(Products_Hangar::getProduct_id)
                .map(productId -> this.productDao.findById(productId).get())
                .collect(Collectors.toList());

    }

    @Override
    public List<Product> getHangarProductByHangarName(String name) {
        long hangarId = this.hangarService.getFirstByName(name).getId();

        return this.getHangarProductsByHangarId(hangarId);
    }


    @Override
    public Products_Hangar includeProductInHangarByHangarId(Product product, long hangarId) {

        if(this.hangarService.getById(hangarId).equals(null))
            throw new GenericException.NotFound(hangarId);

        Products_Hangar products_hangar = new Product_HangarBuilder(product.getId(), hangarId)
                .getProducts_hangar();

        return this.products_hangarDao.includeProductInHangar(products_hangar);
    }
}
