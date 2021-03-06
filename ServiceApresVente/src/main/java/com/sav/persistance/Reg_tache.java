package com.sav.persistance;

import java.util.Date;

// Generated 22 janv. 2015 15:05:41 by Hibernate Tools 3.4.0.CR1

/**
 * Client generated by hbm2java
 */
public class Reg_tache implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idreg_tache;
	private Tache tache;
	private Reparation reparation;
	private Utilisateur utilisateur;
	private Date dateDebutTach;
	private Date dateFinTach;
	
	
	
	public Date getDateDebutTach() {
		return dateDebutTach;
	}
	public void setDateDebutTach(Date dateDebutTach) {
		this.dateDebutTach = dateDebutTach;
	}
	public Date getDateFinTach() {
		return dateFinTach;
	}
	public void setDateFinTach(Date dateFinTach) {
		this.dateFinTach = dateFinTach;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Integer getIdreg_tache() {
		return idreg_tache;
	}
	public void setIdreg_tache(Integer idreg_tache) {
		this.idreg_tache = idreg_tache;
	}
	public Tache getTache() {
		return tache;
	}
	public void setTache(Tache tache) {
		this.tache = tache;
	}
	
	public Reparation getReparation() {
		return reparation;
	}
	public void setReparation(Reparation reparation) {
		this.reparation = reparation;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateDebutTach == null) ? 0 : dateDebutTach.hashCode());
		result = prime * result
				+ ((dateFinTach == null) ? 0 : dateFinTach.hashCode());
		result = prime * result
				+ ((idreg_tache == null) ? 0 : idreg_tache.hashCode());
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
		Reg_tache other = (Reg_tache) obj;
		if (dateDebutTach == null) {
			if (other.dateDebutTach != null)
				return false;
		} else if (!dateDebutTach.equals(other.dateDebutTach))
			return false;
		if (dateFinTach == null) {
			if (other.dateFinTach != null)
				return false;
		} else if (!dateFinTach.equals(other.dateFinTach))
			return false;
		if (idreg_tache == null) {
			if (other.idreg_tache != null)
				return false;
		} else if (!idreg_tache.equals(other.idreg_tache))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reg_tache [idreg_tache=" + idreg_tache + ", dateDebutTach="
				+ dateDebutTach + ", dateFinTach=" + dateFinTach + "]";
	}
	
	
	



	

	



	}

