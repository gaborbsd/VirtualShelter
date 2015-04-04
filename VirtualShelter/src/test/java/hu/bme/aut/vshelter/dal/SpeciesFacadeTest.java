package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;
import hu.bme.aut.vshelter.entity.Species;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class SpeciesFacadeTest {
	@Autowired
	private SpeciesFacade speciesFacade;
	
	@Test
	public void testPersist(){
		Species species = new Species();
		species.setSpeciesName("kutya");
		species.setId(1);

		speciesFacade.create(species);
		List<Species> specieses = speciesFacade.findAll();
		assertTrue(specieses.contains(species));
	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Species species = new Species();
		species.setSpeciesName("macska");
		species.setId(2);
		
		List<Species> specieses = speciesFacade.findAll();
		assertFalse(specieses.contains(species));
	}
	
	@Test
	public void testFindAdvertisementById(){
		Species species = new Species();
		Species actual;
		species.setSpeciesName("leopárd");
		species.setId(3);
		
		speciesFacade.create(species);
		
		actual = speciesFacade.findSpeciesById(3);
		assertEquals(species, actual);
		assertEquals(species.getSpeciesName(), actual.getSpeciesName());
		assertEquals(species.getId(), actual.getId());
	}
	
	@Test
	public void testNoFindSpeciesByNotExistId(){
		Species actual;
		actual = speciesFacade.findSpeciesById(4012);
		assertNull(actual);
	}
	
	@Test
	public void testEditSpeciest(){
		Species species = new Species();
		species.setSpeciesName("tigris");
		species.setId(4);
		
		speciesFacade.create(species);
		
		Species modified = new Species();
		modified.setSpeciesName("majom");
		modified.setId(4);
		
		speciesFacade.edit(modified);
		
		Species actual;
		actual = speciesFacade.findSpeciesById(4);
		assertEquals(modified, actual);
		assertEquals(modified.getSpeciesName(), actual.getSpeciesName());
		assertEquals(species.getId(), actual.getId());
	}
	
	@Test
	public void testDeleteSpeciesById(){
		Species species = new Species();
		species.setSpeciesName("aligátor");
		species.setId(5);
		
		speciesFacade.create(species);
		
		speciesFacade.deleteSpeciesById(5);
		
		Species actual;
		actual = speciesFacade.findSpeciesById(5);
		assertNull(actual);
	}
}
