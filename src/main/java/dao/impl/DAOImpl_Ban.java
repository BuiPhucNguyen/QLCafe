package dao.impl;

import java.rmi.RemoteException;
import java.util.List;

import dao.DAO_Ban;
import entity.Ban;
import entity.NhanVien;

public class DAOImpl_Ban implements DAO_Ban {

	@Override
	public List<Ban> getAllBan() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean xoaBan(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean themBan(NhanVien nv) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean capnhatBan(NhanVien nv) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
