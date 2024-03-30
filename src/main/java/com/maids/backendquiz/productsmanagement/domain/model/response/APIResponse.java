package com.maids.backendquiz.productsmanagement.domain.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class APIResponse<T> {
    private T data;
    private String message;
    private int status;

    public APIResponse(T data){
        this.data=data;
        this.message="Api Received";
        this.status=001;
    }
    public APIResponse(T data,int status){
        this.data=data;
        this.message="Api Received";
        this.status=status;
    }
    public APIResponse(T data,String message,int status){
        this.data=data;
        this.message=message;
        this.status=status;
    }

}
