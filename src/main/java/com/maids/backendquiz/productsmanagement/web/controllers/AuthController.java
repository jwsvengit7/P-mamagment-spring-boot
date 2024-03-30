package com.maids.backendquiz.productsmanagement.web.controllers;

import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.UserRegisterDto;
import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.LoginDto;
import com.maids.backendquiz.productsmanagement.web.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@Validated
public class AuthController {
    private final AuthService authService;
    @PostMapping("/create-account")
    public ResponseEntity<APIResponse<DataResponse>> register( @RequestBody @Valid UserRegisterDto userRegisterDto){
        return authService.register(userRegisterDto);
    }
    @PostMapping("/login")
    public ResponseEntity<APIResponse<DataResponse>> login(@Valid @RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }
}
