package com.example.petstore.services.impl;

import com.example.petstore.models.Order;
import com.example.petstore.services.ApiOrderService;
import com.example.petstore.utils.PetUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OrderServiceImpl implements ApiOrderService<Order> {

    @Override
    public Order create(Order order) {
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(order)
                .log().all()
                .post(PetUtils.getOrderUrl());
        response.then().statusCode(200);
        return response.as(Order.class);
    }

}
