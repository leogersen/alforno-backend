package com.leogersen.alforno.domain.order;

import com.leogersen.alforno.domain.restaurant.*;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.*;
import java.math.*;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_order")
public class ItemOrder implements Serializable {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ItemOrderPK id;

    @NotNull
    @ManyToOne
    private ItemMenu itemMenu;

    @NotNull
    private Integer quantity;

    @Size(max = 50)
    private String obs;

    @NotNull
    private BigDecimal price;

    public BigDecimal getCalculatedPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }



}
