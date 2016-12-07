package hu.bme.aut.sportnetwork.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.MessageController;

@Component
public class ConversationResourceAssembler extends ResourceAssemblerSupport<Conversation, ConversationResource>{
	
	@Autowired
	private AuthOperations authOperations;

	public ConversationResourceAssembler() {
		super(MessageController.class, ConversationResource.class);
	}

	@Override
	public ConversationResource toResource(Conversation entity) {
		ConversationResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	@Override
	public ConversationResource instantiateResource(Conversation entity) {
		ConversationResource resource = new ConversationResource();
		
		resource.setDate(entity.getLastSendTime());
		resource.setMessage(entity.getLastMessage());
		resource.setPublicId(entity.getId());
		for (User u : entity.getParticipants()) {
			if (!u.getName().equals(authOperations.getLoggedInUserName())) {
				resource.setUserName(u.getName());
			}
		}

		return resource;
	}

}
