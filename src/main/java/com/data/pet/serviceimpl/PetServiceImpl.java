package com.data.pet.serviceimpl;

import com.data.pet.constant.ErrorEnum;
import com.data.pet.model.Pet;
import com.data.pet.repository.PetRepository;
import com.data.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet addPet(Pet pet) {
        boolean isPresent = petRepository.findById(pet.getId()).isPresent();
        if(!isPresent){
            Pet response = petRepository.save(pet);
            return response;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorEnum.ERROR404.getMessage());
        }
    }

    @Override
    public Iterable<Pet> getAllPets() {
        Iterable<Pet> data = petRepository.findAll();
        return data;
    }

    @Override
    public Pet getPetById(Long id) {

        boolean isPresent = petRepository.findById(id).isPresent();
        if(isPresent){
            Optional<Pet> response = petRepository.findById(id);
            Pet data = response.get();
            return data;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorEnum.ERROR404.getMessage());
        }
    }

    @Override
    public Pet updatePetById(Long id, Pet pet) {
        boolean isPresent = petRepository.findById(pet.getId()).isPresent();
        if(isPresent && id == pet.getId()) {
            Pet response = petRepository.save(pet);
            return response;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorEnum.ERROR404.getMessage());
        }
    }

    @Override
    public Pet deletePetById(Long id) {

        boolean isPresent = petRepository.findById(id).isPresent();
        if(isPresent){
            Pet response = petRepository.findById(id).get();
            petRepository.deleteById(id);
            return response;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorEnum.ERROR404.getMessage());
        }
    }
}
