package com.example.transactions.services.store.impl;

import com.example.transactions.dao.IStoreRepository;
import com.example.transactions.entity.Store;
import com.example.transactions.entity.User;
import com.example.transactions.exception.ValidationException;
import com.example.transactions.services.store.IStoreService;
import lombok.extern.slf4j.Slf4j;
import com.example.transactions.object.request.StoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private IStoreRepository storeRepository;

    @Transactional
    @Override
    public StoreRequest getStoreDetailsUsingId(Long storeId) throws ValidationException {
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        if(!storeOptional.isPresent()) {
            throw new ValidationException("Invalid Store Id","400");
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
