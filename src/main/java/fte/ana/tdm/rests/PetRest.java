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
import fte.ana.tdm.models.dto.PetDto;
import fte.ana.tdm.services.PetService;

@RestController
@RequestMapping("/api/pet")
public class PetRest {
	
	@Autowired
	private PetService petService;
	
	@PostMapping
	private ResponseEntity<Pet> create(@RequestBody PetDto pet){
		return petService.create(pet);
	}
	
	@GetMapping
	private ResponseEntity<List<Pet>> list(){
		return petService.getPets();
	}
	
	@GetMapping("/{petId}")
	private ResponseEntity<Pet> listOne(@PathVariable long petId){
		return petService.getPet(petId);
	}
	
	@DeleteMapping("/{petId}")
	private ResponseEntity<Void> delete(@PathVariable long petId){
		return petService.delete(petId);
	}
	
	@PutMapping("/{petId}")
	private ResponseEntity<Pet> update(@PathVariable long petId, @RequestBody PetDto pet){
		return petService.update(petId, pet);
	}
}
