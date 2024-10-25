package com.example.petstore.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.example.petstore.enums.PetStatus;
import com.example.petstore.models.ErrorResponse;
import com.example.petstore.models.Pet;
import com.example.petstore.models.ResponseWrapper;
import com.example.petstore.services.impl.PetServiceImpl;
import com.example.petstore.utils.ExtentReportManager;
import com.example.petstore.utils.PetUtils;

@Listeners(ExtentReportManager.class)
public class PetTest {
    private PetServiceImpl petServiceImpl;
    private List<Pet> createdPets;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        petServiceImpl = new PetServiceImpl();
        createdPets = new ArrayList<>();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        createdPets.clear();
    }

    @Test(groups = { "petNotFound", "pet" })
    public void getPetByIdAndNonExistentPet() {
        Long petId = 0L;
        ResponseWrapper responseWrapper = petServiceImpl.get(petId);
        if (!responseWrapper.hasError()) {
            Assert.fail("Expected an error but got a successful response.");
            return;
        }
        ErrorResponse errorResponse = (ErrorResponse) responseWrapper.getData();
        Assert.assertNotNull(errorResponse);
        Assert.assertEquals(errorResponse.getCode(), 1);
        Assert.assertEquals(errorResponse.getType(), "error");
        Assert.assertEquals(errorResponse.getMessage(), "Pet not found");
    }

    @Test(groups = "petUpdate")
    public void updatePet() {
        createInitialPets();
        Pet petToUpdate = createdPets.get(2);
        String newName = "Dogs";
        petToUpdate.setName(newName);
        petToUpdate.setStatus(PetStatus.PENDING);
        Pet updatedPet = petServiceImpl.update(petToUpdate);
        Assert.assertEquals(updatedPet.getName(), newName);
        Assert.assertEquals(updatedPet.getStatus(), PetStatus.PENDING);
    }

    @Test(groups = "findPetsByStatusPending")
    public void findPetsByStatusPending() {
        createInitialPets();
        List<Pet> pendingPets = petServiceImpl.findByStatus(PetStatus.PENDING);
        Assert.assertFalse(pendingPets.isEmpty());
        for (Pet pet : pendingPets) {
            Assert.assertEquals(pet.getStatus(), PetStatus.PENDING);
        }
    }

    private void createInitialPets() {
        createdPets.add(PetUtils.createPet("Dog", PetStatus.AVAILABLE));
        createdPets.add(PetUtils.createPet("Cat", PetStatus.PENDING));
        createdPets.add(PetUtils.createPet("Rabbit", PetStatus.SOLD));
        Assert.assertEquals(createdPets.size(), 3);
    }
}
