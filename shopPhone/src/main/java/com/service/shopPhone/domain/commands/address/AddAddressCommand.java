package com.service.shopPhone.domain.commands.address;

import com.service.shopPhone.domain.commands.ICommand;
import com.service.shopPhone.domain.models.address.AddAddressCommandInputModel;
import com.service.shopPhone.domain.models.address.AddAddressCommandResultModel;
import com.service.shopPhone.domain.models.brand.AddBrandCommandInputModel;
import com.service.shopPhone.domain.models.brand.AddBrandCommandResultModel;
import com.service.shopPhone.entity.AddressEntity;
import com.service.shopPhone.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class AddAddressCommand implements ICommand<AddAddressCommandInputModel, AddAddressCommandResultModel> {
    private final AddressRepository addressRepository;

    public AddAddressCommand(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }
    @Override
    public AddAddressCommandResultModel execute(AddAddressCommandInputModel input){
        try {
            AddressEntity address = new AddressEntity();
            address.setId(UUID.randomUUID());
            address.setState(input.getState());
            address.setCity(input.getCity());
            address.setDetail(input.getDetail());
            address.setCode(input.getCode());
            address.setIsActive(true);
            address = addressRepository.save(address);
            return AddAddressCommandResultModel.builder().idAddress(address.getId()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
