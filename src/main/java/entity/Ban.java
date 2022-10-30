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
	
}
