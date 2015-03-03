package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.dal.AnimalFacade;
import hu.bme.aut.vshelter.entity.Animal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/animal")
public class AnimalController {
	@Autowired
	private AnimalFacade animalFacade;

	@Autowired
	private AnimalResourceAssembler animalResourceAssembler;

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<AnimalResource> addAnimal(@RequestBody Animal animal) {
		animalFacade.create(animal);
		AnimalResource resource = animalResourceAssembler.toResource(animal);
		return new ResponseEntity<AnimalResource>(resource, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<AnimalResource>> findAllAnimals() {
		List<Animal> animals = animalFacade.findAll();
		List<AnimalResource> resourceList = animalResourceAssembler
				.toResources(animals);
		return new ResponseEntity<List<AnimalResource>>(resourceList,
				HttpStatus.OK);
	}
}
