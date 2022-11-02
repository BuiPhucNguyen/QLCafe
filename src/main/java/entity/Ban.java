package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="Ban")
public class Ban implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maBan;
	@Column(nullable = false, columnDefinition = "nvarchar(50)")
	private String tenBan;
	@Column(nullable = false)
	private boolean trangThai;
	
	@OneToMany(mappedBy = "maBan")
	private List<HoaDon> hoaDons;
	
	public Ban() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ban(String maBan, String tenBan, boolean trangThai, List<HoaDon> hoaDons) {
		super();
		this.maBan = maBan;
		this.tenBan = tenBan;
		this.trangThai = trangThai;
		this.hoaDons = hoaDons;
	}

	public Ban(String maBan, String tenBan, boolean trangThai) {
		super();
		this.maBan = maBan;
		this.tenBan = tenBan;
		this.trangThai = trangThai;
	}

	public String getMaBan() {
		return maBan;
	}

	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}

	public String getTenBan() {
		return tenBan;
	}

	public void setTenBan(String tenBan) {
		this.tenBan = tenBan;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public List<HoaDon> getHoaDons() {
		return hoaDons;
	}

	public void setHoaDons(List<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}
	
}
