package com.example.orderservice.dto;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestContext {

    @NonNull private PurchaseOrderRequestDto purchaseOrderRequestDto;

    private ProductDto productDto;

    private TransactionRequestDto transactionRequestDto;

    private TransactionResponseDto transactionResponseDto;
}
