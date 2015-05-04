package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Handicap;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class HandicapFacadeTest {
	@Autowired
	private HandicapFacade handicapFacade;

	@Test
	public void testPersist(){
		Handicap handicap = new Handicap();
		handicap.setHandicapName("gyogyó");
		handicap.setId(1);

		
		try {
			handicapFacade.create(handicap);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		List<Handicap> handicaps;
		try {
			handicaps = handicapFacade.findAll();
			assertTrue(handicaps.contains(handicap));
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Handicap handicap = new Handicap();
		handicap.setHandicapName("vak");
		handicap.setId(2);
		
		List<Handicap> handicaps;
		try {
			handicaps = handicapFacade.findAll();
			assertFalse(handicaps.contains(handicap));
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
	}
	
	@Test
	public void testFindAdvertisementById(){
		Handicap handicap = new Handicap();
		Handicap actual;
		handicap.setHandicapName("amputált lábú");
		handicap.setId(3);
		
		try {
			handicapFacade.create(handicap);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		try {
			actual = handicapFacade.findById(3);
			assertEquals(handicap, actual);
			assertEquals(handicap.getHandicapName(), actual.getHandicapName());
			assertEquals(handicap.getId(), actual.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testNoFindHandicapByNotExistId(){
		Handicap actual;
		try {
			actual = handicapFacade.findById(4012);
			assertNull(actual);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
	}
	
	@Test
	public void testEditHandicapt(){
		Handicap handicap = new Handicap();
		handicap.setHandicapName("látás sérült");
		handicap.setId(4);
		
		try {
			handicapFacade.create(handicap);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Handicap modified = new Handicap();
		modified.setHandicapName("hallás sérült");
		modified.setId(4);
		
		try {
			handicapFacade.edit(modified);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Handicap actual;
		try {
			actual = handicapFacade.findById(4);
			assertEquals(modified, actual);
			assertEquals(modified.getHandicapName(), actual.getHandicapName());
			assertEquals(handicap.getId(), actual.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testDeleteHandicapById(){
		Handicap handicap = new Handicap();
		handicap.setHandicapName("süket");
		handicap.setId(5);
		
		try {
			handicapFacade.create(handicap);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		try {
			handicapFacade.deleteById(5);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Handicap actual;
		try {
			actual = handicapFacade.findById(5);
			assertNull(actual);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	
}
