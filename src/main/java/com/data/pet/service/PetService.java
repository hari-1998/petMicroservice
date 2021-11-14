package com.data.pet.service;

import com.data.pet.model.Pet;


public interface PetService {

    public Pet addPet(Pet pet);
    public Iterable<Pet> getAllPets();
    public Pet getPetById(Long id);
    public Pet updatePetById(Long id, Pet pet);
    public Pet deletePetById(Long id);
}