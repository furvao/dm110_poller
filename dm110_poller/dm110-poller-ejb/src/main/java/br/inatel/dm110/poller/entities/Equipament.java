package br.inatel.dm110.poller.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlTransient;

import br.inatel.dm110.poller.api.to.EquipamentTO;

@Entity
@SequenceGenerator(name = "seq_equipament", sequenceName = "seq_equipament", allocationSize = 1)
public class Equipament {

	@Id
	@GeneratedValue(generator = "seq_equipament", strategy = GenerationType.SEQUENCE)
	private Long id;
	private String address;
	private String status;

	public Equipament() {
		super();
	}
	
	public Equipament(EquipamentTO equipamentTO){
		this.id = equipamentTO.getId();
		this.address = equipamentTO.getAddress();
		this.status = equipamentTO.getStatus();
	}

	public Equipament(Long id, String address, String status) {
		super();
		this.id = id;
		this.address = address;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
