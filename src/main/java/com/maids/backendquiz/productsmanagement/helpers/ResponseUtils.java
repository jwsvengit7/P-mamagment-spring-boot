package com.maids.backendquiz.productsmanagement.helpers;

import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    public static ResponseEntity<APIResponse<DataResponse>> responseType(DataResponse message, HttpStatus httpStatus){
        return new ResponseEntity<>(
                new APIResponse<>(
                        message,
                        httpStatus.value()), httpStatus);
    }
    public static DataResponse getData(){
        return new DataResponse();
    }
}
