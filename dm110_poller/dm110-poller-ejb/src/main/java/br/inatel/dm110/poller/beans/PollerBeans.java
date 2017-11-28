package br.inatel.dm110.poller.beans;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.inatel.dm110.poller.api.to.EquipamentTO;
import br.inatel.dm110.poller.entities.Equipament;
import br.inatel.dm110.poller.entities.dao.EquipamentDAO;
import br.inatel.dm110.poller.interfaces.Poller;
import br.inatel.dm110.poller.interfaces.PollerLocal;
import br.inatel.dm110.poller.interfaces.PollerRemote;

@Stateless
@Local(PollerLocal.class)
@Remote(PollerRemote.class)
public class PollerBeans implements Poller {

	@EJB
	private EquipamentDAO equipamentDAO;

	@Override
	public void insert(String address, String status) {
		try {

			Equipament toEdit = equipamentDAO.consultarPorIp(address);
			if (toEdit != null) {

				toEdit.setStatus(status);
				equipamentDAO.salvar(toEdit);
			} else {

				Equipament equipament = new Equipament();
				equipament.setAddress(address);
				equipament.setStatus(status);
				equipamentDAO.salvar(equipament);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public EquipamentTO consultarPorIP(String address) {

		Equipament toEdit = equipamentDAO.consultarPorIp(address);
		EquipamentTO equipamentTO = null;
		if (toEdit != null) {
			System.out.println(toEdit.getAddress());
			equipamentTO = new EquipamentTO();
			equipamentTO.setAddress(toEdit.getAddress());
			equipamentTO.setStatus(toEdit.getStatus());
		}
		return equipamentTO;
	}

}
