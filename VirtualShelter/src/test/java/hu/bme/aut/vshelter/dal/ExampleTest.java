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
public class ExampleTest {
	@Autowired
	private AnimalFacade animalFacade;

	@Test
	public void testDI() {
		assertNotNull(animalFacade);
		assertEquals(animalFacade.getClass().getSimpleName(),
				"AnimalFacadeInMemoryImpl");
	}
	
	@Test
	public void testPersist() {
		Animal animal = new Animal();
		animal.setName("Cirmi");
		animal.setId(1);
		animalFacade.create(animal);
		List<Animal> animals = animalFacade.findAll();
		assertTrue(animals.contains(animal));
	}
}
