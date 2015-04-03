package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Species;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class BreedFacadeTest {
	@Autowired
	private BreedFacade breedFacade;
	
	@Test
	public void testPersist(){
		Breed breed = new Breed();
		Species species = new Species();
		breed.setBreedName("kutya");
		breed.setId(1);
		breed.setSpecies(species);
		
		breedFacade.create(breed);
		List<Breed> breeds = breedFacade.findAll();
		assertTrue(breeds.contains(breed));
	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Breed breed = new Breed();
		breed.setBreedName("macska");
		breed.setId(2);
		
		List<Breed> breeds = breedFacade.findAll();
		assertFalse(breeds.contains(breed));
	}
	
	@Test
	public void testFindAdvertisementById(){
		Breed breed = new Breed();
		Breed actual;
		breed.setBreedName("leopárd");
		breed.setId(3);
		
		breedFacade.create(breed);
		
		actual = breedFacade.findBreedById(3);
		assertEquals(breed, actual);
		assertEquals(breed.getBreedName(), actual.getBreedName());
		assertEquals(breed.getId(), actual.getId());

	}
	
	@Test
	public void testNoFindBreedByNotExistId(){
		Breed actual;
		actual = breedFacade.findBreedById(4012);
		assertNull(actual);
	}
	
	@Test
	public void testEditBreedt(){
		Breed breed = new Breed();
		breed.setBreedName("tigris");
		breed.setId(4);
		
		breedFacade.create(breed);
		
		Breed modified = new Breed();
		modified.setBreedName("majom");
		modified.setId(4);
		
		Breed actual;
		actual = breedFacade.findBreedById(4);
		assertEquals( modified, actual);
		assertEquals(modified.getBreedName(), actual.getBreedName());
		assertEquals(breed.getId(), actual.getId());
	}
	
	@Test
	public void testDeleteBreedById(){
		Breed breed = new Breed();
		breed.setBreedName("aligátor");
		breed.setId(5);
		
		breedFacade.create(breed);
		
		breedFacade.deleteBreedById(5);
		
		Breed actual;
		actual = breedFacade.findBreedById(5);
		assertNull(actual);
	}
}
