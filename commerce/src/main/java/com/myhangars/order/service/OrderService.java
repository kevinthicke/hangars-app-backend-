package com.myhangars.order.service;

import com.myhangars.order.model.Order;
import com.myhangars.product_order.model.Product_Order;

import java.util.List;

public interface OrderService {
    public List<Order> getAll();
    public Order getById(long id);
    public Order insert(Order order);
}
