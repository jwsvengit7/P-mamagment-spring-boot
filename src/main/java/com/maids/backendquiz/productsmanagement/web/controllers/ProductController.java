package com.maids.backendquiz.productsmanagement.web.controllers;


import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.ProductRequest;

import jakarta.validation.Valid;

import com.maids.backendquiz.productsmanagement.web.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping("/create-product")
    public ResponseEntity<APIResponse<DataResponse>> createPorduct(@RequestBody @Valid ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }
    @GetMapping("/fetch-product/{id}")
    public ResponseEntity<APIResponse<DataResponse>> fetchClient(@PathVariable("id") Long id){
        return productService.fetchProduct(id);
    }
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<APIResponse<DataResponse>> deleteClient(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
    @PutMapping("/update-product/{id}")
    public ResponseEntity<APIResponse<DataResponse>> update(@RequestBody @Valid ProductRequest productRequest,@PathVariable("id") Long id){
        return productService.updateProduct(productRequest,id);
    }
}
