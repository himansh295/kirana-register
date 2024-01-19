package com.example.transactions.object.response;

import com.example.transactions.enums.Role;
import com.example.transactions.object.request.StoreRequest;
import lombok.Data;

@Data
public class UserRO {
    private Long id;
    private String name;
    private StoreRequest store;
    private Role role;
}
