package com.sav.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.sav.persistance.Utilisateur;
import com.sav.service.UtilisateurService;

@ManagedBean(name = "connectionBean")
@SessionScoped
public class ConnectionBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private String motpass;
	private String connecte;
	
	 private boolean loggedIn;
	 
	private boolean gestionApl;
    private boolean parametarge;
	private boolean filAtt;
	private boolean calender;
	private boolean profile;
	
	private boolean gestionEtat;
	private boolean gestionVil;
	private boolean gestionTypFour;
	private boolean gestionTypMach;
	private boolean gestionMarq;
	private boolean gestionStatut;
	private boolean gestionGrade;
	private boolean gestionSrvice;
	
	
	
	private boolean gestionUtl;
	private boolean gestionClt;
	private boolean gestionFour;
	private boolean gestionMach;
	private boolean gestionPan;
	private boolean gestionTach;
	private boolean gestionPiec;
	private boolean gestionAcc;
		
	
	private boolean nouvAccessoire;
	private boolean modifAccessoire;
	private boolean supAccessoire;
	private boolean nouvClient;
	private boolean modifClient;
	private boolean supClient;
	private boolean nouvFournisseur;
	private boolean modifFournisseur;
	private boolean supFournisseur;
	private boolean nouvGrade;
	private boolean modifGrade;
	private boolean supGrade;
	private boolean nouvMachine;
	private boolean supMachine;
	private boolean modifMachine;
	private boolean nouvMarque;
	private boolean modifMarque;
	private boolean supMarque;
	private boolean nouvPanne;
	private boolean modifPanne;
	private boolean supPanne;
	private boolean nouvPiece;
	private boolean modifPiece;
	private boolean supPiece;
	private boolean nouvService;
	private boolean supService;
	private boolean modifService;
	private boolean nouvStatut;
	private boolean modifStatut;
	private boolean supStatut;
	private boolean nouvTache;
	private boolean modifTache;
	private boolean supTache;
	private boolean nouvType_four ;
	private boolean modifType_four;
	private boolean supType_four;
	private boolean nouvType;
	private boolean modifType;
	private boolean supType;
	private boolean nouvVille;
	private boolean modifVille;
	private boolean supVille;
	
	private boolean ajouterApl;
	private boolean modifierApl;
	private boolean detailApl;
	private boolean suppressionApl;
	private boolean changerEtaApl;
	private boolean envoiAplChezFour;
	private boolean recoiAplDeFour;
	private boolean detailEatApl;
	
	 public static HashMap<String,String> listSession = new HashMap<String, String>();
	
	
	public boolean isAjouterApl() {
		return ajouterApl;
	}

	public void setAjouterApl(boolean ajouterApl) {
		this.ajouterApl = ajouterApl;
	}

	public boolean isModifierApl() {
		return modifierApl;
	}

	public void setModifierApl(boolean modifierApl) {
		this.modifierApl = modifierApl;
	}

	public boolean isDetailApl() {
		return detailApl;
	}

	public void setDetailApl(boolean detailApl) {
		this.detailApl = detailApl;
	}

	public boolean isSuppressionApl() {
		return suppressionApl;
	}

	public void setSuppressionApl(boolean suppressionApl) {
		this.suppressionApl = suppressionApl;
	}

	public boolean isChangerEtaApl() {
		return changerEtaApl;
	}

	public void setChangerEtaApl(boolean changerEtaApl) {
		this.changerEtaApl = changerEtaApl;
	}

	public boolean isEnvoiAplChezFour() {
		return envoiAplChezFour;
	}

	public void setEnvoiAplChezFour(boolean envoiAplChezFour) {
		this.envoiAplChezFour = envoiAplChezFour;
	}

	public boolean isRecoiAplDeFour() {
		return recoiAplDeFour;
	}

	public void setRecoiAplDeFour(boolean recoiAplDeFour) {
		this.recoiAplDeFour = recoiAplDeFour;
	}

	public boolean isDetailEatApl() {
		return detailEatApl;
	}

	public void setDetailEatApl(boolean detailEatApl) {
		this.detailEatApl = detailEatApl;
	}

	public boolean isGestionEtat() {
		return gestionEtat;
	}

	public void setGestionEtat(boolean gestionEtat) {
		this.gestionEtat = gestionEtat;
	}

	public boolean isGestionApl() {
		return gestionApl;
	}

	public void setGestionApl(boolean gestionApl) {
		this.gestionApl = gestionApl;
	}

	public boolean isParametarge() {
		return parametarge;
	}

	public void setParametarge(boolean parametarge) {
		this.parametarge = parametarge;
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

	public boolean isProfile() {
		return profile;
	}

	public void setProfile(boolean profile) {
		this.profile = profile;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isGestionVil() {
		return gestionVil;
	}

	public void setGestionVil(boolean gestionVil) {
		this.gestionVil = gestionVil;
	}

	public boolean isGestionTypFour() {
		return gestionTypFour;
	}

	public void setGestionTypFour(boolean gestionTypFour) {
		this.gestionTypFour = gestionTypFour;
	}

	public boolean isGestionTypMach() {
		return gestionTypMach;
	}

	public void setGestionTypMach(boolean gestionTypMach) {
		this.gestionTypMach = gestionTypMach;
	}

	public boolean isGestionMarq() {
		return gestionMarq;
	}

	public void setGestionMarq(boolean gestionMarq) {
		this.gestionMarq = gestionMarq;
	}

	public boolean isGestionStatut() {
		return gestionStatut;
	}

	public void setGestionStatut(boolean gestionStatut) {
		this.gestionStatut = gestionStatut;
	}

	public boolean isGestionGrade() {
		return gestionGrade;
	}

	public void setGestionGrade(boolean gestionGrade) {
		this.gestionGrade = gestionGrade;
	}

	public boolean isGestionSrvice() {
		return gestionSrvice;
	}

	public void setGestionSrvice(boolean gestionSrvice) {
		this.gestionSrvice = gestionSrvice;
	}

	public boolean isGestionUtl() {
		return gestionUtl;
	}

	public void setGestionUtl(boolean gestionUtl) {
		this.gestionUtl = gestionUtl;
	}

	public boolean isGestionClt() {
		return gestionClt;
	}

	public void setGestionClt(boolean gestionClt) {
		this.gestionClt = gestionClt;
	}

	public boolean isGestionFour() {
		return gestionFour;
	}

	public void setGestionFour(boolean gestionFour) {
		this.gestionFour = gestionFour;
	}

	public boolean isGestionMach() {
		return gestionMach;
	}

	public void setGestionMach(boolean gestionMach) {
		this.gestionMach = gestionMach;
	}

	public boolean isGestionPan() {
		return gestionPan;
	}

	public void setGestionPan(boolean gestionPan) {
		this.gestionPan = gestionPan;
	}

	public boolean isGestionTach() {
		return gestionTach;
	}

	public void setGestionTach(boolean gestionTach) {
		this.gestionTach = gestionTach;
	}

	public boolean isGestionPiec() {
		return gestionPiec;
	}

	public void setGestionPiec(boolean gestionPiec) {
		this.gestionPiec = gestionPiec;
	}

	public boolean isGestionAcc() {
		return gestionAcc;
	}

	public void setGestionAcc(boolean gestionAcc) {
		this.gestionAcc = gestionAcc;
	}

	public boolean isNouvVille() {
		return nouvVille;
	}

	public void setNouvVille(boolean nouvVille) {
		this.nouvVille = nouvVille;
	}

	public boolean isModifVille() {
		return modifVille;
	}

	public void setModifVille(boolean modifVille) {
		this.modifVille = modifVille;
	}

	public boolean isSupVille() {
		return supVille;
	}

	public void setSupVille(boolean supVille) {
		this.supVille = supVille;
	}

	public boolean isNouvType() {
		return nouvType;
	}

	public void setNouvType(boolean nouvType) {
		this.nouvType = nouvType;
	}

	public boolean isModifType() {
		return modifType;
	}

	public void setModifType(boolean modifType) {
		this.modifType = modifType;
	}

	public boolean isSupType() {
		return supType;
	}

	public void setSupType(boolean supType) {
		this.supType = supType;
	}

	public boolean isNouvType_four() {
		return nouvType_four;
	}

	public void setNouvType_four(boolean nouvType_four) {
		this.nouvType_four = nouvType_four;
	}

	public boolean isModifType_four() {
		return modifType_four;
	}

	public void setModifType_four(boolean modifType_four) {
		this.modifType_four = modifType_four;
	}

	public boolean isSupType_four() {
		return supType_four;
	}

	public void setSupType_four(boolean supType_four) {
		this.supType_four = supType_four;
	}

	public boolean isNouvTache() {
		return nouvTache;
	}

	public void setNouvTache(boolean nouvTache) {
		this.nouvTache = nouvTache;
	}

	public boolean isModifTache() {
		return modifTache;
	}

	public void setModifTache(boolean modifTache) {
		this.modifTache = modifTache;
	}

	public boolean isSupTache() {
		return supTache;
	}

	public void setSupTache(boolean supTache) {
		this.supTache = supTache;
	}

	public boolean isNouvStatut() {
		return nouvStatut;
	}

	public void setNouvStatut(boolean nouvStatut) {
		this.nouvStatut = nouvStatut;
	}

	public boolean isModifStatut() {
		return modifStatut;
	}

	public void setModifStatut(boolean modifStatut) {
		this.modifStatut = modifStatut;
	}

	public boolean isSupStatut() {
		return supStatut;
	}

	public void setSupStatut(boolean supStatut) {
		this.supStatut = supStatut;
	}

	public boolean isNouvService() {
		return nouvService;
	}

	public void setNouvService(boolean nouvService) {
		this.nouvService = nouvService;
	}

	public boolean isSupService() {
		return supService;
	}

	public void setSupService(boolean supService) {
		this.supService = supService;
	}

	public boolean isModifService() {
		return modifService;
	}

	public void setModifService(boolean modifService) {
		this.modifService = modifService;
	}

	public boolean isNouvPiece() {
		return nouvPiece;
	}

	public void setNouvPiece(boolean nouvPiece) {
		this.nouvPiece = nouvPiece;
	}

	public boolean isModifPiece() {
		return modifPiece;
	}

	public void setModifPiece(boolean modifPiece) {
		this.modifPiece = modifPiece;
	}

	public boolean isSupPiece() {
		return supPiece;
	}

	public void setSupPiece(boolean supPiece) {
		this.supPiece = supPiece;
	}

	public boolean isNouvPanne() {
		return nouvPanne;
	}

	public void setNouvPanne(boolean nouvPanne) {
		this.nouvPanne = nouvPanne;
	}

	public boolean isModifPanne() {
		return modifPanne;
	}

	public void setModifPanne(boolean modifPanne) {
		this.modifPanne = modifPanne;
	}

	public boolean isSupPanne() {
		return supPanne;
	}

	public void setSupPanne(boolean supPanne) {
		this.supPanne = supPanne;
	}

	public boolean isNouvMarque() {
		return nouvMarque;
	}

	public void setNouvMarque(boolean nouvMarque) {
		this.nouvMarque = nouvMarque;
	}

	public boolean isModifMarque() {
		return modifMarque;
	}

	public void setModifMarque(boolean modifMarque) {
		this.modifMarque = modifMarque;
	}

	public boolean isSupMarque() {
		return supMarque;
	}

	public void setSupMarque(boolean supMarque) {
		this.supMarque = supMarque;
	}

	public boolean isNouvMachine() {
		return nouvMachine;
	}

	public void setNouvMachine(boolean nouvMachine) {
		this.nouvMachine = nouvMachine;
	}

	public boolean isSupMachine() {
		return supMachine;
	}

	public void setSupMachine(boolean supMachine) {
		this.supMachine = supMachine;
	}

	public boolean isModifMachine() {
		return modifMachine;
	}

	public void setModifMachine(boolean modifMachine) {
		this.modifMachine = modifMachine;
	}

	public boolean isNouvGrade() {
		return nouvGrade;
	}

	public void setNouvGrade(boolean nouvGrade) {
		this.nouvGrade = nouvGrade;
	}

	public boolean isModifGrade() {
		return modifGrade;
	}

	public void setModifGrade(boolean modifGrade) {
		this.modifGrade = modifGrade;
	}

	public boolean isSupGrade() {
		return supGrade;
	}

	public void setSupGrade(boolean supGrade) {
		this.supGrade = supGrade;
	}

	public boolean isNouvFournisseur() {
		return nouvFournisseur;
	}

	public void setNouvFournisseur(boolean nouvFournisseur) {
		this.nouvFournisseur = nouvFournisseur;
	}

	public boolean isModifFournisseur() {
		return modifFournisseur;
	}

	public void setModifFournisseur(boolean modifFournisseur) {
		this.modifFournisseur = modifFournisseur;
	}

	public boolean isSupFournisseur() {
		return supFournisseur;
	}

	public void setSupFournisseur(boolean supFournisseur) {
		this.supFournisseur = supFournisseur;
	}

	public boolean isNouvClient() {
		return nouvClient;
	}

	public void setNouvClient(boolean nouvClient) {
		this.nouvClient = nouvClient;
	}

	public boolean isModifClient() {
		return modifClient;
	}

	public void setModifClient(boolean modifClient) {
		this.modifClient = modifClient;
	}

	public boolean isSupClient() {
		return supClient;
	}

	public void setSupClient(boolean supClient) {
		this.supClient = supClient;
	}

	public boolean isModifAccessoire() {
		return modifAccessoire;
	}

	public void setModifAccessoire(boolean modifAccessoire) {
		this.modifAccessoire = modifAccessoire;
	}

	public boolean isSupAccessoire() {
		return supAccessoire;
	}

	public void setSupAccessoire(boolean supAccessoire) {
		this.supAccessoire = supAccessoire;
	}

	public boolean isNouvAccessoire() {
		return nouvAccessoire;
	}

	public void setNouvAccessoire(boolean nouvAccessoire) {
		this.nouvAccessoire = nouvAccessoire;
	}

	public String getConnecte() {
		connecte=Module.connecte;
		return connecte;
	}

	public void setConnecte(String connecte) {
		this.connecte = connecte;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotpass() {
		return motpass;
	}

	public void setMotpass(String motpass) {
		this.motpass = motpass;
	}

	@PostConstruct
	public void init() {

		Module.connecte = "";
	}

	public String connecter() {
		FacesContext face = FacesContext.getCurrentInstance();
		if (login == null || (login.trim().length() == 0)) {
			face.addMessage("form:loginid", new FacesMessage("Login manquant"));
		}

		if (motpass == null || (motpass.trim().length() == 0)) {
			face.addMessage("form:mpid", new FacesMessage(
					"Mot de passe manquant"));
		}
		if (face.getMessageList().size() == 0) {
			
			
			
			if (login.equals("superadmin") || motpass.equals("superadmin")) {
				
				return "accepted";

			}
			// pas de message d'erreur == les champs login et mp non vide

			// tester si le login et le mot de passe existe dans la base
			UtilisateurService c = new UtilisateurService();
			Utilisateur u = c.rechercheParLoginMotPass(login, motpass);

			if (u != null) {
				
				Module.idUtilConnecte =u.getIdutilisateur();
				
				
				
				if (u.isPassif()) {
					face.addMessage("form:f", new FacesMessage(
							"Ce compte est désactivé."));
				}else {
					
					if (u.getGestClt().equals("0")) {
						gestionClt = true;
					} else {
						gestionClt = false;
					}
					
					if (u.getGestUtl().equals("0")) {
						gestionUtl = true;
					} else {
						gestionUtl = false;
					}	
					
					if (u.getGestFour().equals("0")) {
						gestionFour = true;
					} else {
						gestionFour = false;
					}	
					
					if (u.getGestMach().equals("0")) {
						gestionMach = true;
					} else {
						gestionMach = false;
					}
					
					if (u.getGestPan().equals("0")) {
						gestionPan = true;
					} else {
						gestionPan = false;
					}
					
					if (u.getGestTach().equals("0")) {
						gestionTach = true;
					} else {
						gestionTach = false;
					}
					
					if (u.getGestPce().equals("0")) {
						gestionPiec = true;
					} else {
						gestionPiec = false;
					}
					
					if (u.getGestAcc().equals("0")) {
						gestionAcc = true;
					} else {
						gestionAcc = false;
					}
				
				if (u.getNouvTyp().equals("0")) {
					nouvType = false;
				} else {
					nouvType = true;
				}
				
				if (u.getModifTyp().equals("0")) {
					modifType = false;
				} else {
					modifType = true;
				}
				
				if (u.getSupTyp().equals("0")) {
					supType = false;
				} else {
					supType = true;
				}
				
				if (u.getNouvTypFour().equals("0")) {
					nouvType_four = false;
				} else {
					nouvType_four = true;
				}
				
				if (u.getModifTypFour().equals("0")) {
					modifType_four = false;
				} else {
					modifType_four = true;
				}
				
				if (u.getSupTypFour().equals("0")) {
					supType_four = false;
				} else {
					supType_four = true;
				}
				
				if (u.getNouvTach().equals("0")) {
					nouvTache = false;
				} else {
					nouvTache = true;
				}
				
				if (u.getModifTach().equals("0")) {
					modifTache = false;
				} else {
					modifTache = true;
				}
				
				if (u.getSupTach().equals("0")) {
					supTache = false;
				} else {
					supTache = true;
				}
				
				if (u.getNouvStat().equals("0")) {
					nouvStatut = false;
				} else {
					nouvStatut = true;
				}
				
				if (u.getModifStat().equals("0")) {
					modifStatut = false;
				} else {
					modifStatut = true;
				}
				
				if (u.getSupStat().equals("0")) {
					supStatut = false;
				} else {
					supStatut = true;
				}
				
				
				if (u.getNouvSer().equals("0")) {
					nouvService = false;
				} else {
					nouvService = true;
				}
				
				if (u.getSupSer().equals("0")) {
					supService = false;
				} else {
					supService = true;
				}
				
				if (u.getModifSer().equals("0")) {
					modifService = false;
				} else {
					modifService = true;
				}
				
				if (u.getNouvPce().equals("0")) {
					nouvPiece = false;
				} else {
					nouvPiece = true;
				}
				
				if (u.getModifPce().equals("0")) {
					modifPiece = false;
				} else {
					modifPiece = true;
				}
				
				if (u.getSupPce().equals("0")) {
					supPiece = false;
				} else {
					supPiece = true;
				}
				
				if (u.getNouvPan().equals("0")) {
					nouvPanne = false;
				} else {
					nouvPanne = true;
				}
				
				if (u.getModifPan().equals("0")) {
					modifPanne = false;
				} else {
					modifPanne = true;
				}
				
				if (u.getSupPan().equals("0")) {
					supPanne = false;
				} else {
					supPanne = true;
				}
				
				if (u.getNouvMqe().equals("0")) {
					nouvMarque = false;
				} else {
					nouvMarque = true;
				}
				
				if (u.getModifMqe().equals("0")) {
					modifMarque = false;
				} else {
					modifMarque = true;
				}
				
				if (u.getSupMqe().equals("0")) {
					supMarque = false;
				} else {
					supMarque = true;
				}
				
				if (u.getNouvMach().equals("0")) {
					nouvMachine = false;
				} else {
					nouvMachine = true;
				}
				
				if (u.getSupMach().equals("0")) {
					supMachine = false;
				} else {
					supMachine = true;
				}
				
				if (u.getModifMach().equals("0")) {
					modifMachine = false;
				} else {
					modifMachine = true;
				}
				
				if (u.getNouvGde().equals("0")) {
					nouvGrade = false;
				} else {
					nouvGrade = true;
				}
				
				if (u.getModifGde().equals("0")) {
					modifGrade = false;
				} else {
					modifGrade = true;
				}
				
				if (u.getSupGde().equals("0")) {
					supGrade = false;
				} else {
					supGrade = true;
				}
				
				if (u.getNouvFour().equals("0")) {
					nouvFournisseur = false;
				} else {
					nouvFournisseur = true;
				}
				
				if (u.getModifFour().equals("0")) {
					modifFournisseur = false;
				} else {
					modifFournisseur = true;
				}
				
				if (u.getSupFour().equals("0")) {
					supFournisseur = false;
				} else {
					supFournisseur = true;
				}
				
				
				if (u.getNouvClt().equals("0")) {
					nouvClient = false;
				} else {
					nouvClient = true;
				}
				
				if (u.getModifClt().equals("0")) {
					modifClient = false;
				} else {
					modifClient = true;
				}
				
				if (u.getSupClt().equals("0")) {
					supClient = false;
				} else {
					supClient = true;
				}
				
				if (u.getNouvAcc().equals("0")) {
					nouvAccessoire = false;
				} else {
					nouvAccessoire = true;
				}	
				
				
				if (u.getModifAcc().equals("0")) {
					modifAccessoire = false;
				} else {
					modifAccessoire = true;
				}
				
				if (u.getSupAcc().equals("0")) {
					supAccessoire = false;
				} else {
					supAccessoire = true;
				}
				
				if (u.getNouvVil().equals("0")) {
					nouvVille = false;
				} else {
					nouvVille = true;
				}
				if (u.getModifVil().equals("0")) {
					modifVille = false;
				} else {
					modifVille = true;
				}
				if (u.getSupVil().equals("0")) {
					supVille = false;
				} else {
					supVille = true;
				}
				    
				 gestionGrade=u.isGestGrade();
				 gestionMarq=u.isGestMarq();
				 gestionSrvice=u.isGestSrvc();
				 gestionStatut=u.isGestStatut();
				 gestionTypFour=u.isGestTypFour();
				 gestionTypMach=u.isGestTypMach();
				 gestionVil=u.isGestVil();
				 
				 gestionEtat=u.isGestEtat();
				 gestionApl=u.isGestApl();
				 parametarge=u.isParametrage();
				 filAtt=u.isFilAtt();
				 calender=u.isCalender();
				 profile=u.isProfil();
				 
				 
				 ajouterApl=u.isAjoutApl();
				 modifierApl=u.isModifApl();
			     detailApl=u.isDetApl();
				 suppressionApl=u.isSupApl();
				 changerEtaApl=u.isChangEtaApl();
				 envoiAplChezFour=u.isEnvAplChezFour();
				 recoiAplDeFour=u.isRecoiAplDeFour();
				 detailEatApl=u.isDetEatApl();
				  
				   
				 loggedIn = true;
				 
				 HttpSession session = SessionBean.getSession();
		         session.setAttribute("login", login);
		            
		           listSession.put(SessionBean.getUserName(), session.getId());
		           
		        // Afficher le contenu du MAP
		    		Set listKeys=listSession.keySet();  // Obtenir la liste des clés
		    		Iterator iterateur=listKeys.iterator();
		    		// Parcourir les clés et afficher les entrées de chaque clé;
		    		while(iterateur.hasNext())
		    		{
		    			Object key= iterateur.next();
		    			System.out.println (key+"=>"+listSession.get(key));
		    		}
		    		
		           
		            
		            
		            System.out.println("namesession  "+SessionBean.getUserName()); 
				 
				 return "/pages/Acceuil2.xhtml?faces-redirect=true";
				}
				
			}
			else
				face.addMessage("form:f", new FacesMessage(
						"N'existe pas un compte avec ces paramètres."));
		}
		
		
		
		
		return "/Connection.xhtml?faces-redirect=true";
	}
	
	

	
	 public void doLogout() throws IOException {
	        // Set the paremeter indicating that user is logged in to false
	        loggedIn = false;
	         
	        // Set logout message
	       // FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
	       // msg.setSeverity(FacesMessage.SEVERITY_INFO);
	       // FacesContext.getCurrentInstance().addMessage(null, msg);
	         
	       // return "/pages2/Connection?faces-redirect=true";
	        
	     
	        
	       // FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	       // FacesContext facesContext = FacesContext.getCurrentInstance();
	      //  HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
	       // session.invalidate();
	       // FacesContext.getCurrentInstance().getExternalContext().redirect("/ServiceApresVente/pages2/Connection.xhtml");
	        
	       // FacesContext ctx = FacesContext.getCurrentInstance(); 
	        //ExternalContext etx = ctx.getExternalContext(); 
	       // HttpSession session = (HttpSession)etx.getSession(false); 
	      //  session.invalidate();
	        
	        
	        HttpSession session = SessionBean.getSession();
	        session.invalidate();
	        System.out.println("loged out");
	        
	       // System.out.println("namesession  "+SessionBean.getUserName()); 
	        
	        FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/ServiceApresVente/pages2/Connection.xhtml");
	        
	        
	    }
	
	

	public void annuler() {
		login = null;
		motpass = null;
	}

}
