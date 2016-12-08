package hu.aut.bme.sportnetwork.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.sportnetwork.api.MessageOperations;
import hu.bme.aut.sportnetwork.api.SportNetworkException;
import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.dal.ConversationReporitory;
import hu.bme.aut.sportnetwork.dal.MessageRepository;
import hu.bme.aut.sportnetwork.dal.NotificationRepository;
import hu.bme.aut.sportnetwork.dal.UserRepository;
import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.Message;
import hu.bme.aut.sportnetwork.entity.User;

public class MessageOperationsImpl implements MessageOperations {
	
	@Autowired
	private ConversationReporitory conversationRepository;

	@Autowired
	private UserRepository userRepositroy;

	@Autowired
	private NotificationRepository notificationRepositroy;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private AuthOperations authOperation;

	private static Logger LOGGER = LoggerFactory.getLogger(MessageOperations.class);

	@Override
	public List<Conversation> listConversatinsByUser() {
		return conversationRepository.findByParticipants(authOperation.getLoggedInUserName());
	}

	@Override
	public List<Message> listMessagesbyConversation(long conversationId) throws SportNetworkException {
		Conversation c = conversationRepository.findByMessages(conversationId);
		if (c == null) {
			throw new SportNetworkException("NO CONVERSATION WITH ID" + conversationId);
		}
		return c.getMessages();
	}

	@Override
	@Transactional
	public List<Message> writeToConversation(long conversationId, String message) throws SportNetworkException {
		User writer = authOperation.getLoggedInUser();
		
		if (!writer.getConversations().stream().anyMatch(c -> c.getId() == conversationId)) {
			throw new SportNetworkException("NOT ALLOWED TO WRITE HERE");
		}

		Conversation c = conversationRepository.findOne(conversationId);

		
		return writeToConversation(writer, c, message);
	}

	@Override
	public Conversation getConversationWithUser(String userName) {

		User writer = authOperation.getLoggedInUser();
		User writeTo = userRepositroy.findByName(userName);

		Optional<Conversation> conv = writer.getConversations().stream()
				.filter(c -> containsConversation(writeTo.getConversations(), c)).findFirst();
		
		if (conv.isPresent()) {
			return conv.get();
		}

		return createNewConversation(writer, writeTo);
	}

	private boolean containsConversation(List<Conversation> conversations, Conversation conv) {
		return conversations.stream().anyMatch(c -> c.getId() == conv.getId() && c.getMemberSize() == 2);
	}

	private Conversation createNewConversation(User writer, User writeTo) {
		Conversation c = new Conversation();

		c.getParticipants().add(writer);
		c.getParticipants().add(writeTo);
		c.setIsActive(false);
		c.setLastSendTime(new Date());
		c.setMemberSize(2);
		c = conversationRepository.save(c);

		return c;
	}
	
	private List<Message> writeToConversation(User writer, Conversation c, String message) {
		Date sendTime = new Date();
		
		Message m = new Message();
		
		m.setWriter(writer);
		m.setMessage(message);
		m.setSendTime(sendTime);
		m.setConversation(c);

		messageRepository.save(m);
		
		return conversationRepository.findByMessages(c.getId()).getMessages();

		/*
		 * Notification not = new MessageNotification(writer, c);
		 * 
		 * User sendTo =
		 * c.getUser1().getName().equals(authOperation.getLoggedInUserName()) ?
		 * c.getUser2() : c.getUser1(); sendTo.setHasNotification(true);
		 * 
		 * userRepositroy.save(sendTo);
		 */

		/*
		 * not.setOwner(sendTo); not.setSendTime(sendTime);
		 */
		
		// notificationRepositroy.save(not);
		
		/*
		 * c.setLastSendTime(sendTime); c.setLastMessage(message);
		 */
				
		/*
		 * if (!c.isActive()) { c.setActive(true); }
		 */
	}

}
