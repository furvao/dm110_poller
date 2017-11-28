package br.inatel.dm110.poller.mdb;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

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
import br.inatel.dm110.poller.interfaces.PollerLocal;
import br.inatel.dm110.poller.interfaces.PollerRemote;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/dm110_poller_queue"),
		/*
		 * @ActivationConfigProperty(propertyName = "maxSession", propertyValue
		 * = "5")
		 */ })
public class PollerMDB implements MessageListener {

	@EJB
	private PollerLocal pollerLocal;

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

							boolean reachable = execPing(e.getAddress());
							String status = reachable ? "Ativo" : "Inativo";
							System.out.println(e.getAddress() + " - " + status);
							
							pollerLocal.insert(e.getAddress(), status);
							
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
	
	public static boolean execPing(String address) {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("ping -n 1 " + address);
			InputStream is = process.getInputStream();
			InputStream es = process.getErrorStream();
			String input = processStream(is);
			String error = processStream(es);
			int code = process.waitFor();
			if (code != 0) {
				return false;
			}
			if (error.length() > 0) {
				return false;
			}
			if (input.contains("Host de destino inacess")) {
				return false;
			}
			return true;
		} catch (IOException | InterruptedException e) {
			return false;
		}
	}

	public static String processStream(InputStream is) {
		StringBuilder input = new StringBuilder();
		try (Scanner scanner = new Scanner(is)) {
			while (scanner.hasNextLine()) {
				input.append(scanner.nextLine()).append("\n");
			}
		}
		return input.toString();
	}
}
