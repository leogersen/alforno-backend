package com.leogersen.alforno.domain.order;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Data
public class ReportOrderFilter {

    private Integer orderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate finalDate;

}
