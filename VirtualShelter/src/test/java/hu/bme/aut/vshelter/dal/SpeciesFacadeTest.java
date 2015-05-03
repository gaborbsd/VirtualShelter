package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;
import hu.bme.aut.vshelter.api.VirtualShelterException;
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

		try {
			speciesFacade.create(species);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		List<Species> specieses;
		try {
			specieses = speciesFacade.findAll();
			assertTrue(specieses.contains(species));
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Species species = new Species();
		species.setSpeciesName("macska");
		species.setId(2);
		
		List<Species> specieses;
		try {
			specieses = speciesFacade.findAll();
			assertFalse(specieses.contains(species));
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testFindAdvertisementById(){
		Species species = new Species();
		Species actual;
		species.setSpeciesName("leopárd");
		species.setId(3);
		
		try {
			speciesFacade.create(species);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		try {
			actual = speciesFacade.findById(3);
			assertEquals(species, actual);
			assertEquals(species.getSpeciesName(), actual.getSpeciesName());
			assertEquals(species.getId(), actual.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testNoFindSpeciesByNotExistId(){
		Species actual;
		try {
			actual = speciesFacade.findById(4012);
			assertNull(actual);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testEditSpeciest(){
		Species species = new Species();
		species.setSpeciesName("tigris");
		species.setId(4);
		
		try {
			speciesFacade.create(species);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Species modified = new Species();
		modified.setSpeciesName("majom");
		modified.setId(4);
		
		try {
			speciesFacade.edit(modified);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Species actual;
		try {
			actual = speciesFacade.findById(4);
			assertEquals(modified, actual);
			assertEquals(modified.getSpeciesName(), actual.getSpeciesName());
			assertEquals(species.getId(), actual.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testDeleteSpeciesById(){
		Species species = new Species();
		species.setSpeciesName("aligátor");
		species.setId(5);
		
		try {
			speciesFacade.create(species);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		try {
			speciesFacade.deleteById(5);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Species actual;
		try {
			actual = speciesFacade.findById(5);
			assertNull(actual);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
	
	}
}
