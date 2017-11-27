package br.inatel.dm110.poller.mdb;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.inatel.dm110.poller.api.to.EquipamentListTO;
import br.inatel.dm110.poller.api.to.EquipamentTO;
import br.inatel.dm110.poller.entities.Equipament;
import br.inatel.dm110.poller.entities.dao.EquipamentDAO;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/dm110_poller_queue"),
		/*
		 * @ActivationConfigProperty(propertyName = "maxSession", propertyValue
		 * = "5")
		 */ })
public class PollerMDB implements MessageListener {

	@EJB
	private EquipamentDAO equipamentDAO;

	@Override
	public void onMessage(Message message) {

		try {
			System.out.println("### Iniciando processamento...");
			if (message instanceof ObjectMessage) {

				ObjectMessage objectMessage = (ObjectMessage) message;
				
				EquipamentListTO list = (EquipamentListTO) objectMessage.getObject();

				if (list != null) {

					for (EquipamentTO e : list.getList()) {

						try {

							InetAddress ia = InetAddress.getByName(e.getAddress());

							boolean reachable = ia.isReachable(1000);
							String status = reachable ? "Ativo" : "Inativo";
							System.out.println(e.getAddress() + " - " + status);
							
							Equipament equipament = new Equipament(e);
							equipament.setStatus(status);
							
							equipamentDAO.salvar(equipament);

						} catch (UnknownHostException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (Exception e1) {
							e1.printStackTrace();
						}

					}
				}

			}

			System.out.println("### ### Finalizando processamento...");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
