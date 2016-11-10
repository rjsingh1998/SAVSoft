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
import com.sav.persistance.Utilisateur;
import com.sav.persistance.Ville;
import com.sav.service.ClientService;
import com.sav.service.FournisseurService;
import com.sav.service.UtilisateurService;
import com.sav.service.VilleService;


@ManagedBean(name = "villeBean")
@SessionScoped
public class VilleBean {

	private Integer idville;
	private String designationVille;
	private String valeurRecherche;
	private List<Ville> villeByNom = new ArrayList<Ville>(0);
	private List<Ville> Villes = new ArrayList<Ville>(0);
	
	
	public String getValeurRecherche() {
		return valeurRecherche;
	}



	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}



	


	public Integer getIdville() {
		return idville;
	}
	public void setIdville(Integer idville) {
		this.idville = idville;
	}
	public String getDesignationVille() {
		return designationVille;
	}


	public void setDesignationVille(String designationVille) {
		this.designationVille = designationVille;
	}




	public List<Ville> getVilles() {
		VilleService ser = new VilleService();
		if(valeurRecherche!=null)
			Villes = ser.rechercheFiltre(valeurRecherche);
		else
		Villes = ser.rechercheTousVille();
		return Villes;
	}




	public void setVilles(List<Ville> Villes) {
		this.Villes = Villes;
	}




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}

	private String action;
	
	
	public void modifierVille (Ville v) {
		action="Modifier";
		System.out.println("id  "+idville);
		idville = v.getIdville();
		designationVille = v.getDesignationVille();
		
	}
		
		
		
		
	public void validation() {
		VilleService ser = new VilleService();
		Ville v = new Ville();
		v.setDesignationVille(designationVille);
		
		System.out.println(action);	
		
		if(action.equals("Modifier"))
		   {
			System.out.println("c'est une modification");
			v.setIdville(idville);
			ser.modifierVille(v);
		   }
	if (action.equals("Ajouter"))
	
	   ser.ajouterVille(v);
	   initialisation();
	}
	
	
	public void initialisation (){
		idville = null;
		designationVille = null;
		
		
	}
	  
	public void ajouterVille(){
		System.out.println("ville ajouter");
	action="Ajouter";
	initialisation();
	
	}


	public void Supprimer(Integer id) {


		
		FacesContext faces = FacesContext.getCurrentInstance();
		VilleService ser = new VilleService();
		UtilisateurService serUtl = new UtilisateurService();
		ClientService serclt = new ClientService();
		FournisseurService serFour = new FournisseurService();
		
		List<Utilisateur> listeUtl =  serUtl.rechercheUtlParVille(id);
		List<Client> listeClt =  serclt.rechercheCltParVille(id);
		List<Fournisseur> listeFour =  serFour.rechercheFourParVille(id);
		
		
		if(listeUtl.size()>0 || listeClt.size()>0 || listeFour.size()>0 ){
			System.out.println("supressopn ville impossible");
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Suppression impossible, ville utilisée",
					"Invalid credentials"));
		}
		else{
		ser.supprimerVille(id);
		faces.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_ERROR, "Ville supprimée avec succès",
				"Invalid credentials"));
		RequestContext.getCurrentInstance().update("f1");
		
		}
	}


	
	public void annulerRecherche() {
		valeurRecherche = null;
		
	}

	
	
	
	
	public void ajouterVilApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		VilleService ser = new VilleService();
		Ville v = new Ville();
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
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Ville ajoutée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

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
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Ville modifiée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
				
						

				}else if(villeByNom.get(0).getIdville() == idville ){
					
					addValid = true;
					v.setDesignationVille(designationVille);
					v.setIdville(idville);
					ser.modifierVille(v);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Ville modifiée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
					
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
	
	
	public void viewListeVille (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
				Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
				File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/villeRapport.jasper"));
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
	
	public void initialisationvil (){
		idville = null;
		designationVille = null;
			
	}
	
}

	

	



	
