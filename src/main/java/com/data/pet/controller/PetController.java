package com.data.pet.controller;

import com.data.pet.model.Pet;
import com.data.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/health")
    public String health(){
        return "Success";
    }

    @PostMapping
    public @ResponseBody Pet addPet(@RequestBody Pet pet){
        return petService.addPet(pet);
    }

    @GetMapping
    public @ResponseBody Iterable<Pet> getAllPets(){
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public @ResponseBody Pet getPetById(@PathVariable(name="id") Long id){
        return petService.getPetById(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody Pet updatePetById(@PathVariable(name="id") Long id,@RequestBody Pet pet){
        return petService.updatePetById(id,pet);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody Pet deletePetById(@PathVariable(name="id") Long id){
        return petService.deletePetById(id);
    }
}
