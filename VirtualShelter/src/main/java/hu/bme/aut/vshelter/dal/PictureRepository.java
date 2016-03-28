package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Picture;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends AbstractRepository<Picture>, PictureRepositoryCustom {

}
