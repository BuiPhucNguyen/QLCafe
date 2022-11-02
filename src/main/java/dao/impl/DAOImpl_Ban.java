package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import connect.MyEMFactory;
import dao.DAO_Ban;
import entity.Ban;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DAOImpl_Ban extends UnicastRemoteObject implements DAO_Ban {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7102566996126084166L;
	private EntityManager em;

	public DAOImpl_Ban() throws RemoteException{
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@Override
	public List<Ban> getAllBan() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<Ban> list = new ArrayList<Ban>();
		try {
			tr.begin();

			list = em.createNativeQuery("select * from Ban", Ban.class).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return null;
	}

	@Override
	public boolean xoaBan(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Ban b = em.find(Ban.class, id);
			em.remove(b);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean themBan(Ban b) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(b);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean capnhatBan(Ban b) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
//			nv.setTaiKhoan(em.find(NhanVien.class, nv.getMaNV()).getTaiKhoan());
			em.merge(b);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

}
