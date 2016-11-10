package com.sav.persistance;

import java.util.HashSet;
import java.util.Set;


// Generated 22 janv. 2015 15:05:41 by Hibernate Tools 3.4.0.CR1

/**
 * Client generated by hbm2java
 */

public class Etat implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idetat;
	private String designationEtat;
	private Set<Appel> appels = new HashSet<Appel>();

	public Set<Appel> getAppels() {
		return appels;
	}

	public void setAppels(Set<Appel> appels) {
		this.appels = appels;
	}

	public Integer getIdetat() {
		return idetat;
	}

	public void setIdetat(Integer idetat) {
		this.idetat = idetat;
	}

	public String getDesignationEtat() {
		return designationEtat;
	}

	public void setDesignationEtat(String designationEtat) {
		this.designationEtat = designationEtat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((designationEtat == null) ? 0 : designationEtat.hashCode());
		result = prime * result + ((idetat == null) ? 0 : idetat.hashCode());
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
		Etat other = (Etat) obj;
		if (designationEtat == null) {
			if (other.designationEtat != null)
				return false;
		} else if (!designationEtat.equals(other.designationEtat))
			return false;
		if (idetat == null) {
			if (other.idetat != null)
				return false;
		} else if (!idetat.equals(other.idetat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Etat [idetat=" + idetat + ", designationEtat="
				+ designationEtat + "]";
	}

}