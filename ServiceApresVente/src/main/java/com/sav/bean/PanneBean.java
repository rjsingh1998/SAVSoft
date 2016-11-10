package com.sav.bean;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

import org.primefaces.context.RequestContext;














import com.mysql.jdbc.Connection;
import com.sav.persistance.App_panne;
import com.sav.persistance.Panne;
import com.sav.persistance.Service;
import com.sav.persistance.Utilisateur;
import com.sav.service.App_panneService;
import com.sav.service.MachineService;
import com.sav.service.PanneService;
import com.sav.service.ServiceService;




@ManagedBean(name = "panneBean")
@SessionScoped
public class PanneBean {

	private Integer idpanne;
	private String designationPan;
	private String valeurRecherche;
	private Service service;
	private Integer idservice;
	private String designationSer;
	private List<Service> services = new ArrayList<Service>(0);
	Service s=null;
	
	private String attribut;
	private String valser;
	private boolean actifser;
	
	private List<Service> serviceByNom = new ArrayList<Service>(0);
	private List<Panne> panneByNom = new ArrayList<Panne>(0);
	private List<String> listesRecherches = new ArrayList<String>();
	private List<Panne> pannes = new ArrayList<Panne>(0);
	
	
	
	public String getValser() {
		return valser;
	}

	public void setValser(String valser) {
		this.valser = valser;
	}

	

	public boolean isActifser() {
		
		if ((attribut != null) && (attribut.equals("Service")))
			actifser = false;
		else
			actifser = true;
		return actifser;
	}
	
	

	public void setActifser(boolean actifser) {
		this.actifser = actifser;
	}

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


	

	public List<String> getListesRecherches() {
		
		listesRecherches.clear();
		listesRecherches.add("--Selectionner--");
		listesRecherches.add("Panne");
		listesRecherches.add("Service");
		
		return listesRecherches;
	}

	public void setListesRecherches(List<String> listesRecherches) {
		this.listesRecherches = listesRecherches;
	}

	public Integer getIdpanne() {
		return idpanne;
	}

	public void setIdpanne(Integer idpanne) {
		this.idpanne = idpanne;
	}




	public Integer getIdservice() {
		if (s!=null) {
			idservice=s.getIdservice();
			s=null;
			
		}
		
		return idservice;
	}

	public void setIdservice(Integer idservice) {
		this.idservice = idservice;
	}

	public List<Service> getServices() {
		ServiceService ser = new ServiceService();
		services = ser.rechercheTousService();
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Service getService() {
		
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getDesignationPan() {
		return designationPan;
	}




	public void setDesignationPan(String designationPan) {
		this.designationPan = designationPan;
	}




	public String getDesignationSer() {
		return designationSer;
	}

	public void setDesignationSer(String designationSer) {
		this.designationSer = designationSer;
	}

	public List<Panne> getPannes() {
		
		PanneService ser = new PanneService();
		
		if((valeurRecherche != null) && (attribut != null))
		{
			System.out.println("valeurRecherche==="+valeurRecherche);
			System.out.println("attribut==="+attribut);
			pannes = ser.rechercheFiltre(attribut, valeurRecherche);
		}
		else
			pannes = ser.rechercheTousPanneAvecJointure();
		
		return pannes;
	}




	public void setPannes(List<Panne> pannes) {
		this.pannes = pannes;
	}




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}

	private String action;
	
	
	
	public void onSeviceChange(){
		valeurRecherche=valser;
	}
	
	
	public void onselectchange(AjaxBehaviorEvent e){
		if ((attribut != null) && (attribut.equals("Service"))){
			actifser = false;
		    valeurRecherche=null;
		    valser=null;
		    }
		
		
			else if((attribut != null) && !(attribut.equals("Sevice"))  ){
				
				valeurRecherche=null;
				valser=null;
				
			}
	}
	
	
	




	public void modifierPanne (Panne p) {
		action="Modifier";
		
		idpanne = p.getIdpanne();
		designationPan = p.getDesignationPan();
	
		System.out.println("id panneeeeeeeee====>>>>  "+idpanne);
		
		if(p.getService()!= null)
			idservice=p.getService().getIdservice();
		
	}
		
		
		
		
	public void validation() {
		PanneService ser = new PanneService();
		
		ServiceService serService = new ServiceService();
		Service s =new Service();
		s=serService.rechercheParId(getIdservice());
		
		Panne p = new Panne();
		
		
		
		p.setDesignationPan(designationPan);
		s.setDesignationSer(designationSer);
		p.setService(s);
		
		
		System.out.println(action);	
		
		if(action.equals("Modifier")) {
			System.out.println("c'est une modification");
			p.setIdpanne(idpanne);
			ser.modifierPanne(p);
		   }
	if (action.equals("Ajouter"))
	
		System.out.println("get in ajout");
	
	   ser.ajouterPanne(p);
	   initialisation();
	}
	
	
	public void initialisation(){
		idpanne = null;
		designationPan = null;
		idservice = null;
		
	}
	
	public void ajouterPanne(){
		initialisation();
	action="Ajouter";
	
	System.out.println("designationpanne="+designationPan);
	}
	
	public void modifierPanne(){
		action="Modifier";
		
		
		}

	public void Supprimer(Integer id) {
		FacesContext faces = FacesContext.getCurrentInstance();
		App_panneService appPanSer = new App_panneService();
		PanneService ser = new PanneService();
		List<App_panne> listeAppPan = appPanSer.rechercheAplByPan(id);
		
		if(listeAppPan.size()>0  ){
			System.out.println("suppression panne impossible, panne utilisée");
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "suppression panne impossible, panne utilisée",
					"Invalid credentials"));
		}
		else{
			
			ser.supprimerPanne(id);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Panne supprimée avec succès",
					"Invalid credentials"));
		    RequestContext.getCurrentInstance().update("f1");
		
		}
		
		
	}

	public void annulerRecherche() {
		valeurRecherche = null;
		attribut=null;
		valser=null;
		
	}
	
	
	public void initialisationser (){
		idservice = null;
		designationSer = null;
		
	}
	
	public void ajouterService(){
		System.out.println("hhhh");
	action="Ajouter";
	initialisationser();
	
	}
	


	public void ajouterPanApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
	    List<Service> liste = new ArrayList<Service>(0);
		PanneService ser = new PanneService();
		ServiceService serService = new ServiceService();
		liste = serService.rechercheServiceParId(idservice);
		Service s =new Service();
		if(liste.size()>0 )	{
	       s=liste.get(0);
		}
		 Panne p= new Panne();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationPan == null || (designationPan.trim().length() == 0)) {// tester
																					// si
																					// panne
				if(idservice==null){
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Service vide",
							"Invalid credentials"));
				}																	// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "panne vide",
						"Invalid credentials"));
			}

			
			else{
				panneByNom = ser.rechercheParNom(designationPan);
				//System.out.println("size liste recherche panne=="
						//+ panneByNom.size());
				if (panneByNom.size() == 0) {// panne n'existe pas
					if(idservice==null){
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Service vide",
								"Invalid credentials"));
					}else{// tester si ce panne existe déjà
					addValid = true;
					p.setDesignationPan(designationPan);
					p.setService(s);
					ser.ajouterPanne(p);
					RequestContext.getCurrentInstance().update("f1");
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Panne ajoutée avec succès",
							"Invalid credentials"));
					initialisation();}

				} 
				else if  (idservice!= null && panneByNom.get(0).getService().getIdservice() != idservice )
				{addValid = true;
				p.setDesignationPan(designationPan);
				p.setService(s);
				ser.ajouterPanne(p);
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Panne ajoutée avec succès",
						"Invalid credentials"));
				RequestContext.getCurrentInstance().update("f1");
				initialisation();
				
				}
				else {
				
					if(idservice==null){
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Service vide",
								"Invalid credentials"));
					}else{
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Panne existe déja",
							"Invalid credentials"));
					}
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationPan == null || (designationPan.trim().length() == 0)) {// tester
																					// si
																					// sevice
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Panne vide",
						"Invalid credentials"));
			} else // tester si cette panne existe déjà
			{
				panneByNom = ser.rechercheParNom(designationPan);
				if (panneByNom.size() == 0) { // panne n'existe pas
					
					addValid = true;
					p.setDesignationPan(designationPan);
					p.setIdpanne(idpanne);
					p.setService(s);
					ser.modifierPanne(p);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Panne Modifiée avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
				}
					 else if(panneByNom.get(0).getIdpanne() == idpanne ){
							
							addValid = true;
							p.setDesignationPan(designationPan);
							p.setIdpanne(idpanne);
							p.setService(s);
							ser.modifierPanne(p);
							faces.addMessage(null, new FacesMessage(
									FacesMessage.SEVERITY_INFO, "Panne Modifiée avec succès",
									"Invalid credentials"));
							RequestContext.getCurrentInstance().update("f1");
							initialisation();
					 }
				
					 else if (idservice!= null && panneByNom.get(0).getService().getIdservice() != idservice)
						{addValid = true;
						p.setDesignationPan(designationPan);
						p.setService(s);
						ser.ajouterPanne(p);
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_INFO, "Panne Modifiée avec succès",
								"Invalid credentials"));
						RequestContext.getCurrentInstance().update("f1");
						initialisation();
						}

				 else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Panne existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}
	
public void ajouterSevceApresValidation() {
		
		System.out.println("entreeemil panne");
				FacesContext faces = FacesContext.getCurrentInstance();
				RequestContext context = RequestContext.getCurrentInstance();
				ServiceService ser = new ServiceService();
				 s = new Service();
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
						System.out.println("size liste recherche sevice=="
								+ serviceByNom.size());
						if (serviceByNom.size() == 0) { // sevice n'existe pas
							System.out.println("ajout sevice*************");
							addValid = true;
							s.setDesignationSer(designationSer);
							ser.ajouterService(s);
							//RequestContext.getCurrentInstance().update("f1");
							context.execute("PF('dialogmodif1').hide();");
							// context.execute("PF('dialogmodif').show();");
							initialisationser();

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
							RequestContext.getCurrentInstance().update("f1");
							initialisationser();

						} else if(serviceByNom.get(0).getIdservice()==idservice){
							addValid = true;
							s.setDesignationSer(designationSer);
							s.setIdservice(idservice);
							ser.modifierService(s);
							RequestContext.getCurrentInstance().update("f1");
							initialisationser();
							
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


public void viewListePanne (ActionEvent actionEvent) throws SQLException, Exception{
	  
	
	Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
	File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/panneRapport.jasper"));
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

	
	