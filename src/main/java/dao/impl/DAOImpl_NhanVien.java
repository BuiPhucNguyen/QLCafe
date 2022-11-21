package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import connect.MyEMFactory;
import dao.DAO_NhanVien;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DAOImpl_NhanVien extends UnicastRemoteObject implements DAO_NhanVien{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6580977133052118598L;
	private EntityManager em;

	public DAOImpl_NhanVien() throws RemoteException{
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	@Override
	public List<NhanVien> getAllNhanVien() {
		EntityTransaction tr = em.getTransaction();
		List<NhanVien> list = new ArrayList<NhanVien>();
		try {
			tr.begin();
			em.clear();
			list = em.createNativeQuery("select * from NhanVien", NhanVien.class).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return null;
	}
	
	@Override
	public NhanVien getNhanVienTheoMa(String id) {
		EntityTransaction tr = em.getTransaction();
		NhanVien nv = null;
		try {
			tr.begin();
			em.clear();
			nv = (NhanVien) em.createNativeQuery("select * from NhanVien where maNV = '"+id+"'", NhanVien.class).getSingleResult();
			
			tr.commit();
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return null;
	}
	
	@Override
	public boolean xoaNhanVien(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			NhanVien nv = em.find(NhanVien.class, id);
			TaiKhoan tk = nv.getTaiKhoan();
			em.remove(tk);
			em.remove(nv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
	@Override
	public boolean themNhanVien(NhanVien nv) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(nv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	@Override
	public boolean capnhatNhanVien(NhanVien nv) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
//			nv.setTaiKhoan(em.find(NhanVien.class, nv.getMaNV()).getTaiKhoan());
			em.merge(nv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	@Override
	public List<NhanVien> getAllNhanVienTheoChucVụ(String chucVu) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<NhanVien> list = new ArrayList<NhanVien>();
		try {
			tr.begin();
			em.clear();
			list = em.createNativeQuery("select * from NhanVien where chucVu = N'"+chucVu+"'", NhanVien.class).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
}
