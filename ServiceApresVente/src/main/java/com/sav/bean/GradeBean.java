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
import com.sav.persistance.Grade;
import com.sav.persistance.Utilisateur;
import com.sav.service.GradeService;
import com.sav.service.UtilisateurService;

@ManagedBean(name = "gradeBean")
@SessionScoped
public class GradeBean {

	private Integer idgrade;
	private String designationgde;
	private String valeurRecherche;
	private String action;
	
	

	private List<Grade> grades = new ArrayList<Grade>(0);
	private List<Grade> gradeByNom = new ArrayList<Grade>(0);

	

	public String getValeurRecherche() {
		return valeurRecherche;
	}

	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}

	public Integer getIdgrade() {
		return idgrade;
	}

	public void setIdgrade(Integer idgrade) {
		this.idgrade = idgrade;
	}

	public String getDesignationgde() {
		return designationgde;
	}

	public void setDesignationgde(String designationgde) {
		this.designationgde = designationgde;
	}

	public List<Grade> getGrades() {
		GradeService ser = new GradeService();

		if (valeurRecherche != null)
			grades = ser.rechercheFiltre(valeurRecherche);
		else
			grades = ser.rechercheTousGrade();

		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void modifierGrade(Grade g) {
		action = "Modifier";
		System.out.println("id  " + idgrade);
		idgrade = g.getIdGrade();
		designationgde = g.getLibelleGrd();

	}

	public void initialisation() {
		idgrade = null;
		designationgde = null;

	}

	public void ajouterGrade() {
		action = "Ajouter";
		initialisation();

	}

	public void Supprimer(Integer id) {
		FacesContext faces = FacesContext.getCurrentInstance();
		GradeService ser = new GradeService();
		UtilisateurService serUtl = new UtilisateurService();
		
		List<Utilisateur> listeUtl =  serUtl.rechercheUtlParIdGrd(id);
		
		
		if(listeUtl.size()>0  ){
			System.out.println("supressopn ville impossible");
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Suppression impossible, grade utilisée",
					"Invalid credentials"));
		}
		else{
			ser.supprimerGrade(id);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Grade supprimée avec succès",
					"Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1");
		
		}
		
		
	}

	public void annulerRecherche() {
		valeurRecherche = null;

	}

	public void dlgHide() {
		System.out.println("hide mesage****");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
				null, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		System.out.println("fin hide");
		// RequestContext rc = RequestContext.getCurrentInstance();
		// rc.execute("dlg.hide()");

	}

	public void ajouterGradeAprèsValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		GradeService ser = new GradeService();
		Grade g = new Grade();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationgde == null || (designationgde.trim().length() == 0)) {// tester
																					// si
																					// grade
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Grade vide",
						"Invalid credentials"));
			}

			else // tester si ce grade existe déjà
			{
				gradeByNom = ser.rechercheParLibl(designationgde);
				System.out.println("size liste recherche grade=="
						+ gradeByNom.size());
				if (gradeByNom.size() == 0) { // grade n'existe pas
					addValid = true;
					g.setLibelleGrd(designationgde);
					ser.ajouterGrade(g);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Grade ajoutée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Grade existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationgde == null || (designationgde.trim().length() == 0)) {// tester
																					// si
																					// grade
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Grade vide",
						"Invalid credentials"));
			} else // tester si cette ville existe déjà
			{
				gradeByNom = ser.rechercheParLibl(designationgde);
				if (gradeByNom.size() == 0) { // grade n'existe pas
					addValid = true;
					g.setLibelleGrd(designationgde);
					g.setIdGrade(idgrade);
					ser.modifierGrade(g);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Grade modifiée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else if(gradeByNom.get(0).getIdGrade()==idgrade){
					addValid = true;
					g.setLibelleGrd(designationgde);
					g.setIdGrade(idgrade);
					ser.modifierGrade(g);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Grade modifiée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
					
				}
				
				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Grade existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}
	
	
	public void viewListeGrade (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/gradeReport.jasper"));
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
