package com.service.shopPhone.domain.quries.address;

import com.service.shopPhone.entity.AddressEntity;
import com.service.shopPhone.repository.AddressRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressQuery {
    private final AddressRepository addressRepository;

    public AddressQuery(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Page<AddressEntity> getAll(String searchText, Pageable pageable) {
        return addressRepository.searchAddress(searchText, pageable);
    }

    public Optional<AddressEntity> findByCity(String city){
        return addressRepository.findByCity(city);
    };

    public Optional<AddressEntity> findByState(String state){
        return addressRepository.findByCity(state);
    };
}
