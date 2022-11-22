package com.service.shopPhone.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.shopPhone.entity.AddressEntity;
import com.service.shopPhone.entity.UserEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, UUID>{
    AddressEntity findByUserAndIsActive(UserEntity user, Boolean isActive);
}
