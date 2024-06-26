package com.p.backend.productsmanagement.web.services;

import com.p.backend.productsmanagement.domain.entities.*;
import com.p.backend.productsmanagement.domain.enums.Role;
import com.p.backend.productsmanagement.domain.model.response.APIResponse;
import com.p.backend.productsmanagement.domain.model.response.DataResponse;
import com.p.backend.productsmanagement.domain.model.response.SalesReport;
import com.p.backend.productsmanagement.domain.model.resquest.SaleRequest;
import com.p.backend.productsmanagement.domain.model.resquest.TransactionRequest;
import com.p.backend.productsmanagement.domain.repository.*;
import com.p.backend.productsmanagement.domain.repository.*;
import com.p.backend.productsmanagement.helpers.GetPrincipalUser;
import com.p.backend.productsmanagement.helpers.ResponseUtils;
import com.p.backend.productsmanagement.domain.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository ;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;


    @Override
    public ResponseEntity<APIResponse<DataResponse>> createSale(SaleRequest saleRequest) {
        DataResponse dataResponse = new DataResponse();
        Client client = clientRepository.findClientByEmail(saleRequest.getClientEmail());

        String adminUser = GetPrincipalUser.principalUser();
        Users users = userRepository.findUsersByEmail(adminUser);
        if (!Objects.nonNull(users)) {
            dataResponse.setMessage("User not found");
            return ResponseUtils.responseType(dataResponse, HttpStatus.NO_CONTENT);
        }
            Product existingProduct = productRepository.findByName(saleRequest.getProduct());

            Sale newSale = new Sale();
            newSale.setSeller(users);
            newSale.setClient(client);
            newSale.setQty(saleRequest.getQty());
            newSale.setTotal(existingProduct.getPrice() * saleRequest.getQty());
            newSale.setCreationDate(LocalDateTime.now());

            saleRepository.save(newSale);
        for (TransactionRequest transactionRequest : saleRequest.getTransactionRequest()) {
            Transaction transaction = new Transaction();
            transaction.setSale(newSale);
            transaction.setPrice(transactionRequest.getPrice());
            transaction.setQuantity(transactionRequest.getQuantity());
            transactionRepository.save(transaction);
        }

            dataResponse.setMessage("Sale created successfully");
            return ResponseUtils.responseType(dataResponse, HttpStatus.CREATED);
        }


    @Override
    public ResponseEntity<APIResponse<SalesReport>> getSalesByDateRange(LocalDate startDate, LocalDate endDate) {
        SalesReport report = new SalesReport();

        List<Sale> sales = saleRepository.findByCreationDateBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));

        report.setTotalSales(sales.size());

        double totalRevenue = sales.stream().mapToDouble(Sale::getTotal).sum();
        report.setTotalRevenue(totalRevenue);

        Map<Product, Integer> productSalesCount = new HashMap<>();
        for (Sale sale : sales) {
            for (Transaction transaction : sale.getTransactions()) {
                Product product = transaction.getProduct();
                productSalesCount.put(product, productSalesCount.getOrDefault(product, 0) + transaction.getQuantity());
            }
        }
        List<Product> topSellingProducts = productSalesCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        report.setTopSellingProducts(topSellingProducts);

        Map<DataResponse, Double> sellerRevenue = new HashMap<>();
        DataResponse dataResponse = new DataResponse();
        for (Sale sale : sales) {
            Users seller = sale.getSeller();
            double saleRevenue = sale.getTotal();
            dataResponse.setId(seller.getId());
            dataResponse.setName(seller.getName());
            dataResponse.setEmail(seller.getEmail());
            dataResponse.setRole(seller.getRole().getName());
            sellerRevenue.put(dataResponse, sellerRevenue.getOrDefault(seller, 0.0) + saleRevenue);
        }
        List<DataResponse> topPerformingSellers = sellerRevenue.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        report.setTopPerformingSellers(topPerformingSellers);

        return new ResponseEntity<>(new APIResponse<>(report),HttpStatus.OK);
    }



}
