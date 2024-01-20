package com.example.transactions.controller;

import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.ControllerBaseResponse;
import com.example.transactions.object.request.UserRequest;
import com.example.transactions.object.response.UserRO;
import com.example.transactions.services.user.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private IUserService userService;

    @Test
    public void testAddNewUser() throws ValidationException {
        UserRequest userRequest = new UserRequest();
        UserRO userRO = new UserRO();
        userRO.setId(11L);
        when(userService.addNewUser(userRequest)).thenReturn(new UserRO());

        ResponseEntity<ControllerBaseResponse> responseEntity = userController.addNewUser(userRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());
        Assert.assertNotNull( responseEntity.getBody().getData());
    }

    @Test
    public void testAddNewUserException() throws ValidationException {
        UserRequest userRequest = new UserRequest();

        doThrow(new ValidationException("Validation failed")).when(userService).addNewUser(userRequest);

        ResponseEntity<ControllerBaseResponse> responseEntity = userController.addNewUser(userRequest);

        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("Validation failed", responseEntity.getBody().getResult().getResultMessage());
    }
}
