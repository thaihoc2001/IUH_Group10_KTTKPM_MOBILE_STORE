package com.service.shopPhone.service.order;

import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.services.order.OrderDomainService;
import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.order.CreateOrderRequestModel;
import com.service.shopPhone.utility.RequestCorrelation;

@Service
public class OrderService implements IOrderService{

    private final OrderDomainService orderDomainService;

    public OrderService(OrderDomainService orderDomainService) {
        this.orderDomainService = orderDomainService;
    }

    @Override
    public Response<StatusResponseModel> createOrder(String username, CreateOrderRequestModel requestModel) {
        boolean result = orderDomainService.createOrder(username, requestModel.getCoupon(), requestModel.getTypePayment(), requestModel.getNote());
        return Response.<StatusResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(StatusResponseModel.builder().success(result).build())
            .build();
    }
    
}
