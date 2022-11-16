package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connect.MyEMFactory;
import dao.DAO_HoaDon;
import entity.Ban;
import entity.HoaDon;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DAOImpl_HoaDon extends UnicastRemoteObject implements DAO_HoaDon {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9056185793167280169L;
	private EntityManager em;

	public DAOImpl_HoaDon() throws RemoteException{
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@Override
	public boolean themHD(HoaDon hd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(hd);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<HoaDon> getAllHD() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			tr.begin();

			list = em.createNativeQuery("select * from HoaDon", HoaDon.class).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<HoaDon> getHDChuaThanhToan() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			tr.begin();

			list = em.createNativeQuery("select * from HoaDon where daThanhToan = 0", HoaDon.class).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public boolean xoaHD(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			HoaDon hd = em.find(HoaDon.class, id);
			em.remove(hd);
			tr.commit();
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Chỉ bàn trống mới được hủy!");
			tr.rollback();
			return false;
		}
	}

	@Override
	public HoaDon getHBTheoMa(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();

			HoaDon hd = (HoaDon) em.createNativeQuery("select * from HoaDon where maHD = N'"+id+"'", HoaDon.class).getSingleResult();
			
			tr.commit();
			return hd;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

}
