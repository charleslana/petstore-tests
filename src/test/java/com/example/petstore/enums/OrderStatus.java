package com.example.petstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PROCESSING("processing"),
    CANCELLED("cancelled"),
    COMPLETED("completed");

    private final String status;
}
