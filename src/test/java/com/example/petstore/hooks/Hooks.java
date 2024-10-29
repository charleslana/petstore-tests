package com.example.petstore.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("Setup before scenario");
    }

    @After
    public void tearDown() {
        System.out.println("Teardown after scenario");
    }
}
