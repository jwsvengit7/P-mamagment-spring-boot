package com.maids.backendquiz.productsmanagement.web.services;


import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.ClientRequest;
import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface ClientService {

     ResponseEntity<APIResponse<DataResponse>> createClient(ClientRequest clientRequest);

     ResponseEntity<APIResponse<DataResponse>> fetchClient(Long id);
     ResponseEntity<APIResponse<DataResponse>> deleteClient(Long id);
     ResponseEntity<APIResponse<DataResponse>> updateClient(ClientRequest clientRequest,Long clientId);

}
