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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

import org.primefaces.context.RequestContext;

import com.mysql.jdbc.Connection;
import com.sav.persistance.Panne;
import com.sav.persistance.App_fil;
import com.sav.persistance.Fil_att;
import com.sav.persistance.Service;
import com.sav.service.PanneService;
import com.sav.service.Fil_attService;
import com.sav.service.ServiceService;

@ManagedBean(name = "serviceBean")
@SessionScoped
public class ServiceBean {

	private Integer idservice;
	private String designationSer;
	private String valeurRecherche;
	private Integer idfil_att;
	private Integer idf;
	private List<Service> serviceByNom = new ArrayList<Service>(0);
	private Set<App_fil> app_fils = new HashSet<App_fil>(0);
	
	
	
	
	public Integer getIdfil_att() {
		return idfil_att;
	}



	public void setIdfil_att(Integer idfil_att) {
		this.idfil_att = idfil_att;
	}



	public Set<App_fil> getApp_fils() {
		return app_fils;
	}



	public void setApp_fils(Set<App_fil> app_fils) {
		this.app_fils = app_fils;
	}



	public String getValeurRecherche() {
		return valeurRecherche;
	}



	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}



	
	private List<Service> services = new ArrayList<Service>(0);
	public Integer getIdservice() {
		return idservice;
	}




	public void setIdservice(Integer idservice) {
		this.idservice = idservice;
	}




	public String getDesignationSer() {
		return designationSer;
	}




	public void setDesignationSer(String designationSer) {
		this.designationSer = designationSer;
	}

	public List<Service> getServices() {
		ServiceService ser = new ServiceService();
		if(valeurRecherche!=null)
			services = ser.rechercheFiltre(valeurRecherche);
		else
	
		services = ser.rechercheTousService();
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	private String action;
	
	
	
	public void modifierService (Service s) {
		action="Modifier";
		System.out.println("id  "+idservice);
		idservice = s.getIdservice();
		designationSer = s.getDesignationSer();
	
	}
		
		
		
		
	public void validation() {
		ServiceService ser = new ServiceService();
		Service s = new Service();
		s.setDesignationSer(designationSer);
		System.out.println(action);	
		
		if(action.equals("Modifier"))
		   {
			System.out.println("c'est une modification");
			s.setIdservice(idservice);
			ser.modifierService(s);
		   }
	if (action.equals("Ajouter"))
	System.out.println("fil et sezr ajouter");
		
	
	   ser.ajouterService(s);
	   initialisation();
	}
	
	
	public void initialisation (){
		idservice = null;
		designationSer = null;
		
	}
	
	public void ajouterService(){
	action="Ajouter";
	initialisation();
	
	}

	public void Supprimerf(Integer id){
		
		Fil_attService serf = new Fil_attService();
		serf.supprimerFil_att(id);
	}
	
	
	
	public void Supprimer(Integer id) {

		FacesContext faces = FacesContext.getCurrentInstance();
		System.out.println("supp ser ");
		Fil_attService serf = new Fil_attService();
		List<Fil_att> listeFil =  serf.rechercheserParSer(id);
		idf = listeFil.get(0).getIdfil_att();
		System.out.println("idfil="+ idf);
		
		/*if(listeFil.size()>0)
		{ System.out.println("fil suppp");
			serf.supprimerFil_att(idf);
		//Supprimerf(id);
			}*/
			
		ServiceService ser = new ServiceService();
		
		
		PanneService serPan = new PanneService();
		List<Panne> listePan =  serPan.recherchePanParIdSrvc(id);
		
		
		if(listePan.size()>0  ){
			System.out.println("supressopn srvc impossible");
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Suppression impossible, service existe dans la table panne",
					"Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1");
		}
		else{
			serf.supprimerFil_att(idf);
			ser.supprimerService(id);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Service supprimée avec succès",
					"Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1");
		
		}
		
	}


	
	public void annulerRecherche() {
		valeurRecherche = null;
		
	}
	
	public void ajouterSevceApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		ServiceService ser = new ServiceService();
		Service s = new Service();
		

		Fil_attService serf = new Fil_attService();
		Fil_att f = new Fil_att();
		
		
		
		boolean addValid = false;

		if (action.equals("Ajouter")) {

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
				
				if (serviceByNom.size() == 0) { 
					addValid = true;
					s.setDesignationSer(designationSer);
					ser.ajouterService(s);
					//f.setIdfil_att(idfil_att);
					f.setService(s);
					
					 serf.ajouterFil_att(f);
					 faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Service ajoutée avec succès",
								"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Sevice existe déja",
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
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Service modifiée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else if(serviceByNom.get(0).getIdservice()==idservice){
					addValid = true;
					s.setDesignationSer(designationSer);
					s.setIdservice(idservice);
					ser.modifierService(s);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Service modifiée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
					
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
	
	
	public void viewListeService (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/serviceReport.jasper"));
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

	

	



	
