package com.myhangars.controller;

import com.myhangars.order.builder.OrderBuilder;
import com.myhangars.order.builder.OrderDtoBuilder;
import com.myhangars.order.dto.OrderDto;
import com.myhangars.order.model.Order;
import com.myhangars.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping(value = "/api")
public class CommerceController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {

        List<OrderDto> orderDtos = new ArrayList<OrderDto>();

        this.orderService.getAll().forEach(order -> {
            orderDtos.add(new OrderDtoBuilder(order).getOrderDto());
        });

        return new ResponseEntity<List>(
                orderDtos,
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody @Valid OrderDto orderDto) {

        Order order = this.orderService
                .insert(new OrderBuilder(orderDto).getOrder());

        return new ResponseEntity<OrderDto>(
                new OrderDtoBuilder(order).getOrderDto(),
                HttpStatus.CREATED
        );
    }

}
