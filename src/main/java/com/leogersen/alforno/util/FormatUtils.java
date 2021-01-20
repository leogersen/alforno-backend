package com.leogersen.alforno.util;

import java.math.*;
import java.text.*;
import java.util.*;

public class FormatUtils{

    private static final Locale LOCALE_BRAZIL = new Locale("pt", "BR");

    public static NumberFormat newCurrencyFormat() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(LOCALE_BRAZIL);

        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setGroupingUsed(false);

        return nf;

    }

    public static String formatCurrency(BigDecimal number) {
        if(number == null) {

            return null;
        }

        return newCurrencyFormat().format(number);

    }
}
