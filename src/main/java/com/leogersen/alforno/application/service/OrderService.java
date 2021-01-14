package com.leogersen.alforno.application.service;

import com.leogersen.alforno.domain.order.*;
import com.leogersen.alforno.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemOrderRepository itemOrderRepository;


    public Order createAndPay(Cart cart, String numCart) {

        Order order = new Order();
        order.setData(LocalDateTime.now());
        order.setClient(SecurityUtils.loggedClient());
        order.setRestaurant(cart.getRestaurant());
        order.setStatus(Order.Status.Production);
        order.setDeliveryTax(cart.getRestaurant().getDeliveryTax());
        order.setSubtotal(cart.getTotalPrice(false));
        order.setTotal(cart.getTotalPrice(true));

        order = orderRepository.save(order);

        int sequence = 1;

        for(ItemOrder itemOrder : cart.getItems()) {
            itemOrder.setId(new ItemOrderPK(order, sequence++));
            itemOrderRepository.save(itemOrder);
        }

        return order;

    }


}