package com.example.orderservice.client;

import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.dto.TransactionRequestDto;
import com.example.orderservice.dto.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
* Contain method to send data to user service
**/
@Service
public class UserClient {
    private final WebClient webClient;

    public UserClient(@Value("${user.service.url}") String url)
    {
        this.webClient = WebClient
                .builder()
                .baseUrl(url)
                .build();
    }


    public Mono<TransactionResponseDto> authorizeTransaction(final TransactionRequestDto transactionRequestDto){
        return this.webClient
                .post()
                .uri("/transaction")
                .bodyValue(transactionRequestDto)
                .retrieve()
                .bodyToMono(TransactionResponseDto.class);
    }


}
