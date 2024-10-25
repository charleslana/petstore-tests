package com.example.petstore.services;

public interface ApiOrderService<T> {
    T create(T entity);
}
