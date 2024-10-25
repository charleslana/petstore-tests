package com.example.petstore.services.impl;

import java.util.List;

import org.testng.Assert;

import com.example.petstore.enums.PetStatus;
import com.example.petstore.models.ErrorResponse;
import com.example.petstore.models.Pet;
import com.example.petstore.models.ResponseWrapper;
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
    public ResponseWrapper get(Long id) {
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .get(PetUtils.getPetUrl().concat("/{id}"));
        if (response.statusCode() == 200) {
            Pet pet = response.as(Pet.class);
            return new ResponseWrapper(pet, false);
        }
        Assert.assertEquals(response.statusCode(), 404);
        ErrorResponse error = response.as(ErrorResponse.class);
        return new ResponseWrapper(error, true);
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
                .queryParam("status", status.getStatus())
                .get(PetUtils.getPetUrl().concat("/findByStatus"));
        response.then().statusCode(200);
        return List.of(response.as(Pet[].class));
    }
}
