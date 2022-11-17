package com.service.shopPhone.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.product.AddProductRequestModel;
import com.service.shopPhone.models.product.AddProductResponseModel;
import com.service.shopPhone.models.product.DeleteProductRequestModel;
import com.service.shopPhone.models.product.GetAllProductRequestModel;
import com.service.shopPhone.models.product.GetDetailProductResponseModel;
import com.service.shopPhone.models.product.GetListProductResponseModel;
import com.service.shopPhone.service.product.IProductService;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    // @GetMapping("")
    // public ResponseEntity<?> createOrder(){
    //     String username = SecurityContextHolder.getContext().getAuthentication().getName();
    //     // User user = userService.findUserByUsername(username);
    //     // Boolean res = orderService.createOrder(order, user.getId());
    //     Map<String, String> res = new HashMap<>();
    //     res.put("user", username);
    //     return ResponseEntity.ok().body(res);
    // }

    @PostMapping("")
    public Response<AddProductResponseModel> createProduct(@RequestBody AddProductRequestModel requestModel) {
        return productService.addProduct(requestModel);
    }

    @PutMapping("/{productId}")
    public Response<StatusResponseModel> updateProduct(@RequestBody AddProductRequestModel requestModel, @PathVariable String productId){

        return productService.updateProduct(requestModel, UUID.fromString(productId));
    }

    @GetMapping("")
    public Response<GetListProductResponseModel> getAll(
        @RequestParam(required = false) String searchText,
        @ParameterObject Pageable pageable
    ) {
        GetAllProductRequestModel requestModel = new GetAllProductRequestModel(searchText, pageable);
        return productService.getAll(requestModel);
    }

    @DeleteMapping("")
    public Response<StatusResponseModel> deleteProducts(@RequestBody DeleteProductRequestModel requestModel){
        return productService.deleteProduct(requestModel);
    }

    @GetMapping("/{id}")
    public Response<GetDetailProductResponseModel> getDetail(@PathVariable UUID id) {
        return productService.getDetailProduct(id);
    }
}
