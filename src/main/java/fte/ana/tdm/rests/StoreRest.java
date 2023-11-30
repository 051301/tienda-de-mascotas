package fte.ana.tdm.rests;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fte.ana.tdm.models.Pet;
import fte.ana.tdm.models.Store;
import fte.ana.tdm.services.StoreService;

@RestController
@RequestMapping("/api/store")
public class StoreRest {
	@Autowired
	private StoreService storeService;
	
	@PostMapping
	private ResponseEntity<Store> create(@RequestBody Store store){
		return storeService.create(store);
	}
	
	@GetMapping
	private ResponseEntity<List<Store>> list(){
		return storeService.getStores();
	}
	
	@GetMapping("/{storeId}")
	private ResponseEntity<Store> listOne(@PathVariable long storeId){
		return storeService.getStore(storeId);
	}
	
	@GetMapping("/{storeId}/pets")
	private ResponseEntity<List<Pet>> listPets(@PathVariable long storeId){
		return storeService.getPetsByStore(storeId);
	}
	
	@DeleteMapping("/{storeId}")
	private ResponseEntity<Void> delete(@PathVariable long storeId){
		return storeService.delete(storeId);
	}
	
	@PutMapping("/{storeId}")
	private ResponseEntity<Store> update(@PathVariable long storeId, @RequestBody Store store){
		return storeService.update(storeId, store);
	}
}
