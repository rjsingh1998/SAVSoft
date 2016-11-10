package com.sav.persistance;

import java.util.HashSet;
import java.util.Set;

public class Diagnostique implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer iddiag;
	private String note;
	private String recmd;
	private Appel appel;
	private Utilisateur utilisateur;
	
	private Set<Diag_panne> diag_pannes = new HashSet<Diag_panne>(0);
	
	
	
	
	public Appel getAppel() {
		return appel;
	}
	public void setAppel(Appel appel) {
		this.appel = appel;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Integer getIddiag() {
		return iddiag;
	}
	public void setIddiag(Integer iddiag) {
		this.iddiag = iddiag;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getRecmd() {
		return recmd;
	}
	public void setRecmd(String recmd) {
		this.recmd = recmd;
	}
	
	
	
	
	public Set<Diag_panne> getDiag_pannes() {
		return diag_pannes;
	}
	public void setDiag_pannes(Set<Diag_panne> diag_pannes) {
		this.diag_pannes = diag_pannes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iddiag == null) ? 0 : iddiag.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((recmd == null) ? 0 : recmd.hashCode());
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
		Diagnostique other = (Diagnostique) obj;
		if (iddiag == null) {
			if (other.iddiag != null)
				return false;
		} else if (!iddiag.equals(other.iddiag))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (recmd == null) {
			if (other.recmd != null)
				return false;
		} else if (!recmd.equals(other.recmd))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Diagnostique [iddiag=" + iddiag + ", note=" + note + ", recmd="
				+ recmd + "]";
	}
	
	
	

}
