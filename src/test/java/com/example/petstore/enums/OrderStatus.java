package com.example.petstore.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    @JsonProperty("processing")
    PROCESSING("processing"),

    @JsonProperty("cancelled")
    CANCELLED("cancelled"),

    @JsonProperty("completed")
    COMPLETED("completed");

    private final String status;
}
