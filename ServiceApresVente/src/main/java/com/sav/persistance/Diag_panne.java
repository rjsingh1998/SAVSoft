package com.sav.persistance;

public class Diag_panne implements java.io.Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer iddiag_panne;
	private Diagnostique diagnostique;
	private Panne panne;
	
	
	public Integer getIddiag_panne() {
		return iddiag_panne;
	}
	public void setIddiag_panne(Integer iddiag_panne) {
		this.iddiag_panne = iddiag_panne;
	}
	public Diagnostique getDiagnostique() {
		return diagnostique;
	}
	public void setDiagnostique(Diagnostique diagnostique) {
		this.diagnostique = diagnostique;
	}
	public Panne getPanne() {
		return panne;
	}
	public void setPanne(Panne panne) {
		this.panne = panne;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((iddiag_panne == null) ? 0 : iddiag_panne.hashCode());
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
		Diag_panne other = (Diag_panne) obj;
		if (iddiag_panne == null) {
			if (other.iddiag_panne != null)
				return false;
		} else if (!iddiag_panne.equals(other.iddiag_panne))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Diag_panne [iddiag_panne=" + iddiag_panne + "]";
	}
	
	
}
