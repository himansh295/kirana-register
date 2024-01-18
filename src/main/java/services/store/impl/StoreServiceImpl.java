package services.store.impl;

import dao.IStoreRepository;
import entity.Store;
import entity.User;
import exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import object.request.StoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.store.IStoreService;

import java.util.Optional;

@Slf4j
@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private IStoreRepository storeRepository;

    @Override
    public StoreRequest getStoreDetailsUsingId(Long storeId) throws ValidationException {
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        if(!storeOptional.isPresent()) {
            throw new ValidationException("Invalid Store Id");
        }

        Store store = storeOptional.get();
        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setId(storeId);
        storeRequest.setLocation(store.getLocation());
        storeRequest.setName(store.getName());
        storeRequest.setOwnerId(store.getOwnerId());
        User user = store.getUser();
        storeRequest.setOwnerName(user.getName());

        return storeRequest;
    }
}
