package hu.aut.bme.sportnetwork.api.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.sportnetwork.api.MessageOperations;
import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.dal.ConversationDAO;
import hu.bme.aut.sportnetwork.dal.MessageDAO;
import hu.bme.aut.sportnetwork.dal.NotificationDAO;
import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.Message;
import hu.bme.aut.sportnetwork.entity.MessageNotification;
import hu.bme.aut.sportnetwork.entity.Notification;
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
	
	@Autowired
	private AuthOperations authOperation;

	private static Logger LOGGER = LoggerFactory.getLogger(MessageOperations.class);

	@Override
	public List<Conversation> listConversatinsByUser() {
		User writer = authOperation.getLoggedInUser();
		List<Conversation> ret = conversationRepository.findByUserAndActive(writer);
		return ret;
	}

	@Override
	public List<Message> listMessagesbyConversation(long conversationId) {
		Conversation c = conversationRepository.findOne(conversationId);
		return messageRepository.findByConversation(c, new Sort("sendTime"));
	}

	@Override
	@Transactional
	public List<Message> writeToConversation(long conversationId, String message) {
		User writer = authOperation.getLoggedInUser();
		
		Conversation c = conversationRepository.findOne(conversationId);
		
		return writeToConversation(writer, c, message);
	}

	@Override
	public Conversation getConversationWithUser(String userName) {
		User writer = authOperation.getLoggedInUser();
		User writeTo = userRepositroy.findByName(userName);
		Conversation c = conversationRepository.getByUser1AndUser2(writer, writeTo);
		
		if (c == null) {
			c = createNewConversation(writer, writeTo);
		} 
		return c;
	}

		private Conversation createNewConversation(User writer, User writeTo) {
			Conversation c;
			c = new Conversation();
			c.setUser1(writer);
			c.setUser2(writeTo);
			c.setActive(false);
		c.setLastSendTime(new Date());
			c = conversationRepository.save(c);
			LOGGER.info("conversation created : " + writer.getName() + " , " + writeTo.getName());
			return c;
		}
	
	private List<Message> writeToConversation(User writer, Conversation c, String message) {
		Date sendTime = new Date();
		
		Message m = new Message();
		m.setSender(writer);
		m.setMessage(message);
		m.setSendTime(sendTime);
		m.setConversation(c);
		
		messageRepository.save(m);
		
		Notification not = new MessageNotification(writer, c);

		User sendTo = c.getUser1().getName().equals(authOperation.getLoggedInUserName()) ? c.getUser2() : c.getUser1();
		sendTo.setHasNotification(true);

		userRepositroy.save(sendTo);

		not.setOwner(sendTo);
		not.setSendTime(sendTime);
		
		notificationRepositroy.save(not);
		
		c.setLastSendTime(sendTime);
		c.setLastMessage(message);
				
		if (!c.isActive()) {
			c.setActive(true);
		}
		
		Conversation co = conversationRepository.save(c);
		
		return listMessagesbyConversation(co.getId());
	}

}
