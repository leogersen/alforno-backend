package com.leogersen.alforno.domain.order;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.client.id =?1 ORDER BY o.data DESC")
    public List<Order> listOfOrders(Integer clientId);

    public List<Order> findByRestaurant_IdOrderByDataDesc(Integer restaurantId);
}
