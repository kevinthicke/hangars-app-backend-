package com.myhangars.products_hangar.service;

import com.myhangars.product.model.Product;
import com.myhangars.products_hangar.model.Products_Hangar;

import java.util.List;

public interface Products_HangarService {
    public List<Products_Hangar> getListProducts_HangarsByHangarId(long id);
    public List<Products_Hangar> getListProducts_HangarsByProductId(long id);
    public List<Product> getHangarProductsByHangarId(long id);
    public List<Product> getHangarProductByHangarName(String name);
    public Products_Hangar includeProductInHangarByHangarId(Product product, long hangarId);
}
