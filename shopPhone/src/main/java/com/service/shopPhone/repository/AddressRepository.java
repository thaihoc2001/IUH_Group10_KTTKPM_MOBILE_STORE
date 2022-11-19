package com.service.shopPhone.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.shopPhone.entity.AddressEntity;
import com.service.shopPhone.entity.UserEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, UUID>{
    AddressEntity findByUserAndIsActive(UserEntity user, Boolean isActive);


    Page<AddressEntity> searchAddress(String searchText, Pageable pageable);

    AddressEntity getAddressByUserId(UserEntity user, Boolean isActive);

    Optional<AddressEntity> findByCity(String city);

    Optional<AddressEntity> findByState(String state);
}
