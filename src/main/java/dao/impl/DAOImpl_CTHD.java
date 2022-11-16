package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import connect.MyEMFactory;
import dao.DAO_CTHD;
import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DAOImpl_CTHD extends UnicastRemoteObject implements DAO_CTHD {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3160744109817765868L;
	private EntityManager em;

	public DAOImpl_CTHD() throws RemoteException {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@Override
	public boolean themHD(ChiTietHoaDon cthd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql = "INSERT INTO [QuanLyCafe].[dbo].[ChiTietHoaDon]\r\n" + "           ([maHD]\r\n"
					+ "           ,[maNuoc]\r\n" + "           ,[soLuong])\r\n" + "     VALUES\r\n" + "           ('"
					+ cthd.getMaHoaDon().getMaHD() + "'\r\n" + "           ,'" + cthd.getMaNuoc().getMaNuoc() + "'\r\n"
					+ "           ," + cthd.getSoLuong() + ")";
			em.createNativeQuery(sql).executeUpdate();
			tr.commit();
//			em.persist(cthd);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<ChiTietHoaDon> getCTHDTheoMa(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
		try {
			tr.begin();

			list = em.createNativeQuery("select * from ChiTietHoaDon where maHD = N'" + id + "'", ChiTietHoaDon.class)
					.getResultList();

			tr.commit();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public boolean capNhatSL(String maHD, String maNuoc, int new_SL) throws RemoteException {
		EntityTransaction tr = em.getTransaction();

		try {
			tr.begin();
			String sql = "UPDATE [QuanLyCafe].[dbo].[ChiTietHoaDon]\r\n" + "SET [soLuong] = " + new_SL + "\r\n"
					+ "WHERE maHD = N'" + maHD + "' and maNuoc = N'" + maNuoc + "'";
			em.createNativeQuery(sql).executeUpdate();

			tr.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean xoaCTHD(String maHD, String maNuoc) throws RemoteException {
		EntityTransaction tr = em.getTransaction();

		try {
			tr.begin();
			String sql = "DELETE FROM [QuanLyCafe].[dbo].[ChiTietHoaDon]\r\n" + "WHERE maHD = N'" + maHD
					+ "' and maNuoc = N'" + maNuoc + "'";
			em.createNativeQuery(sql).executeUpdate();

			tr.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

}
