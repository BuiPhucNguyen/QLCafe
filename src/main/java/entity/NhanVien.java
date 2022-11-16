package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="NhanVien")
public class NhanVien implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maNV;
	@Column(nullable = false, columnDefinition = "nvarchar(50)")
	private String tenNV;
	@Column(nullable = false, columnDefinition = "nvarchar(50)")
	private String sdt;
	@Column(nullable = false, columnDefinition = "nvarchar(50)")
	private String cmnd;
	@Column(nullable = false)
	private boolean gioiTinh;
	@Column(nullable = false, columnDefinition = "date")
	private Date ngaySinh;
	@Column(nullable = false, columnDefinition = "nvarchar(50)")
	private String chucVu;
	@Column(nullable = false)
	private double luong;
	
	@OneToOne(mappedBy = "tenTaiKhoan")
	private TaiKhoan taiKhoan;
	
	@OneToMany(mappedBy = "maNV")
	private List<HoaDon> hoaDon;
	
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public List<HoaDon> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(List<HoaDon> hoaDon) {
		this.hoaDon = hoaDon;
	}

	

	public NhanVien(String maNV, String tenNV, String sdt, String cmnd, boolean gioiTinh, Date ngaySinh, String chucVu,
			double luong, TaiKhoan taiKhoan) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sdt = sdt;
		this.cmnd = cmnd;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.chucVu = chucVu;
		this.luong = luong;
		this.taiKhoan = taiKhoan;
	}
	

	public NhanVien(String maNV, String tenNV, String sdt, String cmnd, boolean gioiTinh, Date ngaySinh, String chucVu,
			double luong) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sdt = sdt;
		this.cmnd = cmnd;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.chucVu = chucVu;
		this.luong = luong;
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

}	
