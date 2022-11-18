package com.service.shopPhone.service.address;

import com.service.shopPhone.domain.models.address.GetListAddressQueryModel;
import com.service.shopPhone.domain.services.address.AddressDomainService;

import com.service.shopPhone.entity.AddressEntity;
import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.address.*;
import com.service.shopPhone.utility.RequestCorrelation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AddressService implements IAddressService {
    private final ModelMapper modelMapper;
    private final AddressDomainService addressDomainService;
    @Autowired
    public AddressService(ModelMapper modelMapper, AddressDomainService addressDomainService) {
        this.modelMapper = modelMapper;
        this.addressDomainService = addressDomainService;
    }

    @Override
    public Response<GetAllAddressResponseModel> getAll(GetAllAddressRequestModel model) {
        GetListAddressQueryModel queryModel = modelMapper.map(model, GetListAddressQueryModel.class);
        Page<AddressEntity> result = addressDomainService.getAll(queryModel);

        List<AddressEntity> addresses = result.getContent();
        List<AddressResponseModel> items = new ArrayList<>();
        for (AddressEntity address : addresses) {
            AddressResponseModel item = modelMapper.map(addresses, AddressResponseModel.class);
            items.add(item);
        }
        GetAllAddressResponseModel data = new GetAllAddressResponseModel();
        data.setCurrentPage(result.getPageable().getPageNumber());
        data.setPageSize(result.getPageable().getPageSize());
        data.setItems(items);
        data.setTotalItems(result.getTotalElements());
        data.setTotalPages(result.getTotalPages());
        return Response.<GetAllAddressResponseModel>builder()
                .id(RequestCorrelation.getRequestId())
                .data(data)
                .build();
    }

    @Override
    public Response<AddAddressResponseModel> addAddress(AddAddressRequestModel model) {
        UUID result = addressDomainService.addAddress(model.getCity(),model.getState(),model.getDetail(),model.getCode());
        return Response.<AddAddressResponseModel>builder()
                .id(RequestCorrelation.getRequestId())
                .data(AddAddressResponseModel.builder().idAddress(result).build())
                .build();
    }
}
