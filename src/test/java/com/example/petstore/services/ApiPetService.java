package com.example.petstore.services;

import java.util.List;

import com.example.petstore.enums.PetStatus;

public interface ApiPetService<T> {
    T create(T entity);

    T get(Long id);

    T update(T entity);

    List<T> findByStatus(PetStatus status);
}
