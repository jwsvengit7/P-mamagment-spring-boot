package com.maids.backendquiz.productsmanagement.web.services;

import com.maids.backendquiz.productsmanagement.domain.entities.Product;
import com.maids.backendquiz.productsmanagement.domain.entities.Users;
import com.maids.backendquiz.productsmanagement.domain.enums.Role;
import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.ProductRequest;
import com.maids.backendquiz.productsmanagement.domain.repository.ProductRepository;
import com.maids.backendquiz.productsmanagement.domain.repository.UserRepository;
import com.maids.backendquiz.productsmanagement.helpers.GetPrincipalUser;
import com.maids.backendquiz.productsmanagement.helpers.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public ResponseEntity<APIResponse<DataResponse>> createProduct(ProductRequest productRequest) {
        DataResponse dataResponse = new DataResponse();
        String adminUser = GetPrincipalUser.principalUser();
        Users users = userRepository.findUsersByEmail(adminUser);
        if (!Objects.nonNull(users)) {
            dataResponse.setMessage("User not found");
            return ResponseUtils.responseType(dataResponse, HttpStatus.NO_CONTENT);
        }
        if (users.getRole().equals(Role.ADMIN)) {
            Product existingProduct = productRepository.findByName(productRequest.getName());

            if (existingProduct != null) {
                dataResponse.setMessage("Product with this name already exists");
                return ResponseUtils.responseType(dataResponse, HttpStatus.CONFLICT);
            }
            Product newProduct = new Product();
            newProduct.setName(productRequest.getName());
            newProduct.setPrice(productRequest.getPrice());
            newProduct.setCreationDate(LocalDateTime.now());
            newProduct.setCategory(productRequest.getCategory());
            newProduct.setDescription(productRequest.getDescription());
            newProduct.setAvailableQuantity(productRequest.getInitialQuantity());
            productRepository.save(newProduct);
            dataResponse.setPrice(newProduct.getPrice());
            dataResponse.setMessage("Product created successfully");
            return ResponseUtils.responseType(dataResponse, HttpStatus.CREATED);
        }
        dataResponse.setMessage("You are not authorized to create Product");
        return ResponseUtils.responseType(dataResponse, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<APIResponse<DataResponse>> fetchProduct(Long productId) {

        String adminUser = GetPrincipalUser.principalUser();

        Users users = userRepository.findUsersByEmail(adminUser);
        DataResponse dataResponse = new DataResponse();
        if (!Objects.nonNull(users)) {

            dataResponse.setMessage("User not found");
            return ResponseUtils.responseType(dataResponse, HttpStatus.NO_CONTENT);
        }
        if (users.getRole().equals(Role.ADMIN)) {
            Product product = productRepository.findById(productId).orElse(null);
            if (Objects.nonNull(product)) {
                dataResponse.setId(product.getId());
                dataResponse.setCategory(product.getCategory());
                dataResponse.setDescription(product.getDescription());
                dataResponse.setPrice(product.getPrice());
                dataResponse.setInitialQuantity(product.getAvailableQuantity());
                dataResponse.setLocalDateTime(product.getCreationDate().format(DateTimeFormatter.ofPattern("y/m/d")));
                dataResponse.setName(product.getName());
                dataResponse.setMessage("Product Details");
                return ResponseUtils.responseType(dataResponse, HttpStatus.OK);
            }else {
                dataResponse.setMessage("No Product found");
                return ResponseUtils.responseType(dataResponse, HttpStatus.NOT_FOUND);
            }

        } else {
            dataResponse.setMessage("You are not authorized to get Product");
            return ResponseUtils.responseType(dataResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<APIResponse<DataResponse>> deleteProduct(Long id) {


        String adminUser = GetPrincipalUser.principalUser();

        Users users = userRepository.findUsersByEmail(adminUser);
        DataResponse dataResponse = new DataResponse();
        if (!Objects.nonNull(users)) {
            dataResponse.setMessage("User not found");
            return ResponseUtils.responseType(dataResponse, HttpStatus.NO_CONTENT);
        }
        if (users.getRole().equals(Role.ADMIN)) {
            Product product = productRepository.findById(id).orElse(null);
            if (Objects.nonNull(product)) {
                productRepository.delete(product);
                dataResponse.setMessage("Delete Product");
                return ResponseUtils.responseType(dataResponse, HttpStatus.OK);
            }else {
                dataResponse.setMessage("No Product found");
                return ResponseUtils.responseType(dataResponse, HttpStatus.NOT_FOUND);
            }

        } else {
            dataResponse.setMessage("You are not authorized to create product");
            return ResponseUtils.responseType(dataResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<APIResponse<DataResponse>> updateProduct(ProductRequest productRequest, Long productId) {
        DataResponse dataResponse = ResponseUtils.getData();
        String adminUser = GetPrincipalUser.principalUser();
        Users users = userRepository.findUsersByEmail(adminUser);
        if (!Objects.nonNull(users)) {
            dataResponse.setMessage("User not found");
            return ResponseUtils.responseType(dataResponse, HttpStatus.NO_CONTENT);
        }
        if (users.getRole().equals(Role.ADMIN)) {
            Product product = productRepository.findById(productId).orElse(null);
            if (Objects.nonNull(product)) {
                dataResponse.setId(product.getId());
                product.setAvailableQuantity(productRequest.getInitialQuantity());
                product.setCategory(productRequest.getCategory());
                product.setDescription(productRequest.getDescription());
                product.setCreationDate(LocalDateTime.now());
                dataResponse.setMessage("Product Have been updated");
                return ResponseUtils.responseType(dataResponse, HttpStatus.OK);
            }else {
                dataResponse.setMessage("No Product found");
                return ResponseUtils.responseType(dataResponse, HttpStatus.NOT_FOUND);
            }

        } else {
            dataResponse.setMessage("You are not authorized to update Product");
            return ResponseUtils.responseType(dataResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}
