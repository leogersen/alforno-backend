package com.leogersen.alforno.infrastruture.web.converter;

import com.leogersen.alforno.util.*;
import org.springframework.core.convert.converter.*;
import org.springframework.stereotype.*;

import java.math.*;

@Component
public class StringToBigDecimalConverter implements Converter<String,BigDecimal> {


    @Override
    public BigDecimal convert(String source) {

        if (StringUtils.isEmpty(source)) {
            return null;

        }

        return BigDecimal.valueOf(Double.parseDouble(source.replace(",", ".").trim()));
    }
}