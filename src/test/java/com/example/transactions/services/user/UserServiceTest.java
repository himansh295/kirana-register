package com.example.transactions.services.user;

import com.example.transactions.dao.IStoreUserMappingRepository;
import com.example.transactions.dao.IUserRepository;
import com.example.transactions.entity.Store;
import com.example.transactions.entity.StoreUserMapping;
import com.example.transactions.entity.User;
import com.example.transactions.enums.Role;
import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.request.StoreRequest;
import com.example.transactions.object.response.UserRO;
import com.example.transactions.services.store.IStoreService;
import com.example.transactions.services.user.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IStoreService storeService;

    @Mock
    private IStoreUserMappingRepository storeUserMappingRepository;


    @Test
    void getUserByIdTest() throws ValidationException {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setName("John Doe");
        user.setRole(Role.EMPLOYEE);

        Store store = new Store();
        store.setId(1L);
        store.setName("Test Store");
        store.setOwnerId(userId);
        store.setLocation("Test Location");

        StoreUserMapping storeUserMapping = new StoreUserMapping();
        storeUserMapping.setUserId(userId);
        storeUserMapping.setStore(store);

        List<StoreUserMapping> storeUserMappings = new ArrayList<>();
        storeUserMappings.add(storeUserMapping);

        user.setStoreUserMappings(storeUserMappings);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserRO result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getRole(), result.getRole());

        StoreRequest storeRequest = result.getStore();
        assertNotNull(storeRequest);
        assertEquals(store.getId(), storeRequest.getId());
        assertEquals(store.getName(), storeRequest.getName());
        assertEquals(store.getOwnerId(), storeRequest.getOwnerId());
        assertEquals(store.getName(), storeRequest.getOwnerName());
        assertEquals(store.getLocation(), storeRequest.getLocation());
    }

    @Test(expected = ValidationException.class)
    void getUserByIdValidationExceptionTest() throws ValidationException {
        Long invalidUserId = 2L;
        when(userRepository.findById(invalidUserId)).thenReturn(Optional.empty());
        userService.getUserById(invalidUserId);
    }
}
