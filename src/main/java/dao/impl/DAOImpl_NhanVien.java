package dao.impl;

import connect.MyEMFactory;
import jakarta.persistence.EntityManager;

public class DAOImpl_NhanVien{
	private EntityManager em;

	public DAOImpl_NhanVien() {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
}
