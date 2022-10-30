package entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ChiTietHoaDonId implements Serializable{
	private String maHoaDon;
	private String maNuoc;
	public ChiTietHoaDonId() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHoaDon == null) ? 0 : maHoaDon.hashCode());
		result = prime * result + ((maNuoc == null) ? 0 : maNuoc.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDonId other = (ChiTietHoaDonId) obj;
		if (maHoaDon == null) {
			if (other.maHoaDon != null)
				return false;
		} else if (!maHoaDon.equals(other.maHoaDon))
			return false;
		if (maNuoc == null) {
			if (other.maNuoc != null)
				return false;
		} else if (!maNuoc.equals(other.maNuoc))
			return false;
		return true;
	}
	
}
