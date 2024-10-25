package com.example.petstore.services;

import java.util.List;

import com.example.petstore.enums.PetStatus;
import com.example.petstore.models.ResponseWrapper;

public interface ApiPetService<T> {
    T create(T entity);

    ResponseWrapper get(Long id);

    T update(T entity);

    List<T> findByStatus(PetStatus status);
}
