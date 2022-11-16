package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@IdClass(ChiTietHoaDonId.class)
@Table(name="ChiTietHoaDon")
public class ChiTietHoaDon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name="maHD")
	private HoaDon maHoaDon;
	
	@Id
	@ManyToOne
	@JoinColumn(name="maNuoc")
	private Nuoc maNuoc;
	@Column(nullable = false)
	private int soLuong;
	
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDon getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(HoaDon maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public Nuoc getMaNuoc() {
		return maNuoc;
	}

	public void setMaNuoc(Nuoc maNuoc) {
		this.maNuoc = maNuoc;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public ChiTietHoaDon(HoaDon maHoaDon, Nuoc maNuoc, int soLuong) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNuoc = maNuoc;
		this.soLuong = soLuong;
	}
	
}
