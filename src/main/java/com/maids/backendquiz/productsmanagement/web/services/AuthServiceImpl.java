package com.maids.backendquiz.productsmanagement.web.services;

import com.maids.backendquiz.productsmanagement.domain.entities.Users;
import com.maids.backendquiz.productsmanagement.domain.enums.Role;
import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.UserRegisterDto;
import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.LoginDto;
import com.maids.backendquiz.productsmanagement.domain.repository.UserRepository;
import com.maids.backendquiz.productsmanagement.helpers.ResponseUtils;
import com.maids.backendquiz.productsmanagement.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<APIResponse<DataResponse>> register(UserRegisterDto userRegisterDto) {
        try {
            DataResponse loginResponse = null;
            Users users = userRepository.findUsersByEmail(userRegisterDto.getEmail());
            if (Objects.nonNull(users)){
                loginResponse = new DataResponse();
                loginResponse.setMessage("User Already Exist");
                return ResponseUtils.responseType(loginResponse, HttpStatus.CONFLICT);
            }



            Users newUser = new Users();
            newUser.setEmail(userRegisterDto.getEmail());
            newUser.setName(userRegisterDto.getName());
            newUser.setRole(Role.valueOf(userRegisterDto.getRole()));
            newUser.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
            userRepository.save(newUser);

            loginResponse = new DataResponse();
            loginResponse.setMessage("Register Successfully");
            return  ResponseUtils.responseType(loginResponse, HttpStatus.OK);
        } catch (Exception e) {
            DataResponse errorResponse = new DataResponse();
            errorResponse.setMessage(e.getMessage());
            return ResponseUtils.responseType(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public  ResponseEntity<APIResponse<DataResponse>> login(LoginDto loginDto) {

        DataResponse loginResponse=null;
        Users user = userRepository.findUsersByEmail(loginDto.getEmail());
        if(!Objects.nonNull(user)){
            loginResponse= new DataResponse();
            loginResponse.setMessage("No user found");
            return ResponseUtils.responseType(loginResponse,HttpStatus.NO_CONTENT);
        }
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
                String jwtToken = jwtService.generateToken(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                loginResponse= new DataResponse();
                loginResponse.setEmail(user.getEmail());
                loginResponse.setRole(user.getRole().getName());
                loginResponse.setJwt(jwtToken);
                return  ResponseUtils.responseType(loginResponse,HttpStatus.OK);

            }else{
                loginResponse= new DataResponse();
                loginResponse.setMessage("Password does not match");
                return ResponseUtils.responseType(loginResponse,HttpStatus.CONFLICT);
            }

    }
}
