package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entity.TaiKhoan;

public interface DAO_TaiKhoan extends Remote {
	public boolean themTaiKhoan(TaiKhoan tk) throws RemoteException;
}
