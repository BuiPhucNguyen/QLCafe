package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import connect.MyEMFactory;
import dao.DAO_DichVu;
import entity.Ban;
import entity.Nuoc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DAOImpl_DichVu extends UnicastRemoteObject implements DAO_DichVu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3164624122788286254L;
	
	private EntityManager em;

	public DAOImpl_DichVu() throws RemoteException{
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	@Override
	public List<Nuoc> getAllDichVu() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<Nuoc> list = new ArrayList<Nuoc>();
		try {
			tr.begin();

			list = em.createNativeQuery("select * from Nuoc", Nuoc.class).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return null;
	}

	@Override
	public boolean xoaDichVu(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Nuoc dv = em.find(Nuoc.class, id);
			em.remove(dv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean themDichVu(Nuoc dv) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(dv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean capnhatDichVu(Nuoc dv) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(dv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

}
