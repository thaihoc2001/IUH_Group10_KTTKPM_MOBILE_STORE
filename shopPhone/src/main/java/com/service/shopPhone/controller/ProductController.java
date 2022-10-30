package com.service.shopPhone.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {
    

    @GetMapping("")
    public ResponseEntity<?> createOrder(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // User user = userService.findUserByUsername(username);
        // Boolean res = orderService.createOrder(order, user.getId());
        Map<String, String> res = new HashMap<>();
        res.put("user", username);
        return ResponseEntity.ok().body(res);
    }
}
