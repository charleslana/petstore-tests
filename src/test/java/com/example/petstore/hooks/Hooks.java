package com.example.petstore.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("Setup before scenario");
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("Before each step");
    }

    @AfterStep
    public void afterStep() {
        System.out.println("After each step");
    }

    @After
    public void tearDown() {
        System.out.println("Teardown after scenario");
    }
}
