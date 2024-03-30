package com.p.backend.productsmanagement.web.services;


import com.p.backend.productsmanagement.domain.model.response.DataResponse;
import com.p.backend.productsmanagement.domain.model.resquest.ClientRequest;
import com.p.backend.productsmanagement.domain.model.response.APIResponse;
import org.springframework.http.ResponseEntity;

public interface ClientService {

     ResponseEntity<APIResponse<DataResponse>> createClient(ClientRequest clientRequest);

     ResponseEntity<APIResponse<DataResponse>> fetchClient(Long id);
     ResponseEntity<APIResponse<DataResponse>> deleteClient(Long id);
     ResponseEntity<APIResponse<DataResponse>> updateClient(ClientRequest clientRequest,Long clientId);

}
