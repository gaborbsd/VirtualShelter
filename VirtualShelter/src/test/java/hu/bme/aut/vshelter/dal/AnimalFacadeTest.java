package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-config.xml"})
public class AnimalFacadeTest {
    @Autowired
    private AnimalFacade animalFacade;

    @Test
    public void testPersist() {
        Animal animal = new Animal();
        animal.setName("Cirmi");
        animal.setId(1);
        try {
            animalFacade.create(animal);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }
        List<Animal> animals;
        try {
            animals = animalFacade.findAll();
            assertTrue(animals.contains(animal));
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }
    }

    @Test
    public void testPersistNotAddedIsNotContains() {
        Animal animal = new Animal();
        animal.setName("Jankó");
        animal.setId(2);

        List<Animal> animals;
        try {
            animals = animalFacade.findAll();
            assertFalse(animals.contains(animal));
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }

    @Test
    public void testFindAdvertisementById() {
        Animal animal = new Animal();
        Animal actual;
        animal.setName("Kormos");
        animal.setDescription("morcos fekete cica");
        animal.setId(3);


        try {
            animalFacade.create(animal);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        try {
            actual = animalFacade.findById(3);
            assertEquals(animal, actual);
            assertEquals(animal.getName(), actual.getName());
            assertEquals(animal.getDescription(), actual.getDescription());
            assertEquals(animal.getId(), actual.getId());
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }
    }

    @Test
    public void testNoFindAnimalByNotExistId() {
        Animal actual;
        try {
            actual = animalFacade.findById(4012);
            assertNull(actual);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }
    }

    @Test
    public void testEditAnimal() {
        Animal animal = new Animal();
        animal.setName("Vörös");
        animal.setId(4);

        try {
            animalFacade.create(animal);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        Animal modified = new Animal();
        modified.setName("Veres");
        animal.setDescription("aranyos vörös cica");
        modified.setId(4);

        try {
            animalFacade.edit(modified);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        Animal actual;
        try {
            actual = animalFacade.findById(4);
            assertEquals(modified, actual);
            assertEquals(modified.getName(), actual.getName());
            assertEquals(modified.getDescription(), actual.getDescription());
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }
    }

    @Test
    public void testDeleteAnimalById() {
        Animal animal = new Animal();
        animal.setName("Fehérke");
        animal.setId(5);

        try {
            animalFacade.create(animal);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        try {
            animalFacade.deleteById(5);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        Animal actual;
        try {
            actual = animalFacade.findById(5);
            assertNull(actual);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }
}
