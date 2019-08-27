package com.myhangars.products_hangar.dao;


import com.myhangars.products_hangar.model.Products_Hangar;
import com.myhangars.products_hangar.repository.Products_HangarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Products_HangarDaoImpl implements Products_HangarDao {

    @Autowired
    private Products_HangarRepository products_hangarRepository;

    @Override
    public List<Products_Hangar> findListProducts_HangarByHangarId(long id) {

        return this.products_hangarRepository
                .findByHangar_id(id);
    }

    @Override
    public List<Products_Hangar> findListProducts_HangarByProductId(long id) {
        return this.products_hangarRepository
                .findByProduct_Id(id);
    }

    @Override
    public Products_Hangar includeProductInHangar(Products_Hangar products_hangar) {
        return this.products_hangarRepository
                .save(products_hangar);
    }
}
