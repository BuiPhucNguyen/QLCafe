package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.DAO_Ban;
import dao.DAO_CTHD;
import dao.DAO_DichVu;
import dao.DAO_HoaDon;
import dao.DAO_NhanVien;
import dao.DAO_TaiKhoan;
import dao.impl.DAOImpl_Ban;
import dao.impl.DAOImpl_CTHD;
import dao.impl.DAOImpl_DichVu;
import dao.impl.DAOImpl_HoaDon;
import dao.impl.DAOImpl_NhanVien;
import dao.impl.DAOImpl_TaiKhoan;

public class Server {
	public static void main(String[] args) throws NamingException, RemoteException {
		Hashtable<String, String> env  = new Hashtable<String, String>();
		env.put("java.security.policy", "rmi/policy.policy");
		Context context = new InitialContext(env);
		LocateRegistry.createRegistry(9999);
		
		DAO_Ban dao_Ban = new DAOImpl_Ban();
		DAO_CTHD dao_CTHD = new DAOImpl_CTHD();
		DAO_DichVu dao_DichVu = new DAOImpl_DichVu();
		DAO_HoaDon dao_HoaDon = new DAOImpl_HoaDon();
		DAO_NhanVien dao_NhanVien = new DAOImpl_NhanVien();
		DAO_TaiKhoan dao_TaiKhoan = new DAOImpl_TaiKhoan();
		
		context.bind("rmi://192.168.101.35:9999/dao_Ban", dao_Ban);
		context.bind("rmi://192.168.101.35:9999/dao_CTHD", dao_CTHD);
		context.bind("rmi://192.168.101.35:9999/dao_DichVu", dao_DichVu);
		context.bind("rmi://192.168.101.35:9999/dao_HoaDon", dao_HoaDon);
		context.bind("rmi://192.168.101.35:9999/dao_NhanVien", dao_NhanVien);
		context.bind("rmi://192.168.101.35:9999/dao_TaiKhoan", dao_TaiKhoan);
		
		System.out.println("Ready...");
	}
}
