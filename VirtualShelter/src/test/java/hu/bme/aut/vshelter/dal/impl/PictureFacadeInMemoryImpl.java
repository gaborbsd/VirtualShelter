package hu.bme.aut.vshelter.dal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import hu.bme.aut.vshelter.dal.PictureFacade;
import hu.bme.aut.vshelter.entity.Picture;

public class PictureFacadeInMemoryImpl implements PictureFacade {

	private List<Picture> pictures = new ArrayList<Picture>();

	@Override
	public Picture findPictureById(long pictureId) {
		try {
			return pictures.stream()
					.filter(a -> a.getId() == pictureId).findFirst()
					.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public List<Picture> findAll() {
		return Collections.unmodifiableList(pictures);
	}

	@Override
	public void create(Picture picture) {
		pictures.add(picture);
	}

	@Override
	public void edit(Picture picture) {
		pictures
			.set(pictures.indexOf(picture), picture);
	}

	@Override
	public void deletePictureById(long pictureId) {
		Picture deletePicture = findPictureById(pictureId);

		if (deletePicture != null)
			pictures.remove(deletePicture);
	}
}
