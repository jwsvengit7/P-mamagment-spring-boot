package com.maids.backendquiz.productsmanagement.web.services;

import com.maids.backendquiz.productsmanagement.domain.entities.Sale;
import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import com.maids.backendquiz.productsmanagement.domain.model.response.SalesReport;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.SaleRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface SaleService {
    ResponseEntity<APIResponse<DataResponse>> createSale(SaleRequest saleRequest);
    ResponseEntity<APIResponse<SalesReport>> getSalesByDateRange(LocalDate startDate, LocalDate endDate);
}
