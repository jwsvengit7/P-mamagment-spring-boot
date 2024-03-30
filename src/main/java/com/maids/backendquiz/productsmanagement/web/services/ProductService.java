package com.maids.backendquiz.productsmanagement.web.services;

import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.ProductRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<APIResponse<DataResponse>> createProduct(ProductRequest productRequest);
    ResponseEntity<APIResponse<DataResponse>> deleteProduct(Long id);
    ResponseEntity<APIResponse<DataResponse>> updateProduct(ProductRequest productRequest, Long productId);
    ResponseEntity<APIResponse<DataResponse>> fetchProduct(Long productId);
}
