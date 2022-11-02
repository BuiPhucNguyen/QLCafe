package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.TaiKhoan;

public interface DAO_TaiKhoan extends Remote {
	public boolean themTaiKhoan(TaiKhoan tk) throws RemoteException;
	public List<TaiKhoan> getAllTaiKhoan() throws RemoteException;
	public boolean capnhatTaiKhoan(TaiKhoan tk) throws RemoteException;
}
