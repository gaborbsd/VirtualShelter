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

	private static final String EVENT_REQUEST_MESSAGE = " wants to join your ";

	private static final String FRIEND_REQUEST_MESSAGE = " wants to be your friend ";

	private static final String MESSAGE_MESSAGE = " wrote to you ";

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
		resource.setNotId(entity.getNotificationId());
		resource.setSender(new UserLinkWrapper(entity.getSender().getId(), entity.getSender().getName()));

		if (entity instanceof EventNotification) {
			resource.setEventId(((EventNotification) entity).getEvent().getId());


			if (entity instanceof EventRequestNotification) {
				resource.setType(EVENT_REQUEST_NOTIFICATION);
				resource.setMessage(EVENT_REQUEST_MESSAGE);
			} else if (entity instanceof EventSimpleNotification) {
				resource.setType(EVENT_SIMPLE_NOTIFICATION);
				resource.setMessage(((EventSimpleNotification) entity).getMessage());
			}
		} else if (entity instanceof FriendRequestNotification) {
			resource.setType(FRIEND_REQUEST_NOTIFICATION);
			resource.setMessage(FRIEND_REQUEST_MESSAGE);
		} else if (entity instanceof MessageNotification) {
			resource.setType(MESSAGE_NOTIFICATION);
			resource.setMessage(MESSAGE_MESSAGE);
			resource.setConversationId(((MessageNotification) entity).getConversation().getId());
		}

		return resource;
	}

}
