package com.example.petstore.services.impl;

import java.util.List;

import com.example.petstore.enums.PetStatus;
import com.example.petstore.models.Pet;
import com.example.petstore.services.ApiPetService;
import com.example.petstore.utils.PetUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetServiceImpl implements ApiPetService<Pet> {

    @Override
    public Pet create(Pet pet) {
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(pet)
                .log().all()
                .post(PetUtils.getPetUrl());
        response.then().statusCode(200);
        return response.as(Pet.class);
    }

    @Override
    public Pet get(Long id) {
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("/", id)
                .get(PetUtils.getPetUrl());
        response.then().statusCode(200);
        return response.as(Pet.class);
    }

    @Override
    public Pet update(Pet pet) {
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(pet)
                .put(PetUtils.getPetUrl());
        response.then().statusCode(200);
        return response.as(Pet.class);
    }

    @Override
    public List<Pet> findByStatus(PetStatus status) {
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("/", "findByStatus")
                .queryParam("status", status.getStatus())
                .get(PetUtils.getPetUrl());
        response.then().statusCode(200);
        return List.of(response.as(Pet[].class));
    }
}
