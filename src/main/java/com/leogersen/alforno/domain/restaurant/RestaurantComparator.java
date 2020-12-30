package com.leogersen.alforno.domain.restaurant;

import com.leogersen.alforno.domain.restaurant.SearchFilter.*;

import java.util.Comparator;

public class RestaurantComparator implements Comparator<Restaurant> {

    private SearchFilter filter;
    private String cep;

    public RestaurantComparator(SearchFilter filter, String cep) {
        this.filter = filter;
        this.cep = cep;
    }

    @Override
    public int compare(Restaurant r1, Restaurant r2) {

        int result;

        if (filter.getOrder() == Order.Tax) {
            result = r1.getDeliveryTax().compareTo(r2.getDeliveryTax());


        } else if (filter.getOrder() == Order.Time) {
            result = r1.getDeliveryTime().compareTo(r2.getDeliveryTime());
        }else {
            throw new IllegalStateException("O valor da ordena��o " + filter.getOrder() + " n�o � v�lido");
        }

        return filter.isAsc() ? result - 1 : result;

    }
}
