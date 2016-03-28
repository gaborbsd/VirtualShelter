package hu.bme.aut.vshelter.rest.resources;

import hu.bme.aut.vshelter.entity.Picture;
import hu.bme.aut.vshelter.rest.PictureController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Converts Picture entities to PictureResource objects
 * with links.
 *
 * @author Kiss László
 */
public class PictureResourceAssembler extends ResourceAssemblerSupport<Picture, PictureResource> {

    public PictureResourceAssembler() {
        super(PictureController.class, PictureResource.class);
    }

    /**
     * Public method for converting a Picture entity to PictureResource object.
     */
    @Override
    public PictureResource toResource(Picture entity) {
        PictureResource resource = createResourceWithId(entity.getId(), entity);
        return resource;
    }

    /**
     * Create the resource object, set it's values and add links to it.
     */
    @Override
    protected PictureResource instantiateResource(Picture entity) {
        PictureResource resource = new PictureResource();

        //TODO

        return resource;
    }

}
