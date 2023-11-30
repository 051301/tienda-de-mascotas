package fte.ana.tdm.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fte.ana.tdm.models.Pet;
import fte.ana.tdm.models.Store;
import fte.ana.tdm.models.dto.PetDto;
import fte.ana.tdm.repositories.PetRepository;
import fte.ana.tdm.repositories.StoreRepository;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	public ResponseEntity<Pet> create(PetDto pet) {
		Optional<Store> store = storeRepository.findById(pet.getStoreId());
		if(store.isEmpty())return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		Pet pet0 = pet.ToPet();
		pet0.setStore(store.get());
		pet0 = petRepository.save(pet0);
		
		try {
			return ResponseEntity.created(new URI("api/pet/"+pet0.getId())).body(pet0);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	public ResponseEntity<List<Pet>> getPets() {
		return ResponseEntity.ok(petRepository.findAll());
	}
	
	public ResponseEntity<Pet> getPet(long petId) {
		Optional<Pet> pet = petRepository.findById(petId);
		if(pet.isPresent()) {
			return new ResponseEntity<Pet>(pet.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<Void> delete(long petId) {
		petRepository.deleteById(petId);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Pet> update(long petId, PetDto pet) {
		if(petId != pet.getId())return ResponseEntity.badRequest().build();
		Optional<Store> store = storeRepository.findById(pet.getStoreId());
		if(store.isEmpty())return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		if(petRepository.findById(petId).isPresent()) {
			Pet p = pet.ToPet();
			p.setId(petId);
			p.setStore(store.get());
			return new ResponseEntity<Pet>(petRepository.save(p), HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
}
