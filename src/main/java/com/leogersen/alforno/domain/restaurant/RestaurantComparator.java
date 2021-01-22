package com.leogersen.alforno.domain.restaurant;

import com.leogersen.alforno.domain.restaurant.SearchFilter.*;

import java.util.Comparator;

public class RestaurantComparator implements Comparator<Restaurant> {

    private SearchFilter filter;
    @SuppressWarnings("unused")
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
            throw new IllegalStateException("O valor da ordenação " + filter.getOrder() + " não é válido");
        }

        return filter.isAsc() ? result : -result;

    }
}
