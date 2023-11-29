package com.example.orderservice.entity;

import com.example.orderservice.dto.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "purchase_order")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "amount")
    private Integer amount;

    @Enumerated
    @JsonProperty("status")
    @Column(name = "order_status")
    private OrderStatus orderStatus;

}