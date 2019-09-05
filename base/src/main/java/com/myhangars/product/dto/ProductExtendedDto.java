package com.myhangars.product.dto;

import com.myhangars.price.model.Price;

import java.util.ArrayList;
import java.util.List;

public class ProductExtendedDto {

    //private long id;
    //private boolean state = true;
    private String name;
    private String description;
    private List<Price> prices = new ArrayList<Price>();
    private int quantity;

    public ProductExtendedDto() { }

    /*
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
