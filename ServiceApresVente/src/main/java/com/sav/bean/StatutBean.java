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
import com.sav.persistance.App_stat;
import com.sav.persistance.Statut;
import com.sav.service.App_panneService;
import com.sav.service.App_statService;
import com.sav.service.PanneService;
import com.sav.service.StatutService;


@ManagedBean(name = "statutBean")
@SessionScoped
public class StatutBean {

	private Integer idstatut;
	private String designationStat;
	
	private String valeurRecherche;
	
	
	
	private List<Statut> statByNom = new ArrayList<Statut>(0);
	private List<Statut> statuts = new ArrayList<Statut>(0);
	
	
	
	
	public String getValeurRecherche() {
		return valeurRecherche;
	}



	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}



	public Integer getIdstatut() {
		return idstatut;
	}



	


	public void setIdstatut(Integer idstatut) {
		this.idstatut = idstatut;
	}


	public String getDesignationStat() {
		return designationStat;
	}


	public void setDesignationStat(String designationStat) {
		this.designationStat = designationStat;
	}




	public List<Statut> getStatuts() {
		StatutService ser = new StatutService();
		if(valeurRecherche!=null)
			statuts = ser.rechercheFiltre(valeurRecherche);
		else
		statuts = ser.rechercheTousStatut();
		return statuts;
	}




	public void setStatuts(List<Statut> statuts) {
		this.statuts = statuts;
	}




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}

	private String action;
	
	
	public void modifierStatut (Statut st) {
		action="Modifier";
		System.out.println("id  "+idstatut);
		idstatut = st.getIdstatut();
		designationStat = st.getDesignationStat();
		
	
	}
		
		
		
		
	public void validation() {
		StatutService ser = new StatutService();
		Statut st = new Statut();
		st.setDesignationStat(designationStat);
		
		System.out.println(action);	
		
		if(action.equals("Modifier"))
		   {
			System.out.println("c'est une modification");
			st.setIdstatut(idstatut);
			ser.modifierStatut(st);
		   }
	if (action.equals("Ajouter"))
	
	   ser.ajouterStatut(st);
	   initialisation();
	}
	
	
	public void initialisation (){
		idstatut = null;
		designationStat = null;
		
		
	}
	
	public void ajouterStatut(){
	action="Ajouter";
	initialisation();
	
	}

	public void Supprimer(Integer id) {
		StatutService ser = new StatutService();
		FacesContext faces = FacesContext.getCurrentInstance();
		App_statService appStatSer = new App_statService();
		List<App_stat> listeStatPan = appStatSer.rechercheAplByStat(id);
		
		if(listeStatPan.size()>0  ){
			System.out.println("suppression stat impossible, stat utilisée");
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "suppression statut impossible, statut utilisé",
					"Invalid credentials"));
		}
		else{
			
			ser.supprimerStatut(id);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Statut supprimé avec succès",
					"Invalid credentials"));
		    RequestContext.getCurrentInstance().update("f1");
		
		}
		
	}

	
	public void annulerRecherche() {
		valeurRecherche = null;
		
	}
	
	public void ajouterStatApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		StatutService ser = new StatutService();
		Statut s = new Statut();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationStat == null || (designationStat.trim().length() == 0)) {// tester
																					// si
																					// statut
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Statut vide",
						"Invalid credentials"));
			}

			else // tester si ce statut existe déjà
			{
				statByNom = ser.rechercheParNom(designationStat);
				System.out.println("size liste recherche statut=="
						+ statByNom.size());
				if (statByNom.size() == 0) { // statut n'existe pas
					addValid = true;
					s.setDesignationStat(designationStat);
					ser.ajouterStatut(s);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Statut ajouté avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Statut existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationStat == null || (designationStat.trim().length() == 0)) {// tester
																					// si
																					// statut
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Statut vide",
						"Invalid credentials"));
			} else // tester si cette ville existe déjà
			{
				statByNom = ser.rechercheParNom(designationStat);
				if (statByNom.size() == 0) { // statut n'existe pas
					addValid = true;
					s.setDesignationStat(designationStat);
					s.setIdstatut(idstatut);
					ser.modifierStatut(s);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Statut modifié avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else if(statByNom.get(0).getIdstatut()==idstatut){
					addValid = true;
					s.setDesignationStat(designationStat);
					s.setIdstatut(idstatut);
					ser.modifierStatut(s);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Statut modifié avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
					
				}
				
				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Statut existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}
	
	public void viewListeStatut (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/statutReport.jasper"));
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



