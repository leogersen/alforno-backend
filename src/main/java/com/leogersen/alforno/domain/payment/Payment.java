package com.leogersen.alforno.domain.payment;

import com.leogersen.alforno.domain.order.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.*;
import java.time.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "payment")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payment implements Serializable {

    @Id
    private Integer id;

    @NotNull
    @OneToOne
    @MapsId
    private Order order;

    @NotNull
    private LocalDateTime data;

    @Column(name = "num_final_card")
    @NotNull
    @Size(min = 4, max = 4)
    private String numCardFinal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private FlagCard flagCard;

    public void defineNumAndFlag(String numCard){
        numCardFinal = numCard.substring(12);
        flagCard = getFlagCard(numCard);
    }

    private FlagCard getFlagCard(String numCard){
        if(numCard.startsWith("1111")){
            return FlagCard.ELO;
        }
        if(numCard.startsWith("0000")){
            return FlagCard.AMEX;
        }
        if(numCard.startsWith("2222")){
            return FlagCard.MASTERCARD;
        }
        return FlagCard.VISA;
    }
}
