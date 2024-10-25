package com.example.petstore.utils;

public class PetUtils {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getPetUrl() {
        return BASE_URL.concat("/pet");
    }

    public static String getOrderUrl() {
        return BASE_URL.concat("/store/order");
    }
}
