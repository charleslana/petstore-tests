package com.example.petstore.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class PetStoreStep {
    @Given("Eu faço uma solicitação POST para {string}")
    public void eu_faço_uma_solicitação_post_para(String string) {
        System.out.println(string);
    }

    @Then("o código de status de resposta deve ser {int}")
    public void o_código_de_status_de_resposta_deve_ser(Integer int1) {
        System.out.println(int1);
    }

    @And("a resposta deve conter status {string}")
    public void a_resposta_deve_conter_status(String string) {
        System.out.println(string);
    }
}
