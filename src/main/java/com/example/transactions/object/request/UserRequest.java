package com.example.transactions.object.request;

import com.example.transactions.enums.Role;
import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private Role role;
    private Long storeId;
    private Long onboardingHelperUserId;
    private StoreRequest store;
}
