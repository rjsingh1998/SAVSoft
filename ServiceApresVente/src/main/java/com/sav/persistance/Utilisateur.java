package com.sav.persistance;

import java.util.HashSet;
import java.util.Set;

// Generated 22 janv. 2015 15:05:41 by Hibernate Tools 3.4.0.CR1

/**
 * Utilisateur generated by hbm2java
 */
public class Utilisateur implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idutilisateur;
	private String nomUtl;
	private String prenomUtl;
	private String adrUtl;
	private String mailUtl;
	private String gsm1Utl;
	private String gsm2Utl;
	private String telUtl;
	private String cinUtl;
	private String login;
	private String motPass;
	private Grade grade;
	private Ville ville;
	private Service service;
	private boolean actif;
	private boolean passif;
	
	private boolean gestEtat;
	private boolean gestApl;
	private boolean parametrage;
	private boolean filAtt;
	private boolean calender;
	private boolean profil;
	private boolean config;
	
	private boolean gestTypFour;
	private boolean gestTypMach;
	private boolean gestMarq;
	private boolean gestGrade;
	private boolean gestVil;
	private boolean gestSrvc;
	private boolean gestStatut;
	
	private String gestClt;
	private String gestUtl;
	private String gestFour;
	private String gestMach;
	private String gestPan;
	private String gestTach;
	private String gestPce; 
	private String gestAcc;
	
	private String supClt;
	private String nouvClt;
	private String modifClt;
	
	private String supTypFour;
	private String nouvTypFour;
	private String modifTypFour;
	
	
	private String supFour;
	private String nouvFour;
	private String modifFour;
	
	
	private String supTyp;
	private String nouvTyp;
	private String modifTyp;
	
	
	private String supVil;
	private String nouvVil;
	private String modifVil;
	
	
	private String supStat;
	private String nouvStat;
	private String modifStat;
	
	

	private String supMqe;
	private String nouvMqe;
	private String modifMqe;
	
	
	private String supSer;
	private String nouvSer;
	private String modifSer;
	
	
	private String supGde;
	private String nouvGde;
	private String modifGde;
	
	private String supMach;
	private String nouvMach;
	private String modifMach;
	
	
	private String supPan;
	private String nouvPan;
	private String modifPan;
	
	
	private String supTach;
	private String nouvTach;
	private String modifTach;
	
	
	private String supPce;
	private String nouvPce;
	private String modifPce;
	
	private String supAcc;
	private String nouvAcc;
	private String modifAcc;
	
	private boolean ajoutApl;
	private boolean modifApl;
	private boolean detApl;
	private boolean supApl;
	private boolean changEtaApl;
	private boolean envAplChezFour;
	private boolean recoiAplDeFour;
	private boolean detEatApl;
	
	private Set<Rep_Utl> rep_utls = new HashSet<Rep_Utl>(0);
	private Set<Reg_piece> reg_pieces= new HashSet<Reg_piece>(0);
	private Set<Reg_tache> reg_taches = new HashSet<Reg_tache>(0);

	
	
	

	public boolean isAjoutApl() {
		return ajoutApl;
	}

	public void setAjoutApl(boolean ajoutApl) {
		this.ajoutApl = ajoutApl;
	}

	public boolean isModifApl() {
		return modifApl;
	}

	public void setModifApl(boolean modifApl) {
		this.modifApl = modifApl;
	}

	public boolean isDetApl() {
		return detApl;
	}

	public void setDetApl(boolean detApl) {
		this.detApl = detApl;
	}

	public boolean isSupApl() {
		return supApl;
	}

	public void setSupApl(boolean supApl) {
		this.supApl = supApl;
	}

	public boolean isChangEtaApl() {
		return changEtaApl;
	}

	public void setChangEtaApl(boolean changEtaApl) {
		this.changEtaApl = changEtaApl;
	}

	public boolean isEnvAplChezFour() {
		return envAplChezFour;
	}

	public void setEnvAplChezFour(boolean envAplChezFour) {
		this.envAplChezFour = envAplChezFour;
	}

	public boolean isRecoiAplDeFour() {
		return recoiAplDeFour;
	}

	public void setRecoiAplDeFour(boolean recoiAplDeFour) {
		this.recoiAplDeFour = recoiAplDeFour;
	}

	public boolean isDetEatApl() {
		return detEatApl;
	}

	public void setDetEatApl(boolean detEatApl) {
		this.detEatApl = detEatApl;
	}

	public boolean isGestEtat() {
		return gestEtat;
	}

	public void setGestEtat(boolean gestEtat) {
		this.gestEtat = gestEtat;
	}

	public boolean isConfig() {
		return config;
	}

	public void setConfig(boolean config) {
		this.config = config;
	}

	public boolean isGestApl() {
		return gestApl;
	}

	public void setGestApl(boolean gestApl) {
		this.gestApl = gestApl;
	}

	public boolean isParametrage() {
		return parametrage;
	}

	public void setParametrage(boolean parametrage) {
		this.parametrage = parametrage;
	}

	public boolean isFilAtt() {
		return filAtt;
	}

	public void setFilAtt(boolean filAtt) {
		this.filAtt = filAtt;
	}

	public boolean isCalender() {
		return calender;
	}

	public void setCalender(boolean calender) {
		this.calender = calender;
	}

	public boolean isProfil() {
		return profil;
	}

	public void setProfil(boolean profil) {
		this.profil = profil;
	}

	public Set<Reg_tache> getReg_taches() {
		return reg_taches;
	}

	public void setReg_taches(Set<Reg_tache> reg_taches) {
		this.reg_taches = reg_taches;
	}

	public Set<Reg_piece> getReg_pieces() {
		return reg_pieces;
	}

	public void setReg_pieces(Set<Reg_piece> reg_pieces) {
		this.reg_pieces = reg_pieces;
	}

	public Set<Rep_Utl> getRep_utls() {
		return rep_utls;
	}

	public void setRep_utls(Set<Rep_Utl> rep_utls) {
		this.rep_utls = rep_utls;
	}

	public Utilisateur() {
	}

	/*public Utilisateur( String nomUtl, String prenomUtl, String adrUtl,String mailUtl, 
	 String gsm1Utl,String gsm2Utl,String telUtl,String cinUtl,String login,String motPass,Grade grade) {
		
		this.nomUtl = nomUtl;
		this.prenomUtl = prenomUtl;
		this.mailUtl = mailUtl;
		this.adrUtl = adrUtl;
		this.gsm1Utl = gsm1Utl;
		this.gsm2Utl = gsm2Utl;
		this.telUtl = telUtl;
		this.cinUtl = cinUtl;
		this.login = login;
		this.motPass = motPass;
		this.grade = grade;
		
	}*/
	
	
	
	
	public Ville getVille() {
		return ville;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	public boolean isGestTypFour() {
		return gestTypFour;
	}

	public void setGestTypFour(boolean gestTypFour) {
		this.gestTypFour = gestTypFour;
	}

	public boolean isGestTypMach() {
		return gestTypMach;
	}

	public void setGestTypMach(boolean gestTypMach) {
		this.gestTypMach = gestTypMach;
	}

	public boolean isGestMarq() {
		return gestMarq;
	}

	public void setGestMarq(boolean gestMarq) {
		this.gestMarq = gestMarq;
	}

	public boolean isGestGrade() {
		return gestGrade;
	}

	public void setGestGrade(boolean gestGrade) {
		this.gestGrade = gestGrade;
	}

	public boolean isGestVil() {
		return gestVil;
	}

	public void setGestVil(boolean gestVil) {
		this.gestVil = gestVil;
	}

	public boolean isGestSrvc() {
		return gestSrvc;
	}

	public void setGestSrvc(boolean gestSrvc) {
		this.gestSrvc = gestSrvc;
	}

	public boolean isGestStatut() {
		return gestStatut;
	}

	public void setGestStatut(boolean gestStatut) {
		this.gestStatut = gestStatut;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public boolean isPassif() {
		return passif;
	}

	public void setPassif(boolean passif) {
		this.passif = passif;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public String getSupAcc() {
		return supAcc;
	}

	public void setSupAcc(String supAcc) {
		this.supAcc = supAcc;
	}

	public String getNouvAcc() {
		return nouvAcc;
	}

	public void setNouvAcc(String nouvAcc) {
		this.nouvAcc = nouvAcc;
	}

	public String getModifAcc() {
		return modifAcc;
	}

	public void setModifAcc(String modifAcc) {
		this.modifAcc = modifAcc;
	}

	public String getSupPce() {
		return supPce;
	}

	public void setSupPce(String supPce) {
		this.supPce = supPce;
	}

	public String getNouvPce() {
		return nouvPce;
	}

	public void setNouvPce(String nouvPce) {
		this.nouvPce = nouvPce;
	}

	public String getModifPce() {
		return modifPce;
	}

	public void setModifPce(String modifPce) {
		this.modifPce = modifPce;
	}

	public String getSupTach() {
		return supTach;
	}

	public void setSupTach(String supTach) {
		this.supTach = supTach;
	}

	public String getNouvTach() {
		return nouvTach;
	}

	public void setNouvTach(String nouvTach) {
		this.nouvTach = nouvTach;
	}

	public String getModifTach() {
		return modifTach;
	}

	public void setModifTach(String modifTach) {
		this.modifTach = modifTach;
	}

	public String getSupPan() {
		return supPan;
	}

	public void setSupPan(String supPan) {
		this.supPan = supPan;
	}

	public String getNouvPan() {
		return nouvPan;
	}

	public void setNouvPan(String nouvPan) {
		this.nouvPan = nouvPan;
	}

	public String getModifPan() {
		return modifPan;
	}

	public void setModifPan(String modifPan) {
		this.modifPan = modifPan;
	}

	public String getSupMach() {
		return supMach;
	}

	public void setSupMach(String supMach) {
		this.supMach = supMach;
	}

	public String getNouvMach() {
		return nouvMach;
	}

	public void setNouvMach(String nouvMach) {
		this.nouvMach = nouvMach;
	}

	public String getModifMach() {
		return modifMach;
	}

	public void setModifMach(String modifMach) {
		this.modifMach = modifMach;
	}

	public String getSupFour() {
		return supFour;
	}

	public void setSupFour(String supFour) {
		this.supFour = supFour;
	}

	public String getNouvFour() {
		return nouvFour;
	}

	public void setNouvFour(String nouvFour) {
		this.nouvFour = nouvFour;
	}

	public String getModifFour() {
		return modifFour;
	}

	public void setModifFour(String modifFour) {
		this.modifFour = modifFour;
	}

	public String getSupGde() {
		return supGde;
	}

	public void setSupGde(String supGde) {
		this.supGde = supGde;
	}

	public String getNouvGde() {
		return nouvGde;
	}

	public void setNouvGde(String nouvGde) {
		this.nouvGde = nouvGde;
	}

	public String getModifGde() {
		return modifGde;
	}

	public void setModifGde(String modifGde) {
		this.modifGde = modifGde;
	}

	public String getSupStat() {
		return supStat;
	}

	public void setSupStat(String supStat) {
		this.supStat = supStat;
	}

	public String getNouvStat() {
		return nouvStat;
	}

	public void setNouvStat(String nouvStat) {
		this.nouvStat = nouvStat;
	}

	public String getModifStat() {
		return modifStat;
	}

	public void setModifStat(String modifStat) {
		this.modifStat = modifStat;
	}

	public String getSupSer() {
		return supSer;
	}

	public void setSupSer(String supSer) {
		this.supSer = supSer;
	}

	public String getNouvSer() {
		return nouvSer;
	}

	public void setNouvSer(String nouvSer) {
		this.nouvSer = nouvSer;
	}

	public String getModifSer() {
		return modifSer;
	}

	public void setModifSer(String modifSer) {
		this.modifSer = modifSer;
	}

	public String getSupMqe() {
		return supMqe;
	}

	public void setSupMqe(String supMqe) {
		this.supMqe = supMqe;
	}

	public String getNouvMqe() {
		return nouvMqe;
	}

	public void setNouvMqe(String nouvMqe) {
		this.nouvMqe = nouvMqe;
	}

	public String getModifMqe() {
		return modifMqe;
	}

	public void setModifMqe(String modifMqe) {
		this.modifMqe = modifMqe;
	}

	public String getSupVil() {
		return supVil;
	}

	public void setSupVil(String supVil) {
		this.supVil = supVil;
	}

	public String getNouvVil() {
		return nouvVil;
	}

	public void setNouvVil(String nouvVil) {
		this.nouvVil = nouvVil;
	}

	public String getModifVil() {
		return modifVil;
	}

	public void setModifVil(String modifVil) {
		this.modifVil = modifVil;
	}

	public String getSupTyp() {
		return supTyp;
	}

	public void setSupTyp(String supTyp) {
		this.supTyp = supTyp;
	}

	public String getNouvTyp() {
		return nouvTyp;
	}

	public void setNouvTyp(String nouvTyp) {
		this.nouvTyp = nouvTyp;
	}

	public String getModifTyp() {
		return modifTyp;
	}

	public void setModifTyp(String modifTyp) {
		this.modifTyp = modifTyp;
	}

	public String getSupTypFour() {
		return supTypFour;
	}

	public void setSupTypFour(String supTypFour) {
		this.supTypFour = supTypFour;
	}

	public String getNouvTypFour() {
		return nouvTypFour;
	}

	public void setNouvTypFour(String nouvTypFour) {
		this.nouvTypFour = nouvTypFour;
	}

	
	
	public String getModifTypFour() {
		return modifTypFour;
	}

	public void setModifTypFour(String modifTypFour) {
		this.modifTypFour = modifTypFour;
	}

	public String getSupClt() {
		return supClt;
	}

	public void setSupClt(String supClt) {
		this.supClt = supClt;
	}

	public String getNouvClt() {
		return nouvClt;
	}

	public void setNouvClt(String nouvClt) {
		this.nouvClt = nouvClt;
	}

	public String getModifClt() {
		return modifClt;
	}

	public void setModifClt(String modifClt) {
		this.modifClt = modifClt;
	}

	public String getGestClt() {
		return gestClt;
	}

	public void setGestClt(String gestClt) {
		this.gestClt = gestClt;
	}

	public String getGestUtl() {
		return gestUtl;
	}

	public void setGestUtl(String gestUtl) {
		this.gestUtl = gestUtl;
	}

	public String getGestFour() {
		return gestFour;
	}

	public void setGestFour(String gestFour) {
		this.gestFour = gestFour;
	}

	public String getGestMach() {
		return gestMach;
	}

	public void setGestMach(String gestMach) {
		this.gestMach = gestMach;
	}

	public String getGestPan() {
		return gestPan;
	}

	public void setGestPan(String gestPan) {
		this.gestPan = gestPan;
	}

	public String getGestTach() {
		return gestTach;
	}

	public void setGestTach(String gestTach) {
		this.gestTach = gestTach;
	}

	public String getGestPce() {
		return gestPce;
	}

	public void setGestPce(String gestPce) {
		this.gestPce = gestPce;
	}

	public String getGestAcc() {
		return gestAcc;
	}

	public void setGestAcc(String gestAcc) {
		this.gestAcc = gestAcc;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Integer getIdutilisateur() {
		return idutilisateur;
	}

	public void setIdutilisateur(Integer idutilisateur) {
		this.idutilisateur = idutilisateur;
	}

	public String getNomUtl() {
		return nomUtl;
	}

	public void setNomUtl(String nomUtl) {
		this.nomUtl = nomUtl;
	}

	public String getPrenomUtl() {
		return prenomUtl;
	}

	public void setPrenomUtl(String prenomUtl) {
		this.prenomUtl = prenomUtl;
	}

	public String getAdrUtl() {
		return adrUtl;
	}

	public void setAdrUtl(String adrUtl) {
		this.adrUtl = adrUtl;
	}

	public String getMailUtl() {
		return mailUtl;
	}

	public void setMailUtl(String mailUtl) {
		this.mailUtl = mailUtl;
	}

	public String getGsm1Utl() {
		return gsm1Utl;
	}

	public void setGsm1Utl(String gsm1Utl) {
		this.gsm1Utl = gsm1Utl;
	}

	public String getGsm2Utl() {
		return gsm2Utl;
	}

	public void setGsm2Utl(String gsm2Utl) {
		this.gsm2Utl = gsm2Utl;
	}

	public String getTelUtl() {
		return telUtl;
	}

	public void setTelUtl(String telUtl) {
		this.telUtl = telUtl;
	}

	public String getCinUtl() {
		return cinUtl;
	}

	public void setCinUtl(String cinUtl) {
		this.cinUtl = cinUtl;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotPass() {
		return motPass;
	}

	public void setMotPass(String motPass) {
		this.motPass = motPass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adrUtl == null) ? 0 : adrUtl.hashCode());
		result = prime * result + ((cinUtl == null) ? 0 : cinUtl.hashCode());
		result = prime * result + ((gestAcc == null) ? 0 : gestAcc.hashCode());
		result = prime * result + ((gestClt == null) ? 0 : gestClt.hashCode());
		result = prime * result
				+ ((gestFour == null) ? 0 : gestFour.hashCode());
		result = prime * result
				+ ((gestMach == null) ? 0 : gestMach.hashCode());
		result = prime * result + ((gestPan == null) ? 0 : gestPan.hashCode());
		result = prime * result + ((gestPce == null) ? 0 : gestPce.hashCode());
		result = prime * result
				+ ((gestTach == null) ? 0 : gestTach.hashCode());
		result = prime * result + ((gestUtl == null) ? 0 : gestUtl.hashCode());
		result = prime * result + ((gsm1Utl == null) ? 0 : gsm1Utl.hashCode());
		result = prime * result + ((gsm2Utl == null) ? 0 : gsm2Utl.hashCode());
		result = prime * result
				+ ((idutilisateur == null) ? 0 : idutilisateur.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((mailUtl == null) ? 0 : mailUtl.hashCode());
		result = prime * result
				+ ((modifAcc == null) ? 0 : modifAcc.hashCode());
		result = prime * result
				+ ((modifClt == null) ? 0 : modifClt.hashCode());
		result = prime * result
				+ ((modifFour == null) ? 0 : modifFour.hashCode());
		result = prime * result
				+ ((modifGde == null) ? 0 : modifGde.hashCode());
		result = prime * result
				+ ((modifMach == null) ? 0 : modifMach.hashCode());
		result = prime * result
				+ ((modifMqe == null) ? 0 : modifMqe.hashCode());
		result = prime * result
				+ ((modifPan == null) ? 0 : modifPan.hashCode());
		result = prime * result
				+ ((modifPce == null) ? 0 : modifPce.hashCode());
		result = prime * result
				+ ((modifSer == null) ? 0 : modifSer.hashCode());
		result = prime * result
				+ ((modifStat == null) ? 0 : modifStat.hashCode());
		result = prime * result
				+ ((modifTach == null) ? 0 : modifTach.hashCode());
		result = prime * result
				+ ((modifTyp == null) ? 0 : modifTyp.hashCode());
		result = prime * result
				+ ((modifTypFour == null) ? 0 : modifTypFour.hashCode());
		result = prime * result
				+ ((modifVil == null) ? 0 : modifVil.hashCode());
		result = prime * result + ((motPass == null) ? 0 : motPass.hashCode());
		result = prime * result + ((nomUtl == null) ? 0 : nomUtl.hashCode());
		result = prime * result + ((nouvAcc == null) ? 0 : nouvAcc.hashCode());
		result = prime * result + ((nouvClt == null) ? 0 : nouvClt.hashCode());
		result = prime * result
				+ ((nouvFour == null) ? 0 : nouvFour.hashCode());
		result = prime * result + ((nouvGde == null) ? 0 : nouvGde.hashCode());
		result = prime * result
				+ ((nouvMach == null) ? 0 : nouvMach.hashCode());
		result = prime * result + ((nouvMqe == null) ? 0 : nouvMqe.hashCode());
		result = prime * result + ((nouvPan == null) ? 0 : nouvPan.hashCode());
		result = prime * result + ((nouvPce == null) ? 0 : nouvPce.hashCode());
		result = prime * result + ((nouvSer == null) ? 0 : nouvSer.hashCode());
		result = prime * result
				+ ((nouvStat == null) ? 0 : nouvStat.hashCode());
		result = prime * result
				+ ((nouvTach == null) ? 0 : nouvTach.hashCode());
		result = prime * result + ((nouvTyp == null) ? 0 : nouvTyp.hashCode());
		result = prime * result
				+ ((nouvTypFour == null) ? 0 : nouvTypFour.hashCode());
		result = prime * result + ((nouvVil == null) ? 0 : nouvVil.hashCode());
		result = prime * result
				+ ((prenomUtl == null) ? 0 : prenomUtl.hashCode());
		result = prime * result + ((supAcc == null) ? 0 : supAcc.hashCode());
		result = prime * result + ((supClt == null) ? 0 : supClt.hashCode());
		result = prime * result + ((supFour == null) ? 0 : supFour.hashCode());
		result = prime * result + ((supGde == null) ? 0 : supGde.hashCode());
		result = prime * result + ((supMach == null) ? 0 : supMach.hashCode());
		result = prime * result + ((supMqe == null) ? 0 : supMqe.hashCode());
		result = prime * result + ((supPan == null) ? 0 : supPan.hashCode());
		result = prime * result + ((supPce == null) ? 0 : supPce.hashCode());
		result = prime * result + ((supSer == null) ? 0 : supSer.hashCode());
		result = prime * result + ((supStat == null) ? 0 : supStat.hashCode());
		result = prime * result + ((supTach == null) ? 0 : supTach.hashCode());
		result = prime * result + ((supTyp == null) ? 0 : supTyp.hashCode());
		result = prime * result
				+ ((supTypFour == null) ? 0 : supTypFour.hashCode());
		result = prime * result + ((supVil == null) ? 0 : supVil.hashCode());
		result = prime * result + ((telUtl == null) ? 0 : telUtl.hashCode());
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
		Utilisateur other = (Utilisateur) obj;
		if (adrUtl == null) {
			if (other.adrUtl != null)
				return false;
		} else if (!adrUtl.equals(other.adrUtl))
			return false;
		if (cinUtl == null) {
			if (other.cinUtl != null)
				return false;
		} else if (!cinUtl.equals(other.cinUtl))
			return false;
		if (gestAcc == null) {
			if (other.gestAcc != null)
				return false;
		} else if (!gestAcc.equals(other.gestAcc))
			return false;
		if (gestClt == null) {
			if (other.gestClt != null)
				return false;
		} else if (!gestClt.equals(other.gestClt))
			return false;
		if (gestFour == null) {
			if (other.gestFour != null)
				return false;
		} else if (!gestFour.equals(other.gestFour))
			return false;
		if (gestMach == null) {
			if (other.gestMach != null)
				return false;
		} else if (!gestMach.equals(other.gestMach))
			return false;
		if (gestPan == null) {
			if (other.gestPan != null)
				return false;
		} else if (!gestPan.equals(other.gestPan))
			return false;
		if (gestPce == null) {
			if (other.gestPce != null)
				return false;
		} else if (!gestPce.equals(other.gestPce))
			return false;
		if (gestTach == null) {
			if (other.gestTach != null)
				return false;
		} else if (!gestTach.equals(other.gestTach))
			return false;
		if (gestUtl == null) {
			if (other.gestUtl != null)
				return false;
		} else if (!gestUtl.equals(other.gestUtl))
			return false;
		if (gsm1Utl == null) {
			if (other.gsm1Utl != null)
				return false;
		} else if (!gsm1Utl.equals(other.gsm1Utl))
			return false;
		if (gsm2Utl == null) {
			if (other.gsm2Utl != null)
				return false;
		} else if (!gsm2Utl.equals(other.gsm2Utl))
			return false;
		if (idutilisateur == null) {
			if (other.idutilisateur != null)
				return false;
		} else if (!idutilisateur.equals(other.idutilisateur))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (mailUtl == null) {
			if (other.mailUtl != null)
				return false;
		} else if (!mailUtl.equals(other.mailUtl))
			return false;
		if (modifAcc == null) {
			if (other.modifAcc != null)
				return false;
		} else if (!modifAcc.equals(other.modifAcc))
			return false;
		if (modifClt == null) {
			if (other.modifClt != null)
				return false;
		} else if (!modifClt.equals(other.modifClt))
			return false;
		if (modifFour == null) {
			if (other.modifFour != null)
				return false;
		} else if (!modifFour.equals(other.modifFour))
			return false;
		if (modifGde == null) {
			if (other.modifGde != null)
				return false;
		} else if (!modifGde.equals(other.modifGde))
			return false;
		if (modifMach == null) {
			if (other.modifMach != null)
				return false;
		} else if (!modifMach.equals(other.modifMach))
			return false;
		if (modifMqe == null) {
			if (other.modifMqe != null)
				return false;
		} else if (!modifMqe.equals(other.modifMqe))
			return false;
		if (modifPan == null) {
			if (other.modifPan != null)
				return false;
		} else if (!modifPan.equals(other.modifPan))
			return false;
		if (modifPce == null) {
			if (other.modifPce != null)
				return false;
		} else if (!modifPce.equals(other.modifPce))
			return false;
		if (modifSer == null) {
			if (other.modifSer != null)
				return false;
		} else if (!modifSer.equals(other.modifSer))
			return false;
		if (modifStat == null) {
			if (other.modifStat != null)
				return false;
		} else if (!modifStat.equals(other.modifStat))
			return false;
		if (modifTach == null) {
			if (other.modifTach != null)
				return false;
		} else if (!modifTach.equals(other.modifTach))
			return false;
		if (modifTyp == null) {
			if (other.modifTyp != null)
				return false;
		} else if (!modifTyp.equals(other.modifTyp))
			return false;
		if (modifTypFour == null) {
			if (other.modifTypFour != null)
				return false;
		} else if (!modifTypFour.equals(other.modifTypFour))
			return false;
		if (modifVil == null) {
			if (other.modifVil != null)
				return false;
		} else if (!modifVil.equals(other.modifVil))
			return false;
		if (motPass == null) {
			if (other.motPass != null)
				return false;
		} else if (!motPass.equals(other.motPass))
			return false;
		if (nomUtl == null) {
			if (other.nomUtl != null)
				return false;
		} else if (!nomUtl.equals(other.nomUtl))
			return false;
		if (nouvAcc == null) {
			if (other.nouvAcc != null)
				return false;
		} else if (!nouvAcc.equals(other.nouvAcc))
			return false;
		if (nouvClt == null) {
			if (other.nouvClt != null)
				return false;
		} else if (!nouvClt.equals(other.nouvClt))
			return false;
		if (nouvFour == null) {
			if (other.nouvFour != null)
				return false;
		} else if (!nouvFour.equals(other.nouvFour))
			return false;
		if (nouvGde == null) {
			if (other.nouvGde != null)
				return false;
		} else if (!nouvGde.equals(other.nouvGde))
			return false;
		if (nouvMach == null) {
			if (other.nouvMach != null)
				return false;
		} else if (!nouvMach.equals(other.nouvMach))
			return false;
		if (nouvMqe == null) {
			if (other.nouvMqe != null)
				return false;
		} else if (!nouvMqe.equals(other.nouvMqe))
			return false;
		if (nouvPan == null) {
			if (other.nouvPan != null)
				return false;
		} else if (!nouvPan.equals(other.nouvPan))
			return false;
		if (nouvPce == null) {
			if (other.nouvPce != null)
				return false;
		} else if (!nouvPce.equals(other.nouvPce))
			return false;
		if (nouvSer == null) {
			if (other.nouvSer != null)
				return false;
		} else if (!nouvSer.equals(other.nouvSer))
			return false;
		if (nouvStat == null) {
			if (other.nouvStat != null)
				return false;
		} else if (!nouvStat.equals(other.nouvStat))
			return false;
		if (nouvTach == null) {
			if (other.nouvTach != null)
				return false;
		} else if (!nouvTach.equals(other.nouvTach))
			return false;
		if (nouvTyp == null) {
			if (other.nouvTyp != null)
				return false;
		} else if (!nouvTyp.equals(other.nouvTyp))
			return false;
		if (nouvTypFour == null) {
			if (other.nouvTypFour != null)
				return false;
		} else if (!nouvTypFour.equals(other.nouvTypFour))
			return false;
		if (nouvVil == null) {
			if (other.nouvVil != null)
				return false;
		} else if (!nouvVil.equals(other.nouvVil))
			return false;
		if (prenomUtl == null) {
			if (other.prenomUtl != null)
				return false;
		} else if (!prenomUtl.equals(other.prenomUtl))
			return false;
		if (supAcc == null) {
			if (other.supAcc != null)
				return false;
		} else if (!supAcc.equals(other.supAcc))
			return false;
		if (supClt == null) {
			if (other.supClt != null)
				return false;
		} else if (!supClt.equals(other.supClt))
			return false;
		if (supFour == null) {
			if (other.supFour != null)
				return false;
		} else if (!supFour.equals(other.supFour))
			return false;
		if (supGde == null) {
			if (other.supGde != null)
				return false;
		} else if (!supGde.equals(other.supGde))
			return false;
		if (supMach == null) {
			if (other.supMach != null)
				return false;
		} else if (!supMach.equals(other.supMach))
			return false;
		if (supMqe == null) {
			if (other.supMqe != null)
				return false;
		} else if (!supMqe.equals(other.supMqe))
			return false;
		if (supPan == null) {
			if (other.supPan != null)
				return false;
		} else if (!supPan.equals(other.supPan))
			return false;
		if (supPce == null) {
			if (other.supPce != null)
				return false;
		} else if (!supPce.equals(other.supPce))
			return false;
		if (supSer == null) {
			if (other.supSer != null)
				return false;
		} else if (!supSer.equals(other.supSer))
			return false;
		if (supStat == null) {
			if (other.supStat != null)
				return false;
		} else if (!supStat.equals(other.supStat))
			return false;
		if (supTach == null) {
			if (other.supTach != null)
				return false;
		} else if (!supTach.equals(other.supTach))
			return false;
		if (supTyp == null) {
			if (other.supTyp != null)
				return false;
		} else if (!supTyp.equals(other.supTyp))
			return false;
		if (supTypFour == null) {
			if (other.supTypFour != null)
				return false;
		} else if (!supTypFour.equals(other.supTypFour))
			return false;
		if (supVil == null) {
			if (other.supVil != null)
				return false;
		} else if (!supVil.equals(other.supVil))
			return false;
		if (telUtl == null) {
			if (other.telUtl != null)
				return false;
		} else if (!telUtl.equals(other.telUtl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Utilisateur [idutilisateur=" + idutilisateur + ", nomUtl="
				+ nomUtl + ", prenomUtl=" + prenomUtl + ", adrUtl=" + adrUtl
				+ ", mailUtl=" + mailUtl + ", gsm1Utl=" + gsm1Utl
				+ ", gsm2Utl=" + gsm2Utl + ", telUtl=" + telUtl + ", cinUtl="
				+ cinUtl + ", login=" + login + ", motPass=" + motPass
				+ ", gestClt=" + gestClt + ", gestUtl=" + gestUtl
				+ ", gestFour=" + gestFour + ", gestMach=" + gestMach
				+ ", gestPan=" + gestPan + ", gestTach=" + gestTach
				+ ", gestPce=" + gestPce + ", gestAcc=" + gestAcc + ", supClt="
				+ supClt + ", nouvClt=" + nouvClt + ", modifClt=" + modifClt
				+ ", supTypFour=" + supTypFour + ", nouvTypFour=" + nouvTypFour
				+ ", modifTypFour=" + modifTypFour + ", supFour=" + supFour
				+ ", nouvFour=" + nouvFour + ", modifFour=" + modifFour
				+ ", supTyp=" + supTyp + ", nouvTyp=" + nouvTyp + ", modifTyp="
				+ modifTyp + ", supVil=" + supVil + ", nouvVil=" + nouvVil
				+ ", modifVil=" + modifVil + ", supStat=" + supStat
				+ ", nouvStat=" + nouvStat + ", modifStat=" + modifStat
				+ ", supMqe=" + supMqe + ", nouvMqe=" + nouvMqe + ", modifMqe="
				+ modifMqe + ", supSer=" + supSer + ", nouvSer=" + nouvSer
				+ ", modifSer=" + modifSer + ", supGde=" + supGde
				+ ", nouvGde=" + nouvGde + ", modifGde=" + modifGde
				+ ", supMach=" + supMach + ", nouvMach=" + nouvMach
				+ ", modifMach=" + modifMach + ", supPan=" + supPan
				+ ", nouvPan=" + nouvPan + ", modifPan=" + modifPan
				+ ", supTach=" + supTach + ", nouvTach=" + nouvTach
				+ ", modifTach=" + modifTach + ", supPce=" + supPce
				+ ", nouvPce=" + nouvPce + ", modifPce=" + modifPce
				+ ", supAcc=" + supAcc + ", nouvAcc=" + nouvAcc + ", modifAcc="
				+ modifAcc + "]";
	}


}
