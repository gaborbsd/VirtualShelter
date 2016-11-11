package hu.bme.aut.sportnetwork.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.sportnetwork.api.IMessageOperations;
import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.Message;
import hu.bme.aut.sportnetwork.rest.resources.ConversationResource;
import hu.bme.aut.sportnetwork.rest.resources.ConversationResourceAssembler;
import hu.bme.aut.sportnetwork.rest.resources.GetConversationWithUserArg;
import hu.bme.aut.sportnetwork.rest.resources.MessageResource;
import hu.bme.aut.sportnetwork.rest.resources.MessageResourceAssembler;
import hu.bme.aut.sportnetwork.rest.resources.WriteMessageArg;

@RestController
@RequestMapping(value="messages")
public class MessageController {
	
	@Autowired
	private IMessageOperations messageOperation;
	
	@Autowired
	private ConversationResourceAssembler conversationResourceAssembler;
	
	@Autowired
	private MessageResourceAssembler messageResourceAssembler;
	
	@RequestMapping(value="conversations", method=RequestMethod.GET)
	ResponseEntity<List<ConversationResource>> findConversationsByUser() {
		List<Conversation> conversations = messageOperation.listConversatinsByUser();
		List<ConversationResource> resourceList = conversationResourceAssembler.toResources(conversations);
		return new ResponseEntity<List<ConversationResource>>(resourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<List<MessageResource>> listConversationMessages(@PathVariable Long id) {
		List<Message> messages = messageOperation.listMessagesbyConversation(id);
		List<MessageResource> resourceList = messageResourceAssembler.toResources(messages);
		return new ResponseEntity<List<MessageResource>>(resourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="conversation", method=RequestMethod.POST) 
	ResponseEntity<ConversationResource> getConversationWithUser(@RequestBody GetConversationWithUserArg arg) {
		Conversation c = messageOperation.getConversationWithUser(arg.getUserName());
		ConversationResource resource = conversationResourceAssembler.toResource(c);
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@RequestMapping(value="write", method=RequestMethod.POST) 
	ResponseEntity<ConversationResource> writeMessage(@RequestBody WriteMessageArg arg) {
		Conversation c = messageOperation.writeToConversation(arg.getConversationId(), arg.getMessage());
		ConversationResource resource = conversationResourceAssembler.toResource(c);
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

}
