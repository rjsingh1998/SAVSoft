package com.sav.persistance;

import java.util.HashSet;
import java.util.Set;



// Generated 22 janv. 2015 15:05:41 by Hibernate Tools 3.4.0.CR1

/**
 * Client generated by hbm2java
 */
public class Ville implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idville;
	private String designationVille;
	
	
	private Set<Client> clients= new HashSet<Client>();
	private Set<Fournisseur> fournisseurs= new HashSet<Fournisseur>();
	private Set<Utilisateur> utilisateurs= new HashSet<Utilisateur>();
	
	public Ville() {
	}
	
	
	
	
	public Set<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Set<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

	public void setFournisseurs(Set<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	

	public Integer getIdville() {
		return idville;
	}

	public void setIdville(Integer idville) {
		this.idville = idville;
	}

	public String getDesignationVille() {
		return designationVille;
	}

	public void setDesignationVille(String designationVille) {
		this.designationVille = designationVille;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((designationVille == null) ? 0 : designationVille.hashCode());
		result = prime * result + ((idville == null) ? 0 : idville.hashCode());
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
		Ville other = (Ville) obj;
		if (designationVille == null) {
			if (other.designationVille != null)
				return false;
		} else if (!designationVille.equals(other.designationVille))
			return false;
		if (idville == null) {
			if (other.idville != null)
				return false;
		} else if (!idville.equals(other.idville))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ville [idville=" + idville + ", designationVille="
				+ designationVille + "]";
	}
	

	
}