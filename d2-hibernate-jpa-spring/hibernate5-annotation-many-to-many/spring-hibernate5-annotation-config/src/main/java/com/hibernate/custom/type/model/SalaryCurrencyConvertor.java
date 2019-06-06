package com.hibernate.custom.type.model;

public class SalaryCurrencyConvertor {

    public static Long convert(Long amount, String oldCurr, String newCurr){
        if (newCurr.equalsIgnoreCase(oldCurr))
            return amount;

        return convertTo();
    }

    private static Long convertTo() {
        return 10L;
    }
}
