package com.maids.backendquiz.productsmanagement.web.services;

import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.UserRegisterDto;
import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.LoginDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<APIResponse<DataResponse>> register(UserRegisterDto userRegisterDto);
    ResponseEntity<APIResponse<DataResponse>> login(LoginDto loginDto);
}
