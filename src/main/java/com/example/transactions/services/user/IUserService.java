package com.example.transactions.services.user;

import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.request.UserRequest;
import com.example.transactions.object.response.UserRO;

public interface IUserService {
    UserRO addNewUser (UserRequest userRequest) throws ValidationException;

    UserRO getUserById(Long userId) throws ValidationException;
}
