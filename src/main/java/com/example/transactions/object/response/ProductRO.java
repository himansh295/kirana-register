package com.example.transactions.object.response;

import com.example.transactions.enums.Currency;
import com.example.transactions.enums.Status;
import lombok.Data;

@Data
public class ProductRO {
    private Long id;
    private String name;
    private CategoryRO categoryRO;
    private Status status;
    private Double price;
    private Currency currency;
}
