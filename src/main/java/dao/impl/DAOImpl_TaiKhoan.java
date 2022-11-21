package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import connect.MyEMFactory;
import dao.DAO_TaiKhoan;
import entity.NhanVien;
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
	
	@Override
	public List<TaiKhoan> getAllTaiKhoan() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<TaiKhoan> list = new ArrayList<TaiKhoan>();
		try {
			tr.begin();
			em.clear();
			list = em.createNativeQuery("select * from TaiKhoan", TaiKhoan.class).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return null;
	}
	@Override
	public boolean capnhatTaiKhoan(TaiKhoan tk) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
//			nv.setTaiKhoan(em.find(NhanVien.class, nv.getMaNV()).getTaiKhoan());
			em.merge(tk);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
}
