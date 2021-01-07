package com.leogersen.alforno.domain.order;

import com.leogersen.alforno.domain.restaurant.*;
import lombok.*;

import java.math.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemOrder {

    @EqualsAndHashCode.Include
    private Integer id;

    private ItemMenu itemMenu;

    private Integer quantity;

    private String obs;

    private BigDecimal price;



}
