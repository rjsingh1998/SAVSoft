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
import com.sav.persistance.Machine;
import com.sav.persistance.Marque;
import com.sav.service.MachineService;
import com.sav.service.MarqueService;


@ManagedBean(name = "marqueBean")
@SessionScoped
public class MarqueBean {

	private Integer idmarque;
	private String designationMarq;

	private String valeurRecherche;

	
	private List<Marque> marques = new ArrayList<Marque>(0);
	private List<Marque> mrqByNom = new ArrayList<Marque>(0);
	
	

	
	
	
	public String getValeurRecherche() {
		return valeurRecherche;
	}



	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}



	public Integer getIdmarque() {
		return idmarque;
	}



	


	public void setIdmarque(Integer idmarque) {
		this.idmarque = idmarque;
	}


	public String getDesignationMarq() {
		return designationMarq;
	}


	public void setDesignationMarq(String designationMarq) {
		this.designationMarq = designationMarq;
	}




	public List<Marque> getMarques() {
		MarqueService ser = new MarqueService();
		if(valeurRecherche!=null)
			marques = ser.rechercheFiltre(valeurRecherche);
		else
		marques = ser.rechercheTousMarque();
		return marques;
	}




	public void setMarques(List<Marque> marques) {
		this.marques = marques;
	}




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}

	private String action;
	
	
	public void modifierMarque (Marque mq) {
		action="Modifier";
		System.out.println("id  "+idmarque);
		idmarque = mq.getIdmarque();
		designationMarq = mq.getDesignationMarq();
		
	
	}
		
		
		
		
	public void validation() {
		MarqueService ser = new MarqueService();
		Marque mq = new Marque();
		mq.setDesignationMarq(designationMarq);
		
		System.out.println(action);	
		
		if(action.equals("Modifier"))
		   {
			System.out.println("c'est une modification");
			mq.setIdmarque(idmarque);
			ser.modifierMarque(mq);
		   }
	if (action.equals("Ajouter"))
	
	   ser.ajouterMarque(mq);
	   initialisation();
	}
	
	
	public void initialisation (){
		idmarque = null;
		designationMarq = null;

		
	}
	
	public void ajouterMarque(){
	action="Ajouter";
	initialisation();
	
	}

	public void Supprimer(Integer id) {
		FacesContext faces = FacesContext.getCurrentInstance();
		MarqueService ser = new MarqueService();		
		MachineService serMach= new MachineService();
		List<Machine> listeMach =  serMach.rechercheMachParIdMrq(id);
		
		if(listeMach.size()>0  ){
			System.out.println("supression impossible, marque utilisé ");
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Suppression impossible, marque utilisée  ",
					"Invalid credentials"));
		}
		else{
			ser.supprimerMarque(id);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Marque supprimée avec succès  ",
					"Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1");
		
		}
		
		
	}

	
	public void annulerRecherche() {
		valeurRecherche = null;
		
	}
	
	public void ajouterMrqApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		MarqueService ser = new MarqueService();
		Marque m = new Marque();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationMarq== null || (designationMarq.trim().length() == 0)) {// tester
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
					m.setDesignationMarq(designationMarq);
					ser.ajouterMarque(m);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Marque ajotée avec succès  ",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Marque existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationMarq == null || (designationMarq.trim().length() == 0)) {// tester
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
					m.setDesignationMarq(designationMarq);
					m.setIdmarque(idmarque);
					ser.modifierMarque(m);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Marque modifiée avec succès  ",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else if(mrqByNom.get(0).getIdmarque()== idmarque){
					addValid = true;
					m.setDesignationMarq(designationMarq);
					m.setIdmarque(idmarque);
					ser.modifierMarque(m);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Marque modifiée avec succès  ",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
					
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
	
	
	
	public void viewListeMarque (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/MarqueReport.jasper"));
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


	

	



	