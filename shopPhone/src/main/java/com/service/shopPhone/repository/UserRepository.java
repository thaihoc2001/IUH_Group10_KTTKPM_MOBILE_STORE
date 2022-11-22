package com.service.shopPhone.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.shopPhone.entity.RoleEntity;
import com.service.shopPhone.entity.UserEntity;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    public UserEntity findByUserid(UUID userId);
    public UserEntity findByEmail(String email);
    public UserEntity findByUsername(String username);
    public List<UserEntity> findAllByRole(RoleEntity role);
}
