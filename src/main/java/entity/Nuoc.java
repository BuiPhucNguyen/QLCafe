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
	
}
