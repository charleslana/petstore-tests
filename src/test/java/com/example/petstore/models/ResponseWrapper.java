package com.example.petstore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper {
    private Object data;
    private boolean hasError;

    public boolean hasError() {
        return hasError;
    }
}
