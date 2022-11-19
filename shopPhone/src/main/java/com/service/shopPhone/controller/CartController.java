package com.service.shopPhone.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.cart.AddToCartRequestModel;
import com.service.shopPhone.models.cart.CartResponseModel;
import com.service.shopPhone.models.cart.RemoveToCartRequestModel;
import com.service.shopPhone.service.cart.ICartService;

@CrossOrigin
@RestController
@RequestMapping("/api/carts")
public class CartController {
    
    private final ICartService cartService;

    @Autowired
    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("")
    public Response<StatusResponseModel> createCart(Principal principal) {
        return cartService.createCart(principal.getName());
    }

    @PostMapping("/cart-detail/add")
    public Response<StatusResponseModel> addToCart(Principal principal, @RequestBody AddToCartRequestModel requestModel) {
        return cartService.addToCart(principal.getName(), requestModel);
    }

    @PostMapping("/cart-detail/remove")
    public Response<StatusResponseModel> addToCart(@RequestBody RemoveToCartRequestModel requestModel) {
        return cartService.removeToCart(requestModel.getCartDetailId());
    }

    @GetMapping("")
    public Response<CartResponseModel> getCartByUser(Principal principal){
        return cartService.getCartByUser(principal.getName());
    }
}
