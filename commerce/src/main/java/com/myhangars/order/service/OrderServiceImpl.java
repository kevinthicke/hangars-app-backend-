package com.myhangars.order.service;

import com.myhangars.exception.GenericException;
import com.myhangars.exception.OrderException;
import com.myhangars.order.dao.OrderDao;
import com.myhangars.order.model.Order;
import com.myhangars.product_order.service.Product_OrderService;
import com.myhangars.products_hangar.service.Products_HangarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private Product_OrderService product_orderService;

    @Autowired
    private Products_HangarService products_hangarService;

    @Override
    public List<Order> getAll() {
        return this.orderDao.findAll();
    }

    private void checkIfEachProductExists(Order order) {

        order.getProduct_orders().forEach(product_order -> {

            long hangarId = product_order.getHangar_id();
            long productId = product_order.getProduct_id();

            try {

                this.products_hangarService.getHangarProduct(hangarId, productId);

            } catch(Exception exception) {
                throw new com.myhangars
                        .products_hangar
                        .exception
                        .GenericException
                        .NotFound(hangarId, productId);
            }
        });
    }

    private void checkIfExistsEnougthStock(Order order) {

        order.getProduct_orders().forEach(product_order -> {

            long hangarId = product_order.getHangar_id();
            long productId = product_order.getProduct_id();

            int stockQuantity = this.products_hangarService
                    .getHangarProduct(hangarId, productId)
                    .getQuantity();

            int demandQuantity = product_order.getQuantity();

            boolean existsEnoughtStock = stockQuantity >= product_order.getQuantity();

            if (!existsEnoughtStock)
                throw new OrderException.NotEnoughtStock(demandQuantity, stockQuantity);
        });

    }


    private void decreaseProducts_HangarQuantity(Order order) {

        order.getProduct_orders().forEach(product_order -> {

            long hangarId = product_order.getHangar_id();
            long productId = product_order.getProduct_id();

            int stockQuantity = this.products_hangarService
                    .getHangarProduct(hangarId, productId)
                    .getQuantity();

            try {

                this.products_hangarService.setQuantityByHangarIdAndProductId(
                        hangarId,
                        productId,
                        stockQuantity - product_order.getQuantity()
                );

            } catch (Exception exception) {
                throw new OrderException.UnableModifyQuantityProducts_Hangar(hangarId, productId);
            }

        });
    }

    @Override
    public Order getById(long id) {
        return this.orderDao
                .findById(id)
                .orElseThrow(GenericException.NotFound::new);
    }

    @Override
    public Order insert(Order order) {

        this.checkIfEachProductExists(order);
        this.checkIfExistsEnougthStock(order);

        this.decreaseProducts_HangarQuantity(order);

        return this.orderDao.save(order);
    }
}
