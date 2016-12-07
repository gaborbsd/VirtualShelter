package hu.aut.bme.sportnetwork.api.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.sportnetwork.api.MessageOperations;
import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.dal.ConversationReporitory;
import hu.bme.aut.sportnetwork.dal.MessageDAO;
import hu.bme.aut.sportnetwork.dal.NotificationRepository;
import hu.bme.aut.sportnetwork.dal.UserRepository;
import hu.bme.aut.sportnetwork.dal.impl.MessageDAOImpl;
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

	private MessageDAO messageRepository;

	@Autowired
	private AuthOperations authOperation;

	private static Logger LOGGER = LoggerFactory.getLogger(MessageOperations.class);

	@PostConstruct
	public void init() {

		messageRepository = new MessageDAOImpl();

	}

	@Override
	public List<Conversation> listConversatinsByUser() {
		User writer = authOperation.getLoggedInUser();
		return conversationRepository.findByParticipants(writer.getName());
	}

	@Override
	public List<Message> listMessagesbyConversation(long conversationId) {
		Conversation c = conversationRepository.findOne(conversationId);
		return messageRepository.findByConversation(c);
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
		/*
		 * User writer = authOperation.getLoggedInUser(); User writeTo =
		 * userRepositroy.findByName(userName); Conversation c =
		 * conversationRepository.getByUser1AndUser2(writer, writeTo);
		 * 
		 * if (c == null) { c = createNewConversation(writer, writeTo); } return
		 * c;
		 */
		return null;
	}

		private Conversation createNewConversation(User writer, User writeTo) {
		Conversation c = null;
		/*
		 * c = new Conversation(); c.setUser1(writer); c.setUser2(writeTo);
		 * c.setActive(false); c.setLastSendTime(new Date());
		 */
			c = conversationRepository.save(c);
			return c;
		}
	
	private List<Message> writeToConversation(User writer, Conversation c, String message) {
		Date sendTime = new Date();
		
		Message m = new Message();
		/*
		 * m.setSender(writer); m.setMessage(message); m.setSendTime(sendTime);
		 * m.setConversation(c);
		 */
		
		messageRepository.save(m);
		
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
		
		Conversation co = conversationRepository.save(c);
		
		return listMessagesbyConversation(co.getId());
	}

}
