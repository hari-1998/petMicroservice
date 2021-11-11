package com.data.pet.repository;

import com.data.pet.model.Pet;
import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends DatastoreRepository<Pet,Long> {

}
