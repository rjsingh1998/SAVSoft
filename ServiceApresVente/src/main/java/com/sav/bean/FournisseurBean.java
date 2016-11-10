package com.sav.bean;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

import org.primefaces.context.RequestContext;

import com.mysql.jdbc.Connection;
import com.sav.persistance.App_four;
import com.sav.persistance.Fournisseur;
import com.sav.persistance.Type_four;
import com.sav.persistance.Ville;
import com.sav.service.App_fourService;
import com.sav.service.FournisseurService;
import com.sav.service.Type_fourService;
import com.sav.service.VilleService;


@ManagedBean(name = "fournisseurBean")
@SessionScoped
public class FournisseurBean {

	private Integer idfournisseur;
	private String nomfour;
	private String adrfour;
	private String mailfour;
	private String gsm1four;
	private String gsm2four;
	private String telfour;
	private String faxfour;
	private String valeurRecherche;
	private String attribut;
	Type_four tf=null;
	Ville v=null;
	private Type_four type_four;
	private Integer idtype_four;
	
	private String nomtype;
	
	private Ville ville;
	private Integer idville;
	private String designationVille;
	
	private List<Fournisseur> fourByNom = new ArrayList<Fournisseur>(0);

	private List<Type_four> typfourByNom = new ArrayList<Type_four>(0);
	private List<String> listesRecherches = new ArrayList<String>();
	private List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>(0);
	private String action;
	
	private List<Ville> villes = new ArrayList<Ville>(0);
	
	private List<Type_four> type_fours = new ArrayList<Type_four>(0);
	
	private List<Ville> villeByNom = new ArrayList<Ville>(0);
	
	
	
	
	
	
	public String getNomtype() {
		return nomtype;
	}
	public void setNomtype(String nomtype) {
		this.nomtype = nomtype;
	}
	public Type_four getType_four() {
		return type_four;
	}
	public void setType_four(Type_four type_four) {
		this.type_four = type_four;
	}
	public Integer getIdtype_four() {
		if (tf!=null) {
			idtype_four=tf.getIdtype_four();
			tf=null;}
			
		return idtype_four;
	}
	public void setIdtype_four(Integer idtype_four) {
		this.idtype_four = idtype_four;
	}
	public List<Type_four> getType_fours() {
		Type_fourService ser = new Type_fourService();
		type_fours = ser.rechercheTousType_four();
		
		return type_fours;
	}
	public void setType_fours(List<Type_four> type_fours) {
		this.type_fours = type_fours;
	}
	
	
	public List<Ville> getVilles() {
		VilleService ser = new VilleService();
		villes = ser.rechercheTousVille();
		return villes;
	}
	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}
	public String getDesignationVille() {
		return designationVille;
	}
	public void setDesignationVille(String designationVille) {
		this.designationVille = designationVille;
	}
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	public Integer getIdville() {
		if (v!=null) {
			idville=v.getIdville();
			v=null;}
		return idville;
	}
	public void setIdville(Integer idville) {
		this.idville = idville;
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
		listesRecherches.add("--Selectionner--");
		listesRecherches.add("Fournisseur");
		listesRecherches.add("Gsm");
		listesRecherches.add("Tel");
		return listesRecherches;
	}
	public void setListesRecherches(List<String> listesRecherches) {
		this.listesRecherches = listesRecherches;
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
	public String getAdrfour() {
		return adrfour;
	}
	public void setAdrfour(String adrfour) {
		this.adrfour = adrfour;
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
	public List<Fournisseur> getFournisseurs() {
		FournisseurService ser = new FournisseurService();
		if ((valeurRecherche != null) && (attribut != null))
			fournisseurs = ser.rechercheFiltre(attribut, valeurRecherche);
		else
			//fournisseurs = ser.rechercheTousFournisseur();
	    fournisseurs = ser.rechercheTousFournisseurAvecJointure();

		return fournisseurs;
	}
	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}



	public void modifierFournisseur (Fournisseur f) {
		action="Modifier";
		idfournisseur = f.getIdfournisseur();
		nomfour = f.getNomfour();
		mailfour= f.getMailfour();
		adrfour = f.getAdrfour();
		gsm1four= f.getGsm1four();
		gsm2four= f.getGsm2four();
		telfour= f.getTelfour();
		faxfour= f.getFaxfour();
		if(f.getVille()!= null)
			idville=f.getVille().getIdville();
		if(f.getType_four()!= null)
			idtype_four=f.getType_four().getIdtype_four();
	}
		
		
		
		
	public void validation() {
		FournisseurService ser = new FournisseurService();
		Fournisseur f = new Fournisseur();
		
		
		
		Type_fourService sertype_four = new Type_fourService();
		Type_four tf =new Type_four();
		tf=sertype_four.rechercheParId(getIdtype_four());
		
		if(idville!= null){
			VilleService serville = new VilleService();
			Ville v =new Ville();
			v=serville.rechercheParId(getIdville());
			f.setVille(v);
			}
		
		f.setNomfour(nomfour);
		f.setMailfour(mailfour);
		f.setAdrfour(adrfour);
		f.setGsm1four(gsm1four);
		f.setGsm2four(gsm2four);
		f.setTelfour(telfour);
		f.setFaxfour(faxfour);
		
		f.setType_four(tf);
		if(action.equals("Modifier"))
		   {
			System.out.println("c'est une modification");
			f.setIdfournisseur(idfournisseur);
			ser.modifierFournisseur(f);
		   }
	if (action.equals("Ajouter"))
		System.out.println("action ajouter");
	   ser.ajouterFournisseur(f);
	   initialisation();
	}
	
	
	public void initialisation (){
		idfournisseur = null;
		nomfour = null;
		mailfour= null;
		adrfour = null;
		gsm1four= null;
		gsm2four= null;
		telfour= null;
		faxfour= null;
		idtype_four = null;
		idville = null;
		
	}
	
	public void ajouterFournisseur(){
	action="Ajouter";
	initialisation();

	
	}

	
	
	public void initialisationtf (){
		idtype_four = null;
		nomtype = null;
		
	}
	
	public void ajouterType_four(){
	action="Ajouter";
	initialisationtf();
	
	}
	public void initialisationv (){
		idville = null;
		designationVille = null;
		
		
	}
	  
	public void ajouterVille(){
		System.out.println("ville ajouter");
	action="Ajouter";
	initialisationv();
	
	}
	
	
	
	public void Supprimer(Integer id) {
		
		
		FacesContext faces = FacesContext.getCurrentInstance();
		FournisseurService ser = new FournisseurService();
		
		App_fourService appFourSer = new App_fourService();
		
		List<App_four> listeAppFour = appFourSer.rechercheAplByFour(id);
		System.out.println("size table=="+listeAppFour.size());
		
		if(listeAppFour.size()>0  ){
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Suppression fournisseur impossible, fournisseur utilisé",
					"Invalid credentials"));
		}
		else{
			ser.supprimerFournisseur(id);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Fournisseur supprimé avec succès",
					"Invalid credentials"));
		    RequestContext.getCurrentInstance().update("f1");
		
		}
		
		
		
		
		
	
	}

	
	public void annulerRecherche() {
		valeurRecherche = null;
		attribut="--Selectionner--";
	}
	public void ajouterTypfourApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		Type_fourService ser = new Type_fourService();
		 tf = new Type_four();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (nomtype == null || (nomtype.trim().length() == 0)) {// tester si Type_four est vide
																				
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Type fourournisseur vide",
						"Invalid credentials"));
			}

			else // tester si ce Type_four existe déjà
			{
				System.out.println("nomtype== "+nomtype);
				typfourByNom = ser.rechercheParNom(nomtype);
				System.out.println("size liste recherche Type_four=="
						+ typfourByNom.size());
				if (typfourByNom.size() == 0) { // Type_four n'existe pas
					addValid = true;
					tf.setnomtype(nomtype);
					ser.ajouterType_four(tf);
				//	RequestContext.getCurrentInstance().update("f1");
					context.execute("PF('dialogmodif1').hide();");
					initialisationtf();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type fournisseur existe déja",
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
					RequestContext.getCurrentInstance().update("f1");
					initialisationtf();

				}else if(typfourByNom.get(0).getIdtype_four()== idtype_four){
					addValid = true;
					tf.setnomtype(nomtype);
					tf.setIdtype_four(idtype_four);
					ser.modifierType_four(tf);
					RequestContext.getCurrentInstance().update("f1");
					initialisationtf();
					
				}
				
				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type fournisseur existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}
	public void ajouterVilApresValidation() {
		System.out.println("entr");

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		VilleService ser = new VilleService();
		v = new Ville();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

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
					context.execute("PF('dialogmodif2').hide();");
					initialisationv();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Ville existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationVille == null || (designationVille.trim().length() == 0)) {// tester
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
				if (villeByNom.size() == 0  ) { // ville n'existe pas
					addValid = true;
					v.setDesignationVille(designationVille);
					v.setIdville(idville);
					ser.modifierVille(v);
					RequestContext.getCurrentInstance().update("f1");
					initialisationv();
				
						

				}else if(villeByNom.get(0).getIdville() == idville ){
					
					addValid = true;
					v.setDesignationVille(designationVille);
					v.setIdville(idville);
					ser.modifierVille(v);
					RequestContext.getCurrentInstance().update("f1");
					initialisationv();
					
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
	
	
	public void ajouterfourApresValidation() {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		FournisseurService ser = new FournisseurService();
		Fournisseur f = new Fournisseur();
		Type_fourService serf = new Type_fourService();
		Type_four tf = new Type_four();
		tf=serf.rechercheParId(getIdtype_four());
		//VilleService serville = new VilleService();
		//Ville V = new Ville();
		//V=serville.rechercheParId(getIdville());
		boolean addValid = false;
		
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		Boolean b =true;
		
		if(mailfour != null && mailfour.trim().length() > 0) 
		    b=mailfour.matches(EMAIL_REGEX);

		if (action.equals("Ajouter")) {

			if (nomfour == null || (nomfour.trim().length() == 0)) {
			addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "founisseur vide",
						"Invalid credentials"));
				
			}
			
			if (idtype_four == null ){
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "type_four vide",
						"Invalid credentials"));
			}
			
			if(!b){
	        	   addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Format email invalide",
							"Invalid credentials"));
				}

			if (faces.getMessageList().size() == 0){
					addValid = true;
					
					if(idville!= null){
						VilleService serville = new VilleService();
						Ville v =new Ville();
						v=serville.rechercheParId(getIdville());
						f.setVille(v);
						}
					
					f.setNomfour(nomfour);
					f.setMailfour(mailfour);
					f.setAdrfour(adrfour);
					f.setGsm1four(gsm1four);
					f.setGsm2four(gsm2four);
					f.setTelfour(telfour);
					f.setFaxfour(faxfour);
					
					f.setType_four(tf);
					ser.ajouterFournisseur(f);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Fournisseur ajouté avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					context.execute("PF('dialogmodif1').hide();");
					initialisation();
			}

			
			
		}
		

		if (action.equals("Modifier")) {
			if (nomfour == null || (nomfour.trim().length() == 0)) {

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "fournisseur vide",
						"Invalid credentials"));
			} 
			if (idtype_four == null ){
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "type_four vide",
						"Invalid credentials"));
			}
			 if(!b){
	        	   addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Format email invalide",
							"Invalid credentials"));
				}
			
			
			 if (faces.getMessageList().size() == 0) {
			
					addValid = true;
					if(idville!= null){
						VilleService serville = new VilleService();
						Ville v =new Ville();
						v=serville.rechercheParId(getIdville());
						f.setVille(v);
						}
					
					f.setIdfournisseur(idfournisseur);
					f.setNomfour(nomfour);
					f.setMailfour(mailfour);
					f.setAdrfour(adrfour);
					f.setGsm1four(gsm1four);
					f.setGsm2four(gsm2four);
					f.setTelfour(telfour);
					f.setFaxfour(faxfour);
					
					f.setType_four(tf);
					ser.modifierFournisseur(f);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Fournisseur modifié avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
				
						

				}
				
          
				
				
				
				
				
				
			}
		

		context.addCallbackParam("addValid", addValid);

	}
	

	
public void viewListeFournisseur (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/fournisseurReport.jasper"));
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

}

	

	



	