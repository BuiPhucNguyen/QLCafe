package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.HoaDon;

public interface DAO_HoaDon extends Remote {
	public boolean themHD(HoaDon hd) throws RemoteException;
	public HoaDon getHBTheoMa(String id) throws RemoteException;
	public HoaDon getHDDaThanhToanTheoMa(String id) throws RemoteException;
	public List<HoaDon> getHoaDonTrongNgay() throws RemoteException;
	public List<HoaDon> getHoaDonTrongTuan() throws RemoteException;
	public List<HoaDon> getHoaDonTrongThang() throws RemoteException;
	public List<HoaDon> getAllHD() throws RemoteException;
	public List<HoaDon> getHDChuaThanhToan() throws RemoteException;
	public List<HoaDon> getHDDAThanhToan() throws RemoteException;
	public boolean xoaHD(String id) throws RemoteException;
	public boolean capNhatBan(String id, String maBan) throws RemoteException;
	public boolean thanhToan(String maHD, double thanhTien) throws RemoteException;
}
