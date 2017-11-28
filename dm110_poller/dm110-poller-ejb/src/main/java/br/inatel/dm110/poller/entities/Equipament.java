package br.inatel.dm110.poller.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.inatel.dm110.poller.api.to.EquipamentTO;

@Entity
public class Equipament {

	@Id
	private String address;
	private String status;

	public Equipament() {
		super();
	}
	
	public Equipament(EquipamentTO equipamentTO){
		this.address = equipamentTO.getAddress();
		this.status = equipamentTO.getStatus();
	}

	public Equipament(Long id, String address, String status) {
		super();
		this.address = address;
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
}
