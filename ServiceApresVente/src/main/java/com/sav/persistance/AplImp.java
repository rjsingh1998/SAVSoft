package com.sav.persistance;

import java.util.Date;

public class AplImp implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer  idAplImp;
	private Integer numApl;
	private String clt;
	private String numSerie;
	private Date date_entre;
	private String etat;
	private String gsm;
	
	private String filtreAttribu;
	private Date dateDeb;
	private Date dateFin;
	private Integer fitreEtat;
	
	
	
	
	public Integer getFitreEtat() {
		return fitreEtat;
	}
	public void setFitreEtat(Integer fitreEtat) {
		this.fitreEtat = fitreEtat;
	}
	public Date getDateDeb() {
		return dateDeb;
	}
	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public String getFiltreAttribu() {
		return filtreAttribu;
	}
	public void setFiltreAttribu(String filtreAttribu) {
		this.filtreAttribu = filtreAttribu;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getGsm() {
		return gsm;
	}
	public void setGsm(String gsm) {
		this.gsm = gsm;
	}
	public Integer getIdAplImp() {
		return idAplImp;
	}
	public void setIdAplImp(Integer idAplImp) {
		this.idAplImp = idAplImp;
	}
	public Integer getNumApl() {
		return numApl;
	}
	public void setNumApl(Integer numApl) {
		this.numApl = numApl;
	}
	public String getClt() {
		return clt;
	}
	public void setClt(String clt) {
		this.clt = clt;
	}
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public Date getDate_entre() {
		return date_entre;
	}
	public void setDate_entre(Date date_entre) {
		this.date_entre = date_entre;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clt == null) ? 0 : clt.hashCode());
		result = prime * result + ((dateDeb == null) ? 0 : dateDeb.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result
				+ ((date_entre == null) ? 0 : date_entre.hashCode());
		result = prime * result + ((etat == null) ? 0 : etat.hashCode());
		result = prime * result
				+ ((filtreAttribu == null) ? 0 : filtreAttribu.hashCode());
		result = prime * result + ((gsm == null) ? 0 : gsm.hashCode());
		result = prime * result
				+ ((idAplImp == null) ? 0 : idAplImp.hashCode());
		result = prime * result + ((numApl == null) ? 0 : numApl.hashCode());
		result = prime * result
				+ ((numSerie == null) ? 0 : numSerie.hashCode());
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
		AplImp other = (AplImp) obj;
		if (clt == null) {
			if (other.clt != null)
				return false;
		} else if (!clt.equals(other.clt))
			return false;
		if (dateDeb == null) {
			if (other.dateDeb != null)
				return false;
		} else if (!dateDeb.equals(other.dateDeb))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (date_entre == null) {
			if (other.date_entre != null)
				return false;
		} else if (!date_entre.equals(other.date_entre))
			return false;
		if (etat == null) {
			if (other.etat != null)
				return false;
		} else if (!etat.equals(other.etat))
			return false;
		if (filtreAttribu == null) {
			if (other.filtreAttribu != null)
				return false;
		} else if (!filtreAttribu.equals(other.filtreAttribu))
			return false;
		if (gsm == null) {
			if (other.gsm != null)
				return false;
		} else if (!gsm.equals(other.gsm))
			return false;
		if (idAplImp == null) {
			if (other.idAplImp != null)
				return false;
		} else if (!idAplImp.equals(other.idAplImp))
			return false;
		if (numApl == null) {
			if (other.numApl != null)
				return false;
		} else if (!numApl.equals(other.numApl))
			return false;
		if (numSerie == null) {
			if (other.numSerie != null)
				return false;
		} else if (!numSerie.equals(other.numSerie))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AplImp [idAplImp=" + idAplImp + ", numApl=" + numApl + ", clt="
				+ clt + ", numSerie=" + numSerie + ", date_entre=" + date_entre
				+ ", etat=" + etat + ", gsm=" + gsm + ", filtreAttribu="
				+ filtreAttribu + ", dateDeb=" + dateDeb + ", dateFin="
				+ dateFin + "]";
	}
	
	
	

}

