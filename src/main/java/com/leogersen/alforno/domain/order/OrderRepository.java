package com.leogersen.alforno.domain.order;

import org.springframework.data.jpa.repository.*;

import java.time.*;
import java.util.*;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.client.id =?1 ORDER BY o.data DESC")
    public List<Order> listOfOrders(Integer clientId);

    public List<Order> findByRestaurant_IdOrderByDataDesc(Integer restaurantId);

    public Order findByIdAndRestaurant_Id(Integer orderId, Integer restaurantId);

    @Query("SELECT o FROM Order o WHERE o.restaurant.id = ?1 AND o.data BETWEEN ?2 AND ?3")
    public List<Order> findByDateInterval(Integer restaurantId, LocalDate initialDate, LocalDate finalDate);

}
