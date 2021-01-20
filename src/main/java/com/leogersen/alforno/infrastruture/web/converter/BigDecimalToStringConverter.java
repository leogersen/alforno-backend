package com.leogersen.alforno.infrastruture.web.converter;

import com.leogersen.alforno.util.*;
import org.springframework.core.convert.converter.*;
import org.springframework.stereotype.*;

import java.math.*;

@Component
public class BigDecimalToStringConverter implements Converter<BigDecimal, String> {


    @Override
    public String convert(BigDecimal source) {
        return FormatUtils.formatCurrency(source);
    }
}
