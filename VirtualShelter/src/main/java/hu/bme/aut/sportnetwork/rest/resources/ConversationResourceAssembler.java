package hu.bme.aut.sportnetwork.rest.resources;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.rest.MessageController;

@Component
public class ConversationResourceAssembler extends ResourceAssemblerSupport<Conversation, ConversationResource>{

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
		resource.setUserName(entity.getUser1().getName().equals("Andras") ? entity.getUser2().getName() : entity.getUser1().getName());
		
		return resource;
	}

}
