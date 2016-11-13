package hu.aut.bme.sportnetwork.api.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import hu.bme.aut.sportnetwork.api.MessageOperations;
import hu.bme.aut.sportnetwork.dal.ConversationDAO;
import hu.bme.aut.sportnetwork.dal.MessageDAO;
import hu.bme.aut.sportnetwork.dal.NotificationDAO;
import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.Message;
import hu.bme.aut.sportnetwork.entity.MessageNotification;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.NotificationStatus;
import hu.bme.aut.sportnetwork.entity.User;

public class MessageOperationsImpl implements MessageOperations {
	
	@Autowired
	private ConversationDAO conversationRepository;
	
	@Autowired
	private UserDAO userRepositroy;
	
	@Autowired
	private NotificationDAO notificationRepositroy;
	
	@Autowired
	private MessageDAO messageRepository;

	@Override
	public List<Conversation> listConversatinsByUser() {
		User writer = userRepositroy.findByName("Andras");
		List<Conversation> ret = conversationRepository.findByUser1AndActiveTrueOrUser2AndActiveTrue(writer, writer, new Sort(Sort.Direction.DESC, "lastSendTime"));
		return ret;
	}

	@Override
	public List<Message> listMessagesbyConversation(long conversationId) {
		Conversation c = conversationRepository.findOne(conversationId);
		return messageRepository.findByConversation(c, new Sort("sendTime"));
	}

	@Override
	public Conversation writeToConversation(long conversationId, String message) {
		User writer = userRepositroy.findByName("Andras");
		
		Conversation c = conversationRepository.findOne(conversationId);
		
		return writeToConversation(writer, c, message);
	}

	@Override
	public Conversation getConversationWithUser(String userName) {
		User writer = userRepositroy.findByName("Andras");
		User writeTo = userRepositroy.findByName(userName);
		Conversation c = conversationRepository.own(writer, writeTo);
		
		if (c == null) {
			c = new Conversation();
			c.setUser1(writer);
			c.setUser2(writeTo);
			c.setActive(false);
			c.setLastSendTime(new Date());
			c = conversationRepository.save(c);
		} 
		return c;
	}
	
	private Conversation writeToConversation(User writer, Conversation c, String message) {
		Date sendTime = new Date();
		
		Message m = new Message();
		m.setSender(writer);
		m.setMessage(message);
		m.setSendTime(sendTime);
		m.setConversation(c);
		
		messageRepository.save(m);
		
		Notification not = new MessageNotification(writer, c);
		not.setOwner(c.getUser1().getName() == "Andras" ? c.getUser2() : c.getUser1());
		not.setMessage(writer.getName() + "has been written you");
		not.setSendTime(sendTime);
		
		notificationRepositroy.save(not);
		
		c.setLastSendTime(sendTime);
		c.setLastMessage(message);
				
		if (!c.isActive()) {
			c.setActive(true);
		}
		
		Conversation ret = conversationRepository.save(c);
		
		return ret;
	}

}
