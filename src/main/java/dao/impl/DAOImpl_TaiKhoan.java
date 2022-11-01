package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import connect.MyEMFactory;
import dao.DAO_TaiKhoan;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DAOImpl_TaiKhoan extends UnicastRemoteObject implements DAO_TaiKhoan {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6485872788865885968L;
	private EntityManager em;

	public DAOImpl_TaiKhoan() throws RemoteException{
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	@Override
	public boolean themTaiKhoan(TaiKhoan tk) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(tk);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
}
