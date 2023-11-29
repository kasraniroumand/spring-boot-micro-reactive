package com.example.orderservice.servcie;

import com.example.orderservice.dto.PurchaseOrderResponseDto;
import com.example.orderservice.repository.PurchaseOrderRepository;
import com.example.orderservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class OrderQueryService {
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    public OrderQueryService(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public Flux<PurchaseOrderResponseDto> getProductByUserId(int userId){
        return Flux.fromStream(
                ()->
                        this
                                .purchaseOrderRepository.findByUserId(userId)
                                .stream()
        )
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

}
