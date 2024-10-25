package com.example.petstore.tests;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.petstore.enums.OrderStatus;
import com.example.petstore.enums.PetStatus;
import com.example.petstore.models.Category;
import com.example.petstore.models.Order;
import com.example.petstore.models.Pet;
import com.example.petstore.models.Tag;
import com.example.petstore.services.impl.OrderServiceImpl;
import com.example.petstore.services.impl.PetServiceImpl;

@Test(groups = "orderCreation")
public class PetStoreTest {
    private PetServiceImpl petServiceImpl;
    private OrderServiceImpl orderServiceImpl;
    private List<Pet> createdPets;

    @BeforeMethod
    public void setUp() {
        petServiceImpl = new PetServiceImpl();
        orderServiceImpl = new OrderServiceImpl();
        createdPets = new ArrayList<>();
        createdPets.add(createPet("Dog", PetStatus.AVAILABLE));
        createdPets.add(createPet("Cat", PetStatus.PENDING));
        createdPets.add(createPet("Rabbit", PetStatus.SOLD));
        System.out.println("Chamou aqui");
    }

    public void createOrder() {
        Long petId = createdPets.get(0).getId();
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

    private Pet createPet(String name, PetStatus status) {
        String categoryName = "category";
        List<String> photoUrls = List.of("url", "url2");
        Category category = Category.builder().name(categoryName).build();
        Tag tag = Tag.builder().name("tag").build();
        Pet pet = Pet.builder()
                .category(category)
                .name(name)
                .photoUrls(photoUrls)
                .tags(List.of(tag))
                .status(status)
                .build();
        Pet petCreated = petServiceImpl.create(pet);
        Assert.assertEquals(petCreated.getCategory().getName(), category.getName());
        Assert.assertEquals(petCreated.getName(), name);
        Assert.assertEquals(petCreated.getPhotoUrls(), photoUrls);
        Assert.assertEquals(petCreated.getPhotoUrls().size(), 2);
        Assert.assertEquals(petCreated.getTags().get(0).getName(), tag.getName());
        Assert.assertEquals(petCreated.getTags().size(), 1);
        Assert.assertEquals(petCreated.getStatus(), status);
        return petCreated;
    }
}
