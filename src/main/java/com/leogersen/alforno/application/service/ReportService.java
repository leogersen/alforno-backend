package com.leogersen.alforno.application.service;

import com.leogersen.alforno.domain.order.*;
import com.leogersen.alforno.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

@Service
public class ReportService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> orderList(Integer restaurantId, ReportOrderFilter filter){

        Integer orderId = filter.getOrderId();

        if(orderId != null) {
            Order order = orderRepository.findByIdAndRestaurant_Id(orderId, restaurantId);
            return List.of(order);
        }
        LocalDate initialDate = filter.getInitialDate();
        LocalDate finalDate = filter.getFinalDate();

        if(initialDate == null){
            return orderRepository.findByRestaurant_IdOrderByDataDesc(restaurantId);
        }

        if(finalDate == null){
            finalDate = LocalDate.now();
        }

        return orderRepository.findByDateInterval(restaurantId, initialDate.atStartOfDay(), finalDate.atTime(23, 59, 59));

    }
	

	}
