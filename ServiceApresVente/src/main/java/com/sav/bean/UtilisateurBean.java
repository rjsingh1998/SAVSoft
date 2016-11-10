package com.sav.bean;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TabChangeEvent;

import com.mysql.jdbc.Connection;
import com.sav.persistance.App_fil;
import com.sav.persistance.Diagnostique;
import com.sav.persistance.Fil_att;
import com.sav.persistance.Grade;
import com.sav.persistance.Reg_piece;
import com.sav.persistance.Reg_tache;
import com.sav.persistance.Rep_Utl;
import com.sav.persistance.Service;
import com.sav.persistance.Utilisateur;
import com.sav.persistance.Ville;
import com.sav.service.DiagnostiqueService;
import com.sav.service.Fil_attService;
import com.sav.service.GradeService;
import com.sav.service.Reg_pieceService;
import com.sav.service.Reg_tacheService;
import com.sav.service.Rep_UtlService;
import com.sav.service.ServiceService;
import com.sav.service.UtilisateurService;
import com.sav.service.VilleService;


@ManagedBean(name = "utilisateurBean")
@SessionScoped
public class UtilisateurBean implements java.io.Serializable {
	
	
	/**
	 * 
	 */
   
   private static final long serialVersionUID = 1L;
	
	private int value;

	private Integer idutilisateur;
	private Integer idGrade;
	private String nomGrade;
	private String nomUtl;
	private String prenomUtl;
	private String nonprUtl;
	private String adrUtl;
	private String mailUtl;
	private String gsm1Utl;
	private String gsm2Utl;
	private String telUtl;
	private String cinUtl;
	private String login;
	private String motPass;
	private String valeurRecherche;
	private String attribut;
	
	private String currentStepId = "";
	
	Ville v=null;
	private String designationVille;
	private Ville ville;
	
	private String designationSer;
	private Integer idservice;
	private Integer idfil_att;
    private Service s=null;
	
	
	
	private Integer idville;
	private String vil;
	private String nomVille;
	private Grade grade;
	private List<Grade> grades;
	private List<Ville> villes;
	
	private List<Service> services;
	
	private String motpass1;
	private String motpass2;
	private Integer idUtilisateur;
	
	private List<String> listesRecherches = new ArrayList<String>();
	private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>(0);
	private List<Utilisateur> listUtlByCin = new ArrayList<Utilisateur>(0);
	private List<Utilisateur> listUtlBylogin = new ArrayList<Utilisateur>(0);
	
	private List<Grade> gradeByNom = new ArrayList<Grade>(0);
	
	private List<Service> serviceByNom = new ArrayList<Service>(0);
	private Set<App_fil> app_fils = new HashSet<App_fil>(0);
	
	private List<Ville> villeByNom = new ArrayList<Ville>(0);
	
	private List<Reg_tache> histoUtls = new ArrayList<Reg_tache>(0);
	
	
	
	public String getNonprUtl() {
		
		nonprUtl=nonprUtl+" "+prenomUtl;
		System.out.println("non et pr utl=====>>>"+nonprUtl);
		return nonprUtl;
	}

	public void setNonprUtl(String nonprUtl) {
		this.nonprUtl = nonprUtl;
	}

	public List<Reg_tache> getHistoUtls() {
		return histoUtls;
	}

	public void setHistoUtls(List<Reg_tache> histoUtls) {
		this.histoUtls = histoUtls;
	}



	private String action;
	private boolean titreAjt;
	private boolean titreModif;
	
	private boolean outputMotpass;
	private boolean inputMotpass;
	
	private boolean fichCltPriv;
	private boolean fichUtlPriv;
	private boolean fichFourPriv;
	private boolean fichMachPriv;
	private boolean fichPanPriv;
	private boolean fichTachPriv;
	private boolean fichPiecePriv;
	private boolean fichAccPriv;
	
	private boolean gestTypFourPriv;
	private boolean gestTypMachPriv;
	private boolean gestMarqPriv;
	private boolean gestSrvcPriv;
	private boolean gestvilPriv;
	private boolean gestGradePriv;
	private boolean gestStatutPriv;
	
	private boolean ajoutFourPriv;
	private boolean modifFourPriv;
	private boolean supFourPriv;
	
	private boolean ajoutCltPriv;
	private boolean modifCltPriv;
	private boolean supCltPriv;
	
	private boolean ajoutMachPriv;
	private boolean modifMachPriv;
	private boolean supMachPriv;
	
	private boolean ajoutPanPriv;
	private boolean modifPanPriv;
	private boolean supPanPriv;
	
	private boolean ajoutAccPriv;
	private boolean modifAccPriv;
	private boolean supAccPriv;
	
	private boolean ajoutPcePriv;
	private boolean modifPcePriv;
	private boolean supPcePriv;
	
	private boolean ajoutTachPriv;
	private boolean modifTachPriv;
	private boolean supTachPriv;
	
	private boolean ajoutTypFourPriv;
	private boolean modifTypFourPriv;
	private boolean supTypFourPriv;
	
	private boolean ajoutTypMachPriv;
	private boolean modifTypMachPriv;
	private boolean supTypMachPriv;
	
	private boolean ajoutMarqPriv;
	private boolean modifMarqPriv;
	private boolean supMarqPriv;
	
	private boolean ajoutGrdePriv;
	private boolean modifGrdePriv;
	private boolean supGrdePriv;
	
	private boolean ajoutStatuPriv;
	private boolean modifStatuPriv;
	private boolean supStatuPriv;
	
	private boolean ajoutSrvcPriv;
	private boolean modifSrvcPriv;
	private boolean supSrvcPriv;
	
	private boolean ajoutVilPriv;
	private boolean modifVilPriv;
	private boolean supVilPriv;
	
	private boolean getAplPriv;
	private boolean paramPriv;
	private boolean filAttPriv;
	private boolean calendrierPriv;
	private boolean profilPriv;
	private boolean gestEatatPriv;
	
	
	private boolean ajoutAplPriv;
	private boolean modifAplPriv;
	private boolean supAplPriv;
	private boolean detailAplPriv;
	private boolean envoiAplFourPriv;
	private boolean recoiAplFourPriv;
	private boolean detailEtatAplPriv;
	private boolean changEtatAplPriv;
	
	

	public boolean isChangEtatAplPriv() {
		return changEtatAplPriv;
	}

	public void setChangEtatAplPriv(boolean changEtatAplPriv) {
		this.changEtatAplPriv = changEtatAplPriv;
	}

	public boolean isAjoutAplPriv() {
		return ajoutAplPriv;
	}

	public void setAjoutAplPriv(boolean ajoutAplPriv) {
		this.ajoutAplPriv = ajoutAplPriv;
	}

	public boolean isModifAplPriv() {
		return modifAplPriv;
	}

	public void setModifAplPriv(boolean modifAplPriv) {
		this.modifAplPriv = modifAplPriv;
	}

	public boolean isSupAplPriv() {
		return supAplPriv;
	}

	public void setSupAplPriv(boolean supAplPriv) {
		this.supAplPriv = supAplPriv;
	}

	public boolean isDetailAplPriv() {
		return detailAplPriv;
	}

	public void setDetailAplPriv(boolean detailAplPriv) {
		this.detailAplPriv = detailAplPriv;
	}

	public boolean isEnvoiAplFourPriv() {
		return envoiAplFourPriv;
	}

	public void setEnvoiAplFourPriv(boolean envoiAplFourPriv) {
		this.envoiAplFourPriv = envoiAplFourPriv;
	}

	public boolean isRecoiAplFourPriv() {
		return recoiAplFourPriv;
	}

	public void setRecoiAplFourPriv(boolean recoiAplFourPriv) {
		this.recoiAplFourPriv = recoiAplFourPriv;
	}

	

	public boolean isDetailEtatAplPriv() {
		return detailEtatAplPriv;
	}

	public void setDetailEtatAplPriv(boolean detailEtatAplPriv) {
		this.detailEtatAplPriv = detailEtatAplPriv;
	}

	public String getDesignationSer() {
		return designationSer;
	}

	public void setDesignationSer(String designationSer) {
		this.designationSer = designationSer;
	}

	public Integer getIdservice() {
		if(s != null){
			idservice=s.getIdservice();
			s=null;
		}
		
		return idservice;
	}

	public void setIdservice(Integer idservice) {
		this.idservice = idservice;
	}

	public Integer getIdfil_att() {
		return idfil_att;
	}

	public void setIdfil_att(Integer idfil_att) {
		this.idfil_att = idfil_att;
	}

	public boolean isGestEatatPriv() {
		return gestEatatPriv;
	}

	public void setGestEatatPriv(boolean gestEatatPriv) {
		this.gestEatatPriv = gestEatatPriv;
	}

	public boolean isGetAplPriv() {
		return getAplPriv;
	}

	public void setGetAplPriv(boolean getAplPriv) {
		this.getAplPriv = getAplPriv;
	}

	public boolean isParamPriv() {
		return paramPriv;
	}

	public void setParamPriv(boolean paramPriv) {
		this.paramPriv = paramPriv;
	}

	public boolean isFilAttPriv() {
		return filAttPriv;
	}

	public void setFilAttPriv(boolean filAttPriv) {
		this.filAttPriv = filAttPriv;
	}

	public boolean isCalendrierPriv() {
		return calendrierPriv;
	}

	public void setCalendrierPriv(boolean calendrierPriv) {
		this.calendrierPriv = calendrierPriv;
	}

	public boolean isProfilPriv() {
		return profilPriv;
	}

	public void setProfilPriv(boolean profilPriv) {
		this.profilPriv = profilPriv;
	}

	public boolean isOutputMotpass() {
		return outputMotpass;
	}

	public void setOutputMotpass(boolean outputMotpass) {
		this.outputMotpass = outputMotpass;
	}

	public boolean isInputMotpass() {
		return inputMotpass;
	}

	public void setInputMotpass(boolean inputMotpass) {
		this.inputMotpass = inputMotpass;
	}

	public String getDesignationVille() {
		return designationVille;
	}

	public void setDesignationVille(String designationVille) {
		this.designationVille = designationVille;
	}

	public boolean isTitreAjt() {
		return titreAjt;
	}

	public void setTitreAjt(boolean titreAjt) {
		this.titreAjt = titreAjt;
	}

	public boolean isTitreModif() {
		return titreModif;
	}

	public void setTitreModif(boolean titreModif) {
		this.titreModif = titreModif;
	}

	public boolean isAjoutTypFourPriv() {
		return ajoutTypFourPriv;
	}

	public void setAjoutTypFourPriv(boolean ajoutTypFourPriv) {
		this.ajoutTypFourPriv = ajoutTypFourPriv;
	}

	public boolean isModifTypFourPriv() {
		return modifTypFourPriv;
	}

	public void setModifTypFourPriv(boolean modifTypFourPriv) {
		this.modifTypFourPriv = modifTypFourPriv;
	}

	public boolean isSupTypFourPriv() {
		return supTypFourPriv;
	}

	public void setSupTypFourPriv(boolean supTypFourPriv) {
		this.supTypFourPriv = supTypFourPriv;
	}

	public boolean isAjoutTypMachPriv() {
		return ajoutTypMachPriv;
	}

	public void setAjoutTypMachPriv(boolean ajoutTypMachPriv) {
		this.ajoutTypMachPriv = ajoutTypMachPriv;
	}

	public boolean isModifTypMachPriv() {
		return modifTypMachPriv;
	}

	public void setModifTypMachPriv(boolean modifTypMachPriv) {
		this.modifTypMachPriv = modifTypMachPriv;
	}

	public boolean isSupTypMachPriv() {
		return supTypMachPriv;
	}

	public void setSupTypMachPriv(boolean supTypMachPriv) {
		this.supTypMachPriv = supTypMachPriv;
	}

	public boolean isAjoutMarqPriv() {
		return ajoutMarqPriv;
	}

	public void setAjoutMarqPriv(boolean ajoutMarqPriv) {
		this.ajoutMarqPriv = ajoutMarqPriv;
	}

	public boolean isModifMarqPriv() {
		return modifMarqPriv;
	}

	public void setModifMarqPriv(boolean modifMarqPriv) {
		this.modifMarqPriv = modifMarqPriv;
	}

	public boolean isSupMarqPriv() {
		return supMarqPriv;
	}

	public void setSupMarqPriv(boolean supMarqPriv) {
		this.supMarqPriv = supMarqPriv;
	}

	public boolean isAjoutGrdePriv() {
		return ajoutGrdePriv;
	}

	public void setAjoutGrdePriv(boolean ajoutGrdePriv) {
		this.ajoutGrdePriv = ajoutGrdePriv;
	}

	public boolean isModifGrdePriv() {
		return modifGrdePriv;
	}

	public void setModifGrdePriv(boolean modifGrdePriv) {
		this.modifGrdePriv = modifGrdePriv;
	}

	public boolean isSupGrdePriv() {
		return supGrdePriv;
	}

	public void setSupGrdePriv(boolean supGrdePriv) {
		this.supGrdePriv = supGrdePriv;
	}

	public boolean isAjoutStatuPriv() {
		return ajoutStatuPriv;
	}

	public void setAjoutStatuPriv(boolean ajoutStatuPriv) {
		this.ajoutStatuPriv = ajoutStatuPriv;
	}

	public boolean isModifStatuPriv() {
		return modifStatuPriv;
	}

	public void setModifStatuPriv(boolean modifStatuPriv) {
		this.modifStatuPriv = modifStatuPriv;
	}

	public boolean isSupStatuPriv() {
		return supStatuPriv;
	}

	public void setSupStatuPriv(boolean supStatuPriv) {
		this.supStatuPriv = supStatuPriv;
	}

	public boolean isAjoutSrvcPriv() {
		return ajoutSrvcPriv;
	}

	public void setAjoutSrvcPriv(boolean ajoutSrvcPriv) {
		this.ajoutSrvcPriv = ajoutSrvcPriv;
	}

	public boolean isModifSrvcPriv() {
		return modifSrvcPriv;
	}

	public void setModifSrvcPriv(boolean modifSrvcPriv) {
		this.modifSrvcPriv = modifSrvcPriv;
	}

	public boolean isSupSrvcPriv() {
		return supSrvcPriv;
	}

	public void setSupSrvcPriv(boolean supSrvcPriv) {
		this.supSrvcPriv = supSrvcPriv;
	}

	public boolean isAjoutVilPriv() {
		return ajoutVilPriv;
	}

	public void setAjoutVilPriv(boolean ajoutVilPriv) {
		this.ajoutVilPriv = ajoutVilPriv;
	}

	public boolean isModifVilPriv() {
		return modifVilPriv;
	}

	public void setModifVilPriv(boolean modifVilPriv) {
		this.modifVilPriv = modifVilPriv;
	}

	public boolean isSupVilPriv() {
		return supVilPriv;
	}

	public void setSupVilPriv(boolean supVilPriv) {
		this.supVilPriv = supVilPriv;
	}

	public boolean isAjoutTachPriv() {
		return ajoutTachPriv;
	}

	public void setAjoutTachPriv(boolean ajoutTachPriv) {
		this.ajoutTachPriv = ajoutTachPriv;
	}

	public boolean isModifTachPriv() {
		return modifTachPriv;
	}

	public void setModifTachPriv(boolean modifTachPriv) {
		this.modifTachPriv = modifTachPriv;
	}

	public boolean isSupTachPriv() {
		return supTachPriv;
	}

	public void setSupTachPriv(boolean supTachPriv) {
		this.supTachPriv = supTachPriv;
	}

	public boolean isAjoutPcePriv() {
		return ajoutPcePriv;
	}

	public void setAjoutPcePriv(boolean ajoutPcePriv) {
		this.ajoutPcePriv = ajoutPcePriv;
	}

	public boolean isModifPcePriv() {
		return modifPcePriv;
	}

	public void setModifPcePriv(boolean modifPcePriv) {
		this.modifPcePriv = modifPcePriv;
	}

	public boolean isSupPcePriv() {
		return supPcePriv;
	}

	public void setSupPcePriv(boolean supPcePriv) {
		this.supPcePriv = supPcePriv;
	}

	public boolean isAjoutAccPriv() {
		return ajoutAccPriv;
	}

	public void setAjoutAccPriv(boolean ajoutAccPriv) {
		this.ajoutAccPriv = ajoutAccPriv;
	}

	public boolean isModifAccPriv() {
		return modifAccPriv;
	}

	public void setModifAccPriv(boolean modifAccPriv) {
		this.modifAccPriv = modifAccPriv;
	}

	public boolean isSupAccPriv() {
		return supAccPriv;
	}

	public void setSupAccPriv(boolean supAccPriv) {
		this.supAccPriv = supAccPriv;
	}

	public boolean isAjoutPanPriv() {
		return ajoutPanPriv;
	}

	public void setAjoutPanPriv(boolean ajoutPanPriv) {
		this.ajoutPanPriv = ajoutPanPriv;
	}

	public boolean isModifPanPriv() {
		return modifPanPriv;
	}

	public void setModifPanPriv(boolean modifPanPriv) {
		this.modifPanPriv = modifPanPriv;
	}

	public boolean isSupPanPriv() {
		return supPanPriv;
	}

	public void setSupPanPriv(boolean supPanPriv) {
		this.supPanPriv = supPanPriv;
	}

	public boolean isAjoutMachPriv() {
		return ajoutMachPriv;
	}

	public void setAjoutMachPriv(boolean ajoutMachPriv) {
		this.ajoutMachPriv = ajoutMachPriv;
	}

	public boolean isModifMachPriv() {
		return modifMachPriv;
	}

	public void setModifMachPriv(boolean modifMachPriv) {
		this.modifMachPriv = modifMachPriv;
	}

	public boolean isSupMachPriv() {
		return supMachPriv;
	}

	public void setSupMachPriv(boolean supMachPriv) {
		this.supMachPriv = supMachPriv;
	}

	public boolean isAjoutCltPriv() {
		return ajoutCltPriv;
	}

	public void setAjoutCltPriv(boolean ajoutCltPriv) {
		this.ajoutCltPriv = ajoutCltPriv;
	}

	public boolean isModifCltPriv() {
		return modifCltPriv;
	}

	public void setModifCltPriv(boolean modifCltPriv) {
		this.modifCltPriv = modifCltPriv;
	}

	public boolean isSupCltPriv() {
		return supCltPriv;
	}

	public void setSupCltPriv(boolean supCltPriv) {
		this.supCltPriv = supCltPriv;
	}

	public boolean isAjoutFourPriv() {
		return ajoutFourPriv;
	}

	public void setAjoutFourPriv(boolean ajoutFourPriv) {
		this.ajoutFourPriv = ajoutFourPriv;
	}

	public boolean isModifFourPriv() {
		return modifFourPriv;
	}

	public void setModifFourPriv(boolean modifFourPriv) {
		this.modifFourPriv = modifFourPriv;
	}

	public boolean isSupFourPriv() {
		return supFourPriv;
	}

	public void setSupFourPriv(boolean supFourPriv) {
		this.supFourPriv = supFourPriv;
	}

	public List<Utilisateur> getListUtlByCin() {
		return listUtlByCin;
	}

	public void setListUtlByCin(List<Utilisateur> listUtlByCin) {
		this.listUtlByCin = listUtlByCin;
	}

	public List<Utilisateur> getListUtlBylogin() {
		return listUtlBylogin;
	}

	public void setListUtlBylogin(List<Utilisateur> listUtlBylogin) {
		this.listUtlBylogin = listUtlBylogin;
	}

	public boolean isGestTypFourPriv() {
		return gestTypFourPriv;
	}

	public void setGestTypFourPriv(boolean gestTypFourPriv) {
		this.gestTypFourPriv = gestTypFourPriv;
	}

	public boolean isGestTypMachPriv() {
		return gestTypMachPriv;
	}

	public void setGestTypMachPriv(boolean gestTypMachPriv) {
		this.gestTypMachPriv = gestTypMachPriv;
	}

	public boolean isGestMarqPriv() {
		return gestMarqPriv;
	}

	public void setGestMarqPriv(boolean gestMarqPriv) {
		this.gestMarqPriv = gestMarqPriv;
	}

	public boolean isGestSrvcPriv() {
		return gestSrvcPriv;
	}

	public void setGestSrvcPriv(boolean gestSrvcPriv) {
		this.gestSrvcPriv = gestSrvcPriv;
	}

	public boolean isGestvilPriv() {
		return gestvilPriv;
	}

	public void setGestvilPriv(boolean gestvilPriv) {
		this.gestvilPriv = gestvilPriv;
	}

	public boolean isGestGradePriv() {
		return gestGradePriv;
	}

	public void setGestGradePriv(boolean gestGradePriv) {
		this.gestGradePriv = gestGradePriv;
	}

	public boolean isGestStatutPriv() {
		return gestStatutPriv;
	}

	public void setGestStatutPriv(boolean gestStatutPriv) {
		this.gestStatutPriv = gestStatutPriv;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getMotpass1() {
		return motpass1;
	}

	public void setMotpass1(String motpass1) {
		this.motpass1 = motpass1;
	}

	public String getMotpass2() {
		return motpass2;
	}

	public void setMotpass2(String motpass2) {
		this.motpass2 = motpass2;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getVil() {
		return vil;
	}

	public void setVil(String vil) {
		this.vil = vil;
	}

	public String getNomGrade() {
		return nomGrade;
	}

	public void setNomGrade(String nomGrade) {
		this.nomGrade = nomGrade;
	}

	public String getNomVille() {
		return nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	public Integer getIdville() {
		if(v != null){
			idville=v.getIdville();
			v=null;
		}
		return idville;
	}

	public void setIdville(Integer idville) {
		this.idville = idville;
	}

	public boolean isFichCltPriv() {
		return fichCltPriv;
	}

	public void setFichCltPriv(boolean fichCltPriv) {
		this.fichCltPriv = fichCltPriv;
	}

	public boolean isFichUtlPriv() {
		return fichUtlPriv;
	}

	public void setFichUtlPriv(boolean fichUtlPriv) {
		this.fichUtlPriv = fichUtlPriv;
	}

	public boolean isFichFourPriv() {
		return fichFourPriv;
	}

	public void setFichFourPriv(boolean fichFourPriv) {
		this.fichFourPriv = fichFourPriv;
	}

	public boolean isFichMachPriv() {
		return fichMachPriv;
	}

	public void setFichMachPriv(boolean fichMachPriv) {
		this.fichMachPriv = fichMachPriv;
	}

	public boolean isFichPanPriv() {
		return fichPanPriv;
	}

	public void setFichPanPriv(boolean fichPanPriv) {
		this.fichPanPriv = fichPanPriv;
	}

	public boolean isFichTachPriv() {
		return fichTachPriv;
	}

	public void setFichTachPriv(boolean fichTachPriv) {
		this.fichTachPriv = fichTachPriv;
	}

	public boolean isFichPiecePriv() {
		return fichPiecePriv;
	}

	public void setFichPiecePriv(boolean fichPiecePriv) {
		this.fichPiecePriv = fichPiecePriv;
	}

	public boolean isFichAccPriv() {
		return fichAccPriv;
	}

	public void setFichAccPriv(boolean fichAccPriv) {
		this.fichAccPriv = fichAccPriv;
	}

	public Integer getIdGrade() {
		if(grade!= null){
			idGrade=grade.getIdGrade();
			grade=null;
			
		}
		return idGrade;
	}

	public void setIdGrade(Integer idGrade) {
		this.idGrade = idGrade;
	}

	public String getValeurRecherche() {
		return valeurRecherche;
	}

	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}

	public String getAttribut() {
		return attribut;
	}

	public void setAttribut(String attribut) {
		this.attribut = attribut;
	}

	public List<String> getListesRecherches() {
		
		listesRecherches.clear();
		listesRecherches.add("---Selectionner---");
		listesRecherches.add("Utilisateur");
		listesRecherches.add("Tel");
		listesRecherches.add("CIN");
		listesRecherches.add("Grade");
		return listesRecherches;
	}

	public void setListesRecherches(List<String> listesRecherches) {
		this.listesRecherches = listesRecherches;
	}

	

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public List<Grade> getGrades() {
		
		GradeService sr = new GradeService();
		grades = sr.rechercheTousGrade();
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	
	
	
	public List<Service> getServices() {
		ServiceService sr= new ServiceService();
		services=sr.rechercheTousService();
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Ville> getVilles() {
		VilleService sr = new VilleService();
		villes = sr.rechercheTousVille();
		
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
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


	public List<Utilisateur> getUtilisateurs() {
		
		UtilisateurService ser= new UtilisateurService();
		if ((valeurRecherche != null) && (attribut != null))
			
			utilisateurs = ser.rechercheFiltre(attribut, valeurRecherche);
		else
			
		utilisateurs= ser.rechercheTousUtilisateur();
		value=1;
		
		/*System.out.println("size "+utilisateurs.size());
		for(int i=0;i<utilisateurs.size();i++)
			System.out.println(utilisateurs.get(i));*/
		return utilisateurs;
	}


	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}


	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void modifierUtilisateur (Utilisateur u) {
		action="Modifier";
		inputMotpass=false;
		outputMotpass=false;
		titreModif=true;
		titreAjt=false;
		idutilisateur = u.getIdutilisateur();
		nomUtl = u.getNomUtl();
		prenomUtl = u.getPrenomUtl();
		mailUtl= u.getMailUtl();
		adrUtl = u.getAdrUtl();
		gsm1Utl= u.getGsm1Utl();
		gsm2Utl= u.getGsm2Utl();
		telUtl= u.getTelUtl();
		cinUtl= u.getCinUtl();
		login = u.getLogin();
		motPass = u.getMotPass();
		
		//if(u.getGrade()!= null)
			//idGrade=u.getGrade().getIdGrade();
		
		if(u.getVille()!= null){
			idville=u.getVille().getIdville();
		    nomVille=u.getVille().getDesignationVille();
		}
		
		if (u.getGrade() != null) {
			idGrade = u.getGrade().getIdGrade();
			nomGrade = u.getGrade().getLibelleGrd();
		}
		
		if(u.getService()!=null){
			idservice=u.getService().getIdservice();
			designationSer=u.getService().getDesignationSer();
		}
		
		
		if (u.getGestClt().equals("1")) {
			fichCltPriv=true;
		} else {
			fichCltPriv=false;
		}
		
		if (u.getGestUtl().equals("1")) {
			fichUtlPriv=true;
		} else {
			fichUtlPriv=false;
		}
		
		if (u.getGestFour().equals("1")) {
			fichFourPriv=true;
		} else {
			fichFourPriv=false;
		}
		
		if (u.getGestMach().equals("1")) {
			 fichMachPriv=true;
		} else {
			 fichMachPriv=false;
		}
		
		if (u.getGestPan().equals("1")) {
			fichPanPriv=true;
		} else {
			fichPanPriv=false;
		}
		
		if (u.getGestTach().equals("1")) {
			 fichTachPriv=true;
		} else {
			 fichTachPriv=false;
		}
		
		if (u.getGestPce().equals("1")) {
			fichPiecePriv=true;
		} else {
			fichPiecePriv=false;
		}
		
		if (u.getGestAcc().equals("1")) {
			fichAccPriv=true;
		} else {
			fichAccPriv=false;
		}
		
		if (u.getNouvFour().equals("1")) {
			ajoutFourPriv=true;
		} else {
			ajoutFourPriv=false;
		}
		
		if (u.getModifFour().equals("1")) {
			modifFourPriv=true;
		} else {
			modifFourPriv=false;
		}
		
		if (u.getSupFour().equals("1")) {
			supFourPriv=true;
		} else {
			supFourPriv=false;
		}
		
		if (u.getNouvClt().equals("1")) {
			ajoutCltPriv=true;
		} else {
			ajoutCltPriv=false;
		}
		
		if (u.getModifClt().equals("1")) {
			modifCltPriv=true;
		} else {
			modifCltPriv=false;
		}
		
		if (u.getSupClt().equals("1")) {
			supCltPriv=true;
		} else {
			supCltPriv=false;
		}
		 
		if (u.getNouvMach().equals("1")) {
			ajoutMachPriv=true;
		} else {
			ajoutMachPriv=false;
		}
		
		if (u.getModifMach().equals("1")) {
			modifMachPriv=true;
		} else {
			modifMachPriv=false;
		}
		
		if (u.getSupMach().equals("1")) {
			supMachPriv=true;
		} else {
			supMachPriv=false;
		}
		
		if (u.getNouvPan().equals("1")) {
			ajoutPanPriv=true;
		} else {
			ajoutPanPriv=false;
		}
		
		if (u.getModifPan().equals("1")) {
			modifPanPriv=true;
		} else {
			modifPanPriv=false;
		}
		
		if (u.getSupPan().equals("1")) {
			supPanPriv=true;
		} else {
			supPanPriv=false;
		}
		
		if (u.getNouvAcc().equals("1")) {
			ajoutAccPriv=true;
		} else {
			ajoutAccPriv=false;
		}
		
		if (u.getModifAcc().equals("1")) {
			modifAccPriv=true;
		} else {
			modifAccPriv=false;
		}
		
		if (u.getSupAcc().equals("1")) {
			supAccPriv=true;
		} else {
			supAccPriv=false;
		}
		
		if (u.getSupPce().equals("1")) {
			supPcePriv=true;
		} else {
			supPcePriv=false;
		}
		
		if (u.getNouvPce().equals("1")) {
			ajoutPcePriv=true;
		} else {
			ajoutPcePriv=false;
		}
		
		if (u.getModifPce().equals("1")) {
			modifPcePriv=true;
		} else {
			modifPcePriv=false;
		}
		
		if (u.getSupPce().equals("1")) {
			supPcePriv=true;
		} else {
			supPcePriv=false;
		}
		
		if (u.getNouvTach().equals("1")) {
			ajoutTachPriv=true;
		} else {
			ajoutTachPriv=false;
		}
		
		if (u.getModifTach().equals("1")) {
			modifTachPriv=true;
		} else {
			modifTachPriv=false;
		}
		
		if (u.getSupTach().equals("1")) {
			supTachPriv=true;
		} else {
			supTachPriv=false;
		}
		
		if (u.getNouvTypFour().equals("1")) {
			ajoutTypFourPriv=true;
		} else {
			ajoutTypFourPriv=false;
		}
		
		if (u.getModifTypFour().equals("1")) {
			modifTypFourPriv=true;
		} else {
			modifTypFourPriv=false;
		}
		
		if (u.getSupTypFour().equals("1")) {
			supTypFourPriv=true;
		} else {
			supTypFourPriv=false;
		}
		
		if (u.getNouvTyp().equals("1")) {
			ajoutTypMachPriv=true;
		} else {
			ajoutTypMachPriv=false;
		}
		
		if (u.getModifTyp().equals("1")) {
			modifTypMachPriv=true;
		} else {
			modifTypMachPriv=false;
		}
		
		if (u.getSupTyp().equals("1")) {
			supTypMachPriv=true;
		} else {
			supTypMachPriv=false;
		}
		
		if (u.getNouvMqe().equals("1")) {
			ajoutMarqPriv=true;
		} else {
			ajoutMarqPriv=false;
		}
		
		if (u.getModifMqe().equals("1")) {
			modifMarqPriv=true;
		} else {
			modifMarqPriv=false;
		}
		
		if (u.getSupMqe().equals("1")) {
			supMarqPriv=true;
		} else {
			supMarqPriv=false;
		}
		
		if (u.getNouvStat().equals("1")) {
			ajoutStatuPriv=true;
		} else {
			ajoutStatuPriv=false;
		}
		
		if (u.getModifStat().equals("1")) {
			modifStatuPriv=true;
		} else {
			modifStatuPriv=false;
		}
		
		if (u.getSupStat().equals("1")) {
			supStatuPriv=true;
		} else {
			supStatuPriv=false;
		}
		
		if (u.getNouvSer().equals("1")) {
			ajoutSrvcPriv=true;
		} else {
			ajoutSrvcPriv=false;
		}
		
		if (u.getModifSer().equals("1")) {
			modifSrvcPriv=true;
		} else {
			modifSrvcPriv=false;
		}
		
		if (u.getSupSer().equals("1")) {
			supSrvcPriv=true;
		} else {
			supSrvcPriv=false;
		}
		
		if (u.getNouvGde().equals("1")) {
			ajoutGrdePriv=true;
		} else {
			ajoutGrdePriv=false;
		}
		
		if (u.getModifGde().equals("1")) {
			modifGrdePriv=true;
		} else {
			modifGrdePriv=false;
		}
		
		if (u.getSupGde().equals("1")) {
			supGrdePriv=true;
		} else {
			supGrdePriv=false;
		}
		
		if (u.getNouvVil().equals("1")) {
			ajoutVilPriv=true;
		} else {
			ajoutVilPriv=false;
		}
		
		if (u.getModifVil().equals("1")) {
			modifVilPriv=true;
		} else {
			modifVilPriv=false;
		}
		
		if (u.getSupVil().equals("1")) {
			supVilPriv=true;
		} else {
			supVilPriv=false;
		}
		
		
		
		
    	 gestTypFourPriv=!(u.isGestTypFour());
		 gestTypMachPriv=!(u.isGestTypMach());
		 gestMarqPriv=!(u.isGestMarq());
		 gestSrvcPriv=!(u.isGestSrvc());
		 gestvilPriv=!(u.isGestVil());
		 gestGradePriv=!(u.isGestGrade());
		 gestStatutPriv=!(u.isGestStatut());
		 
		 gestEatatPriv=!(u.isGestEtat());
		 getAplPriv=!(u.isGestApl());
		 System.out.println("************getAplPriv************="+getAplPriv);
		 
		 paramPriv=!(u.isParametrage());
		 profilPriv=!(u.isProfil());
		 filAttPriv=!(u.isFilAtt());
		 calendrierPriv=!(u.isCalender());
		 
		 ajoutAplPriv=u.isAjoutApl();
		 modifAplPriv=u.isModifApl();
		 supAplPriv=u.isSupApl();
		 detailAplPriv=u.isDetApl();
		 envoiAplFourPriv=u.isEnvAplChezFour();
		 recoiAplFourPriv=u.isRecoiAplDeFour();
		 changEtatAplPriv=u.isChangEtaApl();
		 detailEtatAplPriv=u.isDetEatApl();
		 
		 
		 try{
				FacesContext.getCurrentInstance().getExternalContext().redirect("AjoutUtilisateur.xhtml");
				}catch(Exception e){
					System.out.println(e.getMessage());
				}

		
				
	}
		
	
	public String validation() {
		
		FacesContext faces = FacesContext.getCurrentInstance();
		UtilisateurService ser= new UtilisateurService();
		Utilisateur u = new Utilisateur();
		GradeService gdser= new GradeService();
		VilleService vilser= new VilleService();
		ServiceService serSer= new ServiceService();
		
		listUtlByCin=  ser.rechercheParCin(cinUtl);
		
		if (idGrade != null) // teste si le liste grade n'est pas
			                 // selectioner
         {
			System.out.println("id grade selectionnéé est =="+idGrade);
			Grade g = new Grade();
			g=gdser.rechercheById(idGrade);
			u.setGrade(g);
		}
		
		if (idville != null) // teste si le liste ville n'est pas
                            // selectioner
				{
				Ville v = new Ville();
				v= vilser.rechercheParId(idville);
				u.setVille(v);
				
				}
		
		if (idservice != null) // teste si le liste service n'est pas
            // selectioner
			{
			Service s = new Service();
			s= serSer.rechercheParId(idservice);
			u.setService(s);
			
			}
		
		u.setNomUtl(nomUtl);
		u.setPrenomUtl(prenomUtl);
		u.setMailUtl(mailUtl);
		u.setAdrUtl(adrUtl);
		u.setGsm1Utl(gsm1Utl);
		u.setGsm2Utl(gsm2Utl);
		u.setTelUtl(telUtl);
		u.setCinUtl(cinUtl);
		u.setLogin(login);
		u.setMotPass(motPass);
		u.setActif(true);
		u.setPassif(false);
		
		if (fichCltPriv) {
			u.setGestClt("1");
		} else {
			u.setGestClt("0");
		}
		
		if (fichUtlPriv) {
			u.setGestUtl("1");
		} else {
			u.setGestUtl("0");
		}
		
		if (fichFourPriv) {
			u.setGestFour("1");
		} else {
			u.setGestFour("0");
		}
		
		if (fichMachPriv) {
			u.setGestMach("1");
		} else {
			u.setGestMach("0");
		}
		
		if (fichPanPriv) {
			u.setGestPan("1");
		} else {
			u.setGestPan("0");
		}
		
		if (fichTachPriv) {
			u.setGestTach("1");
		} else {
			u.setGestTach("0");
		}
		
		if (fichAccPriv) {
			u.setGestAcc("1");
		} else {
			u.setGestAcc("0");
		}
		
		if (fichPiecePriv) {
			u.setGestPce("1");
		} else {
			u.setGestPce("0");
		}
		
		
		if (gestGradePriv) {
			u.setGestGrade(false);
		} else {
			u.setGestGrade(true);
		}
		
		if (gestMarqPriv) {
			u.setGestMarq(false);
		} else {
			u.setGestMarq(true);
		}
		
		if (gestTypFourPriv) {
			u.setGestTypFour(false);
		} else {
			u.setGestTypFour(true);
		}
		
		if (gestTypMachPriv) {
			u.setGestTypMach(false);
		} else {
			u.setGestTypMach(true);
		}
		
		if (gestvilPriv) {
			u.setGestVil(false);
		} else {
			u.setGestVil(true);
		}
		
		if (gestSrvcPriv) {
			u.setGestSrvc(false);
		} else {
			u.setGestSrvc(true);
		}
		
		if (gestStatutPriv) {
			u.setGestStatut(false);
		} else {
			u.setGestStatut(true);
		}
		
		if (ajoutFourPriv) {
			u.setNouvFour("1");
		} else {
			u.setNouvFour("0");
		}
		
		if (modifFourPriv) {
			u.setModifFour("1");
		} else {
			u.setModifFour("0");
		}
		
		if (supFourPriv) {
			u.setSupFour("1");
		} else {
			u.setSupFour("0");
		}
		
		if (ajoutCltPriv) {
			u.setNouvClt("1");
		} else {
			u.setNouvClt("0");
		}
		
		if (modifCltPriv) {
			u.setModifClt("1");
		} else {
			u.setModifClt("0");
		}
		
		if (supCltPriv) {
			u.setSupClt("1");
		} else {
			u.setSupClt("0");
		}
		
		if (ajoutMachPriv) {
			u.setNouvMach("1");
		} else {
			u.setNouvMach("0");
		}
		
		if (modifMachPriv) {
			u.setModifMach("1");
		} else {
			u.setModifMach("0");
		}
		
		if (supMachPriv) {
			u.setSupMach("1");
		} else {
			u.setSupMach("0");
		}
		
		if (ajoutPanPriv) {
			u.setNouvPan("1");
		} else {
			u.setNouvPan("0");
		}
		
		if (modifPanPriv) {
			u.setModifPan("1");
		} else {
			u.setModifPan("0");
		}
		
		if (supPanPriv) {
			u.setSupPan("1");
		} else {
			u.setSupPan("0");
		}
		
		if (ajoutAccPriv) {
			u.setNouvAcc("1");
		} else {
			u.setNouvAcc("0");
		}
		
		if (modifAccPriv) {
			u.setModifAcc("1");
		} else {
			u.setModifAcc("0");
		}
		
		if (supAccPriv) {
			u.setSupAcc("1");
		} else {
			u.setSupAcc("0");
		}
		
		if (ajoutPcePriv) {
			u.setNouvPce("1");
		} else {
			u.setNouvPce("0");
		}
		
		if (modifPcePriv) {
			u.setModifPce("1");
		} else {
			u.setModifPce("0");
		}
		
		if (supPcePriv) {
			u.setSupPce("1");
		} else {
			u.setSupPce("0");
		}
		
		if (ajoutTachPriv) {
			u.setNouvTach("1");
		} else {
			u.setNouvTach("0");
		}
		
		if (modifTachPriv) {
			u.setModifTach("1");
		} else {
			u.setModifTach("0");
		}
		
		if (supTachPriv) {
			u.setSupTach("1");
		} else {
			u.setSupTach("0");
		}
		
		if (ajoutTypFourPriv) {
			u.setNouvTypFour("1");
		} else {
			u.setNouvTypFour("0");
		}
		
		if (modifTypFourPriv) {
			u.setModifTypFour("1");
		} else {
			u.setModifTypFour("0");
		}
		
		if (supTypFourPriv) {
			u.setSupTypFour("1");
		} else {
			u.setSupTypFour("0");
		}
		
		if (ajoutTypMachPriv) {
			u.setNouvTyp("1");
		} else {
			u.setNouvTyp("0");
		}
		
		if (modifTypMachPriv) {
			u.setModifTyp("1");
		} else {
			u.setModifTyp("0");
		}
		
		if (supTypMachPriv) {
			u.setSupTyp("1");
		} else {
			u.setSupTyp("0");
		}
		
		if (ajoutMarqPriv) {
			u.setNouvMqe("1");
		} else {
			u.setNouvMqe("0");
		}
		
		if (modifMarqPriv) {
			u.setModifMqe("1");
		} else {
			u.setModifMqe("0");
		}
		
		if (supMarqPriv) {
			u.setSupMqe("1");
		} else {
			u.setSupMqe("0");
		}
		
		if (ajoutStatuPriv) {
			u.setNouvStat("1");
		} else {
			u.setNouvStat("0");
		}
		
		if (modifStatuPriv) {
			u.setModifStat("1");
		} else {
			u.setModifStat("0");
		}
		
		if (supStatuPriv) {
			u.setSupStat("1");
		} else {
			u.setSupStat("0");
		}
		
		if (ajoutSrvcPriv) {
			u.setNouvSer("1");
		} else {
			u.setNouvSer("0");
		}
		
		
		if (modifSrvcPriv) {
			u.setModifSer("1");
		} else {
			u.setModifSer("0");
		}
		
		if (supSrvcPriv) {
			u.setSupSer("1");
		} else {
			u.setSupSer("0");
		}
       
		if (ajoutGrdePriv) {
			u.setNouvGde("1");
		} else {
			u.setNouvGde("0");
		}
		
		if (modifGrdePriv) {
			u.setModifGde("1");
		} else {
			u.setModifGde("0");
		}
		
		if (supGrdePriv) {
			u.setSupGde("1");
		} else {
			u.setSupGde("0");
		}

		if (ajoutVilPriv) {
			u.setNouvVil("1");
		} else {
			u.setNouvVil("0");
		}
		
		if (modifVilPriv) {
			u.setModifVil("1");
		} else {
			u.setModifVil("0");
		}
		
		if (supVilPriv) {
			u.setSupVil("1");
		} else {
			u.setSupVil("0");
		}
		
		if (getAplPriv) {
			u.setGestApl(false);
		} else {
			u.setGestApl(true);
		}
		if (paramPriv) {
			u.setParametrage(false);
		} else {
			u.setParametrage(true);
		}
		if (filAttPriv) {
			u.setFilAtt(false);
		} else {
			u.setFilAtt(true);
		}
		
		if (profilPriv) {
			u.setProfil(false);
		} else {
			u.setProfil(true);
		}
		
		if (calendrierPriv) {
			u.setCalender(false);
		} else {
			u.setCalender(true);
		}
		
		if (gestEatatPriv) {
			u.setGestEtat(false);
		} else {
			u.setGestEtat(true);
		}
		
		if(ajoutAplPriv){
			u.setAjoutApl(true);
		}
		else{
			u.setAjoutApl(false);
		}
		
		if(modifAplPriv){
			u.setModifApl(true);
		}
		else{
			u.setModifApl(false);
		}
		
		if(supAplPriv){
			u.setSupApl(true);
		}
		else{
			u.setSupApl(false);
		}
		
		if(detailAplPriv){
			u.setDetApl(true);
		}
		else{
			u.setDetApl(false);
		}
		
		if(envoiAplFourPriv){
			u.setEnvAplChezFour(true);
		}
		else{
			u.setEnvAplChezFour(false);
		}
		
		if(recoiAplFourPriv){
			u.setRecoiAplDeFour(true);
		}
		else{
			u.setRecoiAplDeFour(false);
		}
		
		if(changEtatAplPriv){
			u.setChangEtaApl(true);
		}
		else{
			u.setChangEtaApl(false);
		}
		
		if(detailEtatAplPriv){
			u.setDetEatApl(true);
		}
		else{
			u.setDetEatApl(false);
		}
		
		if(action.equals("Modifier"))
		   {
			
			u.setIdutilisateur(idutilisateur);
			ser.modifierUtilisateur(u);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, " Utilisateur modifié avec succès ",
					"Invalid credentials"));
		   }
	  if (action.equals("Ajouter")){
		
	      ser.ajouterUtilisateur(u);
	      faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Utilisateur ajouté avec succès",
					"Invalid credentials"));
	      }
	  
	  
	   initialisation();
        
	  	   return "acceptedValidUtl";
		
		}
		
	public void initialisation (){
		idutilisateur = null;
		nomUtl = null;
		prenomUtl = null;
		mailUtl= null;
		adrUtl = null;
		gsm1Utl= null;
		gsm2Utl= null;
		telUtl= null;
		cinUtl= null;
		login = null;
		motPass = null;
		nomVille=null;
		idville=null;
		nomGrade=null;
		idGrade=null;
		
	   designationSer=null;
	   idservice=null;
		
		gestEatatPriv=false;
		getAplPriv=false;
		paramPriv=false;
		filAttPriv=false;
		profilPriv=false;
		calendrierPriv=false;
		
		 fichCltPriv=false;
		 fichUtlPriv=false;
		 fichFourPriv=false;
		 fichMachPriv=false;
		 fichPanPriv=false;
		 fichTachPriv=false;
		 fichPiecePriv=false;
		 fichAccPriv=false;
		
		 gestTypFourPriv=false;
		 gestTypMachPriv=false;
		 gestMarqPriv=false;
		 gestSrvcPriv=false;
		 gestvilPriv=false;
		 gestGradePriv=false;
		 gestStatutPriv=false;
		 
		 ajoutFourPriv=false;
		 modifFourPriv=false;
		 supFourPriv=false;
		 
		 ajoutCltPriv=false;
		 modifCltPriv=false;
		 supCltPriv=false;
		 
		 ajoutMachPriv=false;
		 modifMachPriv=false;
		 supMachPriv=false;
		 
		 ajoutPanPriv=false;
		 modifPanPriv=false;
		 supPanPriv=false;
		 
		 ajoutAccPriv=false;
		 modifAccPriv=false;
		 supAccPriv=false;
		 
		 ajoutPcePriv=false;
		 modifPcePriv=false;
		 supPcePriv=false;
		 
		 ajoutTachPriv=false;
		 modifTachPriv=false;
		 supTachPriv=false;
		 
		 ajoutTypFourPriv=false;
		 modifTypFourPriv=false;
		 supTypFourPriv=false;
		 
		 ajoutTypFourPriv=false;
		 modifTypFourPriv=false;
	     supTypFourPriv=false;
			
		 ajoutTypMachPriv=false;
		 modifTypMachPriv=false;
		 supTypMachPriv=false;
			
		 ajoutMarqPriv=false;
		 modifMarqPriv=false;
		 supMarqPriv=false;
			
		 ajoutGrdePriv=false;
		 modifGrdePriv=false;
		 supGrdePriv=false;
			
		 ajoutStatuPriv=false;
		 modifStatuPriv=false;
		 supStatuPriv=false;
			
	     ajoutSrvcPriv=false;
		 modifSrvcPriv=false;
		 supSrvcPriv=false;
			
		 ajoutVilPriv=false;
		 modifVilPriv=false;
		 supVilPriv=false;
		 
		 ajoutAplPriv=false;
         modifAplPriv=false;
		 supAplPriv=false;
		 detailAplPriv=false;
		 envoiAplFourPriv=false;
		 recoiAplFourPriv=false;
		 changEtatAplPriv=false;
		 detailEtatAplPriv=false;		 
		
		 
			 
		
	}
	
	public void ajouterUtilisateur( ){
		
		
		initialisation ();
		titreAjt=true;
		titreModif=false;
		outputMotpass=true;
		inputMotpass=true;
	    action="Ajouter";
	    
	    //return "AjoutUtilisateur.xhtml?faces-redirect=true";
	    try{
			FacesContext.getCurrentInstance().getExternalContext().redirect("AjoutUtilisateur.xhtml");
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	
	}

	public void Supprimer(Integer id) {
		
		FacesContext faces = FacesContext.getCurrentInstance();
		UtilisateurService ser = new UtilisateurService();
		DiagnostiqueService diagSer= new DiagnostiqueService();
		Rep_UtlService repUtlSer= new Rep_UtlService();
		Reg_pieceService regPceSer= new Reg_pieceService();
		Reg_tacheService regTachSer= new Reg_tacheService();
		
	    List<Diagnostique> diagByUtl = new ArrayList<Diagnostique>(0);
	    List<Rep_Utl> repUtlByUtl = new ArrayList<Rep_Utl>(0);
	    List<Reg_piece> regPceByUtl = new ArrayList<Reg_piece>(0);
	    List<Reg_tache> regTachByUtl = new ArrayList<Reg_tache>(0);
	    
	    
	    diagByUtl= diagSer.rechercheDiagByIdUtl(id);
	    repUtlByUtl= repUtlSer.rechercheRep_UtlByIdUtl(id);
	    regPceByUtl= regPceSer.rechercheReg_pieceByIdUtl(id);
	    regTachByUtl= regTachSer.rechercheReg_tacheByIdUtl(id);
	    		
	    if(diagByUtl.size()>0 || repUtlByUtl.size()>0 || regPceByUtl.size()>0 || regTachByUtl.size()>0  ){
			
	    	faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Suppression impossible, utilisateur utilisé",
					"Invalid credentials"));
		}else{
	    
		ser.supprimerUtilisateur(id);
		
		RequestContext.getCurrentInstance().update("f1");
		faces.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Utilisateur supprimé avec succès",
				"Invalid credentials"));
		}
		
	}
	public void annulerRecherche() {
		valeurRecherche = null;
		attribut="---Selectionner---";
	}
	
	public String redirect(){
		
		System.out.println("rediretmesaaaaaaaaaage");
		return "pageGestionUtl";
	}
	
	
	public void validatecin(FacesContext context,UIComponent componentToValidate,Object value)throws ValidatorException {
		System.out.println("get in valideMethode");
			
		//FacesContext face = FacesContext.getCurrentInstance();
		//face=context;
		    //System.out.println("value======"+value);
		
		
		
		 
			Long cinUtl = (Long) value;
			String cin= String.valueOf(cinUtl);
			UtilisateurService ser= new UtilisateurService();
			 listUtlByCin=  ser.rechercheParCin(cin);
			
		
			if (listUtlByCin.size()>0 && listUtlByCin.get(0).getIdutilisateur() != idutilisateur ) {
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Cin exixte deja.");
				throw new ValidatorException(message);
				}
			
			
				
				//face.addMessage("tabajou:pnl1:cinId", message);
				
	           System.out.println("cin.length=="+cin.length()); 
       
		if(cin.length() < 8 || cin.length() > 8  ){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Cin doit contenir 8 chiffres.");
			
            throw new ValidatorException(message);
		}
		
  }
	
	
	public void validateLogin (FacesContext context,UIComponent componentToValidate,Object value)throws ValidatorException{
		
		UtilisateurService ser= new UtilisateurService();
		 
		String login = (String) value;
		 listUtlBylogin=  ser.rechercheParLogin(login);
		
	if (listUtlBylogin.size()>0 && listUtlBylogin.get(0).getIdutilisateur() != idutilisateur) {
		
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Login exixte deja.");
            throw new ValidatorException(message);
   }
		
	}
	
public void recupererDonnees(Utilisateur u) {

		
		idutilisateur = u.getIdutilisateur();
		nomUtl = u.getNomUtl();
		prenomUtl = u.getPrenomUtl();
		login = u.getLogin();
		motPass = u.getMotPass();
		gsm1Utl=u.getGsm1Utl();
		gsm2Utl=u.getGsm2Utl();
		telUtl = u.getTelUtl();
		adrUtl = u.getAdrUtl();
		mailUtl = u.getMailUtl();
		cinUtl=u.getCinUtl();
		
		if (u.getVille() != null) {
			//Ville v = new Ville();
			//v=u.getVille();
			//idville = u.getVille().getIdville();
			//VilleService ser= new VilleService();
		//	v=ser.rechercheParId(idville);
			nomVille=u.getVille().getDesignationVille();
			

		}
		
		 
        if(u.getGrade() !=null){
			
			nomGrade=u.getGrade().getLibelleGrd();
			System.out.println("nomGrade==============>2 "+nomGrade);
		}	
		
		
		
}
	
	
public void desactiveUtl(Utilisateur u){
	   
	  FacesContext faces = FacesContext.getCurrentInstance();
	   UtilisateurService ser = new UtilisateurService();
	   u.setActif(false);
	   u.setPassif(true);
	   ser.modifierUtilisateur(u);
	   
	   faces.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Utilisateur désactivé avec succè",
				"Invalid credentials"));

	   
}

public void activeUtl(Utilisateur u){
	FacesContext faces = FacesContext.getCurrentInstance();
	   UtilisateurService ser = new UtilisateurService();
	   u.setActif(true);
	   u.setPassif(false);
	   ser.modifierUtilisateur(u);
	   
	   faces.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Utilisateur activé avec succè",
				"Invalid credentials"));

	
}
	



public void recupererUtls(Utilisateur u) {

	idUtilisateur = u.getIdutilisateur();
	
	}

public void modifMotPass() {
	FacesContext face = FacesContext.getCurrentInstance();
	
	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Les deux mots de passe ne sont pas conformes");
	
	UtilisateurService ser = new UtilisateurService();
	System.out.println("id "+idUtilisateur);
	Utilisateur u = ser.rechercheUtilisateur(idUtilisateur);
	if (u != null) {
		 
		if(motpass1.equals(motpass2)){
			
		u.setMotPass(motpass1);
		ser.modifierUtilisateur(u);
	    face.getPartialViewContext().getRenderIds().add("f1");
		//RequestContext rc = RequestContext.getCurrentInstance();
		//rc.execute("dialogMotPass.hide()");
	    idUtilisateur=null;
		motpass1=null;
		motpass1=null;
		face.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_ERROR, "Mot de passe modifié avec succès",
				"Invalid credentials"));
		}
		else{
			
			//face.addMessage(null, new FacesMessage("Mot de passe n'est pa conforme"));
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
		}
	}
}

public void hideDialog2(){
	
	idUtilisateur=null;
	motpass1=null;
	motpass1=null;
}

	
	
	public void touCocherGeneral(){
		
		System.out.println("get in tout autoriser");
		
		gestEatatPriv=true;
		getAplPriv=true;
		paramPriv=true;
		filAttPriv=true;
		profilPriv=true;
		calendrierPriv=true;
		
		 fichCltPriv=true;
		 fichUtlPriv=true;
		 fichFourPriv=true;
		 fichMachPriv=true;
		 fichPanPriv=true;
		 fichTachPriv=true;
		 fichPiecePriv=true;
		 fichAccPriv=true;
		 gestTypFourPriv=true;
	     gestTypMachPriv=true;
		 gestMarqPriv=true;
		 gestSrvcPriv=true;
	     gestvilPriv=true;
		 gestGradePriv=true;
		 gestStatutPriv=true;
		 
		
	}
	public void toudeocherGeneral(){
		
		gestEatatPriv=false;
		getAplPriv=false;
		paramPriv=false;
		filAttPriv=false;
		profilPriv=false;
		calendrierPriv=false;
		
		 fichCltPriv=false;
		 fichUtlPriv=false;
		 fichFourPriv=false;
		 fichMachPriv=false;
		 fichPanPriv=false;
		 fichTachPriv=false;
		 fichPiecePriv=false;
		 fichAccPriv=false;
		 gestTypFourPriv=false;
	     gestTypMachPriv=false;
		 gestMarqPriv=false;
		 gestSrvcPriv=false;
	     gestvilPriv=false;
		 gestGradePriv=false;
		 gestStatutPriv=false;
		 
	}
	
	public void touCocherApel(){
		getAplPriv= true;
		 ajoutAplPriv=true;
         modifAplPriv=true;
		 supAplPriv=true;
		 detailAplPriv=true;
		 envoiAplFourPriv=true;
		 recoiAplFourPriv=true;
		 changEtatAplPriv=true;
		 detailEtatAplPriv=true;	
	}
	
	public void toudeocherApel(){
		 getAplPriv=false;
		 ajoutAplPriv=false;
         modifAplPriv=false;
		 supAplPriv=false;
		 detailAplPriv=false;
		 envoiAplFourPriv=false;
		 recoiAplFourPriv=false;
		 changEtatAplPriv=false;
		 detailEtatAplPriv=false;	
	}
	
	
	public String onFlowProcess(FlowEvent event) {
        
            return event.getNewStep();
        }
    
	
	
		
	public String getEtat(boolean etat) {
		if (etat ){
			
			System.out.println("get in if etat==true");
			return "active";
		}
		else{
			System.out.println("get in else etat==false");
			return "desactive";
			
		}
	}	
		
		
	public void redirectUtl(){
		
		FacesContext context2 = FacesContext.getCurrentInstance();
	    context2.getExternalContext().getFlash().setKeepMessages(true);
	    try {
	     context2.getExternalContext().redirect("GestionUtilisateur.xhtml");
	    }catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//return "../pages/GestionUtilisateur.xhtml?faces-redirect=true";
	}
	
	public void redirectverGestUtl(){
		
		 valeurRecherche=null;
		 attribut=null;
		 FacesContext context2 = FacesContext.getCurrentInstance();
		
		try {
		     context2.getExternalContext().redirect("GestionUtilisateur.xhtml");
		    }catch(Exception e){
				System.out.println(e.getMessage());
			}
			
	}
	
	public void ajouterVilApresValidation() {
		
		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		VilleService ser = new VilleService();
		 v = new Ville();
		boolean addValid = false;

		
			if (designationVille == null || (designationVille.trim().length() == 0)) {// tester
																					// si
																					// ville
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Ville vide",
						"Invalid credentials"));
			}

			else // tester si ce ville existe déjà
			{
				villeByNom = ser.rechercheParNom(designationVille);
				System.out.println("size liste recherche sevice=="
						+ villeByNom.size());
				if (villeByNom.size() == 0) { // ville n'existe pas
					addValid = true;
					v.setDesignationVille(designationVille);
					ser.ajouterVille(v);
					//RequestContext.getCurrentInstance().update("f1");
					context.execute("PF('dialogVil').hide();");
					
					initialisationvil();

				} else {
				
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Ville existe déja",
							"Invalid credentials"));
				}
			}

		}
	
	public void initialisationvil (){
		idville = null;
		designationVille = null;
			
	}
	
	public void redirectUtlisateur(){
		try{
		FacesContext.getCurrentInstance().getExternalContext().redirect("GestionUtilisateur.xhtml");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	
	public String controle(FlowEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext faces = FacesContext.getCurrentInstance();
		UtilisateurService utlser= new UtilisateurService();
		List<Utilisateur> utlBylog= utlser.rechercheParLogin(login);
		List<Utilisateur> utlBymotPss= utlser.rechercheUtilisateurByMotPass(motPass);
		 UtilisateurService ser= new UtilisateurService();
		 
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		Boolean b =true;
		currentStepId = event.getOldStep();
		if (currentStepId == null)
			currentStepId = "personal";
		// get current tab
		String stepToGo = event.getNewStep();
	
		if(currentStepId.equals("personal" )&& stepToGo.equals("priv")){
			
				
			if(mailUtl != null && mailUtl.trim().length() > 0) 
			    b=mailUtl.matches(EMAIL_REGEX);
			
			
	      if (login == null || (login.trim().length() == 0)) {// tester// si	// login	// est	// vide
				
				
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Login vide",
						"Invalid credentials"));
				context.update("f1:growl");
			}else if (utlBylog.size()>0 &&  utlBylog.get(0).getIdutilisateur() != idutilisateur){
				
				
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Login existe déja",
						"Invalid credentials"));
				context.update("f1:growl");
			}
			
	     if (motPass == null || (motPass.trim().length() == 0)) {// tester// si	// motPasse	// est	// vide
				
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Mot de Passe vide",
						"Invalid credentials"));
				         context.update("f1:growl");
		}
			
			
	     if ((nomUtl == null || (nomUtl.trim().length() == 0)) && (prenomUtl == null || (prenomUtl.trim().length() == 0)) ) {// tester// si	// motPasse	// est	// vide
				
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Non et Prenom vide",
						"Invalid credentials"));
				context.update("f1:growl");
			}
	     
	     if(!b){
				
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Format email invalide",
						"Invalid credentials"));
				context.update("f1:growl");
			}
	     
	    
		 listUtlByCin=  ser.rechercheParCin(cinUtl);
		
		if(cinUtl != null && cinUtl.trim().length() > 0) {

		       if (listUtlByCin.size()>0 && listUtlByCin.get(0).getIdutilisateur() != idutilisateur ) {
			
		    	   faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Cin exixte deja.",
							"Invalid credentials"));
		    	   context.update("f1:growl");
			      }
		       
		       try{
		           long cinLong=Long.parseLong(cinUtl);
		           
		           if(cinUtl.length() < 8 || cinUtl.length() > 8  ){
			        	 
			        	 faces.addMessage(null, new FacesMessage(
									FacesMessage.SEVERITY_ERROR, "Cin doit contenir 8 chiffres.",
									"Invalid credentials"));	
			        	 context.update("f1:growl"); }
		           
		       }catch(NumberFormatException nfe){
		    	   System.out.println("get in catch");
		    	   faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Cin invalide.",
							"Invalid credentials"));
		    	   context.update("f1:growl");
		       }

	          
				
		}
		
		
		if (faces.getMessageList().size() == 0) {
			return stepToGo;
		}
		return currentStepId;
	}
		return stepToGo;
	}
	
	
	
	
	public void ajouterGradeAprèsValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		GradeService ser = new GradeService();
		 grade = new Grade();
		boolean addValid = false;

		
		

			if (nomGrade == null || (nomGrade.trim().length() == 0)) {// tester
																					// si
																					// grade
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Grade vide",
						"Invalid credentials"));
			}

			else // tester si ce grade existe déjà
			{
				gradeByNom = ser.rechercheParLibl(nomGrade);
				System.out.println("size liste recherche grade=="
						+ gradeByNom.size());
				if (gradeByNom.size() == 0) { // grade n'existe pas
					addValid = true;
					grade.setLibelleGrd(nomGrade);
					ser.ajouterGrade(grade);
					//RequestContext.getCurrentInstance().update("f1");
					context.execute("PF('dialogGrad').hide();");
					initialisationGrde();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Grade existe déja",
							"Invalid credentials"));
				}
			}

		

		

		context.addCallbackParam("addValid", addValid);

	}
	
	
	public void initialisationGrde() {
		idGrade = null;
		nomGrade = null;

	}
	
	public void ajouterSevceApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		ServiceService ser = new ServiceService();
		 s = new Service();
		Fil_attService serf = new Fil_attService();
		Fil_att f = new Fil_att();
		
		boolean addValid = false;

			if (designationSer == null || (designationSer.trim().length() == 0)) {// tester
																					// si
																					// sevice
																					// est
																					// vide
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Sevice vide",
						"Invalid credentials"));
			}

			else // tester si ce sevice existe déjà
			{
				serviceByNom = ser.rechercheParNom(designationSer);
				System.out.println("size liste recherche sevice=="
						+ serviceByNom.size());
				if (serviceByNom.size() == 0) { 
					System.out.println("fil et sezr ajouter");
					// sevice n'existe pas
					addValid = true;
					s.setDesignationSer(designationSer);
					ser.ajouterService(s);
					f.setIdfil_att(idfil_att);
					f.setService(s);
					
					 serf.ajouterFil_att(f);
					 context.execute("PF('dialogSrvce').hide();");
					initialisationSer();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Sevice existe déja",
							"Invalid credentials"));
				}
			}

		context.addCallbackParam("addValid", addValid);

	}
	
	public void initialisationSer(){
		
		designationSer=null;
		idservice=null;
	}
	
	
	public void viewListeUlilisateur (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/utilisateurReport.jasper"));
		byte[] bytes= JasperRunManager.runReportToPdf(jasper.getPath(), null,connection);
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream outStream= response.getOutputStream();
		outStream.write(bytes, 0, bytes.length);
		outStream.flush();
		outStream.close();
		FacesContext.getCurrentInstance().responseComplete();


	}

	
	
	public void histoUtl(Utilisateur utl){
		
	  
	  Reg_tacheService reg_tachSer= new Reg_tacheService();
	  idutilisateur=utl.getIdutilisateur();
	  nomUtl=utl.getNomUtl();
	  prenomUtl=utl.getPrenomUtl();
	  nomGrade=utl.getGrade().getLibelleGrd();
	  mailUtl=utl.getMailUtl();
	  
	  
	  histoUtls= reg_tachSer.rechercheRegTachByUtl(idutilisateur);
	  
	}
	
	
	public void update(TabChangeEvent event){
		System.out.println("getAplPriv==="+getAplPriv);
		System.out.println("get in update");
		RequestContext.getCurrentInstance().update(":f1");

	}
	
	public void chek(){
		System.out.println("getAplPriv==="+getAplPriv);
		
	}
	

	
	
}

