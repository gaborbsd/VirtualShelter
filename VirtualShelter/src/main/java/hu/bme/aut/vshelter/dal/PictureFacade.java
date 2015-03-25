package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Picture;

import java.util.List;

public interface PictureFacade {
	
	Picture findPictureById(int pictureId);
	
	List<Picture> findAll();
	
	void create(Picture Picture);
	
	void edit(Picture Picture);
	
	void deletePictureById(int pictureId);

}
