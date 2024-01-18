package services.user.impl;

import dao.IStoreRepository;
import dao.IStoreUserMappingRepository;
import dao.IUserRepository;
import entity.Product;
import entity.Store;
import entity.StoreUserMapping;
import entity.User;
import enums.Role;
import enums.Status;
import exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import object.request.StoreRequest;
import object.request.UserRequest;
import object.response.UserRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import services.user.IUserService;
import util.UserUtil;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IStoreRepository storeRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IStoreUserMappingRepository storeUserMappingRepository;

    private void checkAndUpdateRequest(UserRequest userRequest) throws ValidationException {
        if(StringUtils.isEmpty(userRequest.getName())) {
            throw new ValidationException("User name is required");
        }

        if(null == userRequest.getRole()) {
            throw new ValidationException("User role is required");
        }

        if(null == userRequest.getStore()) {
            throw new ValidationException("User Store details are required");
        }

        if(null == userRequest.getStore().getId()) {
            throw new ValidationException("User Store id is required");
        }

        Optional<Store> storeOptional = storeRepository.findById(userRequest.getStore().getId());
        if(!storeOptional.isPresent()) {
            throw new ValidationException("Invalid store");
        }

        userRequest.getStore().setId(storeOptional.get().getId());
        userRequest.getStore().setName(storeOptional.get().getName());
        userRequest.getStore().setLocation(storeOptional.get().getLocation());
        userRequest.getStore().setOwnerId(storeOptional.get().getOwnerId());
        userRequest.getStore().setOwnerName(storeOptional.get().getUser().getName());

        if(userRequest.getRole() == Role.OWNER) {
            throw new ValidationException("Only role with Employee can be registered");
        }
    }

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

    @Override
    public UserRO getUserById(Long userId) throws ValidationException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()) {
            throw new ValidationException("Invalid User Id");
        }

        User user = userOptional.get();
        UserRO userRO = new UserRO();
        userRO.setId(userId);
        userRO.setRole(user.getRole());
        userRO.setName(user.getName());

        Store store = user.getStore();
        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setId(store.getId());
        storeRequest.setName(store.getName());
        storeRequest.setOwnerId(store.getOwnerId());
        storeRequest.setLocation(store.getLocation());

        userRO.setStore(storeRequest);

        return userRO;
    }
}
