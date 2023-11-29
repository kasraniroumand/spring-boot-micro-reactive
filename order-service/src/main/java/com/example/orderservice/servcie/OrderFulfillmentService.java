package com.example.orderservice.servcie;

import com.example.orderservice.client.ProductClient;
import com.example.orderservice.client.UserClient;
import com.example.orderservice.dto.PurchaseOrderRequestDto;
import com.example.orderservice.dto.PurchaseOrderResponseDto;
import com.example.orderservice.dto.RequestContext;
import com.example.orderservice.dto.TransactionRequestDto;
import com.example.orderservice.repository.PurchaseOrderRepository;
import com.example.orderservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class OrderFulfillmentService {

    private ProductClient productClient;
    private UserClient userClient;

    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    public OrderFulfillmentService(ProductClient productClient, UserClient userClient, PurchaseOrderRepository purchaseOrderRepository) {
        this.productClient = productClient;
        this.userClient = userClient;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public Mono<PurchaseOrderResponseDto> processOrder(
            Mono<PurchaseOrderRequestDto> requestDtoMono
    )
    {
        return requestDtoMono
                .map(RequestContext::new)
                .flatMap(this::productRequestResponse)
                .doOnNext(EntityDtoUtil::setTransactionRequestDto)
                .flatMap(this::userRequestResponse)
                .map(EntityDtoUtil::getPurchaseOrder)
                .map(purchaseOrderRepository::save)
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
//                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<RequestContext> productRequestResponse(RequestContext rc)
    {
        return this.productClient
                .getProductById(rc.getPurchaseOrderRequestDto().getProductId())
                .doOnNext(rc::setProductDto)
                .thenReturn(rc);
    }


    private Mono<RequestContext> userRequestResponse(RequestContext rc)
    {
        return this
                .userClient
                .authorizeTransaction(rc.getTransactionRequestDto())
                .doOnNext(rc::setTransactionResponseDto)
                .thenReturn(rc);
    }


}
