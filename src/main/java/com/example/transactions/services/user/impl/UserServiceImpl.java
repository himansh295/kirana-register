package com.example.transactions.services.user.impl;

import com.example.transactions.dao.IStoreUserMappingRepository;
import com.example.transactions.dao.IUserRepository;
import com.example.transactions.entity.Store;
import com.example.transactions.entity.StoreUserMapping;
import com.example.transactions.entity.User;
import com.example.transactions.enums.Role;
import com.example.transactions.enums.Status;
import com.example.transactions.exception.ValidationException;
import com.example.transactions.services.store.IStoreService;
import lombok.extern.slf4j.Slf4j;
import com.example.transactions.object.request.StoreRequest;
import com.example.transactions.object.request.UserRequest;
import com.example.transactions.object.response.UserRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.example.transactions.services.user.IUserService;
import com.example.transactions.util.UserUtil;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IStoreService storeService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IStoreUserMappingRepository storeUserMappingRepository;

    private void checkAndUpdateRequest(UserRequest userRequest) throws ValidationException {
        if(StringUtils.isEmpty(userRequest.getName())) {
            throw new ValidationException("User name is required","400");
        }

        if(null == userRequest.getRole()) {
            throw new ValidationException("User role is required","400");
        }

        if(null == userRequest.getStoreId()) {
            throw new ValidationException("User Store id is required","400");
        }

        if(null == userRequest.getOnboardingHelperUserId()) {
            throw new ValidationException("Onboarding helper userId is required","400");
        }

        UserRO userRO = getUserById(userRequest.getOnboardingHelperUserId());
        if(userRO.getRole() != Role.OWNER) {
            throw new ValidationException("Only owner can onboard new user or new Owner","400");
        }

        if(userRequest.getRole() == Role.EMPLOYEE && !userRequest.getStoreId().equals(userRO.getStore().getId())) {
            throw new ValidationException("A new employee can only be onboarded by its store owner","400");
        }

        if(userRequest.getRole() == Role.OWNER) {
            throw new ValidationException("An Owner cannot be onboarded","400");
        }

        StoreRequest storeRequest = storeService.getStoreDetailsUsingId(userRequest.getStoreId());
        userRequest.setStore(storeRequest);
    }

    @Transactional
    @Override
    public UserRO addNewUser(UserRequest userRequest) throws ValidationException {
        checkAndUpdateRequest(userRequest);

        User user = UserUtil.getUserUsingUserRequest(userRequest);
        userRepository.save(user);

        StoreUserMapping storeUserMapping = new StoreUserMapping();
        storeUserMapping.setUserId(user.getId());
        storeUserMapping.setStoreId(userRequest.getStore().getId());
        storeUserMapping.setStatus(Status.ACTIVE);
        storeUserMappingRepository.save(storeUserMapping);

        UserRO userRO = UserUtil.getUserROUsingUser(user);
        userRO.setStore(userRequest.getStore());

        return userRO;
    }

    @Transactional
    @Override
    public UserRO getUserById(Long userId) throws ValidationException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new ValidationException("Invalid User Id","400");
        }

        User user = userOptional.get();
        UserRO userRO = new UserRO();
        userRO.setId(userId);
        userRO.setRole(user.getRole());
        userRO.setName(user.getName());

        List<StoreUserMapping> storeUserMappings = user.getStoreUserMappings();
        for(StoreUserMapping mapping : storeUserMappings) {
            if(mapping.getUserId().equals(userId)) {
                Store store = mapping.getStore();
                StoreRequest storeRequest = new StoreRequest();
                storeRequest.setId(store.getId());
                storeRequest.setName(store.getName());
                storeRequest.setOwnerId(store.getOwnerId());
                storeRequest.setOwnerName(store.getName());
                storeRequest.setLocation(store.getLocation());
                userRO.setStore(storeRequest);
                break;
            }
        }

        return userRO;
    }
}
