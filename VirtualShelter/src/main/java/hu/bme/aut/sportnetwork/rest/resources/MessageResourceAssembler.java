package hu.bme.aut.sportnetwork.rest.resources;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.Message;
import hu.bme.aut.sportnetwork.rest.MessageController;

@Component
public class MessageResourceAssembler extends ResourceAssemblerSupport<Message, MessageResource>{

	public MessageResourceAssembler() {
		super(MessageController.class, MessageResource.class);
	}

	@Override
	public MessageResource toResource(Message entity) {
		MessageResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	@Override
	public MessageResource instantiateResource(Message entity) {
		MessageResource resource = new MessageResource();
		/*
		 * resource.setMessage(entity.getMessage());
		 * resource.setDate(entity.getSendTime());
		 * resource.setSender(entity.getSender().getName());
		 */
		
		return resource;
	}

}
