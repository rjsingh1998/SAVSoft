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
import com.sav.persistance.App_panne;
import com.sav.persistance.Reg_tache;
import com.sav.persistance.Tache;
import com.sav.service.App_panneService;
import com.sav.service.PanneService;
import com.sav.service.Reg_tacheService;
import com.sav.service.TacheService;



@ManagedBean(name = "tacheBean")
@SessionScoped
public class TacheBean {

	private Integer idtache;
	private String designationTach;
	private String prixTach;
	private String valeurRecherche;
	private String attribut;
	
	
	
	private List<Tache> taches = new ArrayList<Tache>(0);
	
	private List<Tache> tacheByNom = new ArrayList<Tache>(0);
	
	public String getAttribut() {
		return attribut;
	}
	public void setAttribut(String attribut) {
		this.attribut = attribut;
	}
	
	
	public String getValeurRecherche() {
		return valeurRecherche;
	}



	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}

	private List<String> listesRecherches = new ArrayList<String>();
	
	
	
	
	

	public List<String> getListesRecherches() {
		
		listesRecherches.clear();
		listesRecherches.add("--Selectionner--");
		listesRecherches.add("Designation");
		listesRecherches.add("Prix");
		
		return listesRecherches;
	}
	public void setListesRecherches(List<String> listesRecherches) {
		this.listesRecherches = listesRecherches;
	}
	public Integer getIdtache() {
		return idtache;
	}



	public String getPrixTach() {
		return prixTach;
	}


	public void setPrixTach(String prixTach) {
		this.prixTach = prixTach;
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




	public List<Tache> getTaches() {
		TacheService ser = new TacheService();
		if ((valeurRecherche!=null) && (attribut!=null))
			taches = ser.rechercheFiltre(valeurRecherche,attribut);
		else
		taches = ser.rechercheTousTache();
		return taches;
		
	}




	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}

	private String action;
	
	
	public void modifierTache (Tache T) {
		action="Modifier";
		System.out.println("id  "+idtache);
		idtache = T.getIdtache();
		designationTach = T.getDesignationTach();
		prixTach = T.getPrixTach();
	
	}
		
		
		
		
	public void validation() {
		TacheService ser = new TacheService();
		Tache T = new Tache();
		T.setDesignationTach(designationTach);
		T.setPrixTach(prixTach);
		System.out.println(action);	
		
		if(action.equals("Modifier"))
		   {
			System.out.println("c'est une modification");
			T.setIdtache(idtache);
			ser.modifierTache(T);
		   }
	if (action.equals("Ajouter"))
	
	   ser.ajouterTache(T);
	   initialisation();
	}
	
	
	public void initialisation (){
		idtache = null;
		designationTach = null;
		prixTach = null;
		
	}
	
	public void ajouterTache(){
	action="Ajouter";
	initialisation();
	
	}

	public void Supprimer(Integer id) {
		TacheService ser = new TacheService();
		
		FacesContext faces = FacesContext.getCurrentInstance();
		Reg_tacheService regTachSer = new Reg_tacheService();
		List<Reg_tache> listeAppTach = regTachSer.rechercheRegByTach(id);
		
		if(listeAppTach.size()>0  ){
			System.out.println("suppression tache impossible, tache utilisée");
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "suppression tache impossible, tache utilisée",
					"Invalid credentials"));
		}
		else{
			ser.supprimerTache(id);
		    RequestContext.getCurrentInstance().update("f1");
		    faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Suppression Tâche avec succès",
					"Invalid credentials"));
		
		}
		
		
		
	}

	
	public void annulerRecherche() {
		valeurRecherche = null;
		attribut="--Selectionner--";
	}
	
	
	public void ajouterTachApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		TacheService ser = new TacheService();
		Tache T = new Tache();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationTach == null || (designationTach.trim().length() == 0)) {// tester
																					// si
																					//tache
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "tache vide",
						"Invalid credentials"));
			}
			
			if(prixTach!= null){
				
				try{
					double prit=  Double.parseDouble(prixTach);
				}catch(NumberFormatException ex){
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Prix tache invalide",
							"Invalid credentials"));
					}
			}

			if(designationTach!= null && faces.getMessageList().size() == 0 ) // tester si ce tache existe déjà
			{
				tacheByNom = ser.rechercheParNom(designationTach);
				
				
				
				if (tacheByNom.size() == 0 ) { // tache n'existe pas
					addValid = true;
					T.setDesignationTach(designationTach);
					T.setPrixTach(prixTach);
					ser.ajouterTache(T);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Ajout Tâche avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

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
			if (designationTach == null || (designationTach.trim().length() == 0)) {// tester
																					// si
																					// tache
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "tache vide",
						"Invalid credentials"));
			}
			
           if(prixTach!= null){
				
				try{
					double prit=  Double.parseDouble(prixTach);
				}catch(NumberFormatException ex){
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Prix tache invalide",
							"Invalid credentials"));
					}
			}
			
           if(designationTach!= null && faces.getMessageList().size() == 0 )// tester si cette tache existe déjà
			{
				tacheByNom = ser.rechercheParNom(designationTach);
				
				
				
				if (tacheByNom.size() == 0 ) { // tache n'existe pas
					addValid = true;
					T.setDesignationTach(designationTach);
					T.setIdtache(idtache);
					T.setPrixTach(prixTach);
					ser.modifierTache(T);
					
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Modification Tâche avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					
					initialisation();

				}  else if(tacheByNom.get(0).getIdtache() == idtache ){
					
					addValid = true;
					T.setDesignationTach(designationTach);
					T.setIdtache(idtache);
					T.setPrixTach(prixTach);
					ser.modifierTache(T);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Modification Tâche avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					
					initialisation();
				
				
				
				}
				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "tache existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}
	
	
	
	public void viewListeTache (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/tacheRapport.jasper"));
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

	

	



	
