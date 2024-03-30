package com.p.backend.productsmanagement.web.services;

import com.p.backend.productsmanagement.domain.model.response.APIResponse;
import com.p.backend.productsmanagement.domain.model.response.DataResponse;
import com.p.backend.productsmanagement.domain.model.response.SalesReport;
import com.p.backend.productsmanagement.domain.model.resquest.SaleRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface SaleService {
    ResponseEntity<APIResponse<DataResponse>> createSale(SaleRequest saleRequest);
    ResponseEntity<APIResponse<SalesReport>> getSalesByDateRange(LocalDate startDate, LocalDate endDate);
}
