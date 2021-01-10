package com.leogersen.alforno.domain.order;

import com.leogersen.alforno.domain.restaurant.*;
import lombok.*;

import java.io.*;
import java.math.*;
import java.util.*;

@SuppressWarnings("serial")
@Getter
public class Cart implements Serializable {

    private List<ItemOrder> items = new ArrayList<>();
    private Restaurant restaurant;

    public void addItem(ItemMenu itemMenu, Integer quantity, String obs) throws RestaurantDifferentException {

        if (items.size() == 0) {
            restaurant = itemMenu.getRestaurant();

        } else if (!itemMenu.getRestaurant().getId().equals(restaurant.getId())) {
            throw new RestaurantDifferentException();
        }

        if (!exists(itemMenu)) {
            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setItemMenu(itemMenu);
            itemOrder.setQuantity(quantity);
            itemOrder.setObs(obs);
            itemOrder.setPrice(itemMenu.getPrice());
            items.add(itemOrder);

        }
    }

    public void addItem(ItemOrder itemOrder) {
        try {
            addItem(itemOrder.getItemMenu(), itemOrder.getQuantity(), itemOrder.getObs());
        } catch (RestaurantDifferentException e) {

        }

    }

    public void removeItem(ItemMenu itemMenu) {
        for (Iterator<ItemOrder> iterator = items.iterator(); iterator.hasNext(); ) {
            ItemOrder itemOrder = iterator.next();
            if (itemOrder.getItemMenu().getId().equals(itemMenu.getId())) {
                iterator.remove();
                break;
            }
        }
        if (items.size() == 0) {
            restaurant = null;
        }
    }

    private boolean exists(ItemMenu itemMenu) {
        for (ItemOrder itemOrder : items) {
            if (itemOrder.getItemMenu().getId().equals(itemMenu.getId())) {
                return true;
            }
        }
        return false;

    }

    public BigDecimal getTotalPrice(boolean addDeliveryTax) {

        BigDecimal sum = BigDecimal.ZERO;

        for (ItemOrder item : items) {
            sum = sum.add(item.getCalculatedPrice());
        }

        if (addDeliveryTax) {
            sum = sum.add(restaurant.getDeliveryTax());


        }
        return sum;

    }

    public void clear() {
        items.clear();
        restaurant = null;
    }

    public boolean empty() {
        return items.size() == 0;
    }

}
