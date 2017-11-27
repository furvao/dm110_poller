package br.inatel.dm110.poller.mdb.impl;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import br.inatel.dm110.poller.api.to.EquipamentListTO;

@Stateless
public class NetworkDiscoverySender {
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/dm110_poller_queue")
	private Queue queue;

	public void sendTextMessage(EquipamentListTO list) {
		try (Connection connection = connectionFactory.createConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(queue);) {

			ObjectMessage message = session.createObjectMessage(list);

			producer.send(message);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}