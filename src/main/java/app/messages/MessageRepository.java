package app.messages;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class MessageRepository {
	
	private final static Log log = LogFactory.getLog(MessageRepository.class);
	
	private SessionFactory sessionFactory;
	
	public MessageRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Message saveMessage(Message message) {
		// Insert message into DB
		// Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		session.save(message);
		
		log.info("Saved message: " + message.getText());
		return message;
	}
	
	public List<Message> getMessages() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Message";
		Query<Message> query = session.createQuery(hql, Message.class);
		return query.list();
	}
}
