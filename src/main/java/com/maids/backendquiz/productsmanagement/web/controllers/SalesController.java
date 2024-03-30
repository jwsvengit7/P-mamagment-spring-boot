package com.maids.backendquiz.productsmanagement.web.controllers;

import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import com.maids.backendquiz.productsmanagement.domain.model.response.SalesReport;
import com.maids.backendquiz.productsmanagement.domain.model.resquest.SaleRequest;
import com.maids.backendquiz.productsmanagement.web.services.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/sales")
public class SalesController {
    private final SaleService saleService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse<DataResponse>> sale(@RequestBody @Valid SaleRequest saleRequest){
        return saleService.createSale(saleRequest);
    }
    @GetMapping("/report")
    public ResponseEntity<APIResponse<SalesReport>> getSalesReportByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            return ResponseEntity.badRequest().build();
        }
        return saleService.getSalesByDateRange(startDate, endDate);
    }
}
