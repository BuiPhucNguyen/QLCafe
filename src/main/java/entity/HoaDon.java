package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="HoaDon")
public class HoaDon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maHD;
	
	@ManyToOne
	@JoinColumn(name="maNV",nullable = false)
	private NhanVien maNV;
	
	@ManyToOne
	@JoinColumn(name="maBan",nullable = false)
	private Ban maBan;
	
	@OneToMany(mappedBy = "maHoaDon")
	private List<ChiTietHoaDon> nuocs;
	
	@Column(name = "daThanhToan", nullable = false)
	private boolean daThanhToan;
	
	@Column(nullable = true)
	private double tongTien;
	
	@Column(nullable = false, columnDefinition = "date")
	private Date ngayDat;
	
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public NhanVien getMaNV() {
		return maNV;
	}
	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
	}
	public Ban getMaBan() {
		return maBan;
	}
	public void setMaBan(Ban maBan) {
		this.maBan = maBan;
	}
	public List<ChiTietHoaDon> getNuocs() {
		return nuocs;
	}
	public void setNuocs(List<ChiTietHoaDon> nuocs) {
		this.nuocs = nuocs;
	}
	public boolean isDaThanhToan() {
		return daThanhToan;
	}
	public void setDaThanhToan(boolean daThanhToan) {
		this.daThanhToan = daThanhToan;
	}
	
	
	
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public Date getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}
	public HoaDon(String maHD, NhanVien maNV, Ban maBan, boolean daThanhToan, Date ngayDat) {
		super();
		this.maHD = maHD;
		this.maNV = maNV;
		this.maBan = maBan;
		this.daThanhToan = daThanhToan;
		this.ngayDat = ngayDat;
	}
	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}
	
	
}
