package com.p.backend.productsmanagement.domain.model.response;

import com.p.backend.productsmanagement.domain.entities.Product;
import com.p.backend.productsmanagement.domain.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesReport {
    private int totalSales;
    private double totalRevenue;
    private List<Product> topSellingProducts;
    private List<Users> topPerformingSellers;

}
