package com.example.orderservice.controller;

import com.example.orderservice.dto.PurchaseOrderRequestDto;
import com.example.orderservice.dto.PurchaseOrderResponseDto;
import com.example.orderservice.repository.PurchaseOrderRepository;
import com.example.orderservice.servcie.OrderFulfillmentService;
import com.example.orderservice.servcie.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class PurchaseOrderController {
    private OrderFulfillmentService orderFulfillmentService;
    private OrderQueryService orderQueryService;

    @Autowired
    public PurchaseOrderController(OrderFulfillmentService orderFulfillmentService, OrderQueryService orderQueryService) {
        this.orderFulfillmentService = orderFulfillmentService;
        this.orderQueryService = orderQueryService;
    }




    @PostMapping
    public Mono<PurchaseOrderResponseDto> order(@RequestBody Mono<PurchaseOrderRequestDto> requestDtoMono)
    {
        return this.orderFulfillmentService.processOrder(requestDtoMono);
    }

    @GetMapping("users/{userId}")
    public Flux<PurchaseOrderResponseDto> getOrderByUserId(@PathVariable String userId)
    {
        return this.orderQueryService.getProductByUserId(Integer.parseInt(userId));
    }

}
