package br.inatel.dm110.poller.beans.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.dm110.poller.api.PollerService;
import br.inatel.dm110.poller.api.to.EquipamentListTO;
import br.inatel.dm110.poller.api.to.EquipamentTO;
import br.inatel.dm110.poller.beans.util.IpsUtil;
import br.inatel.dm110.poller.interfaces.PollerRemote;
import br.inatel.dm110.poller.mdb.impl.NetworkDiscoverySender;

@RequestScoped
public class PollerServiceImpl implements PollerService {

	@EJB(lookup = "java:app/dm110-poller-ejb-0.1-SNAPSHOT/PollerBeans!br.inatel.dm110.poller.interfaces.PollerRemote")
	private PollerRemote pollerRemote;

	@EJB
	private NetworkDiscoverySender sender;

	@Override
	public void startPoller(String ip, String mask) {

		List<EquipamentTO> equipaments = new ArrayList<>();

		try {
			String[] ipsGenerated = IpsUtil.generateIps(ip, Integer.parseInt(mask));
			for (int i = 0; i < ipsGenerated.length; i++) {
				String s = ipsGenerated[i];
				if (s.indexOf(".0") == -1 || s.indexOf(".255") == -1) {
					EquipamentTO equipamentTO = new EquipamentTO();
					equipamentTO.setAddress(s);
					equipaments.add(equipamentTO);
					if (i != 0 && i % 10 == 0) {
						if(i ==  ipsGenerated.length){
							EquipamentTO equipamentTO1 = new EquipamentTO();
							equipamentTO1.setAddress(ip);
							equipaments.add(equipamentTO1);
						}
						EquipamentListTO list = new EquipamentListTO(equipaments);
						
						sender.sendTextMessage(list);

						equipaments = new ArrayList<>();

					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	@Override
	public EquipamentTO getEquipament(String ip) {
		EquipamentTO equipamentTO = pollerRemote.consultarPorIP(ip);
		return equipamentTO;
	}

}
