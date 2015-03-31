package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;

import java.util.List;

import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;
import hu.bme.aut.vshelter.entity.User;

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
		Advertiser advertiser = new User();
		Animal animal = new Animal();
		
		advertisement.setAnimal(animal);
		advertisement.setAdvertiser(advertiser);
		advertisement.setId(1);

		advertisementFacade.create(advertisement);

		List<Advertisement> advertisements = advertisementFacade.findAll();
		assertTrue(advertisements.contains(advertisement));
	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Advertisement advertisement = new Advertisement();
		Advertiser advertiser = new User();
		Animal animal = new Animal();
		
		advertisement.setAnimal(animal);
		advertisement.setAdvertiser(advertiser);
		advertisement.setId(2);
		
		List<Advertisement> advertisements = advertisementFacade.findAll();
		assertFalse(advertisements.contains(advertisement));
	}
	
	@Test
	public void testFindAdvertisementById(){
		Advertisement advertisement = new Advertisement();
		Advertisement actual;
		Advertiser advertiser = new User();
		Animal animal = new Animal();
		
		advertisement.setAnimal(animal);
		advertisement.setAdvertiser(advertiser);
		advertisement.setId(3);

		advertisementFacade.create(advertisement);
		
		actual = advertisementFacade.findAdvertisementById(3);
		assertEquals(advertisement, actual);
		assertEquals(advertisement.getAnimal(), actual.getAnimal());
		assertEquals(advertisement.getAdvertiser(), actual.getAdvertiser());
		assertEquals(advertisement.getId(), actual.getId());
	}
	
	@Test
	public void testNoFindAdvertisementByNotExistId(){
		Advertisement actual;
		actual = advertisementFacade.findAdvertisementById(3301);
		assertTrue(actual == null);
	}
	
	@Test
	public void testEditAdvertisement(){
		Advertisement advertisement = new Advertisement();
		Advertiser advertiser = new User();
		Animal animal = new Animal();
		advertisement.setAnimal(animal);
		advertisement.setAdvertiser(advertiser);
		advertisement.setId(1);

		advertisementFacade.create(advertisement);
		
		Advertisement modified = new Advertisement();
		Advertiser advertiser2 = new User();
		Animal animal2 = new Animal();
		modified.setAdvertiser(advertiser2);
		modified.setAnimal(animal2);
		modified.setId(5);
		
		advertisementFacade.edit(modified);
		
		Advertisement actual;
		actual = advertisementFacade.findAdvertisementById(5);
		assertEquals(modified, actual);
		assertEquals(modified.getAnimal(), actual.getAnimal());
		assertEquals(modified.getAdvertiser(), actual.getAdvertiser());
		assertEquals(modified.getId(), actual.getId());
	}
	
	@Test
	public void testDeleteAdvertisementById(){
		Advertisement advertisement = new Advertisement();
		Advertiser advertiser = new User();
		Animal animal = new Animal();
		advertisement.setAnimal(animal);
		advertisement.setAdvertiser(advertiser);
		advertisement.setId(6);

		advertisementFacade.create(advertisement);
		
		advertisementFacade.deleteAdvertisementById(6);
		
		Advertisement actual;
		actual = advertisementFacade.findAdvertisementById(6);
		assertTrue(actual == null);
	}

}
