package com.maids.backendquiz.productsmanagement.domain.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class DataResponse {
    private String email;
    private double price;
    private String description;
    private String category;
    private Integer  initialQuantity;
    private String localDateTime;
    private String address;
    private String message;
    private Long id;
    private String name;
    private String lastName;
    private String mobile;
    private String role;
    private String jwt;
    public Double getPrice() {
        return price == 0.00 ? null : price;
    }
}
