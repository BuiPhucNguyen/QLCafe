package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Ban;
import entity.NhanVien;

public interface DAO_Ban extends Remote {
	public List<Ban> getAllBan() throws RemoteException;
	public List<Ban> getAllBanTrong() throws RemoteException;
	public List<Ban> getAllBanDaDat() throws RemoteException;
	public Ban getBanTheoTen(String ten) throws RemoteException;
	public boolean xoaBan(String id) throws RemoteException;
	public boolean themBan(Ban b) throws RemoteException;
	public boolean capnhatBan(Ban b) throws RemoteException;
}
