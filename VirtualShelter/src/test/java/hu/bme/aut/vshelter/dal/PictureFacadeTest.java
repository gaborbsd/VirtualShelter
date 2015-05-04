package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Picture;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class PictureFacadeTest {
	@Autowired
	private PictureFacade pictureFacade;
	
	@Test
	public void testPersist(){
		Picture picture = new Picture();
		picture.setId(1);

		try {
			pictureFacade.create(picture);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		List<Picture> pictures;
		try {
			pictures = pictureFacade.findAll();
			assertTrue(pictures.contains(picture));
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Picture picture = new Picture();
		picture.setId(2);
		
		List<Picture> pictures;
		try {
			pictures = pictureFacade.findAll();
			assertFalse(pictures.contains(picture));
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testFindAdvertisementById(){
		Picture picture = new Picture();
		Picture actual;
		
		picture.setId(3);
		
		try {
			pictureFacade.create(picture);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		try {
			actual = pictureFacade.findById(3);
			assertEquals(picture, actual);
			assertEquals(picture.getId(), actual.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testNoFindPictureByNotExistId(){
		Picture actual;
		try {
			actual = pictureFacade.findById(4012);
			assertNull(actual);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
	}
	
	@Test
	public void testEditPicturet(){
		Picture picture = new Picture();

		picture.setId(4);
		
		try {
			pictureFacade.create(picture);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Picture modified = new Picture();

		modified.setId(4);
		
		try {
			pictureFacade.edit(modified);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Picture actual;
		try {
			actual = pictureFacade.findById(4);
			assertEquals(modified, actual);
			assertEquals(picture.getId(), actual.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testDeletePictureById(){
		Picture picture = new Picture();

		picture.setId(5);
		
		try {
			pictureFacade.create(picture);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		try {
			pictureFacade.deleteById(5);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		Picture actual;
		try {
			actual = pictureFacade.findById(5);
			assertNull(actual);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
}
