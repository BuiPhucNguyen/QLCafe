package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="Nuoc")
public class Nuoc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maNuoc;
	@Column(nullable = false, columnDefinition = "nvarchar(50)")
	private String tenNuoc;
	@Column(nullable = false)
	private double giaTien;
	@Column(nullable = false)
	private boolean trangThai;
	
	@OneToMany(mappedBy = "maNuoc")
	private List<ChiTietHoaDon> hoaDons;
	public Nuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Nuoc(String maNuoc, String tenNuoc, double giaTien, boolean trangThai, List<ChiTietHoaDon> hoaDons) {
		super();
		this.maNuoc = maNuoc;
		this.tenNuoc = tenNuoc;
		this.giaTien = giaTien;
		this.trangThai = trangThai;
		this.hoaDons = hoaDons;
	}
	public Nuoc(String maNuoc, String tenNuoc, double giaTien, boolean trangThai) {
		super();
		this.maNuoc = maNuoc;
		this.tenNuoc = tenNuoc;
		this.giaTien = giaTien;
		this.trangThai = trangThai;
	}
	public String getMaNuoc() {
		return maNuoc;
	}
	public void setMaNuoc(String maNuoc) {
		this.maNuoc = maNuoc;
	}
	public String getTenNuoc() {
		return tenNuoc;
	}
	public void setTenNuoc(String tenNuoc) {
		this.tenNuoc = tenNuoc;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public List<ChiTietHoaDon> getHoaDons() {
		return hoaDons;
	}
	public void setHoaDons(List<ChiTietHoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}
	
}
