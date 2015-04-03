package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;

import java.util.List;

import hu.bme.aut.vshelter.entity.Animal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class AnimalFacadeTest {
	@Autowired
	private AnimalFacade animalFacade;
	
	@Test
	public void testPersist() {
		Animal animal = new Animal();
		animal.setName("Cirmi");
		animal.setId(1);
		animalFacade.create(animal);
		List<Animal> animals = animalFacade.findAll();
		assertTrue(animals.contains(animal));
	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Animal animal = new Animal();
		animal.setName("Jankó");
		animal.setId(2);
		
		List<Animal> animals = animalFacade.findAll();
		assertFalse(animals.contains(animal));
	}
	
	@Test
	public void testFindAdvertisementById(){
		Animal animal = new Animal();
		Animal actual;
		animal.setName("Kormos");
		animal.setDescription("morcos fekete cica");
		animal.setId(3);
		
		
		animalFacade.create(animal);
		
		actual = animalFacade.findAnimalById(3);
		assertEquals(animal, actual);
		assertEquals(animal.getName(), actual.getName());
		assertEquals(animal.getDescription(), actual.getDescription());
		assertEquals(animal.getId(), actual.getId());

	}
	
	@Test
	public void testNoFindAnimalByNotExistId(){
		Animal actual;
		actual = animalFacade.findAnimalById(4012);
		assertNull(actual);
	}
	
	@Test
	public void testEditAnimal(){
		Animal animal = new Animal();
		animal.setName("Vörös");
		animal.setId(4);
		
		animalFacade.create(animal);
		
		Animal modified = new Animal();
		modified.setName("Veres");
		animal.setDescription("aranyos vörös cica");
		modified.setId(4);
		
		animalFacade.edit(modified);
		
		Animal actual;
		actual = animalFacade.findAnimalById(4);
		assertEquals( modified, actual);
		assertEquals(modified.getName(), actual.getName());
		assertEquals(modified.getDescription(), actual.getDescription());
	}
	
	@Test
	public void testDeleteAnimalById(){
		Animal animal = new Animal();
		animal.setName("Fehérke");
		animal.setId(5);
		
		animalFacade.create(animal);
		
		animalFacade.deleteAnimalById(5);
		
		Animal actual;
		actual = animalFacade.findAnimalById(5);
		assertNull(actual);
	}
}
