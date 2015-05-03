package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.User;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class UserFacadeTest {
	@Autowired
	private UserFacade userFacade;
	
	@Test
	public void testPersist(){
		User user = new User();
		user.setEmail("aldar@aladar.hu");
		user.setId(1);
		
		try {
			userFacade.create(user);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		List<User> users;
		try {
			users = userFacade.findAll();
			assertTrue(users.contains(user));
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		User user = new User();
		user.setEmail("erdo@fa.hu");
		user.setId(2);
		
		List<User> users;
		try {
			users = userFacade.findAll();
			assertFalse(users.contains(users));
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testFindAdvertisementById(){
		User user = new User();
		User actual;
		user.setEmail("alma@alma.hu");
		user.setId(3);
		
		try {
			userFacade.create(user);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		try {
			actual = userFacade.findById(3);
			assertEquals(user, actual);
			assertEquals(user.getEmail(), actual.getEmail());
			assertEquals(user.getId(), actual.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testNoFindUsersByNotExistId(){
		User actual;
		try {
			actual = userFacade.findById(4012);
			assertNull(actual);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testEditUserst(){
		User user = new User();
		user.setEmail("tiger@hotmail.com");
		user.setId(4);
		
		try {
			userFacade.create(user);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		User modified = new User();
		modified.setEmail("monkey@hotmail.com");
		modified.setId(4);
		
		try {
			userFacade.edit(modified);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		User actual;
		try {
			actual = userFacade.findById(4);
			assertEquals(modified, actual);
			assertEquals(modified.getEmail(), actual.getEmail());
			assertEquals(user.getId(), actual.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	@Test
	public void testDeleteUsersById(){
		User user = new User();
		user.setEmail("crocodile@animals.hu");
		user.setId(5);
		
		try {
			userFacade.create(user);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		try {
			userFacade.deleteById(5);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}
		
		User actual;
		try {
			actual = userFacade.findById(5);
			assertNull(actual);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("VirtualShelterException dobódott"+ e.getMessage());
		}

	}
	
	//Todo role and email tests
}
