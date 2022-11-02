package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Ban;
import entity.NhanVien;

public interface DAO_Ban extends Remote {
	public List<Ban> getAllBan() throws RemoteException;
	public boolean xoaBan(String id) throws RemoteException;
	public boolean themBan(NhanVien nv) throws RemoteException;
	public boolean capnhatBan(NhanVien nv) throws RemoteException;
}
