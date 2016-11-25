package hu.bme.aut.sportnetwork.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.entity.Comment;
import hu.bme.aut.sportnetwork.entity.EventStatus;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.SportEventController;
import hu.bme.aut.sportnetwork.rest.UserController;

@Component
public class SportEventResourceAssembler extends
	ResourceAssemblerSupport<SportEvent, SportEventResource> {

	public SportEventResourceAssembler() {
		super(SportEventController.class, SportEventResource.class);
	}

	@Override
	public SportEventResource toResource(SportEvent entity) {
		SportEventResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	@Override
	protected SportEventResource instantiateResource(SportEvent entity) {
		SportEventResource resource = new SportEventResource();
		resource.setTitle(entity.getTitle());
		resource.setDate(entity.getDate());
		resource.setType(entity.getType());
		resource.setMaxSize(entity.getMaxSize());
		resource.setIsOpened(entity.getIsOpened());
		resource.setDescription(entity.getDescription());
		resource.setCity(entity.getAddress().getCity());
		resource.setAddress(entity.getAddress().getAddress());
		resource.setLevel(entity.getLevelIntervalFrom() == entity.getLevelIntervalTo() ? String.valueOf(entity.getLevelIntervalFrom()) :
			String.valueOf(entity.getLevelIntervalFrom()) + "-" + String.valueOf(entity.getLevelIntervalTo()));
		
		List<CommentWrapper> comments = new ArrayList<>();
		entity.getComments().forEach(c -> WrapperUtils.addComment(comments, c));
		resource.setComments(comments);
		
		List<UserLinkWrapper> members = new ArrayList<>();
		entity.getMembers().forEach(m -> WrapperUtils.addMember(members, m));
		resource.setMembers(members);
		
		resource.setOwner(entity.getOwner().getName());
		
		
		switch (entity.getStatus()) {
			case OWNER:
				resource.setStatus(0);
				break;
			case MEMBER:
				resource.setStatus(1);
				break;
			case APPLIED:
				resource.setStatus(2);
				break;
			case NOT_MEMBER:
				resource.setStatus(3);
		}
		
		resource.add(linkTo(UserController.class).slash(entity.getOwner().getId()).withRel(String.valueOf(entity.getOwner().getId())));
		return resource;
	}
	


}
