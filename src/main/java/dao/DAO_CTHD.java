package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.ChiTietHoaDon;

public interface DAO_CTHD extends Remote{
	public boolean themHD(ChiTietHoaDon cthd) throws RemoteException;
	public List<ChiTietHoaDon> getCTHDTheoMa(String id) throws RemoteException;
	public boolean capNhatSL(String maHD, String maNuoc, int new_SL) throws RemoteException;
	public boolean xoaCTHD(String maHD, String maNuoc) throws RemoteException;
}
