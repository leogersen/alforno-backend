package com.leogersen.alforno.infrastruture.web.controller;


import com.leogersen.alforno.application.service.*;
import com.leogersen.alforno.domain.order.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.*;

@Controller
@RequestMapping(path = "/client/payment")
@SessionAttributes("cart")
public class PaymentController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/pay")
    public String pay(
            @RequestParam("numCart") String numCart,
            @ModelAttribute("cart") Cart cart,
            SessionStatus sessionStatus,
            Model model) {

        Order order = orderService.createAndPay(cart, numCart);
        sessionStatus.setComplete();

        return "redirect:/client/order/view?orderId=" + order.getId();


    }


}
