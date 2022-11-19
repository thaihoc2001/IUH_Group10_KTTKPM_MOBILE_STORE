package com.service.shopPhone.domain.services.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.order.AddOrderCommand;
import com.service.shopPhone.domain.exceptions.OrderNotExistsException;
import com.service.shopPhone.domain.models.order.AddOrderCommandInputModel;
import com.service.shopPhone.domain.quries.cart.CartDetailQuery;
import com.service.shopPhone.domain.quries.image.ImageQuery;
import com.service.shopPhone.domain.quries.order.OrderQuery;
import com.service.shopPhone.entity.CartDetailEntity;
import com.service.shopPhone.entity.ImageEntity;
import com.service.shopPhone.entity.OrderEntity;
import com.service.shopPhone.models.cart.CartDetailResponseModel;
import com.service.shopPhone.models.order.GetAllOrderResponseModel;
import com.service.shopPhone.models.order.OrderDetailResponseModel;
import com.service.shopPhone.models.order.OrderResponseModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderDomainService {
    private final AddOrderCommand addOrderCommand;
    private final OrderQuery orderQuery;
    private final ImageQuery imageQuery;
    private final CartDetailQuery cartDetailQuery;

    @Autowired
    public OrderDomainService(AddOrderCommand addOrderCommand, OrderQuery orderQuery, ImageQuery imageQuery, CartDetailQuery cartDetailQuery) {
        this.addOrderCommand = addOrderCommand;
        this.orderQuery = orderQuery;
        this.imageQuery = imageQuery;
        this.cartDetailQuery = cartDetailQuery;
    }

    public boolean createOrder(String username, Double coupon, String typePayment, String note) {
        return addOrderCommand.execute(
            AddOrderCommandInputModel.builder().username(username).coupon(coupon).typePayment(typePayment).note(note).build()
        ).isSuccess();
    }

    public GetAllOrderResponseModel getAll(String searchText, String status, Pageable pageable) {
        Page<OrderEntity> orders = orderQuery.getAll(searchText, status, pageable);
        List<OrderResponseModel> items = new ArrayList<>();
        for (OrderEntity order: orders.getContent()) {
            OrderResponseModel model = OrderResponseModel.builder()
                .id(order.getId())
                .status(order.getStatus())
                .note(order.getNote())
                .totalPrice(order.getTotalPrice())
                .createdAt(order.getCreatedAt())
                .username(order.getCart().getUser().getUsername())
                .build();
            items.add(model);
        }
        GetAllOrderResponseModel responseModel = new GetAllOrderResponseModel();
        responseModel.setItems(items);
        responseModel.setCurrentPage(orders.getPageable().getPageNumber());
        responseModel.setPageSize(orders.getPageable().getPageSize());
        responseModel.setTotalItems(orders.getTotalElements());
        responseModel.setTotalPages(orders.getTotalPages());
        return responseModel;
    }

    public OrderDetailResponseModel getDetailOrder(UUID orderId) {
        Optional<OrderEntity> optional = orderQuery.getById(orderId);
        if (!optional.isPresent()) {
            throw new OrderNotExistsException();
        }
        List<CartDetailEntity> cartDetails = cartDetailQuery.getByCartId(optional.get().getCart().getId());
        List<CartDetailResponseModel> cartDetailResponseModels = new ArrayList<>();
        for (CartDetailEntity cartDetail : cartDetails) {
            ImageEntity image = imageQuery.getImageMainByProduct(cartDetail.getProduct().getId());
            CartDetailResponseModel result = CartDetailResponseModel.builder()
                .id(cartDetail.getId())
                .quantity(cartDetail.getQuantity())
                .productName(cartDetail.getProduct().getName())
                .productPrice(cartDetail.getProduct().getPrice())
                .productImage(image.getUrl())
                .productId(cartDetail.getProduct().getId())
                .build();
            cartDetailResponseModels.add(result);
        }
        OrderEntity order = optional.get();
        return OrderDetailResponseModel.builder()
            .id(order.getId())
            .status(order.getStatus())
            .note(order.getNote())
            .totalPrice(order.getTotalPrice())
            .createdAt(order.getCreatedAt())
            .username(order.getCart().getUser().getUsername())
            .details(cartDetailResponseModels)
            .build();
    }
}
