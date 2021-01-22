package com.leogersen.alforno.infrastruture.web.controller;

import com.leogersen.alforno.domain.order.*;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/client/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(path = "/view")
    public String viewOrder(
            @RequestParam("orderId") Integer orderId,
            Model model)

            {

        Order order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);
        model.addAttribute("order", order);

        return "client-order";
    }
}
