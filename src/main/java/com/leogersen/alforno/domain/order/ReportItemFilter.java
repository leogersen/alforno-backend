package com.leogersen.alforno.domain.order;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Data
public class ReportItemFilter {

    private Integer itemId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate finalDate;

}
