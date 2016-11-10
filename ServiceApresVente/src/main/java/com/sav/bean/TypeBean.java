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
import com.sav.persistance.Type;
import com.sav.service.MachineService;
import com.sav.service.TypeService;


@ManagedBean(name = "typeBean")
@SessionScoped
public class TypeBean {

	private Integer idtype;
	private String designationType;
	private String valeurRecherche;
	
	
	private List<Type> typmachByNom = new ArrayList<Type>(0);
	
	
	
	
	private List<Type> types = new ArrayList<Type>(0);
	
	
	
	public String getValeurRecherche() {
		return valeurRecherche;
	}



	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}



	public Integer getIdtype() {
		return idtype;
	}



	

	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}


	public String getDesignationType() {
		return designationType;
	}


	public void setDesignationType(String designationType) {
		this.designationType = designationType;
	}




	public List<Type> getTypes() {
		TypeService ser = new TypeService();
		if(valeurRecherche!=null)
			types = ser.rechercheFiltre(valeurRecherche);
		else
		types = ser.rechercheTousType();
		return types;
	}




	public void setTypes(List<Type> types) {
		this.types = types;
	}




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}

	private String action;
	
	
	public void modifierType (Type ty) {
		action="Modifier";
		System.out.println("id  "+idtype);
		idtype = ty.getIdtype();
		designationType = ty.getDesignationType();
		
	}
		
		
	public void validation() {
		TypeService ser = new TypeService();
		Type ty = new Type();
		ty.setDesignationType(designationType);
		
		System.out.println(action);	
		
		if(action.equals("Modifier"))
		   {
			ty.setIdtype(idtype);
			ser.modifierType(ty);
		   }
	if (action.equals("Ajouter"))
	
	   ser.ajouterType(ty);
	   initialisation();
	}
	
	
	public void initialisation (){
		idtype = null;
		designationType = null;
		
		
	}
	
	public void ajouterType(){
	action="Ajouter";
	initialisation();
	
	}

	public void Supprimer(Integer id) {
		FacesContext faces = FacesContext.getCurrentInstance();
		TypeService ser = new TypeService();
		MachineService serMach= new MachineService();
		List<Machine> listeMach =  serMach.rechercheMachParType(id);
		
		if(listeMach.size()>0  ){
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "supression impossible, type utilisé",
					"Invalid credentials"));
		}
		else{
			ser.supprimerType(id);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Type supprimé avec succès",
					"Invalid credentials"));
			RequestContext.getCurrentInstance().update("f1");
		
		}
		
	}

	
	public void annulerRecherche() {
		valeurRecherche = null;
		
	}
	
	
	public void ajouterTypmachApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		TypeService ser = new TypeService();
		Type ty = new Type();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationType == null || (designationType.trim().length() == 0)) {// tester
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
						+  typmachByNom.size());
				if ( typmachByNom.size() == 0) { // typ_mach n'existe pas
					addValid = true;
					ty.setDesignationType(designationType);
					
					ser.ajouterType(ty);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type ajouté avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type Machine existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationType == null || (designationType.trim().length() == 0)) {// tester
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
				if ( typmachByNom.size() == 0) { // typ_mach n'existe pas
					addValid = true;
					ty.setDesignationType(designationType);
					ty.setIdtype(idtype);
					ser.modifierType(ty);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type modifié avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else if(typmachByNom.get(0).getIdtype()== idtype){
					addValid = true;
					ty.setDesignationType(designationType);
					ty.setIdtype(idtype);
					ser.modifierType(ty);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type modifié avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation(); 
					
				}
				
				
				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type Machine existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	
	public void viewListeType (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/TypeReport.jasper"));
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

	

	



	