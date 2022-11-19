package com.service.shopPhone.controller;

import java.security.Principal;
import java.util.UUID;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.order.CreateOrderRequestModel;
import com.service.shopPhone.models.order.GetAllOrderResponseModel;
import com.service.shopPhone.models.order.OrderDetailResponseModel;
import com.service.shopPhone.service.order.IOrderService;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public Response<StatusResponseModel> createOrder(Principal principal,@RequestBody CreateOrderRequestModel requestModel) {
        return orderService.createOrder(principal.getName(), requestModel);
    }

    @GetMapping("")
    public Response<GetAllOrderResponseModel> getAll(
        @RequestParam(required = false) String searchText,
        @RequestParam(required = false) String status,
        @ParameterObject Pageable pageable
    ) {
        return orderService.getAll(searchText, status, pageable);
    }

    @GetMapping("/user/me")
    public Response<GetAllOrderResponseModel> getAll(
        @RequestParam(required = false) String status,
        @ParameterObject Pageable pageable,
        Principal principal
    ) {
        return orderService.getAll(principal.getName(), status, pageable);
    }


    @GetMapping("/{id}")
    public Response<OrderDetailResponseModel> getDetail(@Valid @PathVariable UUID id) {
        return orderService.getDetail(id);
    }
}
