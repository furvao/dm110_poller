package br.inatel.dm110.poller.entities.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.inatel.dm110.poller.entities.Equipament;

@Stateless
public class EquipamentDAO {

	@PersistenceContext(unitName = "equipament")
	private EntityManager entityManager;

	public Equipament salvar(Equipament equipament) throws Exception {
		if (equipament.getAddress() == null) {
			entityManager.persist(equipament);
		} else {
			equipament = entityManager.merge(equipament);
		}
		return equipament;
	}

	public void excluir(Long id) {
		Equipament equipament = entityManager.find(Equipament.class, id);
		entityManager.remove(equipament);
	}

//	public Equipament consultarPorId(Long id) {
//		Equipament equipament = null;
//		equipament = entityManager.find(Equipament.class, id);
//		return equipament;
//	}
	
	public Equipament consultarPorIp(String address) {
		Equipament equipament = null;
		equipament = entityManager.find(Equipament.class, address);
		return equipament;
	}
	
	public List<Equipament> consultar() {

		List<Equipament> equipaments = null;
		equipaments = entityManager.createQuery("from Equipament e", Equipament.class).getResultList();
		return equipaments;
	}

}
