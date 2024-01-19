package com.example.transactions.controller;

import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.ControllerBaseResponse;
import com.example.transactions.util.ControllerResponseUtil;
import lombok.extern.slf4j.Slf4j;
import com.example.transactions.object.request.UserRequest;
import com.example.transactions.object.response.UserRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.transactions.services.user.IUserService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/addNewUser")
    public ResponseEntity<ControllerBaseResponse> addNewUser(@RequestBody UserRequest userRequest) throws ValidationException {
        log.info("Api: /v1/user/addNewUser , newUserRequest : {}",userRequest);
        return ResponseEntity.ok(ControllerResponseUtil.getSuccessResponse(userService.addNewUser(userRequest)));
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<ControllerBaseResponse> getUserById(@PathVariable Long userId) throws Exception {
        log.info("Api: /v1/user/getUserById/ , userId : {}",userId);
        return ResponseEntity.ok(ControllerResponseUtil.getSuccessResponse(userService.getUserById(userId)));
    }
}
