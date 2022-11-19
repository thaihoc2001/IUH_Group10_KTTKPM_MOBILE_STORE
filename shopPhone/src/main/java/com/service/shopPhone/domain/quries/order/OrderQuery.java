package com.service.shopPhone.domain.quries.order;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.service.shopPhone.entity.OrderEntity;
import com.service.shopPhone.repository.OrderRepository;

@Service
public class OrderQuery {
    
    private final OrderRepository orderRepository;

    public OrderQuery(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<OrderEntity> getAll(String searchText, String status, Pageable pageable) {
        return orderRepository.searchOrder(searchText, status, pageable);
    }

    public Optional<OrderEntity> getById(UUID orderId) {
        return orderRepository.findById(orderId);
    }
}
