package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;
import hu.bme.aut.vshelter.entity.Institution;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class InstitutionFacadeTest {

	@Autowired
	private InstitutionFacade institutionFacade;
	
	@Test
	public void testPersist(){
		Institution institution = new Institution();
		institution.setTaxNumber("123455");
		institution.setId(1);
		
		institutionFacade.create(institution);
		List<Institution> institutions = institutionFacade.findAll();
		assertTrue(institutions.contains(institution));
	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Institution institution = new Institution();
		institution.setTaxNumber("3353455");
		institution.setId(2);
		
		List<Institution> institutions = institutionFacade.findAll();
		assertFalse(institutions.contains(institution));
	}
	
	@Test
	public void testFindAdvertisementById(){
		Institution institution = new Institution();
		Institution actual;
		institution.setTaxNumber("2456345634");
		institution.setId(3);
		
		institutionFacade.create(institution);
		
		actual = institutionFacade.findInstitutionById(3);
		assertEquals(institution, actual);
		assertEquals(institution.getTaxNumber(), actual.getTaxNumber());
		assertEquals(institution.getId(), actual.getId());
	}
	
	@Test
	public void testNoFindInstitutionByNotExistId(){
		Institution actual;
		actual = institutionFacade.findInstitutionById(4012);
		assertNull(actual);
	}
	
	@Test
	public void testEditInstitutiont(){
		Institution institution = new Institution();
		institution.setTaxNumber("3456345634");
		institution.setId(4);
		
		institutionFacade.create(institution);
		
		Institution modified = new Institution();
		modified.setTaxNumber("456345634");
		modified.setId(4);
		
		institutionFacade.edit(modified);
		
		Institution actual;
		actual = institutionFacade.findInstitutionById(4);
		assertEquals(modified, actual);
		assertEquals(modified.getTaxNumber(), actual.getTaxNumber());
		assertEquals(institution.getId(), actual.getId());
	}
	
	@Test
	public void testDeleteInstitutionById(){
		Institution institution = new Institution();
		institution.setTaxNumber("45634563");
		institution.setId(5);
		
		institutionFacade.create(institution);
		
		institutionFacade.deleteInstitutionById(5);
		
		Institution actual;
		actual = institutionFacade.findInstitutionById(5);
		assertNull(actual);
	}
}
