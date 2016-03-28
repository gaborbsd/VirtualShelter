package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.PictureFacade;
import hu.bme.aut.vshelter.entity.Picture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class PictureFacadeInMemoryImpl implements PictureFacade {

    private List<Picture> pictures = new ArrayList<Picture>();

    @Override
    public Picture findById(long pictureId) throws VirtualShelterException {
        try {
            return pictures.stream()
                    .filter(a -> a.getId() == pictureId).findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<Picture> findAll() throws VirtualShelterException {
        return Collections.unmodifiableList(pictures);
    }

    @Override
    public void create(Picture picture) throws VirtualShelterException {
        pictures.add(picture);
    }

    @Override
    public void edit(Picture picture) throws VirtualShelterException {
        pictures
                .set(pictures.indexOf(picture), picture);
    }

    @Override
    public void deleteById(long pictureId) throws VirtualShelterException {
        Picture deletePicture = findById(pictureId);

        if (deletePicture != null)
            pictures.remove(deletePicture);
    }
}
