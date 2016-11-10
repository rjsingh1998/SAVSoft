package com.sav.persistance;

import java.util.Date;

public class MachHisto {
	
	private Integer idMachHisto;
	private String type;
	private String user;
	private String service;
	private String statut;
	private Date date;
	private Integer idapl;
	private Integer qte;
	
	

	public Integer getIdMachHisto() {
		return idMachHisto;
	}


	public void setIdMachHisto(Integer idMachHisto) {
		this.idMachHisto = idMachHisto;
	}


	public Integer getQte() {
		return qte;
	}


	public void setQte(Integer qte) {
		this.qte = qte;
	}


	public Integer getIdapl() {
		return idapl;
	}


	public void setIdapl(Integer idapl) {
		this.idapl = idapl;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getService() {
		return service;
	}


	public void setService(String service) {
		this.service = service;
	}


	public String getStatut() {
		return statut;
	}


	public void setStatut(String statut) {
		this.statut = statut;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "MachHisto [type=" + type + ", user=" + user + ", service="
				+ service + ", statut=" + statut + ", date=" + date + "]";
	}
	
	

}
