package br.inatel.dm110.poller.interfaces;

import br.inatel.dm110.poller.api.to.EquipamentTO;

public interface Poller {
	
	public void insert(String address, String status);

	public EquipamentTO consultarPorIP(String address);
}
