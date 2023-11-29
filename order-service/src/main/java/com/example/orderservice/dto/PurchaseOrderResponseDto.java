package com.example.orderservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class PurchaseOrderResponseDto {
    private Integer orderId;
    private Integer userId;
    private String productId;
    private Integer amount;
    private OrderStatus status;
}