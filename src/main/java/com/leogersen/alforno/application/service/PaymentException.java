package com.leogersen.alforno.application.service;

@SuppressWarnings("serial")
public class PaymentException extends Exception {

    public PaymentException() {
        super();
    }

    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentException(Throwable cause) {
        super(cause);
    }

}
