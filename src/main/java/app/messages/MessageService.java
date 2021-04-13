package app.messages;

import java.util.List;

// import org.apache.commons.logging.LogFactory;
// import org.apache.commons.logging.Log;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageService {
	
	// private final static Log log = LogFactory.getLog(MessageService.class);
	private MessageRepository repository;
	
	public MessageService(MessageRepository repository) {
		this.repository = repository;
	}
	
	@SecurityCheck
	@Transactional(readOnly = true)
	public List<Message> getMessages() {
		return repository.getMessages();
	}
	
	@SecurityCheck
	// @Transactional(noRollbackFor = { UnsupportedOperationException.class })
	@Transactional
	public Message save(String text) {
		// this.repository.saveMessage(new Message(text));
		/* Message message = repository.saveMessage(new Message(text));
		log.info("New message[id=" + message.getId() + "] saved");
		updateStatistics();
		return message; */
		return repository.saveMessage(new Message(text));
	}
	
	/* private void updateStatistics() {
		throw new UnsupportedOperationException("This method is not implemented yet");
	} */
}
