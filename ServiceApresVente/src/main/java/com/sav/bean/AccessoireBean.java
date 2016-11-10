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
import com.sav.persistance.Accessoire;
import com.sav.persistance.App_acc;
import com.sav.persistance.App_panne;
import com.sav.service.AccessoireService;
import com.sav.service.App_accService;
import com.sav.service.App_panneService;
import com.sav.service.PanneService;



@ManagedBean(name = "accessoireBean")
@SessionScoped
public class AccessoireBean {

	private Integer idaccessoire;
	private String designationAcc;
	private String action;
	private String valeurRecherche;
	
	
	
	private List<Accessoire> accessoireByNom = new ArrayList<Accessoire>(0);

	private List<Accessoire> accessoires = new ArrayList<Accessoire>(0);
	
	
	
	

	public String getValeurRecherche() {
		return valeurRecherche;
	}

	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}

	public Integer getIdaccessoire() {
		return idaccessoire;
	}

	public void setIdaccessoire(Integer idaccessoire) {
		this.idaccessoire = idaccessoire;
	}


	public String getDesignationAcc() {
		return designationAcc;
	}


	public void setDesignationAcc(String designationAcc) {
		this.designationAcc = designationAcc;
	}


	public List<Accessoire> getAccessoires() {
		AccessoireService ser = new AccessoireService();
		if(valeurRecherche!=null)
			accessoires = ser.rechercheFiltre(valeurRecherche);
		else
		accessoires = ser.rechercheTousAccessoire();
		
		return accessoires;
	}




	public void setAccessoires(List<Accessoire> accessoires) {
		this.accessoires = accessoires;
	}




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}

	
	
	
	public void modifierAccessoire (Accessoire a) {
		action="Modifier";
		System.out.println("id  "+idaccessoire);
		idaccessoire = a.getIdaccessoire();
		designationAcc = a.getDesignationAcc();
	
	}
			
		
	public void validation() {
		AccessoireService ser = new AccessoireService();
		Accessoire a = new Accessoire();
		a.setDesignationAcc(designationAcc);
		System.out.println(action);	
		
		if(action.equals("Modifier"))
		   {
			System.out.println("c'est une modification");
			a.setIdaccessoire(idaccessoire);
			ser.modifierAccessoire(a);
		   }
	if (action.equals("Ajouter"))
	
		
	
	   ser.ajouterAccessoire(a);
		
	   initialisation();
	}
	
	
	public void initialisation (){
		idaccessoire = null;
		designationAcc = null;
		
	}
	
	public void ajouterAccessoire(){
	action="Ajouter";
	initialisation();
	
	}

	public void Supprimer(Integer id) {
		
		AccessoireService ser = new AccessoireService();
		FacesContext faces = FacesContext.getCurrentInstance();
		App_accService appAccSer = new App_accService();
		List<App_acc> listeAccPan = appAccSer.rechercheAplByAcc(id);
		
		if(listeAccPan.size()>0  ){
			System.out.println("suppression accessoire impossible, accessoire utilisée");
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "suppression accessoire impossible, accessoire utilisée",
					"Invalid credentials"));
		}
		else{
			
			ser.supprimerAccessoire(id);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Accessoire supprimée avec succès",
					"Invalid credentials"));
		    RequestContext.getCurrentInstance().update("f1");
		
		}	
		
	}


	public void annulerRecherche() {
		valeurRecherche = null;
		
	}
	

	
	
	public void ajouterAccApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		AccessoireService ser = new AccessoireService();
		Accessoire a = new Accessoire();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

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
				accessoireByNom = ser.rechercheParNom(designationAcc);
				System.out.println("size liste recherche sevice=="
						+ accessoireByNom.size());
				if (accessoireByNom.size() == 0) { // sevice n'existe pas
					addValid = true;
					a.setDesignationAcc(designationAcc);
					ser.ajouterAccessoire(a);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Accessoire ajoutée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "accessoire existe déja",
							"Invalid credentials"));
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
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Accessoire modifiée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} 
				
else if(accessoireByNom.get(0).getIdaccessoire() == idaccessoire ){
					
					addValid = true;
					a.setDesignationAcc(designationAcc);
					a.setIdaccessoire(idaccessoire);
					ser.modifierAccessoire(a);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Accessoire modifiée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
					
				}
				
				
				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "accessoire existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}
	
	
	
public void viewListeAcc (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/accessoireRapport.jasper"));
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

	

	



	
