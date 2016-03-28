package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Institution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-config.xml"})
public class InstitutionFacadeTest {

    @Autowired
    private InstitutionFacade institutionFacade;

    @Test
    public void testPersist() {
        Institution institution = new Institution();
        institution.setTaxNumber("123455");
        institution.setId(1);

        try {
            institutionFacade.create(institution);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }
        List<Institution> institutions;
        try {
            institutions = institutionFacade.findAll();
            assertTrue(institutions.contains(institution));
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }

    @Test
    public void testPersistNotAddedIsNotContains() {
        Institution institution = new Institution();
        institution.setTaxNumber("3353455");
        institution.setId(2);

        List<Institution> institutions;
        try {
            institutions = institutionFacade.findAll();
            assertFalse(institutions.contains(institution));
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }

    @Test
    public void testFindAdvertisementById() {
        Institution institution = new Institution();
        Institution actual;
        institution.setTaxNumber("2456345634");
        institution.setId(3);

        try {
            institutionFacade.create(institution);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        try {
            actual = institutionFacade.findById(3);
            assertEquals(institution, actual);
            assertEquals(institution.getTaxNumber(), actual.getTaxNumber());
            assertEquals(institution.getId(), actual.getId());
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }

    @Test
    public void testNoFindInstitutionByNotExistId() {
        Institution actual;
        try {
            actual = institutionFacade.findById(4012);
            assertNull(actual);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }

    @Test
    public void testEditInstitutiont() {
        Institution institution = new Institution();
        institution.setTaxNumber("3456345634");
        institution.setId(4);

        try {
            institutionFacade.create(institution);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        Institution modified = new Institution();
        modified.setTaxNumber("456345634");
        modified.setId(4);

        try {
            institutionFacade.edit(modified);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        Institution actual;
        try {
            actual = institutionFacade.findById(4);
            assertEquals(modified, actual);
            assertEquals(modified.getTaxNumber(), actual.getTaxNumber());
            assertEquals(institution.getId(), actual.getId());
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }

    @Test
    public void testDeleteInstitutionById() {
        Institution institution = new Institution();
        institution.setTaxNumber("45634563");
        institution.setId(5);

        try {
            institutionFacade.create(institution);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        try {
            institutionFacade.deleteById(5);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        Institution actual;
        try {
            actual = institutionFacade.findById(5);
            assertNull(actual);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

    }
}
