package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="TaiKhoan")
public class TaiKhoan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@MapsId
	@OneToOne
	@JoinColumn(name="tenTaiKhoan")
	private NhanVien tenTaiKhoan;
	@Column(nullable = false, columnDefinition = "nvarchar(50)")
	private String matKhau;
	
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(NhanVien tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public TaiKhoan(NhanVien tenTaiKhoan, String matKhau) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
	}

}
