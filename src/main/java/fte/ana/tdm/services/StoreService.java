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
import fte.ana.tdm.repositories.PetRepository;
import fte.ana.tdm.repositories.StoreRepository;

@Service
public class StoreService {

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	public ResponseEntity<Store> create(Store store) {
		Store store0 = storeRepository.save(store);
		try {
			return ResponseEntity.created(new URI("api/store/"+store0.getId())).body(store0);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	public ResponseEntity<List<Store>> getStores() {
		return ResponseEntity.ok(storeRepository.findAll());
	}
	
	public ResponseEntity<Store> getStore(long storeId) {
		Optional<Store> store = storeRepository.findById(storeId);
		if(store.isPresent()) {
			return new ResponseEntity<Store>(store.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<Void> delete(long storeId) {
		storeRepository.deleteById(storeId);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Store> update(long storeId, Store store) {
		if(storeId != store.getId())return ResponseEntity.badRequest().build();
		if(storeRepository.findById(storeId).isPresent()) {
			return new ResponseEntity<Store>(storeRepository.save(store), HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<List<Pet>> getPetsByStore(long storeId) {
		return ResponseEntity.ok(petRepository.getPetsByStore(storeId));
	}
}
