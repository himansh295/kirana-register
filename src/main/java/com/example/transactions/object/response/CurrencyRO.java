package com.example.transactions.object.response;

import lombok.Data;

import java.util.Map;

@Data
public class CurrencyRO {
    private String base;
    private Map<String,Double> rates;
}
