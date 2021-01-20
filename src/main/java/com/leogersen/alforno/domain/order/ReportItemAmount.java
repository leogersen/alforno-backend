package com.leogersen.alforno.domain.order;

import lombok.*;
import org.springframework.format.annotation.*;

import java.math.*;
import java.time.*;

@Getter
@AllArgsConstructor
public class ReportItemAmount {

    private String name;
    private Long quantity;
    private BigDecimal amount;


}
