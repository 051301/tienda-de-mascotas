package fte.ana.tdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fte.ana.tdm.models.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
	@Query(value="SELECT * FROM pets WHERE store_id=?1", nativeQuery = true)
	List<Pet> getPetsByStore(long storeId);
}
