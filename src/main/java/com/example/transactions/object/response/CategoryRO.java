package com.example.transactions.object.response;

import com.example.transactions.enums.Status;
import lombok.Data;

@Data
public class CategoryRO {
    private Long id;
    private String name;
    private Status status;
}
