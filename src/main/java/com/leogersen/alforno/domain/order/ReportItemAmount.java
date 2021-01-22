package com.leogersen.alforno.domain.order;

import lombok.*;
import java.math.*;

@Getter
@AllArgsConstructor
public class ReportItemAmount {

    private String name;
    private Long quantity;
    private BigDecimal amount;


}
