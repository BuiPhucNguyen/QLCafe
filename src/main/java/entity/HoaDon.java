package entity;

import java.io.Serializable;
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
	
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
