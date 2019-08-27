package com.myhangars.products_hangar.dao;

import com.myhangars.products_hangar.model.Products_Hangar;

import java.util.List;

public interface Products_HangarDao {
    public List<Products_Hangar> findListProducts_HangarByHangarId(long id);
    public List<Products_Hangar> findListProducts_HangarByProductId(long id);
    public Products_Hangar includeProductInHangar(Products_Hangar products_hangar);
}


