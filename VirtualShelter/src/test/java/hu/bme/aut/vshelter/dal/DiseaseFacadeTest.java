package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Disease;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class DiseaseFacadeTest {
	@Autowired
	private DiseaseFacade diseaseFacade;
	
	@Test
	public void testPersist(){
		Disease disease = new Disease();
		disease.setDiceaseName("köhögős");
		disease.setId(1);
		
		try {
			diseaseFacade.create(disease);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		List<Disease> diseases;
		try {
			diseases = diseaseFacade.findAll();
			assertTrue(diseases.contains(disease));
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Disease disease = new Disease();
		disease.setDiceaseName("rossz májú");
		disease.setId(2);
		
		List<Disease> diseases;
		try {
			diseases = diseaseFacade.findAll();
			assertFalse(diseases.contains(disease));
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testFindAdvertisementById(){
		Disease disease = new Disease();
		Disease actual;
		disease.setDiceaseName("himlős");
		disease.setId(3);
		
		try {
			diseaseFacade.create(disease);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		try {
			actual = diseaseFacade.findById(3);
			assertEquals(disease, actual);
			assertEquals(disease.getDiceaseName(), actual.getDiceaseName());
			assertEquals(disease.getId(), actual.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testNoFindDiseaseByNotExistId(){
		Disease actual;
		try {
			actual = diseaseFacade.findById(4012);
			assertNull(actual);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testEditDiseaset(){
		Disease disease = new Disease();
		disease.setDiceaseName("bolhás");
		disease.setId(4);
		
		try {
			diseaseFacade.create(disease);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Disease modified = new Disease();
		modified.setDiceaseName("tetves");
		modified.setId(4);
		
		try {
			diseaseFacade.edit(modified);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Disease actual;
		try {
			actual = diseaseFacade.findById(4);
			assertEquals(modified, actual);
			assertEquals(modified.getDiceaseName(), actual.getDiceaseName());
			assertEquals(disease.getId(), actual.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testDeleteDiseaseById(){
		Disease disease = new Disease();
		disease.setDiceaseName("álomkóros");
		disease.setId(5);
		
		try {
			diseaseFacade.create(disease);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		try {
			diseaseFacade.deleteById(5);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Disease actual;
		try {
			actual = diseaseFacade.findById(5);
			assertNull(actual);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
}

