package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;
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

		
		handicapFacade.create(handicap);
		List<Handicap> handicaps = handicapFacade.findAll();
		assertTrue(handicaps.contains(handicap));
	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Handicap handicap = new Handicap();
		handicap.setHandicapName("vak");
		handicap.setId(2);
		
		List<Handicap> handicaps = handicapFacade.findAll();
		assertFalse(handicaps.contains(handicap));
	}
	
	@Test
	public void testFindAdvertisementById(){
		Handicap handicap = new Handicap();
		Handicap actual;
		handicap.setHandicapName("amputált lábú");
		handicap.setId(3);
		
		handicapFacade.create(handicap);
		
		actual = handicapFacade.findHandicapById(3);
		assertEquals(handicap, actual);
		assertEquals(handicap.getHandicapName(), actual.getHandicapName());
		assertEquals(handicap.getId(), actual.getId());
	}
	
	@Test
	public void testNoFindHandicapByNotExistId(){
		Handicap actual;
		actual = handicapFacade.findHandicapById(4012);
		assertNull(actual);
	}
	
	@Test
	public void testEditHandicapt(){
		Handicap handicap = new Handicap();
		handicap.setHandicapName("látás sérült");
		handicap.setId(4);
		
		handicapFacade.create(handicap);
		
		Handicap modified = new Handicap();
		modified.setHandicapName("hallás sérült");
		modified.setId(4);
		
		handicapFacade.edit(modified);
		
		Handicap actual;
		actual = handicapFacade.findHandicapById(4);
		assertEquals(modified, actual);
		assertEquals(modified.getHandicapName(), actual.getHandicapName());
		assertEquals(handicap.getId(), actual.getId());
	}
	
	@Test
	public void testDeleteHandicapById(){
		Handicap handicap = new Handicap();
		handicap.setHandicapName("süket");
		handicap.setId(5);
		
		handicapFacade.create(handicap);
		
		handicapFacade.deleteHandicapById(5);
		
		Handicap actual;
		actual = handicapFacade.findHandicapById(5);
		assertNull(actual);
	}
	
	
}
