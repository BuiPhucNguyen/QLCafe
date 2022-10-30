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
	
}	
