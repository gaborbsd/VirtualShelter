package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Species;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-config.xml"})
public class BreedFacadeTest {
    @Autowired
    private BreedFacade breedFacade;

    @Test
    public void testPersist() {
        Breed breed = new Breed();
        Species species = new Species();
        breed.setBreedName("kutya");
        breed.setId(1);

        try {
            breedFacade.create(breed);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }
        List<Breed> breeds;
        try {
            breeds = breedFacade.findAll();
            assertTrue(breeds.contains(breed));
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }

    @Test
    public void testPersistNotAddedIsNotContains() {
        Breed breed = new Breed();
        breed.setBreedName("macska");
        breed.setId(2);

        List<Breed> breeds;
        try {
            breeds = breedFacade.findAll();
            assertFalse(breeds.contains(breed));
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }

    @Test
    public void testFindAdvertisementById() {
        Breed breed = new Breed();
        Breed actual;
        breed.setBreedName("leopárd");
        breed.setId(3);

        try {
            breedFacade.create(breed);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        try {
            actual = breedFacade.findById(3);
            assertEquals(breed, actual);
            assertEquals(breed.getBreedName(), actual.getBreedName());
            assertEquals(breed.getId(), actual.getId());
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }

    @Test
    public void testNoFindBreedByNotExistId() {
        Breed actual;
        try {
            actual = breedFacade.findById(4012);
            assertNull(actual);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }

    @Test
    public void testEditBreedt() {
        Breed breed = new Breed();
        breed.setBreedName("tigris");
        breed.setId(4);

        try {
            breedFacade.create(breed);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        Breed modified = new Breed();
        modified.setBreedName("majom");
        modified.setId(4);

        try {
            breedFacade.edit(modified);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        Breed actual;
        try {
            actual = breedFacade.findById(4);
            assertEquals(modified, actual);
            assertEquals(modified.getBreedName(), actual.getBreedName());
            assertEquals(breed.getId(), actual.getId());
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }

    @Test
    public void testDeleteBreedById() {
        Breed breed = new Breed();
        breed.setBreedName("aligátor");
        breed.setId(5);

        try {
            breedFacade.create(breed);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        try {
            breedFacade.deleteById(5);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        Breed actual;
        try {
            actual = breedFacade.findById(5);
            assertNull(actual);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());

        }

    }
}
