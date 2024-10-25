package com.example.petstore.tests;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.example.petstore.enums.OrderStatus;
import com.example.petstore.enums.PetStatus;
import com.example.petstore.models.Order;
import com.example.petstore.services.impl.OrderServiceImpl;
import com.example.petstore.utils.ExtentReportManager;
import com.example.petstore.utils.PetUtils;

@Listeners(ExtentReportManager.class)
public class PetStoreTest {
    private OrderServiceImpl orderServiceImpl;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        orderServiceImpl = new OrderServiceImpl();
    }

    @Test(groups = "createOrder")
    public void createOrder() {
        Long petId = PetUtils.createPet("Dog", PetStatus.AVAILABLE).getId();
        Integer quantity = 2;
        String shipDate = LocalDateTime.now().plusDays(1).truncatedTo(ChronoUnit.MILLIS).toString().concat("+0000");
        OrderStatus orderStatus = OrderStatus.PROCESSING;
        Boolean complete = false;
        Order order = Order.builder().petId(petId).quantity(quantity).shipDate(shipDate)
                .status(orderStatus)
                .complete(false).build();
        Order orderCreated = orderServiceImpl.create(order);
        Assert.assertEquals(orderCreated.getPetId(), petId);
        Assert.assertEquals(orderCreated.getQuantity(), quantity);
        Assert.assertEquals(orderCreated.getShipDate(), shipDate);
        Assert.assertEquals(orderCreated.getStatus(), orderStatus);
        Assert.assertEquals(orderCreated.getComplete(), complete);
    }
}
