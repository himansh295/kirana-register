package controller;

import dao.IUserRepository;
import exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import object.request.UserRequest;
import object.response.UserRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.user.IUserService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/addNewUser")
    public UserRO addNewUser(@RequestBody UserRequest userRequest) throws ValidationException {
        return userService.addNewUser(userRequest);
    }

}
