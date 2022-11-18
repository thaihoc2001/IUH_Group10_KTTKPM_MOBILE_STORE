package com.service.shopPhone.domain.services.address;

import com.service.shopPhone.domain.commands.address.AddAddressCommand;
import com.service.shopPhone.domain.exceptions.BrandNameExistedException;
import com.service.shopPhone.domain.models.address.AddAddressCommandInputModel;
import com.service.shopPhone.domain.models.address.AddAddressCommandResultModel;
import com.service.shopPhone.domain.models.address.GetListAddressQueryModel;
import com.service.shopPhone.domain.quries.address.AddressQuery;

import com.service.shopPhone.entity.AddressEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AddressDomainService {
    private final AddressQuery addressQuery;
    private final AddAddressCommand addAddressCommand;

    public AddressDomainService(AddressQuery addressQuery, AddAddressCommand addAddressCommand) {
        this.addressQuery = addressQuery;
        this.addAddressCommand = addAddressCommand;
    }


    public Page<AddressEntity> getAll(GetListAddressQueryModel model) {
        return addressQuery.getAll(model.getSearchText(), model.getPageable());
    }

    public UUID addAddress(String address, String states, String detail, String code) {
        Optional<AddressEntity> add = addressQuery.findByCity(address);
        Optional<AddressEntity> state = addressQuery.findByState(states);
        if (add.isPresent() && state.isPresent()) {
            throw new BrandNameExistedException();
        }
        AddAddressCommandResultModel result = addAddressCommand.execute(
                AddAddressCommandInputModel.builder().city(address).state(states).detail(detail).code(code).build()
        );
        return result.getIdAddress();
    }
}
