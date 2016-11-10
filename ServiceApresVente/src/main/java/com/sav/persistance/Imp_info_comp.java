package com.sav.persistance;

public class Imp_info_comp  implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idimpInfoComp;
	private String info;
	private Boolean activation;
	private String valeur;
	
	
	public Integer getIdimpInfoComp() {
		return idimpInfoComp;
	}
	public void setIdimpInfoComp(Integer idimpInfoComp) {
		this.idimpInfoComp = idimpInfoComp;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
	public Boolean getActivation() {
		return activation;
	}
	public void setActivation(Boolean activation) {
		this.activation = activation;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activation == null) ? 0 : activation.hashCode());
		result = prime * result
				+ ((idimpInfoComp == null) ? 0 : idimpInfoComp.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((valeur == null) ? 0 : valeur.hashCode());
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
		Imp_info_comp other = (Imp_info_comp) obj;
		if (activation == null) {
			if (other.activation != null)
				return false;
		} else if (!activation.equals(other.activation))
			return false;
		if (idimpInfoComp == null) {
			if (other.idimpInfoComp != null)
				return false;
		} else if (!idimpInfoComp.equals(other.idimpInfoComp))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (valeur == null) {
			if (other.valeur != null)
				return false;
		} else if (!valeur.equals(other.valeur))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Imp_info_comp [idimpInfoComp=" + idimpInfoComp + ", info="
				+ info + ", activation=" + activation + ", valeur=" + valeur
				+ "]";
	}
	
	
	

}
