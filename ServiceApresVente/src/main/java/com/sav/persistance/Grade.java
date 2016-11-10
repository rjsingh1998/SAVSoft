package com.sav.persistance;

import java.util.HashSet;
import java.util.Set;

public class Grade implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idGrade;
	private String libelleGrd;
	private Set<Utilisateur> utilisateurs = new HashSet<Utilisateur>(0);
	
	
	
	
	
	public Set<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public Integer getIdGrade() {
		return idGrade;
	}
	public void setIdGrade(Integer idGrade) {
		this.idGrade = idGrade;
	}
	public String getLibelleGrd() {
		return libelleGrd;
	}
	public void setLibelleGrd(String libelleGrd) {
		this.libelleGrd = libelleGrd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGrade == null) ? 0 : idGrade.hashCode());
		result = prime * result
				+ ((libelleGrd == null) ? 0 : libelleGrd.hashCode());
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
		Grade other = (Grade) obj;
		if (idGrade == null) {
			if (other.idGrade != null)
				return false;
		} else if (!idGrade.equals(other.idGrade))
			return false;
		if (libelleGrd == null) {
			if (other.libelleGrd != null)
				return false;
		} else if (!libelleGrd.equals(other.libelleGrd))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Grade [idGrade=" + idGrade + ", libelleGrd=" + libelleGrd + "]";
	}
	
	
	
	

}
