package br.inatel.dm110.poller.api.to;

import java.io.Serializable;
import java.util.List;

public class EquipamentListTO implements Serializable{

	private List<EquipamentTO> list;

	public EquipamentListTO(List<EquipamentTO> list) {
		super();
		this.list = list;
	}

	public List<EquipamentTO> getList() {
		return list;
	}

	public void setList(List<EquipamentTO> list) {
		this.list = list;
	}
	
	
}
