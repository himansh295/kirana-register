package com.example.transactions.util;

import com.example.transactions.entity.User;
import com.example.transactions.enums.Role;
import com.example.transactions.enums.Status;
import com.example.transactions.object.request.UserRequest;
import com.example.transactions.object.response.UserRO;

public class UserUtil {

    private UserUtil() {

    }

    public static User getUserUsingUserRequest(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setRole(userRequest.getRole());
        user.setStatus(Status.ACTIVE);
        return user;
    }

    public static UserRO getUserROUsingUser(User user) {
        UserRO userRO = new UserRO();
        userRO.setId(user.getId());
        userRO.setName(user.getName());
        userRO.setRole(user.getRole());
        return userRO;
    }
}
