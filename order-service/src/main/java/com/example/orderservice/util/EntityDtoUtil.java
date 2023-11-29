package com.example.orderservice.util;

import com.example.orderservice.dto.*;
import com.example.orderservice.entity.PurchaseOrder;
import com.example.orderservice.repository.PurchaseOrderRepository;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static void setTransactionRequestDto(RequestContext rc)
    {
        TransactionRequestDto dto = new TransactionRequestDto();
        dto.setUserId(rc.getPurchaseOrderRequestDto().getUserId());
        dto.setAmount(rc.getProductDto().getPrice());
        rc.setTransactionRequestDto(dto);
    }

    public static PurchaseOrder getPurchaseOrder(RequestContext requestContext)
    {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setUserId(requestContext.getTransactionResponseDto().getUserId());
        purchaseOrder.setProductId(requestContext.getProductDto().getId());
        purchaseOrder.setAmount(requestContext.getProductDto().getPrice());
        TransactionStatus status = requestContext.getTransactionResponseDto().getStatus();
        OrderStatus orderStatus = TransactionStatus.APPROVED.equals(status) ? OrderStatus.COMPLETED : OrderStatus.FAILED;
        purchaseOrder.setOrderStatus(orderStatus);
        return purchaseOrder;
    }

    public static PurchaseOrderResponseDto getPurchaseOrderResponseDto(PurchaseOrder purchaseOrder)
    {
        PurchaseOrderResponseDto purchaseOrderResponseDto = new PurchaseOrderResponseDto();
        BeanUtils.copyProperties(purchaseOrder,purchaseOrderResponseDto);
        purchaseOrderResponseDto.setOrderId(purchaseOrder.getId());
        return purchaseOrderResponseDto;
    }




}
