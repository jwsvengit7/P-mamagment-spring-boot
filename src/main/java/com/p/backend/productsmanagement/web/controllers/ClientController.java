package com.p.backend.productsmanagement.web.controllers;


import com.p.backend.productsmanagement.domain.model.response.APIResponse;
import com.p.backend.productsmanagement.domain.model.response.DataResponse;
import com.p.backend.productsmanagement.domain.model.resquest.ClientRequest;
import com.p.backend.productsmanagement.web.services.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/client")
public class ClientController {
    private final ClientService clientService;
    @PostMapping("/create-client")
    public ResponseEntity<APIResponse<DataResponse>> createClient(@RequestBody @Valid ClientRequest clientRequest){
        return clientService.createClient(clientRequest);
    }
    @GetMapping("/fetch-client/{id}")
    public ResponseEntity<APIResponse<DataResponse>> fetchClient(@PathVariable("id") Long id){
        return clientService.fetchClient(id);
    }
    @DeleteMapping("/delete-client/{id}")
    public ResponseEntity<APIResponse<DataResponse>> deleteClient(@PathVariable("id") Long id){
        return clientService.deleteClient(id);
    }

    @PutMapping("/update-client/{id}")
    public ResponseEntity<APIResponse<DataResponse>> update(@RequestBody @Valid ClientRequest clientRequest, @PathVariable("id") Long id){
        return clientService.updateClient(clientRequest,id);
    }
}
