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
import com.sav.persistance.Appel;
import com.sav.persistance.Etat;
import com.sav.service.AppelService;
import com.sav.service.EtatService;





@ManagedBean(name = "etatBean")
@SessionScoped
public class EtatBean {

	private Integer idetat;
	private String designationEtat;
	
	private List<Etat> etats = new ArrayList<Etat>(0);
	private List<Etat> etatByNom = new ArrayList<Etat>(0);
	private String valeurRecherche;
	

	public String getValeurRecherche() {
		return valeurRecherche;
	}




	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}




	public List<Etat> getEtats() {
		EtatService ser = new EtatService();
		if(valeurRecherche!=null)
			etats=ser.rechercheFiltre(valeurRecherche);
		else
			etats= ser.rechercheTousEtat();
		return etats;
	}




	public void setEtats(List<Etat> etats) {
		this.etats = etats;
	}


	public void annulerRecherche() {
		valeurRecherche = null;
		
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




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}

	private String action;
	
	
	public void modifierEtat (Etat et) {
		action="Modifier";
		
		idetat = et.getIdetat();
		designationEtat = et.getDesignationEtat();
		
	
	}
		
		
		
		
	public void validation() {
		EtatService ser = new EtatService();
		Etat e = new Etat();
		e.setDesignationEtat(designationEtat);
		
		System.out.println(action);	
		
		if(action.equals("Modifier"))
		   {
			System.out.println("c'est une modification");
			e.setIdetat(idetat);
			ser.modifierEtat(e);
		   }
	if (action.equals("Ajouter"))
	
	   ser.ajouterEtat(e);
	   initialisation();
	}
	
	
	public void initialisation (){
		idetat = null;
		designationEtat = null;

		
	}
	
	public void ajouterEtat(){
	action="Ajouter";
	initialisation();
	
	}

	public void Supprimer(Integer id) {
		FacesContext faces = FacesContext.getCurrentInstance();
		EtatService ser = new EtatService();
		AppelService aplSer= new AppelService();
		List<Appel> l= aplSer.rechercheParEtat(id);
		System.out.println("l============>"+l.size());
		if(l.size() >0 ){
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Suppression etat impossible, etat utilisé",
					"Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1");
		}else{
			 
			ser.supprimerEtat(id);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Etat supprimé avec succès",
					"Invalid credentials"));
			 RequestContext.getCurrentInstance().update("f1");
		}
		
	}

	
	
	public void ajouterMrqApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		EtatService ser = new EtatService();
		Etat e= new Etat();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationEtat== null || (designationEtat.trim().length() == 0)) {// tester
																					// si
																					// etat
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Etat vide",
						"Invalid credentials"));
			}

			else // tester si ce marque existe déjà
			{
				etatByNom = ser.rechercheParNom(designationEtat);
				System.out.println("size liste recherche etat=="
						+ etatByNom.size());
				if (etatByNom.size() == 0) { // etat n'existe pas
					addValid = true;
					e.setDesignationEtat(designationEtat);
					ser.ajouterEtat(e);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Etat ajouté avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Etat existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationEtat == null || (designationEtat.trim().length() == 0)) {// tester
																					// si
																					// etat
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Etat vide",
						"Invalid credentials"));
			} else // tester si cette etat existe déjà
			{
				etatByNom = ser.rechercheParNom(designationEtat);
				if (etatByNom.size() == 0) { // etat n'existe pas
					addValid = true;
					e.setDesignationEtat(designationEtat);
					e.setIdetat(idetat);
					ser.modifierEtat(e);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Etat modifié avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
				}
					else if(etatByNom.get(0).getIdetat()== idetat){
						addValid = true;
						e.setDesignationEtat(designationEtat);
						e.setIdetat(idetat);
						ser.modifierEtat(e);
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Etat modifié avec succès",
								"Invalid credentials"));
						RequestContext.getCurrentInstance().update("f1");
						initialisation(); 
					
				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Etat existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}
	
		
	public void viewListeEtat (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/etatReport.jasper"));
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

	

	



	