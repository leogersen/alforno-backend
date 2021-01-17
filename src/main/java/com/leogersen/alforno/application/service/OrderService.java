package com.leogersen.alforno.application.service;

import com.leogersen.alforno.domain.order.*;
import com.leogersen.alforno.domain.payment.*;
import com.leogersen.alforno.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;
import org.springframework.web.client.*;

import java.time.*;
import java.util.*;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemOrderRepository itemOrderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${alforno.4pay.url}")
    private String _4PayUrl;

    @Value("${alforno.4pay.token}")
    private String _4PayToken;

    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = PaymentException.class)
    public Order createAndPay(Cart cart, String numCard) throws PaymentException {

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

        CardData cardData = new CardData();
        cardData.setNumCard(numCard);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Token", _4PayToken);

        HttpEntity<CardData> requestEntity = new HttpEntity<>(cardData,headers);

        RestTemplate restTemplate = new RestTemplate();


        Map<String, String> response;
        try {
           response = restTemplate.postForObject(_4PayUrl, requestEntity, Map.class);
        } catch (Exception e) {
            throw new PaymentException("Erro no servidor do pagamento");
           }

        PaymentStatus paymentStatus = PaymentStatus.valueOf(response.get("status"));

        if(paymentStatus != PaymentStatus.Authorized) {
            throw new PaymentException(paymentStatus.getDescription());
        }

        Payment payment = new Payment();
        payment.setData(LocalDateTime.now());
        payment.setOrder(order);
        payment.defineNumAndFlag(numCard);
        paymentRepository.save(payment);

        

        return order;

    }


}