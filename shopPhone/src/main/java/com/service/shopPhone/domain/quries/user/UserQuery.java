package com.service.shopPhone.domain.quries.user;

import org.springframework.stereotype.Service;

import com.service.shopPhone.entity.UserEntity;
import com.service.shopPhone.repository.UserRepository;

import java.util.UUID;

@Service
public class UserQuery {
    private final UserRepository userRepository;

    public UserQuery(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity getUserByUserId(UUID userid){
        return userRepository.findByUserid(userid);
    }
}
