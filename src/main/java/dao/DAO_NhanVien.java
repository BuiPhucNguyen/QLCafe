package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhanVien;

public interface DAO_NhanVien extends Remote {
	public List<NhanVien> getAllNhanVien() throws RemoteException;
	public boolean xoaNhanVien(String id) throws RemoteException;
	public boolean themNhanVien(NhanVien nv) throws RemoteException;
	public boolean capnhatNhanVien(NhanVien nv) throws RemoteException;
}
