package com.service.shopPhone.controller;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.address.AddAddressRequestModel;
import com.service.shopPhone.models.address.AddAddressResponseModel;
import com.service.shopPhone.models.address.GetAllAddressRequestModel;
import com.service.shopPhone.models.address.GetAllAddressResponseModel;
import com.service.shopPhone.service.address.IAddressService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final IAddressService addressService;

    @Autowired
    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("")
    public Response<GetAllAddressResponseModel> getAll(
            @RequestParam(required = false) String searchText,
            @ParameterObject Pageable pageable
    ) {
        GetAllAddressRequestModel requestModel = new GetAllAddressRequestModel(searchText, pageable);
        return addressService.getAll(requestModel);

    }
    @PostMapping("")
    public Response<AddAddressResponseModel> addAddress(@Valid @RequestBody AddAddressRequestModel requestModel){
        return addressService.addAddress(requestModel);
    }


}
