package com.example.petstore.utils;

import java.util.List;

import org.testng.Assert;

import com.example.petstore.enums.PetStatus;
import com.example.petstore.models.Category;
import com.example.petstore.models.Pet;
import com.example.petstore.models.Tag;
import com.example.petstore.services.impl.PetServiceImpl;

public class PetUtils {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static PetServiceImpl petServiceImpl = new PetServiceImpl();

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getPetUrl() {
        return BASE_URL.concat("/pet");
    }

    public static String getOrderUrl() {
        return BASE_URL.concat("/store/order");
    }

    public static Pet createPet(String name, PetStatus status) {
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
        Assert.assertEquals(petCreated.getPhotoUrls().size(), 2);
        Assert.assertEquals(petCreated.getPhotoUrls(), photoUrls);
        Assert.assertEquals(petCreated.getTags().size(), 1);
        Assert.assertEquals(petCreated.getTags().get(0).getName(), tag.getName());
        Assert.assertEquals(petCreated.getStatus(), status);
        return petCreated;
    }
}
