package com.sav.bean;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

import com.mysql.jdbc.Connection;
import com.sav.persistance.Accessoire;
import com.sav.persistance.AplImp;
import com.sav.persistance.App_acc;
import com.sav.persistance.App_fil;
import com.sav.persistance.App_four;
import com.sav.persistance.App_panne;
import com.sav.persistance.App_stat;
import com.sav.persistance.Appel;
import com.sav.persistance.Client;
import com.sav.persistance.Detaille_Etat;
import com.sav.persistance.Diag_panne;
import com.sav.persistance.Diagnostique;
import com.sav.persistance.Etat;
import com.sav.persistance.Fil_att;
import com.sav.persistance.Fournisseur;
import com.sav.persistance.Info_apl;
import com.sav.persistance.Machine;
import com.sav.persistance.Marque;
import com.sav.persistance.Panne;
import com.sav.persistance.Piece;
import com.sav.persistance.Reg_piece;
import com.sav.persistance.Reg_tache;
import com.sav.persistance.Rep_Utl;
import com.sav.persistance.Reparation;
import com.sav.persistance.Service;
import com.sav.persistance.Statut;
import com.sav.persistance.Tache;
import com.sav.persistance.Type;
import com.sav.persistance.Type_four;
import com.sav.persistance.Utilisateur;
import com.sav.persistance.Ville;
import com.sav.service.AccessoireService;
import com.sav.service.AplImpService;
import com.sav.service.App_accService;
import com.sav.service.App_filService;
import com.sav.service.App_fourService;
import com.sav.service.App_panneService;
import com.sav.service.App_statService;
import com.sav.service.AppelService;
import com.sav.service.ClientService;
import com.sav.service.Detaille_EtatService;
import com.sav.service.Diag_panneService;
import com.sav.service.DiagnostiqueService;
import com.sav.service.EtatService;
import com.sav.service.Fil_attService;
import com.sav.service.FournisseurService;
import com.sav.service.Info_aplService;
import com.sav.service.MachHistoService;
import com.sav.service.MachineService;
import com.sav.service.MarqueService;
import com.sav.service.PanneService;
import com.sav.service.PieceService;
import com.sav.service.Reg_pieceService;
import com.sav.service.Reg_tacheService;
import com.sav.service.Rep_UtlService;
import com.sav.service.ReparationService;
import com.sav.service.ServiceService;
import com.sav.service.StatutService;
import com.sav.service.TacheService;
import com.sav.service.TypeService;
import com.sav.service.Type_fourService;
import com.sav.service.UtilisateurService;
import com.sav.service.VilleService;

@ManagedBean(name = "appelBean")
@SessionScoped
public class AppelBean {

	private boolean diag;
	private Integer idappel;
	private String emplacement;
	private Date date_entre;
	private String note;
	private String valeurRecherche;
	private String attribut;
	private String nomclt;
	private List<Statut> stats = new ArrayList<Statut>();
	private List<String> listesRecherches = new ArrayList<String>();
	private List<Appel> filteredAppels = new ArrayList<Appel>(0);
	private List<Appel> appels = new ArrayList<Appel>(0);
	private String currentStepId = "";
	private String action;
	private String designationSer;
	private String num_serie;
	private String designationEtat;
	private Service service;
	private Client client;
	private Machine machine;
	private Statut statut;
	private Machine mch = null;
	private Date date = new Date();
	private List<Accessoire> accs = new ArrayList<Accessoire>();
	private List<String> selectedAccessoires = new ArrayList<String>();
	private Integer idclt;
	private Client clt = null;
	private List<Type> types = new ArrayList<Type>();
	private Integer idtype;
	private List<Marque> marques = new ArrayList<Marque>();
	private Integer idmarque;
	private Etat etat;
	private List<Etat> etats = new ArrayList<Etat>();
	private Integer idetat;
	private List<String> selectedStats = new ArrayList<String>();
	private List<Service> srs = new ArrayList<Service>();
	private String[] selectedsers;
	private Panne pan = new Panne();
	private List<Panne> Selectedpans = new ArrayList<Panne>();
	private List<Panne> selectedDiagpans = new ArrayList<Panne>();
	private String designationAcc;
	private Integer idaccessoire;
	Accessoire a = null;
	private List<Accessoire> accessoireByNom = new ArrayList<Accessoire>(0);
	private Set<Accessoire> accessoires = new HashSet<Accessoire>();
	private List<Panne> pannes = new ArrayList<Panne>();
	private Set<Statut> statuts = new HashSet<Statut>();
	private Set<App_panne> app_pan = new HashSet<App_panne>();
	private List<String> p = new ArrayList<String>();
	private List<String> ac = new ArrayList<String>();
	private List<String> st = new ArrayList<String>();
	private String designationPan;
	private Integer idpanne;
	private List<Panne> panneByNom = new ArrayList<Panne>(0);
	private List<Service> serviceByNom = new ArrayList<Service>(0);
	private List<Ville> villeByNom = new ArrayList<Ville>(0);
	private List<Marque> mrqByNom = new ArrayList<Marque>(0);
	private List<Type> typmachByNom = new ArrayList<Type>(0);
	private List<Machine> machByNom = new ArrayList<Machine>(0);
	private List<Tache> tacheByNom = new ArrayList<Tache>(0);
	private List<Piece> pieceByNom = new ArrayList<Piece>(0);
	private List<Type_four> typfourByNom = new ArrayList<Type_four>(0);
	
	private List<Reparation> repParIdapl= new ArrayList<Reparation>(0);
    private Integer idReparation;
	
	public List<Panne> getSelectedDiagpans() {
		return selectedDiagpans;
	}

	public void setSelectedDiagpans(List<Panne> selectedDiagpans) {
		this.selectedDiagpans = selectedDiagpans;
	}

	public boolean isDiag() {
		return diag;
	}

	public void setDiag(boolean diag) {
		this.diag = diag;
	}

	private List<Detaille_Etat> det_etat = new ArrayList<Detaille_Etat>(0);// récupère
																			// les
																			// details
																			// pour
																			// l'afficher
																			// dans
																			// le
																			// profile

	private List<Detaille_Etat> detEtatApls = new ArrayList<Detaille_Etat>(0);// réupérer
																				// les
																				// detail
																				// de
																				// chaque
																				// apel
																				// pir
																				// l'afficher
																				// dans
																				// gestion
																				// apel

	private List<Reg_tache> regTachs = new ArrayList<Reg_tache>();

	private List<AplImp> aplImps = new ArrayList<AplImp>();

	public List<Reg_tache> getRegTachs() {

		if (T != null) {
			Reg_tache rt = new Reg_tache();
			rt.setTache(T);
			rt.setDateDebutTach(date);
			regTachs.add(rt);
			T = null;
		}
		total_tache = 0.0;
		for (int i = 0; i < regTachs.size(); i++) {
			total_tache = total_tache
					+ Double.parseDouble(regTachs.get(i).getTache()
							.getPrixTach());
		}

		return regTachs;
	}

	public void setRegTachs(List<Reg_tache> regTachs) {
		this.regTachs = regTachs;
	}

	private String observation_rep;

	private Client selectedclt;
	private Machine selectedMach;
	private Panne selectedPan;

	public List<Detaille_Etat> getDetEtatApls() {
		detEtatApls.clear();
		System.out.println("idappel==" + idappel);
		Detaille_EtatService derDetapl = new Detaille_EtatService();
		detEtatApls = derDetapl.rechercheTousDetaille_EtatByIdapl(idappel);

		return detEtatApls;
	}

	public void setDetEtatApls(List<Detaille_Etat> detEtatApl) {
		this.detEtatApls = detEtatApl;
	}

	public void detailEtat(Integer id) {
		System.out.println("***id*****" + id);
		idappel = id;

	}

	public List<Detaille_Etat> getDet_etat() {
		det_etat.clear();
		AppelService serap = new AppelService();
		DiagnostiqueService diagSer = new DiagnostiqueService();
		Detaille_EtatService derDetapl = new Detaille_EtatService();
		Rep_UtlService serep = new Rep_UtlService();
		Integer idSer = user.getService().getIdservice();
		List<Rep_Utl> rep_utl = serep.rechercheParUtilisateur(user
				.getIdutilisateur());// liste des réparations d'1 utl
		List<Integer> lapl = new ArrayList<Integer>();// liste contient les ids
														// appel de chaque
														// réparation

		List<Diagnostique> diagparutl = diagSer
				.rechercheDiagParUtilisateur(user.getIdutilisateur());// liste
																		// des
																		// diagnostiue
																		// d'1
																		// utl

		for (int i = 0; i < rep_utl.size(); i++) {
			lapl.add(rep_utl.get(i).getReparation().getAppel().getIdappel());
		}

		for (int i = 0; i < lapl.size(); i++) {
			System.out.println("app  " + lapl.get(i) + "ser   " + idSer);
			Detaille_Etat x = derDetapl.rechercheParIdWithJoin(lapl.get(i),
					idSer, "Reparation");
			if (x != null) {

				det_etat.add(x);
			}

		}

		for (int i = 0; i < diagparutl.size(); i++) {

			Detaille_Etat deteta = derDetapl.rechercheParIdWithJoin(diagparutl
					.get(i).getAppel().getIdappel(), idSer, "Diagnostique");
			if (deteta != null) {

				det_etat.add(deteta);
			}
		}

		for (int i = 0; i < det_etat.size(); i++) {
			System.out.println(det_etat.get(i));
		}

		return det_etat;
	}

	public void setDet_etat(List<Detaille_Etat> det_etat) {
		this.det_etat = det_etat;
	}

	public Panne getSelectedPan() {
		return selectedPan;
	}

	public void setSelectedPan(Panne selectedPan) {
		this.selectedPan = selectedPan;
	}

	public Machine getSelectedMach() {
		return selectedMach;
	}

	public void setSelectedMach(Machine selectedMach) {
		this.selectedMach = selectedMach;
	}

	public Client getSelectedclt() {
		return selectedclt;
	}

	public void setSelectedclt(Client selectedclt) {
		this.selectedclt = selectedclt;
	}

	public String getObservation_rep() {
		return observation_rep;
	}

	public void setObservation_rep(String observation_rep) {
		this.observation_rep = observation_rep;
	}

	private String note_diag;
	private String recumendation;

	private Panne pa = null;
	private Service s = null;
	private Client c = null;
	private Ville v = null;
	private Machine m = null;
	private Marque mq = null;
	private Type ty = null;
	private Integer idservice;
	private Integer idtache;
	private String designationTach;
	private String prixTach;
	private List<Service> services = new ArrayList<Service>(0);
	private Integer idville;
	private Integer idclient;
	private String adrclt;
	private String mailclt;
	private String gsm1clt;
	private String gsm2clt;
	private String telclt;
	private String faxclt;
	private String contact;
	private String type;
	private List<Ville> villes = new ArrayList<Ville>(0);
	private String designationVille;
	private String valeurRecherchep;
	private Integer idmachine;
	private String model;
	private String designationMarq;
	private String designationType;
	private Integer selectedetat = null;
	private List<Info_apl> infos = new ArrayList<Info_apl>(0);
	private List<Service> sers = new ArrayList<Service>(0);
	List<Panne> lp = new ArrayList<Panne>();
	private Date date_debut = null;
	private Date date_fin = null;
	private String info1;
	private String info2;
	private String info3;
	private String info4;
	private String info5;
	private String info6;
	private String info7;
	private String info8;
	private String info9;
	private String info10;
	private String info11;
	private String info12;

	private String libelé1;
	private String libelé2;
	private String libelé3;
	private String libelé4;
	private String libelé5;
	private String libelé6;
	private String libelé7;
	private String libelé8;
	private String libelé9;
	private String libelé10;
	private String libelé11;
	private String libelé12;

	private boolean activation1;
	private boolean activation2;
	private boolean activation3;
	private boolean activation4;
	private boolean activation5;
	private boolean activation6;
	private boolean activation7;
	private boolean activation8;
	private boolean activation9;
	private boolean activation10;
	private boolean activation11;
	private boolean activation12;
	private Integer changed_etat;
	private Utilisateur user;
	private Integer idinfo1;
	private Integer idinfo2;
	private Integer idinfo3;
	private Integer idinfo4;
	private Integer idinfo5;
	private Integer idinfo6;
	private Integer idinfo7;
	private Integer idinfo8;
	private Integer idinfo9;
	private Integer idinfo10;
	private Integer idinfo11;
	private Integer idinfo12;

	private Info_apl inf1 = new Info_apl();
	private Info_apl inf2 = new Info_apl();
	private Info_apl inf3 = new Info_apl();
	private Info_apl inf4 = new Info_apl();
	private Info_apl inf5 = new Info_apl();
	private Info_apl inf6 = new Info_apl();
	private Info_apl inf7 = new Info_apl();
	private Info_apl inf8 = new Info_apl();
	private Info_apl inf9 = new Info_apl();
	private Info_apl inf10 = new Info_apl();
	private Info_apl inf11 = new Info_apl();
	private Info_apl inf12 = new Info_apl();

	private boolean app_suiv = false;
	private Integer idpiece;
	private String designationPce;
	private String prixPce;
	private List<Tache> taches = new ArrayList<Tache>();
	private List<Reg_piece> pieces = new ArrayList<Reg_piece>();
	private Integer qte;
	private Integer idreg_piece;
	private Integer idreparation;
	private Integer modif_etat;
	private List<Appel> appels_encour = new ArrayList<Appel>();

	private App_filService serf = new App_filService();
	private EtatService se = new EtatService();
	private AppelService sa = new AppelService();
	private Fil_attService serfil = new Fil_attService();
	private ReparationService serep = new ReparationService();
	private Rep_UtlService srep_utl = new Rep_UtlService();
	private Rep_Utl rep_utl = new Rep_Utl();
	private Reparation rep;
	private Diagnostique diagnostique;
	private Reparation Selectedrep;
	private Appel Selectedapl;
	private List<Fil_att> f;
	private List<App_fil> lf;
	private int k = 0;
	private Appel ap;
	private boolean suppt = true;
	private boolean supp = true;
	private List<Reg_tache> reg_taches = new ArrayList<Reg_tache>();
	private List<Reg_piece> reg_pieces = new ArrayList<Reg_piece>();
	private List<Tache> tchs = new ArrayList<Tache>();
	private List<Piece> piec = new ArrayList<Piece>();
	private Piece pi = null;
	private Tache T = null;
	private List<Detaille_Etat> lde = new ArrayList<Detaille_Etat>();
	private Integer nouv_etat;
	private Double total_piece;
	private Double total_tache;
	private Double total_rep;

	private Integer idfournisseur;
	private String nomfour;
	private String adrfour;
	private String mailfour;
	private String gsm1four;
	private String gsm2four;
	private String telfour;
	private String faxfour;
	private Integer idtype_four;
	private String nomtype;
	private Type_four tf = null;
	private Fournisseur four = null;
	private Fournisseur selected_four;
	private List<Type_four> type_fours = new ArrayList<Type_four>(0);
	private Fournisseur selfour;
	private String observation_four;

	private Date date_sortie;
	private Integer etat_apl;
	private String montant;

	private App_four app_four;
	// Recuperer date system avec le temps.
	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+01:00"),
			Locale.FRANCE);
	private Date date_retour = calendar.getTime();

	public String getNote_diag() {
		return note_diag;
	}

	public void setNote_diag(String note_diag) {
		this.note_diag = note_diag;
	}

	public String getRecumendation() {
		return recumendation;
	}

	public void setRecumendation(String recumendation) {
		this.recumendation = recumendation;
	}

	public App_four getApp_four() {
		return app_four;
	}

	public void setApp_four(App_four app_four) {
		this.app_four = app_four;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	public Date getDate_retour() {

		return date_retour;
	}

	public void setDate_retour(Date date_retour) {
		this.date_retour = date_retour;
	}

	public Integer getEtat_apl() {
		return etat_apl;
	}

	public void setEtat_apl(Integer etat_apl) {
		this.etat_apl = etat_apl;
	}

	public Date getDate_sortie() {
		return date_sortie;
	}

	public void setDate_sortie(Date date_sortie) {
		this.date_sortie = date_sortie;
	}

	public String getObservation_four() {
		return observation_four;
	}

	public void setObservation_four(String observation_four) {
		this.observation_four = observation_four;
	}

	public Fournisseur getSelfour() {
		return selfour;
	}

	public void setSelfour(Fournisseur selfour) {
		this.selfour = selfour;
	}

	public List<Type_four> getType_fours() {
		Type_fourService ser = new Type_fourService();
		type_fours = ser.rechercheTousType_four();

		return type_fours;
	}

	public void setType_fours(List<Type_four> type_fours) {
		this.type_fours = type_fours;
	}

	public String getAdrfour() {
		return adrfour;
	}

	public void setAdrfour(String adrfour) {
		this.adrfour = adrfour;
	}

	public Fournisseur getSelected_four() {
		if (four != null) {
			selected_four = four;
			four = null;
		}
		return selected_four;
	}

	public void setSelected_four(Fournisseur selected_four) {
		this.selected_four = selected_four;
	}

	public String getNomtype() {
		return nomtype;
	}

	public void setNomtype(String nomtype) {
		this.nomtype = nomtype;
	}

	public Integer getIdfournisseur() {
		return idfournisseur;
	}

	public void setIdfournisseur(Integer idfournisseur) {
		this.idfournisseur = idfournisseur;
	}

	public String getNomfour() {
		return nomfour;
	}

	public void setNomfour(String nomfour) {
		this.nomfour = nomfour;
	}

	public String getMailfour() {
		return mailfour;
	}

	public void setMailfour(String mailfour) {
		this.mailfour = mailfour;
	}

	public String getGsm1four() {
		return gsm1four;
	}

	public void setGsm1four(String gsm1four) {
		this.gsm1four = gsm1four;
	}

	public String getGsm2four() {
		return gsm2four;
	}

	public void setGsm2four(String gsm2four) {
		this.gsm2four = gsm2four;
	}

	public String getTelfour() {
		return telfour;
	}

	public void setTelfour(String telfour) {
		this.telfour = telfour;
	}

	public String getFaxfour() {
		return faxfour;
	}

	public void setFaxfour(String faxfour) {
		this.faxfour = faxfour;
	}

	public Integer getIdtype_four() {
		if (tf != null) {
			idtype_four = tf.getIdtype_four();
			tf = null;
		}

		return idtype_four;
	}

	public void setIdtype_four(Integer idtype_four) {
		this.idtype_four = idtype_four;
	}

	public Double getTotal_piece() {
		if (total_piece == null)
			total_piece = 0.0;
		return total_piece;
	}

	public void setTotal_piece(Double total_piece) {
		this.total_piece = total_piece;
	}

	public Double getTotal_tache() {
		if (total_tache == null)
			total_tache = 0.0;
		return total_tache;
	}

	public void setTotal_tache(Double total_tache) {
		this.total_tache = total_tache;
	}

	public Double getTotal_rep() {
		total_rep = total_tache + total_piece;
		return total_rep;
	}

	public void setTotal_rep(Double total_rep) {
		this.total_rep = total_rep;
	}

	public Integer getNouv_etat() {
		return nouv_etat;
	}

	public void setNouv_etat(Integer nouv_etat) {
		this.nouv_etat = nouv_etat;
	}

	public List<Detaille_Etat> getLde() {
		return lde;
	}

	public void setLde(List<Detaille_Etat> lde) {
		this.lde = lde;
	}

	public List<Piece> getPiec() {
		return piec;
	}

	public void setPiec(List<Piece> piec) {
		this.piec = piec;
	}

	public List<Tache> getTchs() {
		return tchs;
	}

	public void setTchs(List<Tache> tchs) {
		this.tchs = tchs;
	}

	public boolean isSuppt() {
		return suppt;
	}

	public void setSuppt(boolean suppt) {
		this.suppt = suppt;
	}

	public List<Reg_tache> getReg_taches() {
		return reg_taches;
	}

	public void setReg_taches(List<Reg_tache> reg_taches) {
		this.reg_taches = reg_taches;
	}

	public List<Reg_piece> getReg_pieces() {
		return reg_pieces;
	}

	public void setReg_pieces(List<Reg_piece> reg_pieces) {
		this.reg_pieces = reg_pieces;
	}

	public boolean tiit(Integer x) {
		if (x % 2 == 0)
			return true;
		else
			return false;
	}

	public boolean isSupp() {
		return supp;
	}

	public void setSupp(boolean supp) {
		this.supp = supp;
	}

	public Integer getModif_etat() {
		return modif_etat;
	}

	public void setModif_etat(Integer modif_etat) {
		this.modif_etat = modif_etat;
	}

	public Integer getIdreparation() {
		return idreparation;
	}

	public void setIdreparation(Integer idreparation) {
		this.idreparation = idreparation;
	}

	public Integer getIdreg_piece() {
		return idreg_piece;
	}

	public void setIdreg_piece(Integer idreg_piece) {
		this.idreg_piece = idreg_piece;
	}

	public Integer getQte() {
		if (qte == null) {
			qte = 1;
		}
		return qte;
	}

	public void setQte(Integer qte) {
		this.qte = qte;
	}

	public Integer getIdpiece() {
		return idpiece;
	}

	public void setIdpiece(Integer idpiece) {
		this.idpiece = idpiece;
	}

	public String getDesignationPce() {
		return designationPce;
	}

	public void setDesignationPce(String designationPce) {
		this.designationPce = designationPce;
	}

	public String getPrixPce() {
		return prixPce;
	}

	public void setPrixPce(String prixPce) {
		this.prixPce = prixPce;
	}

	public Integer getIdtache() {
		return idtache;
	}

	public void setIdtache(Integer idtache) {
		this.idtache = idtache;
	}

	public String getDesignationTach() {
		return designationTach;
	}

	public void setDesignationTach(String designationTach) {
		this.designationTach = designationTach;
	}

	public String getPrixTach() {
		return prixTach;
	}

	public void setPrixTach(String prixTach) {
		this.prixTach = prixTach;
	}

	public List<Tache> getTaches() {
		if (T != null) {
			taches.add(T);
			T = null;
		}
		total_tache = 0.0;
		for (int i = 0; i < taches.size(); i++) {
			total_tache = total_tache
					+ Double.parseDouble(taches.get(i).getPrixTach());
		}
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public List<Reg_piece> getPieces() {
		if (pi != null) {
			Reg_piece regp = new Reg_piece();
			regp.setPiece(pi);
			pieces.add(regp);
			pi = null;
		}
		total_piece = 0.0;
		for (int k = 0; k < pieces.size(); k++) {
			total_piece = total_piece
					+ Double.parseDouble(pieces.get(k).getPiece().getPrixPce())
					* pieces.get(k).getQte();
		}
		return pieces;
	}

	public void setPieces(List<Reg_piece> pieces) {
		this.pieces = pieces;
	}

	public boolean isApp_suiv() {
		return app_suiv;
	}

	public void setApp_suiv(boolean app_suiv) {
		this.app_suiv = app_suiv;
	}

	public Integer getIdinfo1() {
		return idinfo1;
	}

	public void setIdinfo1(Integer idinfo1) {
		this.idinfo1 = idinfo1;
	}

	public Integer getIdinfo2() {
		return idinfo2;
	}

	public void setIdinfo2(Integer idinfo2) {
		this.idinfo2 = idinfo2;
	}

	public Integer getIdinfo3() {
		return idinfo3;
	}

	public void setIdinfo3(Integer idinfo3) {
		this.idinfo3 = idinfo3;
	}

	public Integer getIdinfo4() {
		return idinfo4;
	}

	public void setIdinfo4(Integer idinfo4) {
		this.idinfo4 = idinfo4;
	}

	public Integer getIdinfo5() {
		return idinfo5;
	}

	public void setIdinfo5(Integer idinfo5) {
		this.idinfo5 = idinfo5;
	}

	public Integer getIdinfo6() {
		return idinfo6;
	}

	public void setIdinfo6(Integer idinfo6) {
		this.idinfo6 = idinfo6;
	}

	public Integer getIdinfo7() {
		return idinfo7;
	}

	public void setIdinfo7(Integer idinfo7) {
		this.idinfo7 = idinfo7;
	}

	public Integer getIdinfo8() {
		return idinfo8;
	}

	public void setIdinfo8(Integer idinfo8) {
		this.idinfo8 = idinfo8;
	}

	public Integer getIdinfo9() {
		return idinfo9;
	}

	public void setIdinfo9(Integer idinfo9) {
		this.idinfo9 = idinfo9;
	}

	public Integer getIdinfo10() {
		return idinfo10;
	}

	public void setIdinfo10(Integer idinfo10) {
		this.idinfo10 = idinfo10;
	}

	public Integer getIdinfo11() {
		return idinfo11;
	}

	public void setIdinfo11(Integer idinfo11) {
		this.idinfo11 = idinfo11;
	}

	public Integer getIdinfo12() {
		return idinfo12;
	}

	public void setIdinfo12(Integer idinfo12) {
		this.idinfo12 = idinfo12;
	}

	public Utilisateur getUser() {
		UtilisateurService serut = new UtilisateurService();
		user = serut.rechercheUtilisateur(Module.idUtilConnecte);

		return user;
	}

	// methode retourne liste des appls d'un utl
	public List<Appel> getAppels_encour() {
		appels_encour.clear();
		AppelService serap = new AppelService();
		Rep_UtlService serep = new Rep_UtlService();
		List<Rep_Utl> rep_utl = serep.rechercheParUtilisateur(user
				.getIdutilisateur());// liste des réparations d'1 utl
		List<Integer> lapl = new ArrayList<Integer>();// liste contient les ids
														// appel de chaque
														// réparation
		for (int i = 0; i < rep_utl.size(); i++) {
			lapl.add(rep_utl.get(i).getReparation().getAppel().getIdappel());
		}
		for (int i = 0; i < lapl.size(); i++) {
			appels_encour.add(serap.rechercheParId(lapl.get(i)));
		}
		return appels_encour;
	}

	public void setAppels_encour(List<Appel> appels_encour) {
		this.appels_encour = appels_encour;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Integer getChanged_etat() {
		return changed_etat;
	}

	public void setChanged_etat(Integer changed_etat) {
		this.changed_etat = changed_etat;
	}

	public boolean isActivation1() {
		return activation1;
	}

	public void setActivation1(boolean activation1) {
		this.activation1 = activation1;
	}

	public boolean isActivation2() {
		return activation2;
	}

	public void setActivation2(boolean activation2) {
		this.activation2 = activation2;
	}

	public boolean isActivation3() {
		return activation3;
	}

	public void setActivation3(boolean activation3) {
		this.activation3 = activation3;
	}

	public boolean isActivation4() {
		return activation4;
	}

	public void setActivation4(boolean activation4) {
		this.activation4 = activation4;
	}

	public boolean isActivation5() {
		return activation5;
	}

	public void setActivation5(boolean activation5) {
		this.activation5 = activation5;
	}

	public boolean isActivation6() {
		return activation6;
	}

	public void setActivation6(boolean activation6) {
		this.activation6 = activation6;
	}

	public boolean isActivation7() {
		return activation7;
	}

	public void setActivation7(boolean activation7) {
		this.activation7 = activation7;
	}

	public boolean isActivation8() {
		return activation8;
	}

	public void setActivation8(boolean activation8) {
		this.activation8 = activation8;
	}

	public boolean isActivation9() {
		return activation9;
	}

	public void setActivation9(boolean activation9) {
		this.activation9 = activation9;
	}

	public boolean isActivation10() {
		return activation10;
	}

	public void setActivation10(boolean activation10) {
		this.activation10 = activation10;
	}

	public boolean isActivation11() {
		return activation11;
	}

	public void setActivation11(boolean activation11) {
		this.activation11 = activation11;
	}

	public boolean isActivation12() {
		return activation12;
	}

	public void setActivation12(boolean activation12) {
		this.activation12 = activation12;
	}

	public String getLibelé1() {
		return libelé1;
	}

	public void setLibelé1(String libelé1) {
		this.libelé1 = libelé1;
	}

	public String getLibelé2() {
		return libelé2;
	}

	public void setLibelé2(String libelé2) {
		this.libelé2 = libelé2;
	}

	public String getLibelé3() {
		return libelé3;
	}

	public void setLibelé3(String libelé3) {
		this.libelé3 = libelé3;
	}

	public String getLibelé4() {
		return libelé4;
	}

	public void setLibelé4(String libelé4) {
		this.libelé4 = libelé4;
	}

	public String getLibelé5() {
		return libelé5;
	}

	public void setLibelé5(String libelé5) {
		this.libelé5 = libelé5;
	}

	public String getLibelé6() {
		return libelé6;
	}

	public void setLibelé6(String libelé6) {
		this.libelé6 = libelé6;
	}

	public String getLibelé7() {
		return libelé7;
	}

	public void setLibelé7(String libelé7) {
		this.libelé7 = libelé7;
	}

	public String getLibelé8() {
		return libelé8;
	}

	public void setLibelé8(String libelé8) {
		this.libelé8 = libelé8;
	}

	public String getLibelé9() {
		return libelé9;
	}

	public void setLibelé9(String libelé9) {
		this.libelé9 = libelé9;
	}

	public String getLibelé10() {
		return libelé10;
	}

	public void setLibelé10(String libelé10) {
		this.libelé10 = libelé10;
	}

	public String getLibelé11() {
		return libelé11;
	}

	public void setLibelé11(String libelé11) {
		this.libelé11 = libelé11;
	}

	public String getLibelé12() {
		return libelé12;
	}

	public void setLibelé12(String libelé12) {
		this.libelé12 = libelé12;
	}

	public String getInfo11() {
		return info11;
	}

	public void setInfo11(String info11) {
		this.info11 = info11;
	}

	public String getInfo12() {
		return info12;
	}

	public void setInfo12(String info12) {
		this.info12 = info12;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public String getInfo3() {
		return info3;
	}

	public void setInfo3(String info3) {
		this.info3 = info3;
	}

	public String getInfo4() {
		return info4;
	}

	public void setInfo4(String info4) {
		this.info4 = info4;
	}

	public String getInfo5() {
		return info5;
	}

	public void setInfo5(String info5) {
		this.info5 = info5;
	}

	public String getInfo6() {
		return info6;
	}

	public void setInfo6(String info6) {
		this.info6 = info6;
	}

	public String getInfo7() {
		return info7;
	}

	public void setInfo7(String info7) {
		this.info7 = info7;
	}

	public String getInfo8() {
		return info8;
	}

	public void setInfo8(String info8) {
		this.info8 = info8;
	}

	public String getInfo9() {
		return info9;
	}

	public void setInfo9(String info9) {
		this.info9 = info9;
	}

	public String getInfo10() {
		return info10;
	}

	public void setInfo10(String info10) {
		this.info10 = info10;
	}

	public Date getDate_debut() {
		date = new Date();

		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getSt() {
		return st;
	}

	public void setSt(List<String> st) {
		this.st = st;
	}

	public List<String> getAc() {
		return ac;
	}

	public void setAc(List<String> ac) {
		this.ac = ac;
	}

	public List<Service> getSers() {
		return sers;
	}

	public void setSers(List<Service> sers) {
		this.sers = sers;
	}

	public List<Info_apl> getInfos() {
		Info_aplService ser = new Info_aplService();
		infos = ser.rechercheTousInfo_apl();

		return infos;
	}

	public void setInfos(List<Info_apl> infos) {
		this.infos = infos;
	}

	public Integer getSelectedetat() {

		return selectedetat;
	}

	public void setSelectedetat(Integer selectedetat) {
		this.selectedetat = selectedetat;
	}

	public String getDesignationType() {
		return designationType;
	}

	public void setDesignationType(String designationType) {
		this.designationType = designationType;
	}

	public String getDesignationMarq() {
		return designationMarq;
	}

	public void setDesignationMarq(String designationMarq) {
		this.designationMarq = designationMarq;
	}

	public Integer getIdmachine() {
		return idmachine;
	}

	public void setIdmachine(Integer idmachine) {
		this.idmachine = idmachine;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getValeurRecherchep() {
		return valeurRecherchep;
	}

	public void setValeurRecherchep(String valeurRecherchep) {
		this.valeurRecherchep = valeurRecherchep;
	}

	public String getDesignationVille() {
		return designationVille;
	}

	public void setDesignationVille(String designationVille) {
		this.designationVille = designationVille;
	}

	public List<Ville> getVilles() {
		VilleService ser = new VilleService();
		villes = ser.rechercheTousVille();
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}

	public Integer getIdclient() {
		return idclient;
	}

	public void setIdclient(Integer idclient) {
		this.idclient = idclient;
	}

	public String getAdrclt() {
		return adrclt;
	}

	public void setAdrclt(String adrclt) {
		this.adrclt = adrclt;
	}

	public String getMailclt() {
		return mailclt;
	}

	public void setMailclt(String mailclt) {
		this.mailclt = mailclt;
	}

	public String getGsm1clt() {
		return gsm1clt;
	}

	public void setGsm1clt(String gsm1clt) {
		this.gsm1clt = gsm1clt;
	}

	public String getGsm2clt() {
		return gsm2clt;
	}

	public void setGsm2clt(String gsm2clt) {
		this.gsm2clt = gsm2clt;
	}

	public String getTelclt() {
		return telclt;
	}

	public void setTelclt(String telclt) {
		this.telclt = telclt;
	}

	public String getFaxclt() {
		return faxclt;
	}

	public void setFaxclt(String faxclt) {
		this.faxclt = faxclt;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIdville() {
		if (v != null) {
			idville = v.getIdville();
			v = null;
		}
		return idville;
	}

	public void setIdville(Integer idville) {
		this.idville = idville;
	}

	public List<Service> getServices() {
		ServiceService ser = new ServiceService();
		services = ser.rechercheTousService();
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Integer getIdservice() {
		if (s != null) {
			idservice = s.getIdservice();
			s = null;
		}
		return idservice;
	}

	public void setIdservice(Integer idservice) {
		this.idservice = idservice;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<String> getSelectedAccessoires() {

		if (a != null) {
			selectedAccessoires.add(a.getDesignationAcc());
			a = null;
		}
		return selectedAccessoires;
	}

	public void setSelectedAccessoires(List<String> selectedAccessoires) {
		this.selectedAccessoires = selectedAccessoires;
	}

	public List<String> getSelectedStats() {
		return selectedStats;
	}

	public void setSelectedStats(List<String> selectedStats) {
		this.selectedStats = selectedStats;
	}

	public String getDesignationPan() {
		return designationPan;
	}

	public void setDesignationPan(String designationPan) {
		this.designationPan = designationPan;
	}

	public Integer getIdpanne() {
		return idpanne;
	}

	public void setIdpanne(Integer idpanne) {
		this.idpanne = idpanne;
	}

	public List<Panne> getPannes() {
		PanneService ser = new PanneService();
		if (getValeurRecherchep() != null) {
			pannes = ser.rechercheFiltre(valeurRecherchep);
		} else
			pannes = ser.rechercheTousPanneAvecJointure();

		return pannes;
	}

	public void setPannes(List<Panne> pannes) {
		this.pannes = pannes;
	}

	public List<String> getP() {
		return p;
	}

	public void setP(List<String> p) {
		this.p = p;
	}

	public Set<App_panne> getApp_pan() {

		return app_pan;
	}

	public void setApp_pan(Set<App_panne> app_pan) {
		this.app_pan = app_pan;
	}

	public Set<Statut> getStatuts() {

		return statuts;
	}

	public void setStatuts(Set<Statut> statuts) {
		this.statuts = statuts;
	}

	public Set<Accessoire> getAccessoires() {

		return accessoires;
	}

	public void setAccessoires(Set<Accessoire> accessoires) {
		this.accessoires = accessoires;
	}

	public String getDesignationAcc() {
		return designationAcc;
	}

	public void setDesignationAcc(String designationAcc) {
		this.designationAcc = designationAcc;
	}

	public Integer getIdaccessoire() {
		return idaccessoire;
	}

	public void setIdaccessoire(Integer idaccessoire) {
		this.idaccessoire = idaccessoire;
	}

	public String getDesignationEtat() {
		return designationEtat;
	}

	public void setDesignationEtat(String designationEtat) {
		this.designationEtat = designationEtat;
	}

	public List<Panne> getSelectedpans() {

		if (pa != null) {
			Selectedpans.add(pa);
			pa = null;
		}
		return Selectedpans;
	}

	public void setSelectedpans(List<Panne> selectedpans) {
		Selectedpans = selectedpans;
	}

	public Panne getPan() {
		return pan;
	}

	public void setPan(Panne pan) {
		this.pan = pan;
	}

	public List<Service> getSrs() {
		ServiceService ss = new ServiceService();
		srs = ss.rechercheTousService();
		return srs;
	}

	public void setSrs(List<Service> srs) {
		this.srs = srs;
	}

	public String[] getSelectedsers() {
		return selectedsers;
	}

	public void setSelectedsers(String[] selectedsers) {
		this.selectedsers = selectedsers;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public List<Etat> getEtats() {
		EtatService se = new EtatService();
		etats = se.rechercheTousEtat();
		return etats;
	}

	public void setEtats(List<Etat> etats) {
		this.etats = etats;
	}

	public Integer getIdetat() {
		System.out.println("idetat : " + idetat);
		return idetat;
	}

	public void setIdetat(Integer idetat) {
		this.idetat = idetat;
	}

	public List<Marque> getMarques() {
		MarqueService sm = new MarqueService();
		marques = sm.rechercheTousMarque();
		return marques;
	}

	public void setMarques(List<Marque> marques) {
		this.marques = marques;
	}

	public Integer getIdmarque() {
		if (mq != null) {
			idmarque = mq.getIdmarque();
			mq = null;
		}
		return idmarque;
	}

	public void setIdmarque(Integer idmarque) {
		this.idmarque = idmarque;
	}

	public List<Type> getTypes() {
		TypeService stp = new TypeService();
		types = stp.rechercheTousType();
		System.out.println("type= " + types.size());
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public Integer getIdtype() {
		if (ty != null) {
			idtype = ty.getIdtype();
			ty = null;
		}
		return idtype;
	}

	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}

	public Integer getIdclt() {
		if (clt != null)
			idclt = clt.getIdclient();
		System.out.println("Client" + idclt);
		return idclt;
	}

	public void setIdclt(Integer idclt) {
		this.idclt = idclt;
	}

	public Client getClt() {
		if (c != null) {
			clt = c;
			c = null;
		}
		return clt;
	}

	public void setClt(Client clt) {
		this.clt = clt;
	}

	public List<Statut> getStats() {
		StatutService st = new StatutService();
		stats = st.rechercheTousStatut();
		return stats;
	}

	public void setStats(List<Statut> stats) {
		this.stats = stats;
	}

	public List<Accessoire> getAccs() {
		AccessoireService ser = new AccessoireService();
		accs = ser.rechercheTousAccessoire();
		return accs;
	}

	public void setAccs(List<Accessoire> accs) {
		this.accs = accs;
	}

	public String getDesignationSer() {
		return designationSer;
	}

	public Machine getMch() {
		if (m != null) {
			mch = m;
			m = null;
		}
		return mch;
	}

	public void setMch(Machine mch) {
		this.mch = mch;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getNum_serie() {
		return num_serie;
	}

	public void setNum_serie(String num_serie) {
		this.num_serie = num_serie;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getNomclt() {
		return nomclt;
	}

	public void setDesignationSer(String designationSer) {
		this.designationSer = designationSer;
	}

	public void setNomclt(String nomclt) {
		this.nomclt = nomclt;
	}

	public List<Appel> getFilteredAppels() {
		return filteredAppels;
	}

	public void setFilteredAppels(List<Appel> filteredAppels) {
		this.filteredAppels = filteredAppels;
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

	public Integer getIdappel() {
		return idappel;
	}

	public void setIdappel(Integer idappel) {
		this.idappel = idappel;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public Date getDate_entre() {
		return date_entre;
	}

	public void setDate_entre(Date date_entre) {
		this.date_entre = date_entre;
	}

	public List<String> getListesRecherches() {

		listesRecherches.clear();
		listesRecherches.add("--Selectionner--");
		listesRecherches.add("Client");
		listesRecherches.add("S/N");

		return listesRecherches;

	}

	public void setListesRecherches(List<String> listesRecherches) {
		this.listesRecherches = listesRecherches;
	}

	public List<Appel> getAppels() {

		AppelService ser = new AppelService();

		if (valeurRecherche != null || date_debut != null || date_fin != null
				|| selectedetat != null) {

			appels = ser.rechercheFiltre(attribut, valeurRecherche, date_debut,
					date_fin, selectedetat);
		}

		else
			appels = ser.rechercheTousAppelAvecJointure();

		System.out.println(appels.size());
		return appels;

	}

	public void setAppels(List<Appel> appels) {
		this.appels = appels;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void modifierAppel(Appel A) {

		action = "modifier";
		idappel = A.getIdappel();
		info1 = A.getInfo1();
		info2 = A.getInfo2();
		info3 = A.getInfo3();
		info4 = A.getInfo4();
		info5 = A.getInfo5();
		info6 = A.getInfo6();
		info7 = A.getInfo7();
		info8 = A.getInfo8();
		info9 = A.getInfo9();
		info10 = A.getInfo10();
		info11 = A.getInfo11();
		info12 = A.getInfo12();
		date_entre = A.getDate_entre();
		note = A.getNote();
		clt = A.getClient();
		mch = A.getMachine();
		etat = A.getEtat();

		App_panneService serp = new App_panneService();
		App_accService serac = new App_accService();
		App_statService serst = new App_statService();
		List<App_panne> l = serp.rechercheParAppel(A.getIdappel());
		List<App_acc> la = serac.rechercheParAppel(A.getIdappel());
		List<App_stat> lst = serst.rechercheParAppel(A.getIdappel());
		lp.clear();
		for (int i = 0; i < l.size(); i++)
			lp.add(l.get(i).getPanne());
		ac.clear();
		for (int i = 0; i < la.size(); i++)
			ac.add(la.get(i).getAccessoire().getDesignationAcc());
		st.clear();
		for (int i = 0; i < lst.size(); i++)
			st.add(lst.get(i).getStatut().getDesignationStat());

		Selectedpans = lp;
		selectedAccessoires = ac;
		selectedStats = st;
	}

	public void validation() {

		List<Info_apl> list_info_apls = new ArrayList<Info_apl>();
		Info_aplService infoSer= new Info_aplService();
		
		AppelService ser = new AppelService();
		App_panneService ser2 = new App_panneService();
		App_statService ser3 = new App_statService();
		App_accService ser4 = new App_accService();
		App_filService ser5 = new App_filService();
		EtatService se = new EtatService();
	   
		list_info_apls= infoSer.rechercheTousInfo_apl();
		
		Appel A = new Appel();
		A.setEmplacement(emplacement);
		A.setInfo1(info1);
		A.setInfo2(info2);
		A.setInfo3(info3);
		A.setInfo4(info4);
		A.setInfo5(info5);
		A.setInfo6(info6);
		A.setInfo7(info7);
		A.setInfo8(info8);
		A.setInfo9(info9);
		A.setInfo10(info10);
		A.setInfo11(info11);
		A.setInfo12(info12);
		A.setClient(clt);
		A.setMachine(mch);

		A.setNote(note);
		System.out.println("clt : " + clt.getIdclient());
		System.out.println(action);

		if (action.equals("modifier")) {
			System.out.println("c'est une modification");
			A.setDate_entre(date_entre);
			A.setEtat(etat);
			A.setDiag(diag);
			A.setIdappel(idappel);
			ser.modifierAppel(A);

			App_panneService serp = new App_panneService();
			App_accService serac = new App_accService();
			App_statService serst = new App_statService();
			App_filService serf = new App_filService();
			List<App_panne> l = serp.rechercheParAppel(A.getIdappel());
			List<App_acc> la = serac.rechercheParAppel(A.getIdappel());
			List<App_stat> lst = serst.rechercheParAppel(A.getIdappel());
			List<App_fil> lsf = serf.rechercheParAppel(A.getIdappel());
			for (int i = 0; i < l.size(); i++)
				serp.supprimerApp_panne(l.get(i).getIdapp_panne());
			for (int i = 0; i < la.size(); i++)
				serac.supprimerApp_acc(la.get(i).getIdapp_acc());
			for (int i = 0; i < lst.size(); i++)
				serst.supprimerApp_stat(lst.get(i).getIdapp_stat());
			for (int i = 0; i < lsf.size(); i++)
				serf.supprimerApp_fil(lsf.get(i).getIdapp_fil());

			for (int i = 0; i < Selectedpans.size(); i++) {
				System.out.println("nombre des services : " + i + ":"
						+ sers.size());
				int j = 0;
				boolean test = false;
				App_panne ap = new App_panne();
				ap.setAppel(ser.rechercheParExemple(A).get(0));
				ap.setPanne(Selectedpans.get(i));
				ser2.ajouterApp_panne(ap);
				if (sers.size() != 0) {
					while (j < sers.size() && test == false) {
						if (sers.get(j).getIdservice() == Selectedpans.get(i)
								.getService().getIdservice()) {
							test = true;
						} else
							j++;
					}
				}
				if (test == false)
					sers.add(Selectedpans.get(i).getService());

			}
			System.out.println("nombre des services : " + sers.size());
			StatutService serSt = new StatutService();
			for (int i = 0; i < selectedStats.size(); i++) {

				App_stat as = new App_stat();

				as.setAppel(A);
				System.out
						.println("id stat selectionné" + selectedStats.get(i));

				Statut s = new Statut();
				s = serSt.rechercheParNom(selectedStats.get(i)).get(0);
				System.out.println(s);

				as.setStatut(s);
				ser3.ajouterApp_stat(as);
			}
			AccessoireService serAc = new AccessoireService();
			for (int i = 0; i < selectedAccessoires.size(); i++) {

				App_acc ac = new App_acc();

				ac.setAppel(A);

				Accessoire acc = new Accessoire();
				acc = serAc.rechercheParNom(selectedAccessoires.get(i)).get(0);
				System.out.println(acc);

				ac.setAccessoire(acc);
				ser4.ajouterApp_acc(ac);
			}
			Fil_attService serFil = new Fil_attService();
			for (int i = 0; i < sers.size(); i++) {

				App_fil af = new App_fil();

				af.setAppel(A);
				if (A.isDiag()) {
					af.setDiagstiq(true);
				} else {
					af.setDiagstiq(false);
				}

				Fil_att f = new Fil_att();
				List<Fil_att> lat = new ArrayList<Fil_att>();
				Integer ids = sers.get(i).getIdservice();
				lat = serFil.rechercheFiltre(ids);
				System.out.println("fil_att : " + l.size());
				f = lat.get(0);
				System.out.println(f);
				af.setFil_att(f);
				af.setDate_affect(date);
				af.setDatePriorite(date);
				if (A.getClient().getType().equals("particulier"))
					af.setPriorite(3);
				else
					af.setPriorite(2);
				System.out.println("af : " + af);
				ser5.ajouterApp_fil(af);
			}

		}
		if (action.equals("Ajouter")) {
			A.setDate_entre(date);
			System.out.println("date ==" + date);
			A.setEtat(se.rechercheParNom("EnAttente").get(0));
			A.setDiag(diag);
			ser.ajouterAppel(A);
			for (int i = 0; i < Selectedpans.size(); i++) {
				System.out.println("nombre des services : " + i + ":"
						+ sers.size());
				int j = 0;
				boolean test = false;
				App_panne ap = new App_panne();
				ap.setAppel(ser.rechercheParExemple(A).get(0));
				ap.setPanne(Selectedpans.get(i));
				ser2.ajouterApp_panne(ap);
				if (sers.size() != 0) {
					while (j < sers.size() && test == false) {
						if (sers.get(j).getIdservice() == Selectedpans.get(i)
								.getService().getIdservice()) {
							test = true;
						} else
							j++;
					}
				}
				if (test == false)
					sers.add(Selectedpans.get(i).getService());

			}
			System.out.println("nombre des services : " + sers.size());
			StatutService serSt = new StatutService();
			for (int i = 0; i < selectedStats.size(); i++) {

				App_stat as = new App_stat();
				List<Appel> la = ser.rechercheTousAppelAvecJointure();
				Appel a = la.get(0);
				as.setAppel(a);
				System.out
						.println("id stat selectionné" + selectedStats.get(i));

				Statut s = new Statut();
				s = serSt.rechercheParNom(selectedStats.get(i)).get(0);
				System.out.println(s);

				as.setStatut(s);
				ser3.ajouterApp_stat(as);
			}
			AccessoireService serAc = new AccessoireService();
			for (int i = 0; i < selectedAccessoires.size(); i++) {

				App_acc ac = new App_acc();
				List<Appel> la = ser.rechercheTousAppelAvecJointure();
				Appel a = la.get(0);
				ac.setAppel(a);

				Accessoire acc = new Accessoire();
				acc = serAc.rechercheParNom(selectedAccessoires.get(i)).get(0);
				System.out.println(acc);

				ac.setAccessoire(acc);
				ser4.ajouterApp_acc(ac);
			}

			Fil_attService serFil = new Fil_attService();
			List<Appel> la = ser.rechercheTousAppelAvecJointure();
			Appel a = la.get(0);// le dernier appel ajouté puisque la liste est
								// desc

			for (int i = 0; i < sers.size(); i++) {

				App_fil af = new App_fil();

				af.setAppel(a);
				if (a.isDiag()) {
					af.setDiagstiq(true);
				} else {
					af.setDiagstiq(false);
				}

				Fil_att f = new Fil_att();
				List<Fil_att> l = new ArrayList<Fil_att>();
				Integer ids = sers.get(i).getIdservice();
				l = serFil.rechercheFiltre(ids);// recherche fil att par id
												// service
				System.out.println("fil_att : " + l.size());
				f = l.get(0);
				af.setFil_att(f);
				af.setDate_affect(date);
				af.setDatePriorite(date);
				if (a.getClient().getType().equals("particulier"))
					af.setPriorite(3);
				else
					af.setPriorite(2);
				System.out.println("af : " + af);
				ser5.ajouterApp_fil(af);
			}

		}
		ajoutDetailEtat();
		initialisation();
	}

	public void initialisation() {
		idappel = null;
		emplacement = null;
		info1 = null;
		info2 = null;
		info3 = null;
		info4 = null;
		info5 = null;
		info6 = null;
		info7 = null;
		info8 = null;
		info9 = null;
		info10 = null;
		info11 = null;
		info12 = null;
		selectedAccessoires.clear();
		selectedStats.clear();
		Selectedpans.clear();
		clt = null;
		mch = null;
		note = null;
		valeurRecherche = null;
		selectedetat = null;
		date_debut = null;
		date_fin = null;
	}

	public void ajouterAppel() {
		action = "Ajouter";
		initialisation();
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("AjoutAppel.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void Supprimer(Integer id) {

		App_filService serf = new App_filService();
		App_accService serac = new App_accService();
		App_panneService serp = new App_panneService();
		App_statService serst = new App_statService();
		List<App_fil> lf = serf.rechercheParAppel(id);
		List<App_acc> lac = serac.rechercheParAppel(id);
		List<App_panne> lp = serp.rechercheParAppel(id);
		List<App_stat> lst = serst.rechercheParAppel(id);

		for (int i = 0; i < lp.size(); i++)
			serp.supprimerApp_panne(lp.get(i).getIdapp_panne());
		for (int i = 0; i < lac.size(); i++)
			serac.supprimerApp_acc(lac.get(i).getIdapp_acc());
		for (int i = 0; i < lst.size(); i++)
			serst.supprimerApp_stat(lst.get(i).getIdapp_stat());
		for (int i = 0; i < lf.size(); i++)
			serf.supprimerApp_fil(lf.get(i).getIdapp_fil());
		AppelService ser = new AppelService();
		ser.supprimerAppel(id);
		System.out.println("get in supprimer appel");

	}

	public void annulerRecherche() {
		valeurRecherche = null;
		attribut = "--Selectionner--";
		date_debut = null;
		date_fin = null;
		selectedetat = null;
	}

	public void onRowSelectmch(SelectEvent event) {
		mch = (Machine) event.getObject();
		System.out.println("idclt====" + mch.getIdmachine());

	}

	public void onRowSelectfour(SelectEvent event) {
		selfour = (Fournisseur) event.getObject();
		selected_four = selfour;
		selfour = null;
		System.out.println("Fournisseur : " + selected_four.getNomfour());

	}

	public void afficher() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogmachine').show();");

	}

	public void onRowSelectpan(SelectEvent event) {
		FacesContext faces = FacesContext.getCurrentInstance();
		Integer i = 0;
		boolean test = false;

		pan = (Panne) event.getObject();

		if (Selectedpans.size() != 0)

			while (i < Selectedpans.size() && test == false) {
				System.out.println("pans" + Selectedpans.size());
				if (pan.getDesignationPan().equals(
						Selectedpans.get(i).getDesignationPan())) {

					test = true;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Panne déja selectionnée", "Invalid credentials"));
					RequestContext.getCurrentInstance().update("f2:growl");
					RequestContext.getCurrentInstance().update("f4:growl");
				} else
					i++;
			}
		if (test == false)
			Selectedpans.add(pan);

		selectedPan = null;
	}

	public void onRowSelectDiagpan(SelectEvent event) {
		FacesContext faces = FacesContext.getCurrentInstance();
		Integer i = 0;
		boolean test = false;

		pan = (Panne) event.getObject();

		if (selectedDiagpans.size() != 0)

			while (i < selectedDiagpans.size() && test == false) {
				System.out.println("pans" + selectedDiagpans.size());
				if (pan.getDesignationPan().equals(
						selectedDiagpans.get(i).getDesignationPan())) {

					test = true;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Panne deja selectionner", "Invalid credentials"));
					RequestContext.getCurrentInstance().update("f2:growl");
					RequestContext.getCurrentInstance().update("f4:growl");
				} else
					i++;
			}
		if (test == false)
			selectedDiagpans.add(pan);

		selectedPan = null;
	}

	public void onRowSelecttache(SelectEvent event) {

		tchs.clear();
		Tache t = (Tache) event.getObject();
		tchs.add(t);

	}

	public void clearTachs() {
		tchs.clear();

	}

	public void select() {
		FacesContext faces = FacesContext.getCurrentInstance();
		boolean test = false;
		if (taches.size() != 0) {// cad on a deja selectionné des taches

			for (int i = 0; i < tchs.size(); i++)
				if (test == true)
					break;
				else
					for (int j = 0; j < taches.size(); j++) {
						if (test == true)
							break;
						// System.out.println("tch : "+tchs.get(i).getDesignationTach());
						System.out.println("tache: "
								+ taches.get(j).getDesignationTach());
						if (tchs.get(i).getDesignationTach()
								.equals(taches.get(j).getDesignationTach())) {
							test = true;
							faces.addMessage(null, new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Tache deja selectionner",
									"Invalid credentials"));
							RequestContext.getCurrentInstance().update(
									"f1:growl");
						}
					}
		}

		if (test == false)
			taches.addAll(tchs);

		for (int i = 0; i < tchs.size(); i++) {
			Reg_tache regtch = new Reg_tache();
			regtch.setTache(tchs.get(i));
			regtch.setDateDebutTach(date);
			regTachs.add(regtch);
		}

		tchs.clear();

	}

	public void onRowSelectpiece(SelectEvent event) {

		piec.clear();
		Piece p = (Piece) event.getObject();
		piec.add(p);
	}

	public void selectp() {
		FacesContext faces = FacesContext.getCurrentInstance();
		Integer i = 0;
		Integer j = 0;
		boolean test = false;
		if (pieces.size() != 0)

			while (i < piec.size() && test == false) {
				while (j < pieces.size() && test == false) {
					System.out.println("piece" + pieces.size());
					if (piec.get(i)
							.getDesignationPce()
							.equals(pieces.get(j).getPiece()
									.getDesignationPce())) {

						test = true;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"Piece deja selectionner",
								"Invalid credentials"));
						RequestContext.getCurrentInstance().update("f1:growl");
					} else
						j++;

				}
				i++;
			}
		if (test == false)
			for (int k = 0; k < piec.size(); k++) {
				Reg_piece regp = new Reg_piece();
				regp.setPiece(piec.get(k));
				regp.setQte(1);
				pieces.add(regp);
			}
		piec.clear();

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("get in onrow");
		// clt=new Client();

		clt = ((Client) event.getObject());
		System.out.println("client" + clt.getIdclient());

	}

	// action btn ok selection client
	public void okAction() {

		clt = selectedclt;
		selectedclt = null;
	}

	public void annulerAction() {
		selectedclt = null;

	}

	// action btn ok selection machine
	public void okActionMach() {

		mch = selectedMach;
		selectedMach = null;
	}

	// action btn annuler selection machine
	public void annulerActionMach() {
		selectedMach = null;

	}

	public void supPanne(Panne p) {
		Selectedpans.remove(p);
	}

	public void supTache(Tache t) {
		taches.remove(t);
	}

	public void supRegTach(Reg_tache t) {
		regTachs.remove(t);

	}

	public void supPiece(Reg_piece p) {
		pieces.remove(p);
	}

	public void ajouterAccApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		AccessoireService ser = new AccessoireService();
		boolean addValid = false;

		if (action.equals("Ajouter")) {
			System.out.println("get in ajoutacc");
			if (designationAcc == null || (designationAcc.trim().length() == 0)) {// tester
																					// si
																					// accessoire
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "accessoire vide",
						"Invalid credentials"));
			}

			else // tester si ce accessoire existe déjà
			{
				a = new Accessoire();
				accessoireByNom = ser.rechercheParNom(designationAcc);
				System.out.println("size liste recherche service=="
						+ accessoireByNom.size());
				if (accessoireByNom.size() == 0) { // sevice n'existe pas
					addValid = true;
					a.setDesignationAcc(designationAcc);

					System.out.println("accessoire" + a.getIdaccessoire());
					ser.ajouterAccessoire(a);
					RequestContext.getCurrentInstance().update("f1");
					initialisationAc();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"accessoire existe déja", "Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationAcc == null || (designationAcc.trim().length() == 0)) {// tester
																					// si
																					// sevice
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "accessoire vide",
						"Invalid credentials"));
			} else // tester si cette accessoireexiste déjà
			{
				accessoireByNom = ser.rechercheParNom(designationAcc);
				if (accessoireByNom.size() == 0) { // ville n'existe pas
					addValid = true;
					a.setDesignationAcc(designationAcc);
					a.setIdaccessoire(idaccessoire);
					ser.modifierAccessoire(a);
					RequestContext.getCurrentInstance().update("f1");
					initialisationAc();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"accessoire existe déja", "Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	public void detail(Appel a) {

		idappel = a.getIdappel();
		info1 = a.getInfo1();
		info2 = a.getInfo2();
		info3 = a.getInfo3();
		info4 = a.getInfo4();
		info5 = a.getInfo5();
		info6 = a.getInfo6();
		info7 = a.getInfo7();
		info8 = a.getInfo8();
		info9 = a.getInfo9();
		info10 = a.getInfo10();
		info11 = a.getInfo11();
		info12 = a.getInfo12();
		date_entre = a.getDate_entre();
		note = a.getNote();
		clt = a.getClient();
		mch = a.getMachine();
		etat = a.getEtat();

		App_panneService serp = new App_panneService();
		App_accService serac = new App_accService();
		App_statService serst = new App_statService();
		List<App_panne> l = serp.rechercheParAppel(a.getIdappel());
		List<App_acc> la = serac.rechercheParAppel(a.getIdappel());
		List<App_stat> lst = serst.rechercheParAppel(a.getIdappel());
		lp.clear();
		for (int i = 0; i < l.size(); i++)
			lp.add(l.get(i).getPanne());
		ac.clear();
		for (int i = 0; i < la.size(); i++)
			ac.add(la.get(i).getAccessoire().getDesignationAcc());
		st.clear();
		for (int i = 0; i < lst.size(); i++)
			st.add(lst.get(i).getStatut().getDesignationStat());

		Selectedpans = lp;
		selectedAccessoires = ac;
		selectedStats = st;

	}

	public void validationPan() {
		PanneService ser = new PanneService();

		ServiceService serService = new ServiceService();
		Service s = new Service();
		s = serService.rechercheParId(getIdservice());

		pa = new Panne();

		pa.setDesignationPan(designationPan);
		pa.setService(s);
		pa.setDesignationSer(s.getDesignationSer());

		System.out.println(action);

		if (action.equals("Modifier")) {
			System.out.println("c'est une modification");
			pa.setIdpanne(idpanne);
			ser.modifierPanne(pa);
		}
		if (action.equals("Ajouter"))
			System.out.println("get in panne");
		ser.ajouterPanne(pa);

		initialisationPan();
		System.out.println("pannnnnnne");
	}

	public void ajouterSevceApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		ServiceService ser = new ServiceService();
		s = new Service();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationSer == null || (designationSer.trim().length() == 0)) {
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Service vide",
						"Invalid credentials"));
			}

			else // tester si ce sevice existe déjà
			{
				serviceByNom = ser.rechercheParNom(designationSer);
				System.out.println("size liste recherche service=="
						+ serviceByNom.size());
				if (serviceByNom.size() == 0) { // sevice n'existe pas
					addValid = true;
					s.setDesignationSer(designationSer);
					ser.ajouterService(s);
					// RequestContext.getCurrentInstance().update("f2");
					initialisationSer();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Service existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationSer == null || (designationSer.trim().length() == 0)) {// tester
																					// si
																					// sevice
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Sevice vide",
						"Invalid credentials"));
			} else // tester si cette ville existe déjà
			{
				serviceByNom = ser.rechercheParNom(designationSer);
				if (serviceByNom.size() == 0) { // sevice n'existe pas
					addValid = true;
					s.setDesignationSer(designationSer);
					s.setIdservice(idservice);
					ser.modifierService(s);
					RequestContext.getCurrentInstance().update("f1");
					initialisationSer();

				} else if (serviceByNom.get(0).getIdservice() == idservice) {
					addValid = true;
					s.setDesignationSer(designationSer);
					s.setIdservice(idservice);
					ser.modifierService(s);
					RequestContext.getCurrentInstance().update("f1");
					initialisationSer();

				}

				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Sevice existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	public void initialisationPan() {
		idpanne = null;
		designationPan = null;
		idservice = null;

	}

	public void ajouterPanne() {
		action = "Ajouter";
		initialisationPan();
	}

	public void ajouterService() {
		action = "Ajouter";
		initialisationSer();

	}

	public void validationclt() {
		System.out.println("entr client");

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		ClientService ser = new ClientService();

		VilleService serville = new VilleService();
		Ville V = new Ville();
		if (idville != null)
			V = serville.rechercheParId(getIdville());
		boolean addValid = false;

		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		Boolean b = true;

		if (mailclt != null && mailclt.trim().length() > 0)
			b = mailclt.matches(EMAIL_REGEX);

		if (action.equals("Ajouter")) {

			if (!b) {
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Format email invalide",
						"Invalid credentials"));
				RequestContext.getCurrentInstance().update("f2:growl");
			}

			if (nomclt == null || (nomclt.trim().length() == 0)) {// tester //
																	// si//
																	// ville //
																	// est//
																	// vide
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "client vide",
						"Invalid credentials"));
				if (type == null || (type.trim().length() == 0)) {
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "type vide",
							"Invalid credentials"));
				}
				RequestContext.getCurrentInstance().update("f2:growl");
			}

			else if (nomclt != null || (nomclt.trim().length() != 0)) {
				if (type == null || (type.trim().length() == 0)) {
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "type vide",
							"Invalid credentials"));

					RequestContext.getCurrentInstance().update("f2:growl");

				} else {
					c = new Client();
					addValid = true;
					c.setNomclt(nomclt);
					c.setMailclt(mailclt);
					c.setAdrclt(adrclt);
					c.setGsm1clt(gsm1clt);
					c.setGsm2clt(gsm2clt);
					c.setTelclt(telclt);
					c.setFaxclt(faxclt);
					c.setContact(contact);
					c.setType(type);
					if (v != null)
						c.setVille(V);

					ser.ajouterClient(c);
					RequestContext.getCurrentInstance().update("f1");
					// context.execute("PF('dialogmodif1').hide();");
					initialisationclt();
				}

			}

		}

		if (action.equals("Modifier")) {

			System.out.println("mailclt==" + mailclt);
			System.out.println("b===" + b);

			if (!b) {
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Format email invalide",
						"Invalid credentials"));
				RequestContext.getCurrentInstance().update("f2:growl");
			}

			if (nomclt == null || (nomclt.trim().length() == 0)) {// tester si
																	// clt es
																	// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "client vide",
						"Invalid credentials"));
				RequestContext.getCurrentInstance().update("f2:growl");
			}
			if (faces.getMessageList().size() == 0) {
				c = new Client();
				addValid = true;
				c.setNomclt(nomclt);
				c.setIdclient(idclient);
				c.setMailclt(mailclt);
				c.setAdrclt(adrclt);
				c.setGsm1clt(gsm1clt);
				c.setGsm2clt(gsm2clt);
				c.setTelclt(telclt);
				c.setFaxclt(faxclt);
				c.setContact(contact);
				c.setType(type);
				if (v != null)
					c.setVille(V);
				System.out.println("client   :" + c);
				ser.modifierClient(c);
				// RequestContext.getCurrentInstance().update("f1");
				initialisationclt();

			}

		}

		context.addCallbackParam("addValid", addValid);

	}

	public void ajouterVilApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		VilleService ser = new VilleService();
		v = new Ville();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationVille == null
					|| (designationVille.trim().length() == 0)) {// tester
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
					RequestContext.getCurrentInstance().update("f1");
					initialisationvil();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Ville existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationVille == null
					|| (designationVille.trim().length() == 0)) {// tester
																	// si
																	// sevice
																	// est
																	// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Ville vide",
						"Invalid credentials"));
			} else // tester si cette ville existe déjà
			{
				villeByNom = ser.rechercheParNom(designationVille);
				if (villeByNom.size() == 0) { // ville n'existe pas
					addValid = true;
					v.setDesignationVille(designationVille);
					v.setIdville(idville);
					ser.modifierVille(v);
					RequestContext.getCurrentInstance().update("f1");
					initialisationvil();

				} else if (villeByNom.get(0).getIdville() == idville) {

					addValid = true;
					v.setDesignationVille(designationVille);
					v.setIdville(idville);
					ser.modifierVille(v);
					RequestContext.getCurrentInstance().update("f1");
					initialisationvil();

				}

				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Ville existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	public void ajouterVille() {
		System.out.println("ville ajouter");
		action = "Ajouter";
		initialisationvil();

	}

	public void initialisationvil() {
		idville = null;
		designationVille = null;

	}

	public void initialisationSer() {
		idservice = null;
		designationSer = null;

	}

	public void initialisationAc() {
		idaccessoire = null;
		designationAcc = null;

	}

	public void ajouterAccessoire() {
		action = "Ajouter";
		initialisationAc();

	}

	public String initialisationclt() {

		idclient = null;
		nomclt = null;
		mailclt = null;
		adrclt = null;
		gsm1clt = null;
		gsm2clt = null;
		telclt = null;
		faxclt = null;
		contact = null;
		type = null;
		idville = null;
		return null;

	}

	public void ajouterClient() {
		initialisationclt();
		action = "Ajouter";

		System.out.println("get in ajoutclt");
	}

	public void annulerRecherchep() {
		valeurRecherchep = null;

	}

	public void validationmch() {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		MachineService ser = new MachineService();

		MarqueService serm = new MarqueService();
		Marque M = new Marque();
		TypeService sert = new TypeService();
		Type t = new Type();
		if (idmarque != null)
			M = serm.rechercheParId(getIdmarque());
		if (idtype != null)
			t = sert.rechercheParId(getIdtype());

		boolean addValid = false;

		if (action.equals("Ajouter")) {
			System.out.println("ajout mach");
			if (num_serie == null || (num_serie.trim().length() == 0)) {// tester//
																		// si //
																		// ville
																		// //
																		// est
																		// //
																		// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "machine vide",
						"Invalid credentials"));

				if (idtype == null) {// tester
					// si// type// est// vide
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type vide",
							"Invalid credentials"));
				}
				RequestContext.getCurrentInstance().update("f2:growl");
				if (idmarque == null) {// tester
					// si// type// est// vide
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "  Marque vide",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f2:growl");
				}

			} else // tester si ce machine existe déjà
			{
				machByNom = ser.rechercheParNom(num_serie);
				System.out.println("size liste recherche machine=="
						+ machByNom.size());
				if (machByNom.size() == 0) {// machine n'existe pas.
					if ((idtype == null) && (idmarque != null)) {// tester
						// si// type// est// vide
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"Type obligatoire ", "Invalid credentials"));
						RequestContext.getCurrentInstance().update("f2:growl");
					}

					else if ((idtype != null) && (idmarque == null)) {// tester
						// si// type// est// vide
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"Marquee obligatoire ", "Invalid credentials"));
						RequestContext.getCurrentInstance().update("f2:growl");
					} else if ((idtype == null) && (idmarque == null)) {// tester
						// si// type// est// vide
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"Marquee  obligatoire ", "Invalid credentials"));
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								" Type obligatoire ", "Invalid credentials"));
						RequestContext.getCurrentInstance().update("f2:growl");
					}

					else {
						m = new Machine();
						addValid = true;
						m.setNum_serie(num_serie);
						m.setModel(model);
						m.setType(t);
						m.setMarque(M);
						mch = m;
						System.out.println("mch : " + mch);
						ser.ajouterMachine(m);
						RequestContext.getCurrentInstance().update("f1");
						// context.execute("PF('dialogmodif1').hide();");
						initialisationmch();
					}
				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "machine existe déja",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f2:growl");
					// if
					// ((idtype == null ) && (idmarque != null )) {// tester
					// // si// type// est// vide
					// addValid = false;
					// faces.addMessage(null, new FacesMessage(
					// FacesMessage.SEVERITY_ERROR, "type obligatoire ",
					// "Invalid credentials"));}
					//
					// else if
					// ((idtype != null ) && (idmarque == null )) {// tester
					// // si// type// est// vide
					// addValid = false;
					// faces.addMessage(null, new FacesMessage(
					// FacesMessage.SEVERITY_ERROR, "marquee obligatoire ",
					// "Invalid credentials"));}
					// else if
					// ((idtype == null ) && (idmarque == null )) {// tester
					// // si// type// est// vide
					// addValid = false;
					// faces.addMessage(null, new FacesMessage(
					// FacesMessage.SEVERITY_ERROR, "marquee  obligatoire ",
					// "Invalid credentials"));
					// faces.addMessage(null, new FacesMessage(
					// FacesMessage.SEVERITY_ERROR, " type obligatoire ",
					// "Invalid credentials")); }

				}
			}

		}

		if (action.equals("Modifier")) {
			System.out.println("modif mach");
			if (num_serie == null || (num_serie.trim().length() == 0)) {// tester
																		// si
																		// sevice
																		// est
																		// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "machine vide",
						"Invalid credentials"));
			} else // tester si cette machine existe déjà
			{
				machByNom = ser.rechercheParNom(num_serie);
				if (machByNom.size() == 0) { // machine n'existe pas
					m = new Machine();
					addValid = true;
					m.setIdmachine(idmachine);
					m.setNum_serie(num_serie);
					m.setIdmachine(idmachine);
					m.setModel(model);
					m.setType(t);
					m.setMarque(M);

					ser.modifierMachine(m);
					// RequestContext.getCurrentInstance().update("f1");
					initialisationmch();

				} else if (machByNom.get(0).getIdmachine() == idmachine) {
					m = new Machine();
					addValid = true;
					m.setIdmachine(idmachine);
					m.setNum_serie(num_serie);
					m.setModel(model);
					m.setType(t);
					m.setMarque(M);
					ser.modifierMachine(m);
					// RequestContext.getCurrentInstance().update("f1");
					initialisationmch();

				}

				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "machine existe déja",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f2:growl");
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	public void initialisationmch() {
		idmachine = null;
		num_serie = null;

		model = null;
		idtype = null;
		idmarque = null;

	}

	public void ajouterMachine() {
		action = "Ajouter";
		initialisationmch();

	}

	public void ajouterMrqApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		MarqueService ser = new MarqueService();
		mq = new Marque();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationMarq == null
					|| (designationMarq.trim().length() == 0)) {// tester
																// si
																// marque
																// est
																// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Marque vide",
						"Invalid credentials"));
			}

			else // tester si ce marque existe déjà
			{
				mrqByNom = ser.rechercheParNom(designationMarq);
				System.out.println("size liste recherche marque=="
						+ mrqByNom.size());
				if (mrqByNom.size() == 0) { // marque n'existe pas
					addValid = true;
					System.out
							.println("designationMarq=====" + designationMarq);
					mq.setDesignationMarq(designationMarq);
					ser.ajouterMarque(mq);
					RequestContext.getCurrentInstance().update("f1");
					initialisationmq();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Marque existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationMarq == null
					|| (designationMarq.trim().length() == 0)) {// tester
																// si
																// marque
																// est
																// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Marque vide",
						"Invalid credentials"));
			} else // tester si cette ville existe déjà
			{
				mrqByNom = ser.rechercheParNom(designationMarq);
				if (mrqByNom.size() == 0) { // marque n'existe pas
					addValid = true;
					mq.setDesignationMarq(designationMarq);
					mq.setIdmarque(idmarque);
					ser.modifierMarque(mq);
					RequestContext.getCurrentInstance().update("f1");
					initialisationmq();

				} else if (mrqByNom.get(0).getIdmarque() == idmarque) {
					addValid = true;
					mq.setDesignationMarq(designationMarq);
					mq.setIdmarque(idmarque);
					ser.modifierMarque(mq);
					RequestContext.getCurrentInstance().update("f1");
					initialisationmq();

				}

				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Marque existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	public void initialisationmq() {
		idmarque = null;
		designationMarq = null;

	}

	public void ajouterMarque() {
		action = "Ajouter";
		initialisationmq();

	}

	public void ajouterTypmachApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		TypeService ser = new TypeService();
		ty = new Type();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationType == null
					|| (designationType.trim().length() == 0)) {// tester
																// si
																// typ_mach
																// est
																// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Type Machine vide",
						"Invalid credentials"));
			}

			else // tester si ce typ_mach existe déjà
			{
				typmachByNom = ser.rechercheParNom(designationType);
				System.out.println("size liste recherche typ_mach=="
						+ typmachByNom.size());
				if (typmachByNom.size() == 0) { // typ_mach n'existe pas
					addValid = true;
					ty.setDesignationType(designationType);

					ser.ajouterType(ty);
					RequestContext.getCurrentInstance().update("f1");
					initialisationtype();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Type Machine existe déja", "Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationType == null
					|| (designationType.trim().length() == 0)) {// tester
																// si
																// typ_mach
																// est
																// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Type Machine vide",
						"Invalid credentials"));
			} else // tester si cette ville existe déjà
			{
				typmachByNom = ser.rechercheParNom(designationType);
				if (typmachByNom.size() == 0) { // typ_mach n'existe pas
					addValid = true;
					ty.setDesignationType(designationType);
					ty.setIdtype(idtype);
					ser.modifierType(ty);
					RequestContext.getCurrentInstance().update("f1");
					initialisationtype();

				} else if (typmachByNom.get(0).getIdtype() == idtype) {
					addValid = true;
					ty.setDesignationType(designationType);
					ty.setIdtype(idtype);
					ser.modifierType(ty);
					RequestContext.getCurrentInstance().update("f1");
					initialisationtype();

				}

				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Type Machine existe déja", "Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	public void initialisationtype() {
		idtype = null;
		designationType = null;

	}

	public void ajouterType() {
		action = "Ajouter";
		initialisationtype();

	}

	public void ajouterPanApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		PanneService ser = new PanneService();
		ServiceService serService = new ServiceService();
		Service s = new Service();
		s = serService.rechercheParId(getIdservice());
		pa = new Panne();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationPan == null || (designationPan.trim().length() == 0)) {// tester
																					// si
																					// panne
				if (idservice == null) {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Service vide",
							"Invalid credentials"));
				} // est
					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "panne vide",
						"Invalid credentials"));
			}

			else {
				panneByNom = ser.rechercheParNom(designationPan);
				// System.out.println("size liste recherche panne=="
				// + panneByNom.size());
				if (panneByNom.size() == 0) {// panne n'existe pas
					if (idservice == null) {
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Service vide",
								"Invalid credentials"));
					} else {// tester si ce panne existe déjà
						addValid = true;
						pa.setDesignationPan(designationPan);
						pa.setService(s);
						ser.ajouterPanne(pa);
						RequestContext.getCurrentInstance().update("f1");
						initialisationPan();
					}

				} else {

					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Panne existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationPan == null || (designationPan.trim().length() == 0)) {// tester
																					// si
																					// sevice
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Panne vide",
						"Invalid credentials"));
			} else // tester si cette panne existe déjà
			{
				panneByNom = ser.rechercheParNom(designationPan);
				if (panneByNom.size() == 0) { // panne n'existe pas
					addValid = true;
					pa.setDesignationPan(designationPan);
					pa.setIdpanne(idpanne);
					ser.modifierPanne(pa);
					RequestContext.getCurrentInstance().update("f1");
					initialisationPan();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Panne existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	public void modifierClient() {
		RequestContext request = RequestContext.getCurrentInstance();
		FacesContext faces = FacesContext.getCurrentInstance();
		if (clt == null) {
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Il faut selectionner un client", "Invalid credentials"));
			request.update("f2:growl");
		} else {
			request.execute("PF('dialogajout').show();");
			action = "Modifier";
			idclient = clt.getIdclient();
			System.out.println("id  " + idclient);
			nomclt = clt.getNomclt();
			mailclt = clt.getMailclt();
			adrclt = clt.getAdrclt();
			gsm1clt = clt.getGsm1clt();
			gsm2clt = clt.getGsm2clt();
			telclt = clt.getTelclt();
			faxclt = clt.getFaxclt();
			contact = clt.getContact();
			type = clt.getType();
		}

	}

	public void modifierMachine() {
		RequestContext request = RequestContext.getCurrentInstance();
		FacesContext faces = FacesContext.getCurrentInstance();
		if (mch == null) {
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Il faut selectionner une machine", "Invalid credentials"));
			request.update("f2:growl");
		} else {
			request.execute("PF('dialogmodif').show();");
			action = "Modifier";
			System.out.println("id  " + idmachine);
			idmachine = mch.getIdmachine();

			num_serie = mch.getNum_serie();
			model = mch.getModel();

			if (mch.getMarque() != null)
				idmarque = mch.getMarque().getIdmarque();

			if (mch.getType() != null)
				idtype = mch.getType().getIdtype();
		}
	}

	public String controle(FlowEvent event) {
		RequestContext request = RequestContext.getCurrentInstance();
		FacesContext faces = FacesContext.getCurrentInstance();
		currentStepId = event.getOldStep();
		if (currentStepId == null)
			currentStepId = "pan";
		// get current tab
		String stepToGo = event.getNewStep();
		if ((currentStepId.equals("pan") && stepToGo.equals("info_client"))
				|| (currentStepId.equals("info_client") && stepToGo
						.equals("info_machine"))
				|| (currentStepId.equals("info_machine") && stepToGo
						.equals("accessoire"))) {
			switch (currentStepId) {
			case "pan":
				selectedPan = null;
				if (Selectedpans.size() == 0 && selectedStats.size() == 0) {
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Il faut selectionner au moins une panne",
							"Invalid credentials"));
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Il faut selectionner au moins une statut",
							"Invalid credentials"));
					request.update("f2:growl");
					request.update("f4:growl");
					return currentStepId;
				} else if (Selectedpans.size() == 0) {
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Il faut selectionner au moins une panne",
							"Invalid credentials"));
					request.update("f2:growl");
					request.update("f4:growl");
					return currentStepId;

				} else if (selectedStats.size() == 0) {
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Il faut selectionner au moins une statut",
							"Invalid credentials"));
					request.update("f2:growl");
					request.update("f4:growl");
					return currentStepId;
				} else
					return stepToGo;
			case "info_client":
				if (clt == null) {
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Il faut selectionner un client",
							"Invalid credentials"));
					request.update("f2:growl");
					request.update("f4:growl");
					return currentStepId;
				} else
					return stepToGo;

			case "info_machine":
				if (mch == null) {
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Il faut selectionner une machine",
							"Invalid credentials"));
					request.update("f2:growl");
					request.update("f4:growl");
					return currentStepId;
				} else
					return stepToGo;
			case "accessoire":
				if (selectedAccessoires.size() == 0) {
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Il faut selectionner au moin un accessoire",
							"Invalid credentials"));
					request.update("f2:growl");
					request.update("f4:growl");
					return currentStepId;
				} else
					return stepToGo;
			default:
				return stepToGo;
			}

		} else
			return event.getNewStep();
	}

	public void verif() {
		RequestContext request = RequestContext.getCurrentInstance();
		FacesContext faces = FacesContext.getCurrentInstance();
		if (clt == null) {
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Il faut selectionner un client", "Invalid credentials"));
			request.update("f2:growl");
			request.update("f4:growl");
		}
		if (mch == null) {
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Il faut selectionner une machine", "Invalid credentials"));
			request.update("f2:growl");
			request.update("f4:growl");
		}

	}

	public void updatePriorité() {

		f = serfil.rechercheFiltre(user.getService().getIdservice());// liste
																		// des
																		// apl
																		// dans
																		// fil
																		// att
																		// selon
																		// idservice
		lf = serf.rechercheParFil(f.get(0).getIdfil_att());// list app_fil selon
															// idFil ordonnées
		Date d = new Date();

		for (int i = 0; i < lf.size(); i++) {
			long datePrio = lf.get(i).getDatePriorite().getTime();
			long dateCourant = d.getTime();
			long intervalle = dateCourant - datePrio;

			if ((intervalle > 1000000000) || (lf.get(i).getPriorite() > 1)) {
				System.out.println("et in if intervalle");
				int id = lf.get(i).getIdapp_fil();
				lf.get(i).setPriorite(lf.get(i).getPriorite() - 1);
				lf.get(i).setDatePriorite(d);
				lf.get(i).setIdapp_fil(id);
				App_filService serappfill = new App_filService();

				serappfill.modifierApp_fil(lf.get(i));
				System.out.println(lf.get(i));

			}

			System.out.println("datePrio==" + datePrio);
			System.out.println("dateCourant==" + dateCourant);
			System.out.println("intervalle==" + intervalle);

		}
	}

	public Appel appel_suiv() {
		updatePriorité();
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext faces = FacesContext.getCurrentInstance();
		f = serfil.rechercheFiltre(user.getService().getIdservice());// liste
																		// des
																		// fil
																		// att
																		// selon
																		// idservice
																		// de
																		// l'utilisateur
		lf = serf.rechercheParFil(f.get(0).getIdfil_att());// list app_fil selon
															// idFil ordonnées
		rep = new Reparation();
		rep_utl = new Rep_Utl();
		rep_utl.setUtilisateur(user);
		k = 0;

		if (lf.size() == 0) {

			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Pas d'appel",
					"Invalid credentials"));
			context.update("f1:growl");
			return null;

		} else {

			ap = lf.get(k).getAppel();
			detail_etat(ap.getIdappel());// recuperer les details de l
											// appl(pannes et detailEtat)

			if (serf.rechercheParAppel(ap.getIdappel()).size() != 1// si apl
																	// appartien
																	// a =sieur
																	// serviece
					|| serep.rechercheParAppel(ap.getIdappel()).size() != 0) {// ou
																				// il
																				// existe
																				// une
																				// repartion
																				// avec
																				// ce
																				// id
																				// apl
				context.execute("PF('apl').show();");

				return null;
			} else {
				System.out.println("get in appel suivant ");

				apl();// redirection vers page reparation
				if (serep.rechercheParAppel(ap.getIdappel()).size() == 0) {
					rep.setAppel(ap);
					rep.setTotalpiece(0.0);
					rep.setTotalrep(0.0);
					rep.setTotaltache(0.0);
					serep.ajouterReparation(rep);
				}
				rep_utl.setReparation(serep.rechercheParAppel(ap.getIdappel())
						.get(0));
				Selectedrep = serep.rechercheParAppel(ap.getIdappel()).get(0);
				srep_utl.ajouterRep_Utl(rep_utl);
				idreparation = serep.rechercheParExemple(rep).get(0)
						.getIdreparation();

				detail(ap);
				serf.supprimerApp_fil(lf.get(k).getIdapp_fil());
				ap.setEtat(se.rechercheParNom("EnCours").get(0));
				sa.modifierAppel(ap);
				Selectedapl = ap;
				// ---------------------------------------
				System.out.println("appel : " + Selectedapl);

				Reg_tacheService sertch = new Reg_tacheService();
				reg_taches = sertch.rechercheParReparation(Selectedrep
						.getIdreparation());
				if (reg_taches.size() != 0) {
					taches.clear();
					for (int i = 0; i < reg_taches.size(); i++) {
						taches.add(reg_taches.get(i).getTache());

					}
				} else
					taches.clear();
				total_tache = Selectedrep.getTotaltache();
				total_piece = Selectedrep.getTotalpiece();
				total_rep = Selectedrep.getTotalrep();
				Reg_pieceService serp = new Reg_pieceService();
				PieceService serpiec = new PieceService();

				reg_pieces = serp.rechercheParReparation(Selectedrep
						.getIdreparation());
				if (reg_pieces.size() != 0) {
					pieces.clear();

					for (int i = 0; i < reg_pieces.size(); i++) {
						pieces.add(reg_pieces.get(i));
						serpiec.modifierPiece(reg_pieces.get(i).getPiece());
					}

				} else
					pieces.clear();
				return ap;
			}
		}
	}

	public Appel diag_suiv() {

		System.out.println("diag suiv *******");

		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext faces = FacesContext.getCurrentInstance();

		f = serfil.rechercheFiltre(user.getService().getIdservice());// liste
																		// des
																		// fil
																		// att
																		// selon
																		// idservice
																		// de
																		// l'utilisateur
		lf = serf.rechercheParFilandDiag(f.get(0).getIdfil_att());// list
																	// app_fil
																	// ordonnées
																	// selon
		// idFil and diag=true

		System.out.println("size de lf===========>>" + lf.size());
		diagnostique = new Diagnostique();

		if (lf.size() == 0) {

			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Pas de diagnoste dans le fil d'attente",
					"Invalid credentials"));
			context.update("f1:growl");
			return null;

		} else {
			EtatService etaSer = new EtatService();
			Etat eta = new Etat();
			eta = etaSer.rechercheParId(4);
			ap = lf.get(0).getAppel();
			App_panneService serp = new App_panneService();
			DiagnostiqueService diagSer = new DiagnostiqueService();
			Detaille_EtatService detEtSer = new Detaille_EtatService();
			Detaille_Etat detEt = new Detaille_Etat();
			detEt.setAppel(ap);
			detEt.setEtat(eta);
			detEt.setService(user.getService());
			detEt.setTypeApl("Diagnostique");
			detEtSer.ajouterDetaille_Etat(detEt);
			apl();// redirection vers page reparation
			// Appel a = ap;
			List<Panne> lp = new ArrayList<Panne>();
			List<App_panne> l = serp.rechercheParAppel(ap.getIdappel());
			for (int i = 0; i < l.size(); i++)
				lp.add(l.get(i).getPanne());
			Selectedpans = lp;

			diagnostique.setUtilisateur(user);
			diagnostique.setAppel(ap);
			diagSer.ajouteridDiagnostique(diagnostique);
			detail(ap);
			serf.supprimerApp_fil(lf.get(0).getIdapp_fil());
			ap.setDiag(false);
			sa.modifierAppel(ap);
			Selectedapl = ap;

		}

		return ap;

	}

	public Appel ok() {
		serf.supprimerApp_fil(lf.get(k).getIdapp_fil());
		if (serep.rechercheParAppel(ap.getIdappel()).size() == 0) {
			rep.setAppel(ap);
			serep.ajouterReparation(rep);
		}
		rep_utl.setReparation(serep.rechercheParAppel(ap.getIdappel()).get(0));
		Selectedrep = serep.rechercheParAppel(ap.getIdappel()).get(0);
		srep_utl.ajouterRep_Utl(rep_utl);
		idreparation = serep.rechercheParExemple(rep).get(0).getIdreparation();
		apl();
		detail(ap);
		ap.setEtat(se.rechercheParNom("EnCours").get(0));
		sa.modifierAppel(ap);
		Selectedapl = ap;
		k = 0;
		total_tache = Selectedrep.getTotaltache();
		total_piece = Selectedrep.getTotalpiece();
		total_rep = Selectedrep.getTotalrep();
		Reg_tacheService sertch = new Reg_tacheService();
		reg_taches = sertch.rechercheParReparation(Selectedrep
				.getIdreparation());
		if (reg_taches.size() != 0) {
			taches.clear();
			for (int i = 0; i < reg_taches.size(); i++) {
				taches.add(reg_taches.get(i).getTache());

			}
		} else
			taches.clear();

		Reg_pieceService serp = new Reg_pieceService();

		reg_pieces = serp.rechercheParReparation(Selectedrep.getIdreparation());
		if (reg_pieces.size() != 0) {
			pieces.clear();
			for (int i = 0; i < reg_pieces.size(); i++) {
				pieces.add(reg_pieces.get(i));

			}
		} else
			pieces.clear();
		return ap;

	}

	public Appel app_suiv() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext faces = FacesContext.getCurrentInstance();

		if (lf.size() <= 1) {
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Pas d'appel",
					"Invalid credentials"));
			context.update("f1:growl");
			k = 0;
			return null;
		} else {
			k++;
			ap = lf.get(k).getAppel();
			serf.supprimerApp_fil(lf.get(k).getIdapp_fil());
			if (serep.rechercheParAppel(ap.getIdappel()).size() == 0) {
				rep.setAppel(ap);
				serep.ajouterReparation(rep);
			}
			rep_utl.setReparation(serep.rechercheParAppel(ap.getIdappel()).get(
					0));
			Selectedrep = serep.rechercheParAppel(ap.getIdappel()).get(0);
			srep_utl.ajouterRep_Utl(rep_utl);
			total_tache = Selectedrep.getTotaltache();
			total_piece = Selectedrep.getTotalpiece();
			total_rep = Selectedrep.getTotalrep();
			idreparation = serep.rechercheParExemple(rep).get(0)
					.getIdreparation();
			detail(ap);
			apl();
			ap.setEtat(se.rechercheParNom("EnCours").get(0));
			sa.modifierAppel(ap);
			Selectedapl = ap;

			k = 0;

			Reg_tacheService sertch = new Reg_tacheService();
			reg_taches = sertch.rechercheParReparation(Selectedrep
					.getIdreparation());
			if (reg_taches.size() != 0) {
				taches.clear();
				for (int i = 0; i < reg_taches.size(); i++) {
					taches.add(reg_taches.get(i).getTache());
				}
			} else
				taches.clear();

			Reg_pieceService serp = new Reg_pieceService();

			reg_pieces = serp.rechercheParReparation(Selectedrep
					.getIdreparation());
			if (reg_pieces.size() != 0) {
				pieces.clear();
				for (int i = 0; i < reg_pieces.size(); i++) {
					pieces.add(reg_pieces.get(i));

				}
			} else
				pieces.clear();
			return ap;
		}

	}

	public void detail_etat(Integer id) {
		Detaille_EtatService serde = new Detaille_EtatService();
		lde = serde.rechercheParAppel(id);// liste detaille_etat by id
											// appel(donne l etat de apl dans
											// chaque sevice)

		App_panneService serp = new App_panneService();
		Appel a = ap;
		List<Panne> lp = new ArrayList<Panne>();
		List<App_panne> l = serp.rechercheParAppel(a.getIdappel());
		lp.clear();
		for (int i = 0; i < l.size(); i++)
			lp.add(l.get(i).getPanne());
		Selectedpans = lp;
	}

	public String rech_ser(Integer id) {
		ServiceService ss = new ServiceService();
		Service s = ss.rechercheParId(id);
		return s.getDesignationSer();
	}

	public String rech_etat(Integer id) {
		EtatService se = new EtatService();
		Etat e = se.rechercheParId(id);
		return e.getDesignationEtat();
	}

	public void charger_appel(Integer idapl) {

		Appel a = new Appel();
		AppelService appSer = new AppelService();
		a = appSer.rechercheParId(idapl);
		detail(a);
		Reg_tacheService sertch = new Reg_tacheService();
		ReparationService srep = new ReparationService();
		Reparation rep = srep.rechercheParAppel(a.getIdappel()).get(0);
		System.out.println("la reparation==" + rep);
		Selectedrep = rep;
		total_tache = Selectedrep.getTotaltache();
		total_piece = Selectedrep.getTotalpiece();
		total_rep = Selectedrep.getTotalrep();
		Selectedapl = a;
		if (Selectedrep.getTotaltache() != null)
			total_tache = Selectedrep.getTotaltache();
		if (Selectedrep.getTotalpiece() != null)
			total_piece = Selectedrep.getTotalpiece();
		if (Selectedrep.getTotalrep() != null)
			total_rep = Selectedrep.getTotalrep();
		reg_taches = sertch.rechercheParReparation(rep.getIdreparation());
		if (reg_taches.size() != 0) {
			taches.clear();
			for (int i = 0; i < reg_taches.size(); i++) {
				taches.add(reg_taches.get(i).getTache());

			}
		} else
			taches.clear();
		Reg_pieceService serp = new Reg_pieceService();
		reg_pieces = serp.rechercheParReparation(rep.getIdreparation());
		if (reg_pieces.size() != 0) {
			pieces.clear();
			for (int i = 0; i < reg_pieces.size(); i++) {
				pieces.add(reg_pieces.get(i));

			}
		} else
			pieces.clear();

	}

	public void validation_Info() {
		Info_aplService serinf = new Info_aplService();
		inf1.setIdinfo_apl(idinfo1);
		inf1.setDesignationinfo_apl(libelé1);
		inf1.setActivation(activation1);

		inf2.setDesignationinfo_apl(libelé2);
		inf2.setActivation(activation2);
		inf2.setIdinfo_apl(idinfo2);
		inf3.setDesignationinfo_apl(libelé3);
		inf3.setActivation(activation3);
		inf3.setIdinfo_apl(idinfo3);
		inf4.setDesignationinfo_apl(libelé4);
		inf4.setActivation(activation4);
		inf4.setIdinfo_apl(idinfo4);
		inf5.setDesignationinfo_apl(libelé5);
		inf5.setActivation(activation5);
		inf5.setIdinfo_apl(idinfo5);
		inf6.setDesignationinfo_apl(libelé6);
		inf6.setActivation(activation6);
		inf6.setIdinfo_apl(idinfo6);
		inf7.setDesignationinfo_apl(libelé7);
		inf7.setActivation(activation7);
		inf7.setIdinfo_apl(idinfo7);
		inf8.setDesignationinfo_apl(libelé8);
		inf8.setActivation(activation8);
		inf8.setIdinfo_apl(idinfo8);
		inf9.setDesignationinfo_apl(libelé9);
		inf9.setActivation(activation9);
		inf9.setIdinfo_apl(idinfo9);
		inf10.setDesignationinfo_apl(libelé10);
		inf10.setActivation(activation10);
		inf10.setIdinfo_apl(idinfo10);
		inf11.setDesignationinfo_apl(libelé11);
		inf11.setActivation(activation11);
		inf11.setIdinfo_apl(idinfo11);
		inf12.setDesignationinfo_apl(libelé12);
		inf12.setActivation(activation12);
		inf12.setIdinfo_apl(idinfo12);

		serinf.modifierInfo_apl(inf1);
		serinf.modifierInfo_apl(inf2);

		serinf.modifierInfo_apl(inf3);

		serinf.modifierInfo_apl(inf4);

		serinf.modifierInfo_apl(inf5);

		serinf.modifierInfo_apl(inf6);

		serinf.modifierInfo_apl(inf7);

		serinf.modifierInfo_apl(inf8);

		serinf.modifierInfo_apl(inf9);

		serinf.modifierInfo_apl(inf10);

		serinf.modifierInfo_apl(inf11);

		serinf.modifierInfo_apl(inf12);
		init_info();
	}

	public void init_info() {
		Info_aplService ser = new Info_aplService();
		infos = ser.rechercheTousInfo_apl();

		libelé1 = infos.get(0).getDesignationinfo_apl();
		activation1 = infos.get(0).isActivation();
		idinfo1 = infos.get(0).getIdinfo_apl();

		libelé2 = infos.get(1).getDesignationinfo_apl();
		activation2 = infos.get(1).isActivation();
		idinfo2 = infos.get(1).getIdinfo_apl();

		libelé3 = infos.get(2).getDesignationinfo_apl();
		activation3 = infos.get(2).isActivation();
		idinfo3 = infos.get(2).getIdinfo_apl();

		libelé4 = infos.get(3).getDesignationinfo_apl();
		activation4 = infos.get(3).isActivation();
		idinfo4 = infos.get(3).getIdinfo_apl();

		libelé5 = infos.get(4).getDesignationinfo_apl();
		activation5 = infos.get(4).isActivation();
		idinfo5 = infos.get(4).getIdinfo_apl();

		libelé6 = infos.get(5).getDesignationinfo_apl();
		activation6 = infos.get(5).isActivation();
		idinfo6 = infos.get(5).getIdinfo_apl();

		libelé7 = infos.get(6).getDesignationinfo_apl();
		activation7 = infos.get(6).isActivation();
		idinfo7 = infos.get(6).getIdinfo_apl();

		libelé8 = infos.get(7).getDesignationinfo_apl();
		activation8 = infos.get(7).isActivation();
		idinfo8 = infos.get(7).getIdinfo_apl();

		libelé9 = infos.get(8).getDesignationinfo_apl();
		activation9 = infos.get(8).isActivation();
		idinfo9 = infos.get(8).getIdinfo_apl();

		libelé10 = infos.get(9).getDesignationinfo_apl();
		activation10 = infos.get(9).isActivation();
		idinfo10 = infos.get(9).getIdinfo_apl();

		libelé11 = infos.get(10).getDesignationinfo_apl();
		activation11 = infos.get(10).isActivation();
		idinfo11 = infos.get(10).getIdinfo_apl();

		libelé12 = infos.get(11).getDesignationinfo_apl();
		activation12 = infos.get(11).isActivation();
		idinfo12 = infos.get(11).getIdinfo_apl();
	}

	public void modif_etat(Appel a) {
		EtatService sere = new EtatService();
		a.setEtat(sere.rechercheParId(changed_etat));
		RequestContext.getCurrentInstance().update("f1:growl");
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogajout').hide();");
	}

	public void dateSelectDB(SelectEvent event) {
		date_debut = ((Date) (event.getObject()));
		System.out.println("date_debut" + date_debut);
	}

	public void dateSelectDR(SelectEvent event) {
		date_retour = ((Date) (event.getObject()));

	}

	public void dateSelectDF(SelectEvent event) {

		date_fin = (Date) (event.getObject());
		System.out.println("date_fin" + date_fin);
		if (date_debut != null)
			if (date_fin.compareTo(date_debut) < 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Date Invalide", "Invalid credentials"));
				RequestContext.getCurrentInstance().update("f1:f2:growl");
				date_fin = null;

			}
	}

	public void ajouterTachApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		TacheService ser = new TacheService();
		T = new Tache();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationTach == null
					|| (designationTach.trim().length() == 0)) {// tester
																// si
																// tache
																// est
																// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "tache vide",
						"Invalid credentials"));
			}

			else // tester si ce tache existe déjà
			{
				tacheByNom = ser.rechercheParNom(designationTach);
				System.out.println("size liste recherche sevice=="
						+ tacheByNom.size());
				if (tacheByNom.size() == 0) { // tache n'existe pas
					addValid = true;
					T.setDesignationTach(designationTach);
					T.setPrixTach(prixTach);
					ser.ajouterTache(T);
					RequestContext.getCurrentInstance().update("f1");
					initialisationTache();

				}

				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Tache existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationTach == null
					|| (designationTach.trim().length() == 0)) {// tester
																// si
																// tache
																// est
																// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "tache vide",
						"Invalid credentials"));
			} else // tester si cette tache existe déjà
			{
				tacheByNom = ser.rechercheParNom(designationTach);
				if (tacheByNom.size() == 0) { // tache n'existe pas
					addValid = true;
					T.setDesignationTach(designationTach);
					T.setIdtache(idtache);
					T.setPrixTach(prixTach);
					ser.modifierTache(T);
					RequestContext.getCurrentInstance().update("f1");
					initialisationTache();

				} else if (tacheByNom.get(0).getIdtache() == idtache) {

					addValid = true;
					T.setDesignationTach(designationTach);
					T.setIdtache(idtache);
					T.setPrixTach(prixTach);
					ser.modifierTache(T);
					RequestContext.getCurrentInstance().update("f1");
					initialisationTache();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "tache existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	public void modifierTache(Tache T) {
		action = "Modifier";
		System.out.println("id  " + idtache);
		idtache = T.getIdtache();
		designationTach = T.getDesignationTach();
		prixTach = T.getPrixTach();

	}

	public void ajouterTache() {
		action = "Ajouter";
		initialisationTache();

	}

	public void initialisationTache() {
		idtache = null;
		designationTach = null;
		prixTach = null;

	}

	public void modifierPiece(Piece p) {
		action = "Modifier";
		System.out.println("id  " + idpiece);
		idpiece = p.getIdpiece();
		designationPce = p.getDesignationPce();
		prixPce = p.getPrixPce();

	}

	public void initialisationPiece() {
		idpiece = null;
		designationPce = null;
		prixPce = null;

	}

	public void ajouterPiece() {
		action = "Ajouter";
		initialisationPiece();

	}

	public void ajouterPceApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		PieceService ser = new PieceService();
		pi = new Piece();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationPce == null || (designationPce.trim().length() == 0)) {// tester
																					// si
																					// sevice
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Piece vide",
						"Invalid credentials"));
			}

			else // tester si ce Piece existe déjà
			{
				pieceByNom = ser.rechercheParNom(designationPce);
				System.out.println("size liste recherche sevice=="
						+ pieceByNom.size());

				if (pieceByNom.size() == 0) { // sevice n'existe pas
					addValid = true;
					pi.setDesignationPce(designationPce);
					pi.setPrixPce(prixPce);
					ser.ajouterPiece(pi);
					RequestContext.getCurrentInstance().update("f1");
					initialisationPiece();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Piece existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationPce == null || (designationPce.trim().length() == 0)) {// tester
																					// si
																					// sevice
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Piece vide",
						"Invalid credentials"));
			} else // tester si cette ville existe déjà
			{
				pieceByNom = ser.rechercheParNom(designationPce);
				if (pieceByNom.size() == 0) { // n'existe pas
					addValid = true;
					pi.setDesignationPce(designationPce);
					pi.setIdpiece(idpiece);
					pi.setPrixPce(prixPce);
					ser.modifierPiece(pi);
					RequestContext.getCurrentInstance().update("f1");
					initialisationPiece();

				} else if (pieceByNom.get(0).getIdpiece() == idpiece) {

					addValid = true;
					pi.setDesignationPce(designationPce);
					pi.setIdpiece(idpiece);
					pi.setPrixPce(prixPce);
					ser.modifierPiece(pi);
					RequestContext.getCurrentInstance().update("f1");
					initialisationPiece();
				}

				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "piece existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	public void apl() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("Reparation.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void diag() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("Diagnostique.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void enregistrer() {
		FacesContext faces = FacesContext.getCurrentInstance();
		Reg_pieceService sreg = new Reg_pieceService();
		Reg_tacheService stache = new Reg_tacheService();
		Detaille_EtatService serdet = new Detaille_EtatService();
		Detaille_Etat det = new Detaille_Etat();
		Rep_UtlService serutl = new Rep_UtlService();
		ReparationService srep = new ReparationService();

		Rep_Utl rep_utl = serutl.rechercheParRep_Utl(user.getIdutilisateur(),
				Selectedrep.getIdreparation()).get(0);// rep_utl==> contient obj
														// Rep_utl avec idUtl et
														// idRep données

		Selectedrep.setTotalpiece(total_piece);
		Selectedrep.setTotaltache(total_tache);
		Selectedrep.setTotalrep(total_rep);
		srep.modifierReparation(Selectedrep);

		List<Reg_piece> regpieces = sreg.rechercheParReparation(Selectedrep
				.getIdreparation());// liste reg_pce filtré par idReparation
		List<Reg_tache> regtaches = stache.rechercheParReparation(Selectedrep
				.getIdreparation());// liste reg_tache filtré par idReparation

		List<Detaille_Etat> ldet = new ArrayList<Detaille_Etat>();
		List<Reg_piece> piece_dif = new ArrayList<Reg_piece>();
		List<Reg_tache> tache_dif = new ArrayList<Reg_tache>();

		if (regpieces.size() == 0 && regtaches.size() == 0) { // if1==>c à dire
																// la réparation
																// n'a pas pas
																// des pces ni
																// taches

			for (int i = 0; i < pieces.size(); i++) {
				Reg_piece reg_piece = new Reg_piece();
				reg_piece.setQte(qte);
				reg_piece.setPiece(pieces.get(i).getPiece());
				reg_piece.setUtilisateur(user);
				reg_piece.setReparation(Selectedrep);
				sreg.ajouterReg_piece(reg_piece);
			}

			for (int i = 0; i < taches.size(); i++) {
				Reg_tache reg_tache = new Reg_tache();
				reg_tache.setTache(taches.get(i));
				reg_tache.setUtilisateur(user);
				reg_tache.setReparation(Selectedrep);
				stache.ajouterReg_tache(reg_tache);
			}
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Données Enregistrées", "Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1:growl");

		}// fin if1
		else if (regpieces.size() != 0 && regtaches.size() == 0) {// if2==>réparation
																	// n'a pas
																	// des
																	// taches
																	// mais
																	// seulement
																	// des pces

			for (int i = 0; i < regpieces.size(); i++) {// parcourir liste des
														// regpces filtré selon
														// idReparation

				if (regpieces.get(i).getUtilisateur().getIdutilisateur() != user
						.getIdutilisateur()) {
					piece_dif.add(regpieces.get(i));
					for (int j = 0; j < pieces.size(); j++)
						if (regpieces
								.get(i)
								.getPiece()
								.getDesignationPce()
								.equals(pieces.get(j).getPiece()
										.getDesignationPce()))
							pieces.remove(pieces.get(j));
				}
			}// fin for
			for (int i = 0; i < piece_dif.size(); i++)
				regpieces.remove(piece_dif.get(i));

			System.out.println("pieces : " + pieces.size());
			if (regpieces.size() != 0)
				for (int i = 0; i < regpieces.size(); i++)
					sreg.supprimerReg_piece(regpieces.get(i).getIdreg_piece());
			for (int i = 0; i < pieces.size(); i++) {
				Reg_piece reg_piece = new Reg_piece();
				reg_piece.setQte(qte);
				reg_piece.setPiece(pieces.get(i).getPiece());
				reg_piece.setUtilisateur(user);
				reg_piece.setReparation(Selectedrep);
				sreg.ajouterReg_piece(reg_piece);
			}
		}// fin if2
		else if (regtaches.size() != 0 && regpieces.size() == 0) {// if3==>
																	// reglement
																	// n' a pas
																	// des pces
																	// mais
																	// seulement
																	// des
																	// taches
			for (int i = 0; i < regtaches.size(); i++) {
				if (regtaches.get(i).getUtilisateur().getIdutilisateur() != user
						.getIdutilisateur()) {
					tache_dif.add(regtaches.get(i));
					for (int j = 0; j < taches.size(); j++)
						if (regtaches.get(i).getTache().getDesignationTach()
								.equals(taches.get(j).getDesignationTach()))
							taches.remove(taches.get(j));

				}
			}
			for (int i = 0; i < tache_dif.size(); i++)
				regtaches.remove(tache_dif.get(i));
			if (regtaches.size() != 0)
				for (int i = 0; i < regtaches.size(); i++)
					stache.supprimerReg_tache(regtaches.get(i).getIdreg_tache());
			for (int i = 0; i < taches.size(); i++) {
				System.out.println("get in ajout");
				Reg_tache reg_tache = new Reg_tache();
				reg_tache.setTache(taches.get(i));
				reg_tache.setUtilisateur(user);
				reg_tache.setReparation(Selectedrep);
				stache.ajouterReg_tache(reg_tache);
			}

			for (int i = 0; i < pieces.size(); i++) {
				Reg_piece reg_piece = new Reg_piece();
				reg_piece.setQte(qte);
				reg_piece.setPiece(pieces.get(i).getPiece());
				reg_piece.setUtilisateur(user);
				reg_piece.setReparation(Selectedrep);
				sreg.ajouterReg_piece(reg_piece);
			}
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Données Enregistrer", "Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1:growl");

		}// fin if3
		else if (regtaches.size() != 0 && regpieces.size() != 0) {// if4==>
																	// reglement
																	// a des
																	// pces et
																	// des
																	// taches

			for (int i = 0; i < regpieces.size(); i++) {
				if (regpieces.get(i).getUtilisateur().getIdutilisateur() != user
						.getIdutilisateur()) {
					piece_dif.add(regpieces.get(i));
					for (int j = 0; j < pieces.size(); j++)
						if (regpieces
								.get(i)
								.getPiece()
								.getDesignationPce()
								.equals(pieces.get(j).getPiece()
										.getDesignationPce()))
							pieces.remove(pieces.get(j));

				}
			}
			for (int i = 0; i < piece_dif.size(); i++)
				regpieces.remove(piece_dif.get(i));
			if (regpieces.size() != 0)
				for (int i = 0; i < regpieces.size(); i++)
					sreg.supprimerReg_piece(regpieces.get(i).getIdreg_piece());
			for (int i = 0; i < regtaches.size(); i++) {
				if (regtaches.get(i).getUtilisateur().getIdutilisateur() != user
						.getIdutilisateur()) {
					tache_dif.add(regtaches.get(i));
					for (int j = 0; j < taches.size(); j++)
						if (regtaches.get(i).getTache().getDesignationTach()
								.equals(taches.get(j).getDesignationTach()))
							taches.remove(taches.get(j));

				}
			}
			for (int i = 0; i < tache_dif.size(); i++)
				regtaches.remove(tache_dif.get(i));

			if (regtaches.size() != 0)
				for (int i = 0; i < regtaches.size(); i++)
					stache.supprimerReg_tache(regtaches.get(i).getIdreg_tache());

			for (int i = 0; i < pieces.size(); i++) {
				Reg_piece reg_piece = new Reg_piece();
				reg_piece.setQte(qte);
				reg_piece.setPiece(pieces.get(i).getPiece());
				reg_piece.setUtilisateur(user);
				reg_piece.setReparation(Selectedrep);
				sreg.ajouterReg_piece(reg_piece);
			}

			for (int i = 0; i < taches.size(); i++) {
				System.out.println("get in ajout");
				Reg_tache reg_tache = new Reg_tache();
				reg_tache.setTache(taches.get(i));
				reg_tache.setUtilisateur(user);
				reg_tache.setReparation(Selectedrep);
				stache.ajouterReg_tache(reg_tache);
			}
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Données Enregistrer", "Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1:growl");
		}// fin if4

		Detaille_EtatService serde = new Detaille_EtatService();

		Etat etat = new Etat();

		ldet = serde.rechercheParAppel(Selectedapl.getIdappel());
		System.out.println("apppp : " + Selectedapl.getIdappel());

		EtatService seretat = new EtatService();
		AppelService serapl = new AppelService();

		boolean ok = true;
		boolean test = false;
		int j = 0;

		if (ldet.size() > 1) {
			while (j < ldet.size() - 1 && ok == true) {
				if (ldet.get(j).getEtat().getIdetat() != ldet.get(j + 1)
						.getEtat().getIdetat())
					ok = false;
				else
					j++;
			}
			if (ok == true) {

				Selectedapl.setEtat(seretat.rechercheParId(ldet.get(0)
						.getEtat().getIdetat()));
				serapl.modifierAppel(Selectedapl);
			}
		}

		det.setAppel(Selectedapl);
		det.setService(user.getService());

		if (modif_etat != null) {

			System.out.println("***modif_etat==" + modif_etat);
			etat = seretat.rechercheParId(modif_etat);

			// det.setIdetat(modif_etat);

			det.setEtat(etat);
		} else
			det.setEtat(Selectedapl.getEtat());

		if (ldet.size() == 0) {
			serdet.ajouterDetaille_Etat(det);
		} else {

			for (int i = 0; i < ldet.size(); i++) {
				if (ldet.get(i).getService().getIdservice() == user
						.getService().getIdservice()) {
					det.setIddetaille_etat(ldet.get(i).getIddetaille_etat());
					serdet.modifierDetaille_Etat(det);
					test = true;
				}
			}
			if (test == false)
				serdet.ajouterDetaille_Etat(det);
		}

		Selectedrep.setObservationRep(observation_rep);
		srep.modifierReparation(Selectedrep);
	}

	public void onrowEdit(CellEditEvent event) {
		qte = (Integer) event.getNewValue();
		Integer index = event.getRowIndex();
		Reg_pieceService sreg = new Reg_pieceService();
		Reg_piece regp = pieces.get(index);
		total_piece = total_piece
				+ Double.parseDouble(regp.getPiece().getPrixPce()) * qte;
		regp.setQte(qte);
		if (sreg.rechercheRegByPce(regp.getPiece().getIdpiece()).size() != 0) {
			sreg.modifierReg_piece(regp);
			RequestContext.getCurrentInstance().update("f1:piec");
			RequestContext.getCurrentInstance().update("f1:tab:totalid");
			RequestContext.getCurrentInstance().update("f1:tab:pcetotalid");
			// RequestContext.getCurrentInstance().update("f1:totaltachid");
		}

	}

	public void modifier_rep(Reg_piece regpiece) {
		idreg_piece = regpiece.getIdreg_piece();
		qte = regpiece.getQte();
	}

	public void initrep() {
		taches.clear();
		pieces.clear();
	}

	Appel aa = new Appel();

	public void init_nouv_etat(Appel a) {
		nouv_etat = a.getEtat().getIdetat();
		aa = a;
	}

	public void changer_etat() {
		EtatService sere = new EtatService();
		Etat eta = new Etat();
		eta = sere.rechercheParId(nouv_etat);
		aa.setEtat(eta);

		if (eta.getDesignationEtat().equals("Sortie")) {
			System.out
					.println("*******************date*******************************"
							+ date);
			aa.setDate_sortie(date);
		}

		AppelService sera = new AppelService();
		sera.modifierAppel(aa);
	}

	public void ajouterfourApresValidation() {
		System.out.println("entre four");

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		FournisseurService ser = new FournisseurService();
		four = new Fournisseur();
		Type_fourService serf = new Type_fourService();
		Type_four tf = new Type_four();
		tf = serf.rechercheParId(getIdtype_four());
		VilleService serville = new VilleService();
		Ville V = new Ville();
		V = serville.rechercheParId(getIdville());
		boolean addValid = false;

		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		Boolean b = true;

		if (mailfour != null && mailfour.trim().length() > 0)
			b = mailfour.matches(EMAIL_REGEX);

		if (action.equals("Ajouter")) {

			if (nomfour == null || (nomfour.trim().length() == 0)) {
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "founisseur vide",
						"Invalid credentials"));

			}

			if (idtype_four == null) {
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "type_four vide",
						"Invalid credentials"));
			}

			if (!b) {
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Format email invalide",
						"Invalid credentials"));
			}

			if (faces.getMessageList().size() == 0) {
				addValid = true;

				four.setNomfour(nomfour);
				four.setMailfour(mailfour);
				four.setAdrfour(adrfour);
				four.setGsm1four(gsm1four);
				four.setGsm2four(gsm2four);
				four.setTelfour(telfour);
				four.setFaxfour(faxfour);
				four.setVille(V);
				four.setType_four(tf);
				ser.ajouterFournisseur(four);
				RequestContext.getCurrentInstance().update("f1");
				context.execute("PF('dialogmodif1').hide();");
				initialisationFour();
			}

		}

		if (action.equals("Modifier")) {
			if (nomfour == null || (nomfour.trim().length() == 0)) {

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "fournisseur vide",
						"Invalid credentials"));
			}
			if (idtype_four == null) {
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "type_four vide",
						"Invalid credentials"));
			}
			if (!b) {
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Format email invalide",
						"Invalid credentials"));
			}

			if (faces.getMessageList().size() == 0) {

				addValid = true;
				four.setIdfournisseur(idfournisseur);
				four.setNomfour(nomfour);
				four.setMailfour(mailfour);
				four.setAdrfour(adrfour);
				four.setGsm1four(gsm1four);
				four.setGsm2four(gsm2four);
				four.setTelfour(telfour);
				four.setFaxfour(faxfour);
				four.setVille(V);
				four.setType_four(tf);
				ser.modifierFournisseur(four);
				RequestContext.getCurrentInstance().update("f1");
				initialisationFour();

			}
		}

		// context.execute("PF('dialogmodif').hide();");
		context.addCallbackParam("addValid", addValid);

	}

	public void modifierFournisseur() {
		RequestContext request = RequestContext.getCurrentInstance();
		FacesContext faces = FacesContext.getCurrentInstance();
		if (selected_four == null) {
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Il faut selectionner un fournisseur",
					"Invalid credentials"));
			request.update("f1:f2:growl");
		} else {
			request.execute("PF('dialogmodif').show();");
			action = "Modifier";
			idfournisseur = selected_four.getIdfournisseur();
			nomfour = selected_four.getNomfour();
			mailfour = selected_four.getMailfour();
			adrfour = selected_four.getAdrfour();
			gsm1four = selected_four.getGsm1four();
			gsm2four = selected_four.getGsm2four();
			telfour = selected_four.getTelfour();
			faxfour = selected_four.getFaxfour();
			if (selected_four.getVille() != null)
				idville = selected_four.getVille().getIdville();
			if (selected_four.getType_four() != null)
				idtype_four = selected_four.getType_four().getIdtype_four();
		}
	}

	public void initialisationFour() {
		idfournisseur = null;
		nomfour = null;
		mailfour = null;
		adrfour = null;
		gsm1four = null;
		gsm2four = null;
		telfour = null;
		faxfour = null;
		idtype_four = null;
		idville = null;

	}

	public void ajouterFournisseur() {
		action = "Ajouter";
		RequestContext request = RequestContext.getCurrentInstance();
		request.execute("PF('dialogmodif').show();");
		initialisationFour();

	}

	public void initialisationtf() {
		idtype_four = null;
		nomtype = null;

	}

	public void ajouterType_four() {
		action = "Ajouter";
		initialisationtf();

	}

	public void ajouterTypfourApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		Type_fourService ser = new Type_fourService();
		tf = new Type_four();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (nomtype == null || (nomtype.trim().length() == 0)) {// tester si
																	// Type_four
																	// est vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Type fourournisseur vide", "Invalid credentials"));
			}

			else // tester si ce Type_four existe déjà
			{
				System.out.println("nomtype== " + nomtype);
				typfourByNom = ser.rechercheParNom(nomtype);
				System.out.println("size liste recherche Type_four=="
						+ typfourByNom.size());
				if (typfourByNom.size() == 0) { // Type_four n'existe pas
					addValid = true;
					tf.setnomtype(nomtype);
					ser.ajouterType_four(tf);
					// RequestContext.getCurrentInstance().update("f1");
					context.execute("PF('dialogmodif1').hide();");
					initialisationtf();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Type fournisseur existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (nomtype == null || (nomtype.trim().length() == 0)) {// tester
																	// si
																	// Type_four
																	// est
																	// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Type fournisseur vide",
						"Invalid credentials"));
			} else // tester si cette ville existe déjà
			{
				typfourByNom = ser.rechercheParNom(nomtype);
				if (typfourByNom.size() == 0) { // Type_four n'existe pas
					addValid = true;
					tf.setnomtype(nomtype);
					tf.setIdtype_four(idtype_four);
					ser.modifierType_four(tf);
					// RequestContext.getCurrentInstance().update("f1");
					initialisationtf();

				} else if (typfourByNom.get(0).getIdtype_four() == idtype_four) {
					addValid = true;
					tf.setnomtype(nomtype);
					tf.setIdtype_four(idtype_four);
					ser.modifierType_four(tf);
					// RequestContext.getCurrentInstance().update("f1");
					initialisationtf();

				}

				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Type fournisseur existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	public void list() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogliste').hide();");
	}

	Appel af;

	public void initSelected_four(Appel a) {
		selected_four = null;
		af = a;
	}

	public void enregistrer_four() {

		App_four apf = new App_four();
		App_fourService apfser = new App_fourService();
		EtatService seretat = new EtatService();
		AppelService serapl = new AppelService();
		apf.setFournisseur(selected_four);
		apf.setDate_sortie(date);

		apf.setAppel(af);
		apf.setObservation(observation_four);
		apfser.ajouterApp_four(apf);
		af.setEtat(seretat.rechercheParNom("Chez_Fournisseur").get(0));
		serapl.modifierAppel(af);

	}

	Appel aaa;

	public void recup_four(Appel a) {
		montant = null;
		etat_apl = null;
		aaa = a;
		App_fourService serap = new App_fourService();
		List<App_four> apps = serap.rechercheParAppel(a.getIdappel());
		app_four = apps.get(apps.size() - 1);

		System.out.println("app_four : " + app_four);

	}

	public void retour_four() {
		FacesContext faces = FacesContext.getCurrentInstance();
		App_four app = new App_four();
		App_fourService serap = new App_fourService();
		AppelService serapl = new AppelService();
		EtatService es = new EtatService();
		System.out.println("montant : " + montant);
		if (montant == null || montant.trim().length() == 0) {
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Champ montant obligatoire",
					"Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1:f2:growl");

		}
		if (etat_apl == null) {
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Champ etat obligatoire",
					"Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1:f2:growl");

		}
		if (montant != null && etat_apl != null && montant.trim().length() != 0) {
			app.setIdapl_four(app_four.getIdapl_four());
			app.setMontant(montant);
			app.setDate_retour(date_retour);
			app.setAppel(app_four.getAppel());
			app.setDate_sortie(app_four.getDate_sortie());
			app.setObservation(app_four.getObservation());
			app.setFournisseur(app_four.getFournisseur());
			serap.modifierApp_four(app);
			Appel a = aaa;
			a.setEtat(es.rechercheParId(etat_apl));
			serapl.modifierAppel(a);
			RequestContext.getCurrentInstance().update("f1");
		}
	}

	public boolean setrenderedenvoi(Appel a) {
		if (a.getEtat().getDesignationEtat().equals("Chez_Fournisseur"))
			return false;
		else
			return true;
	}

	public boolean setrenderedrecev(Appel a) {
		if (a.getEtat().getDesignationEtat().equals("Chez_Fournisseur"))
			return true;
		else
			return false;
	}

	public void ajoutDetailEtat() {

		List<App_panne> laplpann = new ArrayList<App_panne>(0);
		List<Appel> lapls = new ArrayList<Appel>(0);
		List<Service> services = new ArrayList<Service>(0);
		List<Etat> etats = new ArrayList<Etat>(0);
		AppelService aplSer = new AppelService();
		EtatService etatSer = new EtatService();
		App_panneService apl_panServ = new App_panneService();
		Detaille_EtatService detEtatSer = new Detaille_EtatService();

		// recupère la dernière apl ajoutée
		lapls = aplSer.rechercheTousAppel();
		// int size=lapls.size();
		Appel apl = new Appel();
		apl = lapls.get(0);

		idappel = apl.getIdappel();
		laplpann = apl_panServ.rechercheParAppel(idappel);

		etats = etatSer.rechercheParNom("EnAttente");
		Etat etat = new Etat();
		etat = etats.get(0);

		for (int i = 0; i < laplpann.size(); i++) {
			Service ser = laplpann.get(i).getPanne().getService();
			if (!services.contains(ser))
				services.add(ser);
		}

		for (int i = 0; i < services.size(); i++) {

			Detaille_Etat det = new Detaille_Etat();
			det.setAppel(apl);
			det.setService(services.get(i));
			det.setEtat(etat);
			detEtatSer.ajouterDetaille_Etat(det);

		}
	}

	// récuperer liste des apl filtré dans une liste des objet de type AplImp
	// pour l'imprimer
	public void getaplImp() {

		for (int f = 0; f < appels.size(); f++) {
			//System.out.println("----------i===-------" + f + "  "
					//+ appels.get(f));
		
			AplImp aplimp = new AplImp();
			if(!attribut.equals("--Selectionner--")){
				
				aplimp.setFiltreAttribu(attribut);
			}
			
			if(date_debut != null){
				
				aplimp.setDateDeb(date_debut);
			}
			
	    if(date_fin != null){
				
				aplimp.setDateFin(date_fin);
			}
	    
	    if(selectedetat != null){
			
			aplimp.setFitreEtat(selectedetat);
		}
	    
			
			aplimp.setClt(appels.get(f).getClient().getNomclt());
			aplimp.setNumApl(appels.get(f).getIdappel());
			aplimp.setNumSerie(appels.get(f).getMachine().getNum_serie());
			aplimp.setDate_entre(appels.get(f).getDate_entre());
			aplimp.setEtat(appels.get(f).getEtat().getDesignationEtat());
			aplimp.setGsm(appels.get(f).getClient().getGsm1clt() + "  "
					+ appels.get(f).getClient().getGsm2clt());
			aplImps.add(aplimp);

		}

	}

	public void viewListApl(ActionEvent actionEvent) throws SQLException,
			Exception {

		getaplImp();

		AplImpService aplImpSer = new AplImpService();
		for (int f = 0; f < aplImps.size(); f++) {
			//System.out.println("----------i===-------" + f + "  "
					//+ aplImps.get(f));

			aplImpSer.ajouterAplImp(aplImps.get(f));

		}

		Connection connection = (Connection) DriverManager.getConnection(
				"jdbc:mysql://localhost/sav", "root", "root");
		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/Report/aplListFiltre.jasper"));

		byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null,
				connection);
		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(bytes, 0, bytes.length);
		outStream.flush();
		outStream.close();
		FacesContext.getCurrentInstance().responseComplete();

		for (int f = 0; f < aplImps.size(); f++) {

			aplImpSer.supprimerAplImp(aplImps.get(f).getIdAplImp());

		}

	}

	public void enregisterDiag() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext faces = FacesContext.getCurrentInstance();
		DiagnostiqueService diagSer = new DiagnostiqueService();
		Diag_panneService dpSer = new Diag_panneService();
		diagnostique = new Diagnostique();
		List<Diagnostique> ldiag = new ArrayList<Diagnostique>();

		ldiag = diagSer.rechercheTousDiagnostique();
		diagnostique = ldiag.get(ldiag.size() - 1);

		for (int i = 0; i < selectedDiagpans.size(); i++) {
			Diag_panne diag_pan = new Diag_panne();
			diag_pan.setDiagnostique(diagnostique);
			diag_pan.setPanne(selectedDiagpans.get(i));
			dpSer.ajouterDiag_panne(diag_pan);

		}

		diagnostique.setNote(note_diag);
		diagnostique.setRecmd(recumendation);
		diagnostique.setIddiag(diagnostique.getIddiag());

		diagSer.modifieridDiagnostique(diagnostique);

		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Données Enregistrer avec succès", "Invalid credentials"));
		RequestContext.getCurrentInstance().update("f1:growl");

	}

	public void validerDiag() {

		AppelService aplSer = new AppelService();
		Appel ap = new Appel();
		System.out.println(diagnostique);
		int idap = diagnostique.getAppel().getIdappel();
		ap = aplSer.rechercheParId(idap);

		System.out.println("id apl de diagnostique" + ap.getIdappel());
		ap.setDiag(false);
		ap.setIdappel(ap.getIdappel());
		aplSer.modifierAppel(ap);

		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("GestionAppel.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	public void viewBonReparation(ActionEvent actionEvent) throws SQLException,
	Exception {
		
		System.out.println("id appel de limpression de bon de reparation:  "+idReparation);
		

Connection connection = (Connection) DriverManager.getConnection(
		"jdbc:mysql://localhost/sav", "root", "root");
File jasper = new File(FacesContext.getCurrentInstance()
		.getExternalContext()
		.getRealPath("/Report/bon Reparation2.jasper"));

Map<String, Object> param = new HashMap<String, Object>();
param.put("idRep",idReparation);
byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), param,
		connection);
HttpServletResponse response = (HttpServletResponse) FacesContext
		.getCurrentInstance().getExternalContext().getResponse();
response.setContentType("application/pdf");
response.setContentLength(bytes.length);
ServletOutputStream outStream = response.getOutputStream();
outStream.write(bytes, 0, bytes.length);
outStream.flush();
outStream.close();
FacesContext.getCurrentInstance().responseComplete();



}
	
public void getIdRepAvecIdapl(Integer idApl) throws SQLException, Exception	{

	FacesContext faces = FacesContext.getCurrentInstance();
	System.out.println("id apl =======>>> "+idApl);
	ReparationService repSer= new ReparationService();
	repParIdapl=repSer.rechercheParAppel(idApl);
	if(repParIdapl.size()==0){
		faces.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_ERROR,
				"Appel non réparé", "Invalid credentials"));
	}else{
	  idReparation= repParIdapl.get(0).getIdreparation();
	  viewBonReparation(null);
	}
	
}
	
	
	
	
	
	
	
	
	
	
}
