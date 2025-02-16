package com.leogersen.alforno.infrastruture.web.controller;

import com.leogersen.alforno.domain.order.*;
import com.leogersen.alforno.domain.restaurant.*;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.*;

@Controller
@RequestMapping(path = "/client/cart")
@SessionAttributes("cart")
public class CartController {

    @Autowired
    private ItemMenuRepository itemMenuRepository;

    @Autowired
    private OrderRepository orderRepository;

    @ModelAttribute("cart")
    public Cart cart() {

        return new Cart();
    }


    @GetMapping(path = "/view")
    public String viewCart() {
        return "client-cart";

    }

    @GetMapping(path = "/add")
    public String addItem(
            @RequestParam("itemId") Integer itemId,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("obs") String obs,
            @ModelAttribute("cart") Cart cart,
            Model model) {

        ItemMenu itemMenu = itemMenuRepository.findById(itemId).orElseThrow(NoSuchElementException::new);

        try {
            cart.addItem(itemMenu, quantity, obs);
        } catch (RestaurantDifferentException e) {
            model.addAttribute("msg", "Não é possível misturar comidas de restaurantes diferentes");
        }

        return "client-cart";
    }

    @GetMapping(path = "/remove")
    public String removeItem(
            @RequestParam("itemId") Integer itemId,
            @ModelAttribute("cart") Cart cart,
            SessionStatus sessionStatus,
            Model model) {

        ItemMenu itemMenu = itemMenuRepository.findById(itemId).orElseThrow(NoSuchElementException::new);

        cart.removeItem(itemMenu);

        if (cart.empty()){
            sessionStatus.setComplete();

        }

        return "client-cart";
    }

    @GetMapping(path = "/redoCart")
    public String redoCart(
            @RequestParam("orderId") Integer orderId,
            @ModelAttribute("cart") Cart cart,
            Model model){

        Order order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

        cart.clear();

        for (ItemOrder itemOrder : order.getItems()) {
            cart.addItem(itemOrder);
        }

        return"client-cart";
    }



}
