package com.leogersen.alforno.domain.order;

@SuppressWarnings("serial")
public class RestaurantDifferentException extends Exception {


    public RestaurantDifferentException() {
        super();
    }

    public RestaurantDifferentException(String message) {
        super(message);
    }
}
