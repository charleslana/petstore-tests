package com.example.petstore.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetStatus {
    @JsonProperty("available")
    AVAILABLE("available"),

    @JsonProperty("pending")
    PENDING("pending"),

    @JsonProperty("sold")
    SOLD("sold");

    private final String status;
}
