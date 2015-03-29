package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;

import java.util.List;

import hu.bme.aut.vshelter.entity.Advertisement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class AdvertisementFacadeTest {

	
	@Autowired
	private AdvertisementFacade advertisementFacade;
	
	@Test
	public void testDI() {
		assertNotNull(advertisementFacade);
		assertEquals(advertisementFacade.getClass().getSimpleName(),
				"AdvertisementFacadeInMemoryImpl");
	}
	
	@Test
	public void testPersistAddedIsContains() {
		Advertisement advertisement = new Advertisement();
		advertisement.setId(1);
		
		advertisementFacade.create(advertisement);
		
		List<Advertisement> animals = advertisementFacade.findAll();
		assertTrue(animals.contains(advertisement));
	}
	
	//Todo
}
