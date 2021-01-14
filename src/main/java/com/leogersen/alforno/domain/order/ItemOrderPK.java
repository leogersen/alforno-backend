package com.leogersen.alforno.domain.order;

import com.sun.istack.*;
import lombok.*;

import javax.persistence.*;
import java.io.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@SuppressWarnings("serial")
public class ItemOrderPK implements Serializable {

    @NotNull
    @ManyToOne
    private Order order;

    @NotNull
    private Integer command;


}
