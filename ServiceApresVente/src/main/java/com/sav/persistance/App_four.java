package com.sav.persistance;

import java.util.Date;

public class App_four implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idapl_four;
	private Date date_sortie;
	private Date date_retour;
	private String observation;
	private String montant;
	private Appel appel;
	private Fournisseur fournisseur;
	public Integer getIdapl_four() {
		return idapl_four;
	}
	public void setIdapl_four(Integer idapl_four) {
		this.idapl_four = idapl_four;
	}
	
	public Date getDate_sortie() {
		return date_sortie;
	}
	public void setDate_sortie(Date date_sortie) {
		this.date_sortie = date_sortie;
	}
	
	public Date getDate_retour() {
		return date_retour;
	}
	public void setDate_retour(Date date_retour) {
		this.date_retour = date_retour;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public String getMontant() {
		return montant;
	}
	public void setMontant(String montant) {
		this.montant = montant;
	}
	public Appel getAppel() {
		return appel;
	}
	public void setAppel(Appel appel) {
		this.appel = appel;
	}
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((date_retour == null) ? 0 : date_retour.hashCode());
		result = prime * result
				+ ((date_sortie == null) ? 0 : date_sortie.hashCode());
		result = prime * result
				+ ((idapl_four == null) ? 0 : idapl_four.hashCode());
		result = prime * result + ((montant == null) ? 0 : montant.hashCode());
		result = prime * result
				+ ((observation == null) ? 0 : observation.hashCode());
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
		App_four other = (App_four) obj;
		if (date_retour == null) {
			if (other.date_retour != null)
				return false;
		} else if (!date_retour.equals(other.date_retour))
			return false;
		if (date_sortie == null) {
			if (other.date_sortie != null)
				return false;
		} else if (!date_sortie.equals(other.date_sortie))
			return false;
		if (idapl_four == null) {
			if (other.idapl_four != null)
				return false;
		} else if (!idapl_four.equals(other.idapl_four))
			return false;
		if (montant == null) {
			if (other.montant != null)
				return false;
		} else if (!montant.equals(other.montant))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "App_four [idapl_four=" + idapl_four + ", date_sortie="
				+ date_sortie + ", date_retour=" + date_retour
				+ ", observation=" + observation + ", montant=" + montant + "]";
	}
	
	
	

}
