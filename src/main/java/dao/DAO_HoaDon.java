package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.HoaDon;

public interface DAO_HoaDon extends Remote {
	public boolean themHD(HoaDon hd) throws RemoteException;
	public HoaDon getHBTheoMa(String id) throws RemoteException;
	public List<HoaDon> getAllHD() throws RemoteException;
	public List<HoaDon> getHDChuaThanhToan() throws RemoteException;
	public boolean xoaHD(String id) throws RemoteException;
}
