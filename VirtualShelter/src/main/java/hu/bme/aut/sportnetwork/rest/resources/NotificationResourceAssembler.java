package hu.bme.aut.sportnetwork.rest.resources;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.EventSimpleNotification;
import hu.bme.aut.sportnetwork.entity.EventNotification;
import hu.bme.aut.sportnetwork.entity.EventRequestNotification;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.FriendRequestNotification;
import hu.bme.aut.sportnetwork.entity.MessageNotification;
import hu.bme.aut.sportnetwork.rest.NotificationController;

@Component
public class NotificationResourceAssembler extends ResourceAssemblerSupport<Notification, NotificationResource> {

	private static final int EVENT_REQUEST_NOTIFICATION = 2;

	private static final int EVENT_SIMPLE_NOTIFICATION = 3;

	private static final int FRIEND_REQUEST_NOTIFICATION = 4;

	private static final int MESSAGE_NOTIFICATION = 5;

	public NotificationResourceAssembler() {
		super(NotificationController.class, NotificationResource.class);
	}

	@Override
	public NotificationResource toResource(Notification entity) {
		NotificationResource resource = createResourceWithId(entity.getNotificationId(), entity);
		return resource;
	}

	public NotificationResource instantiateResource(Notification entity) {
		NotificationResource resource = new NotificationResource();
		resource.setMessage(entity.getMessage());
		resource.setSender(new UserLinkWrapper(entity.getOwner().getId(), entity.getOwner().getName()));

		if (entity instanceof EventNotification) {
			resource.setEventId(((EventNotification) entity).getEvent().getId());
			resource.setType(EVENT_SIMPLE_NOTIFICATION);

			if (entity instanceof EventRequestNotification) {
				resource.setType(EVENT_REQUEST_NOTIFICATION);
			}
		} else if (entity instanceof FriendRequestNotification) {
			resource.setType(FRIEND_REQUEST_NOTIFICATION);
		} else if (entity instanceof MessageNotification) {
			resource.setType(MESSAGE_NOTIFICATION);
			resource.setConversationId(((MessageNotification) entity).getConversation().getId());
		}

		return resource;
	}

}
