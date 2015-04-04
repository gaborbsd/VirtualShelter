package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;
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
		
		userFacade.create(user);
		List<User> users = userFacade.findAll();
		assertTrue(users.contains(user));
	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		User user = new User();
		user.setEmail("erdo@fa.hu");
		user.setId(2);
		
		List<User> users = userFacade.findAll();
		assertFalse(users.contains(users));
	}
	
	@Test
	public void testFindAdvertisementById(){
		User user = new User();
		User actual;
		user.setEmail("alma@alma.hu");
		user.setId(3);
		
		userFacade.create(user);
		
		actual = userFacade.findUserById(3);
		assertEquals(user, actual);
		assertEquals(user.getEmail(), actual.getEmail());
		assertEquals(user.getId(), actual.getId());
	}
	
	@Test
	public void testNoFindUsersByNotExistId(){
		User actual;
		actual = userFacade.findUserById(4012);
		assertNull(actual);
	}
	
	@Test
	public void testEditUserst(){
		User user = new User();
		user.setEmail("tiger@hotmail.com");
		user.setId(4);
		
		userFacade.create(user);
		
		User modified = new User();
		modified.setEmail("monkey@hotmail.com");
		modified.setId(4);
		
		userFacade.edit(modified);
		
		User actual;
		actual = userFacade.findUserById(4);
		assertEquals(modified, actual);
		assertEquals(modified.getEmail(), actual.getEmail());
		assertEquals(user.getId(), actual.getId());
	}
	
	@Test
	public void testDeleteUsersById(){
		User user = new User();
		user.setEmail("crocodile@animals.hu");
		user.setId(5);
		
		userFacade.create(user);
		
		userFacade.deleteUserById(5);
		
		User actual;
		actual = userFacade.findUserById(5);
		assertNull(actual);
	}
	
	//Todo role and email tests
}
