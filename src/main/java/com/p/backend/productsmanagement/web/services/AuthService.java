package com.p.backend.productsmanagement.web.services;

import com.p.backend.productsmanagement.domain.model.response.DataResponse;
import com.p.backend.productsmanagement.domain.model.resquest.UserRegisterDto;
import com.p.backend.productsmanagement.domain.model.response.APIResponse;
import com.p.backend.productsmanagement.domain.model.resquest.LoginDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<APIResponse<DataResponse>> register(UserRegisterDto userRegisterDto);
    ResponseEntity<APIResponse<DataResponse>> login(LoginDto loginDto);
}
