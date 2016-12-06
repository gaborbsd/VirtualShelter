package hu.bme.aut.sportnetwork.rest.resources;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.FriendRequestNotification;
import hu.bme.aut.sportnetwork.rest.NotificationController;

@Component
public class NotificationResourceAssembler extends ResourceAssemblerSupport<Notification, NotificationResource> {

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
		/*
		 * resource.setNotId(entity.getNotificationId()); resource.setSender(new
		 * UserLinkWrapper(entity.getSender().getId(),
		 * entity.getSender().getName()));
		 * 
		 * if (entity instanceof EventNotification) {
		 * resource.setEventId(((EventNotification) entity).getEvent().getId());
		 * 
		 * 
		 * if (entity instanceof EventRequestNotification) {
		 * resource.setType(EventRequestNotification.EVENT_REQUEST_NOTIFICATION)
		 * ;
		 * resource.setMessage(EventRequestNotification.EVENT_REQUEST_MESSAGE);
		 * } else if (entity instanceof EventSimpleNotification) {
		 * resource.setType(EventSimpleNotification.EVENT_SIMPLE_NOTIFICATION);
		 * resource.setMessage(((EventSimpleNotification) entity).getMessage());
		 * } else if (entity instanceof EventRateNotification) {
		 * resource.setType(EventRateNotification.EVENT_RATE_NOTIFICATION);
		 * resource.setMessage(EventRateNotification.EVENT_RATE_MESSAGE); }
		 * 
		 * } else if (entity instanceof FriendRequestNotification) {
		 * resource.setType(FriendRequestNotification.
		 * FRIEND_REQUEST_NOTIFICATION);
		 * resource.setMessage(FriendRequestNotification.FRIEND_REQUEST_MESSAGE)
		 * ; } else if (entity instanceof MessageNotification) {
		 * resource.setType(MessageNotification.MESSAGE_NOTIFICATION);
		 * resource.setMessage(MessageNotification.MESSAGE_MESSAGE);
		 * resource.setConversationId(((MessageNotification)
		 * entity).getConversation().getId()); }
		 */

		return resource;
	}

}
