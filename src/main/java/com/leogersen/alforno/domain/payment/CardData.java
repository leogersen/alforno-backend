package com.leogersen.alforno.domain.payment;

import javax.validation.constraints.*;

public class CardData {


    @Pattern(regexp = "\\d{16}", message = "O número do cartão é inválido")
    private String numCard;

    public String getNumCard() {
        return numCard;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }
}
