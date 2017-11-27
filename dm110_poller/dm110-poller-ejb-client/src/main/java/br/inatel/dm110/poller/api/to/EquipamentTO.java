package br.inatel.dm110.poller.api.to;

import java.io.Serializable;

public class EquipamentTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1855794205501127544L;
	private Long id;
	private String address;
	private String status;

	public EquipamentTO() {
		super();
	}

	public EquipamentTO(Long id, String address, String status) {
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
