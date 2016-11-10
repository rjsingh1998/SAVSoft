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
import com.sav.persistance.Client;
import com.sav.persistance.Fournisseur;
import com.sav.persistance.Type_four;
import com.sav.persistance.Utilisateur;
import com.sav.service.ClientService;
import com.sav.service.FournisseurService;
import com.sav.service.Type_fourService;
import com.sav.service.UtilisateurService;
import com.sav.service.VilleService;

@ManagedBean(name = "type_fourBean")
@SessionScoped
public class Type_fourBean {

	private Integer idtype_four;
	private String nomtype;
	private String valeurRecherche;
	
	
	
	private List<Type_four> typfourByNom = new ArrayList<Type_four>(0);
	
	
	public String getValeurRecherche() {
		return valeurRecherche;
	}


	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}

	private List<Type_four> type_fours = new ArrayList<Type_four>(0);
	
	
	
	public Integer getIdtype_four() {
		return idtype_four;
	}




	public void setIdtype_four(Integer idtype_four) {
		this.idtype_four = idtype_four;
	}






	public String getNomtype() {
		return nomtype;
	}
	public void setNomtype(String nomtype) {
		this.nomtype = nomtype;
	}
	public List<Type_four> getType_fours() {
		Type_fourService ser = new Type_fourService();
		if(valeurRecherche!=null)
			type_fours = ser.rechercheFiltre(valeurRecherche);
		else
		type_fours = ser.rechercheTousType_four();
		return type_fours;
	}




	public void setType_fours(List<Type_four> type_fours) {
		this.type_fours = type_fours;
	}




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}

	private String action;
	
	

	public void modifierType_four (Type_four Tf) {
		action="Modifier";
		idtype_four = Tf.getIdtype_four();
		nomtype = Tf.getnomtype();
	
	}
		
	public void validation() {
		Type_fourService ser = new Type_fourService();
		Type_four Tf = new Type_four();
		Tf.setnomtype(nomtype);
		
		if(action.equals("Modifier"))
		   {
			Tf.setIdtype_four(idtype_four);
			ser.modifierType_four(Tf);
		   }
	if (action.equals("Ajouter"))
	
		
	
	   ser.ajouterType_four(Tf);
	   initialisation();
	}
	
	
	public void initialisation (){
		idtype_four = null;
		nomtype = null;
		
	}
	
	public void ajouterType_four(){
	action="Ajouter";
	initialisation();
	
	}

	public void Supprimer(Integer id) {
		FacesContext faces = FacesContext.getCurrentInstance();
		Type_fourService ser = new Type_fourService();
		FournisseurService serFour = new FournisseurService();
		List<Fournisseur> listeFour =  serFour.rechercheFourParTypFour(id);
		
		
		if(listeFour.size()>0 ){
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Suppression type fournisseur impossible",
					"Invalid credentials"));
		}
		else{
			ser.supprimerType_four(id);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Type fournisseur supprimé avec succès",
					"Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1");
		
		}
	
		
	}

	public void annulerRecherche() {
		valeurRecherche = null;
		
	}
	
	public void ajouterTypfourApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		Type_fourService ser = new Type_fourService();
		Type_four tf = new Type_four();
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
				if (typfourByNom.size() == 0) { // Type_four n'existe pas
					addValid = true;
					tf.setnomtype(nomtype);
					ser.ajouterType_four(tf);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type fournisseur ajouté avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

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
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type fournisseur modifié avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				}else if(typfourByNom.get(0).getIdtype_four()== idtype_four){
					addValid = true;
					tf.setnomtype(nomtype);
					tf.setIdtype_four(idtype_four);
					ser.modifierType_four(tf);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type fournisseur modifié avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
					
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


	public void viewListetypFour (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/TypFourReport.jasper"));
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

	

	



	