package com.voatads.customer.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionDTO {
    private UUID customerId;
    private LocalDateTime transactionDate;
    private Double miles;
    private String type;
    private String description;
}
