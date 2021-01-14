package com.leogersen.alforno.domain.order;

import com.leogersen.alforno.domain.client.*;
import com.leogersen.alforno.domain.restaurant.Restaurant;
import com.sun.istack.*;
import lombok.*;

import javax.persistence.*;
import java.io.*;
import java.math.*;
import java.time.*;
import java.util.*;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    public enum Status {
        Production(1, "Em produção", false),
        Delivery(2, "Saiu para entrega", false),
        Completed(3, "Concluido", true);

        int sequence;
        String description;
        boolean lastOne;

        Status(int sequence, String description, boolean lastOne) {
            this.description = description;
            this.sequence = sequence;
            this.lastOne = lastOne;

        }

        public int getSequence() {
            return sequence;
        }

        public String getDescription() {
            return description;
        }

        public boolean getLastOne() {
            return lastOne;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDateTime data;

    @NotNull
    private Status status;

    @NotNull
    @ManyToOne
    private Client client;
    
    @NotNull
    @ManyToOne
    private Restaurant restaurant;

    @NotNull
    private BigDecimal subtotal;

    @NotNull
    @Column(name = "delivery_tax")
    private BigDecimal deliveryTax;

    @NotNull
    private BigDecimal total;

    @OneToMany(mappedBy = "id.order")
    private Set<ItemOrder> items;
}
