package hu.bme.aut.sportnetwork.rest.resources;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.rest.NotificationController;

@Component
public class NotificationResourceAssembler extends ResourceAssemblerSupport<Notification, NotificationResource> {

	public NotificationResourceAssembler() {
		super(NotificationController.class, NotificationResource.class);
	}

	@Override
	public NotificationResource toResource(Notification entity) {
		NotificationResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}

	public NotificationResource instantiateResource(Notification entity) {
		NotificationResource resource = new NotificationResource();

		resource.setNotId(entity.getId());

		if (entity.getSender() != null) {
			resource.setSender(new UserLinkWrapper(entity.getSender().getId(), entity.getSender().getName()));
		}

		if (entity.getEvent() != null) {
			resource.setEventId(entity.getEvent().getId());
		}

		if (entity.getConversation() != null) {
			resource.setConversationId(entity.getConversation().getId());
		}
		
		switch (entity.getType()) {
			case CONVERSATION:
				resource.setType(Notification.MESSAGE_NOTIFICATION);
				resource.setMessage(Notification.MESSAGE_MESSAGE);
				break;
			case FRIEND_REQUEST:
				resource.setType(Notification.FRIEND_REQUEST_NOTIFICATION);
				resource.setMessage(Notification.FRIEND_REQUEST_MESSAGE);
				break;
			case SIMPE_EVENT:
				resource.setType(Notification.EVENT_SIMPLE_NOTIFICATION);
				resource.setMessage(entity.getMessage());
				break;
			case EVENT_REQUEST:
				resource.setType(Notification.EVENT_REQUEST_NOTIFICATION);
				resource.setMessage(Notification.EVENT_REQUEST_MESSAGE);
				break;
			case EVENT_RATE:
			resource.setType(Notification.EVENT_RATE_NOTIFICATION);
				resource.setMessage(Notification.EVENT_RATE_MESSAGE);
				break;
			}

		return resource;
	}

}
