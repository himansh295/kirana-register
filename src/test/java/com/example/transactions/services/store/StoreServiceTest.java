package com.example.transactions.services.store;

import com.example.transactions.dao.IStoreRepository;
import com.example.transactions.entity.Store;
import com.example.transactions.entity.User;
import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.request.StoreRequest;
import com.example.transactions.services.store.impl.StoreServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class StoreServiceTest {

    @InjectMocks
    private StoreServiceImpl storeService;

    @Mock
    private IStoreRepository storeRepository;

    @Test
    void getStoreDetailsUsingIdTest() throws ValidationException {
        Long storeId = 1L;
        Store store = new Store();
        store.setId(storeId);
        store.setName("Test Store");
        store.setLocation("Test Location");
        store.setOwnerId(101L);

        User user = new User();
        user.setId(101L);
        user.setName("John Doe");
        store.setUser(user);

        when(storeRepository.findById(storeId)).thenReturn(Optional.of(store));

        StoreRequest result = storeService.getStoreDetailsUsingId(storeId);

        assertNotNull(result);
        assertEquals(storeId, result.getId());
        assertEquals(store.getName(), result.getName());
        assertEquals(store.getLocation(), result.getLocation());
        assertEquals(store.getOwnerId(), result.getOwnerId());
        assertEquals(user.getName(), result.getOwnerName());
    }

    @Test(expected = ValidationException.class)
    void getStoreDetailsUsingIdValidationException() throws ValidationException {
        Long invalidStoreId = 2L;
        when(storeRepository.findById(invalidStoreId)).thenReturn(Optional.empty());
        storeService.getStoreDetailsUsingId(invalidStoreId);
    }
}
