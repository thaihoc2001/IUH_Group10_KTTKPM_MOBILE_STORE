package com.service.shopPhone.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.order.CreateOrderRequestModel;
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
}
