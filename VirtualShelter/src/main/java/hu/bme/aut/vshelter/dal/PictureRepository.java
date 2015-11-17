package hu.bme.aut.vshelter.dal;

import org.springframework.stereotype.Repository;

import hu.bme.aut.vshelter.entity.Picture;

@Repository
public interface PictureRepository extends AbstractRepository<Picture>, PictureRepositoryCustom {

}
