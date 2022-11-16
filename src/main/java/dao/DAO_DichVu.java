package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Nuoc;

public interface DAO_DichVu extends Remote {
	public List<Nuoc> getAllDichVu() throws RemoteException;
	public List<Nuoc> getAllDichVuConMon() throws RemoteException;
	public Nuoc getDVTheoTen(String ten) throws RemoteException;
	public boolean xoaDichVu(String id) throws RemoteException;
	public boolean themDichVu(Nuoc dv) throws RemoteException;
	public boolean capnhatDichVu(Nuoc dv) throws RemoteException;
}
