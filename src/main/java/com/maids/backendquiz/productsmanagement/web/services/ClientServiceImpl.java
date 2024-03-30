package com.maids.backendquiz.productsmanagement.web.services;

import com.maids.backendquiz.productsmanagement.domain.entities.Client;
import com.maids.backendquiz.productsmanagement.domain.entities.Users;
import com.maids.backendquiz.productsmanagement.domain.enums.Role;
import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.ClientRequest;
import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import com.maids.backendquiz.productsmanagement.domain.repository.ClientRepository;
import com.maids.backendquiz.productsmanagement.domain.repository.UserRepository;
import com.maids.backendquiz.productsmanagement.helpers.GetPrincipalUser;
import com.maids.backendquiz.productsmanagement.helpers.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl  implements ClientService{
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    @Override
    public ResponseEntity<APIResponse<DataResponse>> createClient(ClientRequest clientRequest) {
        DataResponse dataResponse = getData();

        String principalUser = GetPrincipalUser.principalUser();
        Users users = userRepository.findUsersByEmail(principalUser);
        if(!Objects.nonNull(users)){
            dataResponse.setMessage("User not found");
            return  ResponseUtils.responseType(dataResponse,HttpStatus.NO_CONTENT);
        }
        if(users.getRole().equals(Role.ADMIN)) {
         Client client=   clientRepository.findClientByEmail(clientRequest.getEmail());
         if(Objects.nonNull(client)) {
             dataResponse.setMessage("Email Have already been taken");
             return  ResponseUtils.responseType(dataResponse,HttpStatus.CONFLICT);
         }else{
             Client newClient = new Client();
             newClient.setAddress(clientRequest.getAddress());
             newClient.setEmail(clientRequest.getEmail());
             newClient.setName(clientRequest.getName());
             newClient.setMobile(clientRequest.getMobile());
             newClient.setLastName(clientRequest.getLastName());
             clientRepository.save(newClient);
             dataResponse.setMessage("Client have been created");
             return ResponseUtils.responseType(
                     dataResponse,HttpStatus.OK);
         }
        }else{

            dataResponse.setMessage("You are not authorized to create client");
            return ResponseUtils.responseType(dataResponse,HttpStatus.UNAUTHORIZED);
        }

    }


    @Override
    public ResponseEntity<APIResponse<DataResponse>> fetchClient(Long id) {
        DataResponse dataResponse = getData();

        String principalUser = GetPrincipalUser.principalUser();
        Users users = userRepository.findUsersByEmail(principalUser);
        if (!Objects.nonNull(users)) {
            dataResponse.setMessage("User not found");
            return ResponseUtils.responseType(dataResponse, HttpStatus.NO_CONTENT);
        }
        if (users.getRole().equals(Role.ADMIN)) {
            Client client = clientRepository.findById(id).orElse(null);
            if (Objects.nonNull(client)) {
                dataResponse.setId(client.getId());
                dataResponse.setLastName(client.getLastName());
                dataResponse.setEmail(client.getEmail());
                dataResponse.setAddress(client.getAddress());
                dataResponse.setName(client.getName());
                dataResponse.setMessage("User Details");
                return ResponseUtils.responseType(dataResponse, HttpStatus.OK);
            }else {
                dataResponse.setMessage("No client found");
                return ResponseUtils.responseType(dataResponse, HttpStatus.NOT_FOUND);
            }

        } else {
            dataResponse.setMessage("You are not authorized to create client");
            return ResponseUtils.responseType(dataResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<APIResponse<DataResponse>> deleteClient(Long id) {
        DataResponse dataResponse = getData();

        String principalUser = GetPrincipalUser.principalUser();
        Users users = userRepository.findUsersByEmail(principalUser);
        if (!Objects.nonNull(users)) {
            dataResponse.setMessage("User not found");
            return ResponseUtils.responseType(dataResponse, HttpStatus.NO_CONTENT);
        }
        if (users.getRole().equals(Role.ADMIN)) {
            Client client = clientRepository.findById(id).orElse(null);
            if (Objects.nonNull(client)) {
                clientRepository.delete(client);
                dataResponse.setMessage("Delete Details");
                return ResponseUtils.responseType(dataResponse, HttpStatus.OK);
            }else {
                dataResponse.setMessage("No client found");
                return ResponseUtils.responseType(dataResponse, HttpStatus.NOT_FOUND);
            }

        } else {
            dataResponse.setMessage("You are not authorized to create client");
            return ResponseUtils.responseType(dataResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<APIResponse<DataResponse>> updateClient(ClientRequest clientRequest,Long clientId) {
        DataResponse dataResponse = getData();

        String principalUser = GetPrincipalUser.principalUser();
        Users users = userRepository.findUsersByEmail(principalUser);
        if (!Objects.nonNull(users)) {
            dataResponse.setMessage("User not found");
            return ResponseUtils.responseType(dataResponse, HttpStatus.NO_CONTENT);
        }
        if (users.getRole().equals(Role.ADMIN)) {
            Client client = clientRepository.findById(clientId).orElse(null);
            if (Objects.nonNull(client)) {
                dataResponse.setId(client.getId());
                client.setLastName(client.getLastName());
                client.setEmail(client.getEmail());
                client.setAddress(client.getAddress());
                client.setName(client.getName());
                dataResponse.setMessage("Client Have been updated");
                return ResponseUtils.responseType(dataResponse, HttpStatus.OK);
            }else {
                dataResponse.setMessage("No client found");
                return ResponseUtils.responseType(dataResponse, HttpStatus.NOT_FOUND);
            }

        } else {
            dataResponse.setMessage("You are not authorized to create client");
            return ResponseUtils.responseType(dataResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    private DataResponse getData(){
        return new DataResponse();
    }
}
