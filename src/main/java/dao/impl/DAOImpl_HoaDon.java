package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import app.FrameDatBan;
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
			JOptionPane.showMessageDialog(null, "Chỉ khi hóa đơn trống mới được hủy!");
			FrameDatBan.chkMangVe.setSelected(true);
			FrameDatBan.lblBanDangChon.setText("Mua mang về");
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

	@Override
	public boolean thanhToan(String maHD, double thanhTien) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();

			em.createNativeQuery("UPDATE [QuanLyCafe].[dbo].[HoaDon]\r\n"
					+ "SET [daThanhToan] = 1,[tongTien] = "+thanhTien+"\r\n"
					+ "WHERE maHD = N'"+maHD+"'").executeUpdate();
			
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean capNhatBan(String id, String maBan) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();

			em.createNativeQuery("UPDATE [QuanLyCafe].[dbo].[HoaDon]\r\n"
					+ "SET [maBan] = N'"+maBan+"'\r\n"
					+ "WHERE maHD = N'"+id+"'").executeUpdate();
			
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<HoaDon> getHoaDonTrongNgay() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			tr.begin();
			String sql = "SELECT [maHD]\r\n"
					+ "      ,[maBan]\r\n"
					+ "      ,[maNV]\r\n"
					+ "      ,[daThanhToan]\r\n"
					+ "      ,[ngayDat]\r\n"
					+ "      ,[tongTien]\r\n"
					+ "  FROM [QuanLyCafe].[dbo].[HoaDon]\r\n"
					+ "  where daThanhToan = 1  and CONVERT(date,ngayDat) = CONVERT(date,GETDATE())";
			list = em.createNativeQuery(sql, HoaDon.class).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<HoaDon> getHoaDonTrongTuan() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			tr.begin();
			String sql = "SELECT [maHD]\r\n"
					+ "      ,[maBan]\r\n"
					+ "      ,[maNV]\r\n"
					+ "      ,[daThanhToan]\r\n"
					+ "      ,[ngayDat]\r\n"
					+ "      ,[tongTien]\r\n"
					+ "  FROM [QuanLyCafe].[dbo].[HoaDon]\r\n"
					+ "  where daThanhToan = 1  and DATEDIFF(day,CONVERT(date,ngayDat) , CONVERT(date,GETDATE())) <= 7";
			list = em.createNativeQuery(sql, HoaDon.class).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<HoaDon> getHoaDonTrongThang() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			tr.begin();
			String sql = "SELECT [maHD]\r\n"
					+ "      ,[maBan]\r\n"
					+ "      ,[maNV]\r\n"
					+ "      ,[daThanhToan]\r\n"
					+ "      ,[ngayDat]\r\n"
					+ "      ,[tongTien]\r\n"
					+ "  FROM [QuanLyCafe].[dbo].[HoaDon]\r\n"
					+ "  where daThanhToan = 1  and DATEDIFF(day,CONVERT(date,ngayDat) , CONVERT(date,GETDATE())) <= 30";
			list = em.createNativeQuery(sql, HoaDon.class).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public HoaDon getHDDaThanhToanTheoMa(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();

			HoaDon hd = (HoaDon) em.createNativeQuery("select * from HoaDon where maHD = N'"+id+"' and daThanhToan = 1", HoaDon.class).getSingleResult();
			
			tr.commit();
			return hd;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<HoaDon> getHDDAThanhToan() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			tr.begin();

			list = em.createNativeQuery("select * from HoaDon where daThanhToan = 1", HoaDon.class).getResultList();
			
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

}
