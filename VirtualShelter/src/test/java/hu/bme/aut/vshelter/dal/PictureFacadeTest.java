package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;
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

		pictureFacade.create(picture);
		List<Picture> pictures = pictureFacade.findAll();
		assertTrue(pictures.contains(picture));
	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Picture picture = new Picture();
		picture.setId(2);
		
		List<Picture> pictures = pictureFacade.findAll();
		assertFalse(pictures.contains(picture));
	}
	
	@Test
	public void testFindAdvertisementById(){
		Picture picture = new Picture();
		Picture actual;
		
		picture.setId(3);
		
		pictureFacade.create(picture);
		
		actual = pictureFacade.findById(3);
		assertEquals(picture, actual);
		assertEquals(picture.getId(), actual.getId());
	}
	
	@Test
	public void testNoFindPictureByNotExistId(){
		Picture actual;
		actual = pictureFacade.findById(4012);
		assertNull(actual);
	}
	
	@Test
	public void testEditPicturet(){
		Picture picture = new Picture();

		picture.setId(4);
		
		pictureFacade.create(picture);
		
		Picture modified = new Picture();

		modified.setId(4);
		
		pictureFacade.edit(modified);
		
		Picture actual;
		actual = pictureFacade.findById(4);
		assertEquals(modified, actual);

		assertEquals(picture.getId(), actual.getId());
	}
	
	@Test
	public void testDeletePictureById(){
		Picture picture = new Picture();

		picture.setId(5);
		
		pictureFacade.create(picture);
		
		pictureFacade.deleteById(5);
		
		Picture actual;
		actual = pictureFacade.findById(5);
		assertNull(actual);
	}
}
