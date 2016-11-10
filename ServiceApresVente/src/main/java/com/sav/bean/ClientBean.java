package com.sav.bean;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.sf.jasperreports.engine.JasperRunManager;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.mysql.jdbc.Connection;
import com.sav.persistance.Accessoire;
import com.sav.persistance.App_acc;
import com.sav.persistance.App_panne;
import com.sav.persistance.App_stat;
import com.sav.persistance.Appel;
import com.sav.persistance.Client;
import com.sav.persistance.Fournisseur;
import com.sav.persistance.Machine;
import com.sav.persistance.Panne;
import com.sav.persistance.Reg_piece;
import com.sav.persistance.Reg_tache;
import com.sav.persistance.Reparation;
import com.sav.persistance.Statut;
import com.sav.persistance.Tache;
import com.sav.persistance.Utilisateur;
import com.sav.persistance.Ville;
import com.sav.service.AccessoireService;
import com.sav.service.App_accService;
import com.sav.service.App_panneService;
import com.sav.service.App_statService;
import com.sav.service.AppelService;
import com.sav.service.ClientService;
import com.sav.service.FournisseurService;
import com.sav.service.MachHistoService;
import com.sav.service.Reg_pieceService;
import com.sav.service.Reg_tacheService;
import com.sav.service.ReparationService;
import com.sav.service.StatutService;
import com.sav.service.UtilisateurService;
import com.sav.service.VilleService;


@ManagedBean(name ="clientBean")
@SessionScoped
public class ClientBean {

	private Integer idclient;
	private String nomclt;
	private String adrclt;
	private String mailclt;
	private String gsm1clt;
	private String gsm2clt;
	private String telclt;
	private String faxclt;
	private String valeurRecherche;
	private String attribut;
	private String contact;
	private String type;
	Ville v=null;
	private String designationVille;
	private Ville ville;
	private Integer idville;
	
	
	private Integer idApl;
	private Date date_entre;
	private Date date_sortie;
	private String etat;
	private Machine mch;
	

	private List<Panne> pannes = new ArrayList<Panne>(0);
	private List<String> status = new ArrayList<String>(0);//récurérer les designations des tatuts selectionnées
	private List<Statut> stats = new ArrayList<Statut>(0);
	private List<String> accessoires = new ArrayList<String>();
	private List<Accessoire> accs = new ArrayList<Accessoire>();
	private List<Reg_tache> taches = new ArrayList<Reg_tache>();
	private List<Reg_piece> pieces = new ArrayList<Reg_piece>();
	
	 private Client clt=new Client();
	 private Client cltmodif=new Client();
	private Integer idclt;
		
		private List<Client> clientByNom = new ArrayList<Client>(0);
		private List<Ville> villeByNom = new ArrayList<Ville>(0);
		private List<Appel> histAplClts = new ArrayList<Appel>(0);
	
		
		public List<Accessoire> getAccs() {
			AccessoireService ser = new AccessoireService();
			accs = ser.rechercheTousAccessoire();
			return accs;
		}

		public void setAccs(List<Accessoire> accs) {
			this.accs = accs;
		}
		
		
		

		public List<Reg_tache> getTaches() {
			return taches;
		}

		public void setTaches(List<Reg_tache> taches) {
			this.taches = taches;
		}

		public List<String> getAccessoires() {
			return accessoires;
		}

		public void setAccessoires(List<String> accessoires) {
			this.accessoires = accessoires;
		}

		public List<Statut> getStats() {
			StatutService st = new StatutService();
			stats = st.rechercheTousStatut();
			return stats;
		}

		public void setStats(List<Statut> stats) {
			this.stats = stats;
		}	
		
	
	
	public List<String> getStatus() {
			return status;
		}

		public void setStatus(List<String> status) {
			this.status = status;
		}

	public List<Panne> getPannes() {
			return pannes;
		}

		public void setPannes(List<Panne> pannes) {
			this.pannes = pannes;
		}

	public Machine getMch() {
			return mch;
		}

		public void setMch(Machine mch) {
			this.mch = mch;
		}

	public Date getDate_entre() {
			return date_entre;
		}

		public void setDate_entre(Date date_entre) {
			this.date_entre = date_entre;
		}

		public Date getDate_sortie() {
			return date_sortie;
		}

		public void setDate_sortie(Date date_sortie) {
			this.date_sortie = date_sortie;
		}

		public String getEtat() {
			return etat;
		}

		public void setEtat(String etat) {
			this.etat = etat;
		}

	public Integer getIdApl() {
			return idApl;
		}

		public void setIdApl(Integer idApl) {
			this.idApl = idApl;
		}

	public List<Appel> getHistAplClts() {
		AppelService serapl= new AppelService();
		histAplClts=serapl.rechercheAppParClientAvecJointure(idclient);
			return histAplClts;
		}
	
		public void setHistAplClts(List<Appel> histAplClts) {
			this.histAplClts = histAplClts;
		}
	public Client getCltmodif() {
		return cltmodif;
	}
	public void setCltmodif(Client cltmodif) {
		this.cltmodif = cltmodif;
	}


	 
	 public Client getClt() {
		return clt;
	}
	public void setClt(Client clt) {
		this.clt = clt;
	}
 
	
	

	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}






	private List<Client> clients = new ArrayList<Client>(0);
	private List<String> listesRecherches = new ArrayList<String>();
	private String action;

	
	private List<Ville> villes = new ArrayList<Ville>(0);
	
	
	public List<Ville> getVilles() {
		VilleService ser = new VilleService();
		villes = ser.rechercheTousVille();
		return villes;
	}
	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}
	public Integer getIdville() {
		if (v!=null) {
			idville=v.getIdville();
			v=null;
		}
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
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	public String getValeurRecherche() {
		return valeurRecherche;
	}
	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}
	public String getAttribut() {
		return attribut;
	}
	public void setAttribut(String attribut) {
		this.attribut = attribut;
	}
	public Integer getIdclient() {
		return idclient;
	}
	public void setIdclient(Integer idclient) {
		this.idclient = idclient;
	}
	public String getNomclt() {
		return nomclt;
	}
	public void setNomclt(String nomclt) {
		this.nomclt = nomclt;
	}
	
	
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAdrclt() {
		return adrclt;
	}
	public void setAdrclt(String adrclt) {
		this.adrclt = adrclt;
	}
	public String getMailclt() {
		return mailclt;
	}
	public void setMailclt(String mailclt) {
		this.mailclt = mailclt;
	}
	public String getGsm1clt() {
		return gsm1clt;
	}
	public void setGsm1clt(String gsm1clt) {
		this.gsm1clt = gsm1clt;
	}
	public String getGsm2clt() {
		return gsm2clt;
	}
	public void setGsm2clt(String gsm2clt) {
		this.gsm2clt = gsm2clt;
	}
	public String getTelclt() {
		return telclt;
	}
	public void setTelclt(String telclt) {
		this.telclt = telclt;
	}
	public String getFaxclt() {
		return faxclt;
	}
	public void setFaxclt(String faxclt) {
		this.faxclt = faxclt;
	}
	
	
	public List<String> getListesRecherches() {
		
		listesRecherches.clear();
		listesRecherches.add("--Selectionner--");
		listesRecherches.add("Client");
		listesRecherches.add("GSM");
		listesRecherches.add("Tel");


		return listesRecherches;
		
	}
	public void setListesRecherches(List<String> listesRecherches) {
		this.listesRecherches = listesRecherches;
	}
	
	
	
	
	
	public List<Client> getClients() {
		
		ClientService ser = new ClientService();
		if ((valeurRecherche != null) && (attribut != null))
			clients = ser.rechercheFiltre(attribut, valeurRecherche);
		else
			//clients = ser.rechercheTousClient();
			clients = ser.rechercheTousClientAvecJointure();
		
		return clients;

	}
	
	
	
	
	
	
	
	
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	public void modifierClient (Client c) {
		action="Modifier";
		idclient = c.getIdclient();
		System.out.println("id  "+idclient);
		nomclt = c.getNomclt();
		mailclt= c.getMailclt();
		adrclt = c.getAdrclt();
		gsm1clt= c.getGsm1clt();
		gsm2clt= c.getGsm2clt();
		telclt= c.getTelclt();
		faxclt= c.getFaxclt();
		contact= c.getContact();
		type= c.getType();
		if(c.getVille()!= null)
			idville=c.getVille().getIdville();
	}
		
	public void modifierClientclt () {
		action="Modifier";
		idclient = idclt;
		System.out.println("id  "+idclient);
		nomclt = clt.getNomclt();
		System.out.println("Nom  "+nomclt);
		mailclt= clt.getMailclt();
		adrclt = clt.getAdrclt();
		gsm1clt= clt.getGsm1clt();
		gsm2clt= clt.getGsm2clt();
		telclt= clt.getTelclt();
		faxclt= clt.getFaxclt();
	
	}
	
	
	public void validation() {
		System.out.println("type=" +type);
		System.out.println("get in valdation");
		ClientService ser = new ClientService();
		Client c = new Client();
		
		VilleService serville = new VilleService();
		Ville V = new Ville();
		V=serville.rechercheParId(getIdville());
		
		
		
		c.setNomclt(nomclt);
		c.setMailclt(mailclt);
		c.setAdrclt(adrclt);
		c.setGsm1clt(gsm1clt);
		c.setGsm2clt(gsm2clt);
		c.setTelclt(telclt);
		c.setFaxclt(faxclt);
		c.setContact(contact);
		c.setType(type);
		c.setVille(V);
		
		System.out.println("laction est=="+action);
		
		if(action.equals("Modifier"))
		   {
			System.out.println("c'est une modification");
			c.setIdclient(idclient);
			ser.modifierClient(c);
		   }
	if (action.equals("Ajouter"))
		System.out.println("action ajouter");
	   ser.ajouterClient(c);
	   
	  

	   initialisation();
	   
	  

	  
	   
	}
	
	public void validationclt() {
		System.out.println("get in valdation");
		ClientService ser = new ClientService();
		Client c = new Client();
		
		
		c.setNomclt(nomclt);
		c.setMailclt(mailclt);
		c.setAdrclt(adrclt);
		c.setGsm1clt(gsm1clt);
		c.setGsm2clt(gsm2clt);
		c.setTelclt(telclt);
		c.setFaxclt(faxclt);
		System.out.println("laction est=="+action);
		
		if(action.equals("Modifier"))
		   {
			System.out.println("c'est une modification");
			c.setIdclient(idclient);
			ser.modifierClient(c);
			 RequestContext context = RequestContext.getCurrentInstance();
		     context.execute("PF('dialogaj').hide();");
		   }
	if (action.equals("Ajouter")){
		System.out.println("action ajouter");
	   ser.ajouterClient(c);
	   RequestContext context = RequestContext.getCurrentInstance();
	     context.execute("PF('dialogajout').hide();");
	}
	   initialisation();
	  
	   
	}
	
	
	public String initialisation(){
		
		idclient = null;
		nomclt = null;
		mailclt= null;
		adrclt = null;
		gsm1clt= null;
		gsm2clt= null;
		telclt= null;
		faxclt= null;
		contact=null;
		type=null;
		idville=null;
		return null;
		
	}
	
	
public String initialisationclt(){
		
		idclient = null;
		nomclt = null;
		mailclt= null;
		adrclt = null;
		gsm1clt= null;
		gsm2clt= null;
		telclt= null;
		faxclt= null;
		 RequestContext context = RequestContext.getCurrentInstance();
	     context.execute("PF('dialogajout').hide();");
		return null;
		
	}
	
	public void ajouterClient(){
		System.out.println("get in ajoutclt");
	action="Ajouter";
	initialisation();
	
	}
	
	public void modifierClient(){
		action="Modifier";
	
	}


	
	public void supprimer(Integer id) {
		FacesContext faces = FacesContext.getCurrentInstance();
		ClientService ser = new ClientService();
		AppelService serapp = new AppelService();
		List<Appel> listeapp =  serapp.rechercheAppParClient(id);
		if(listeapp.size()>0){
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Suppression impossible,client utilisé",
					"Invalid credentials"));
		
		}
		else{
		ser.supprimerClient(id);
		faces.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_ERROR, "Client supprimé avec succès",
				"Invalid credentials"));
		RequestContext.getCurrentInstance().update("f1");
		
		}
	}

	public void initialisationvil (){
		idville = null;
		designationVille = null;
			
	}
	  
	public void ajouterVille(){
		System.out.println("ville ajouter");
	action="Ajouter";
	initialisationvil();
	
	}
	
	
	public void annulerRecherche() {
		valeurRecherche = null;
		attribut="--Selectionner--";
	}

	
	public void onRowSelect(SelectEvent event) {
		System.out.println("get in onrow");
	    //clt=new Client();
		
		idclt=((Client) event.getObject()).getIdclient();
		System.out.println("idclt===="+idclt);
		ClientService ser=new ClientService();
		clt=ser.rechercheParId(idclt);
		System.out.println(clt.toString());
		
		
		
		
		cltmodif=ser.rechercheParId(idclt);
		System.out.println("cltmodif : "+cltmodif.toString());
		
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String logo = servletContext.getRealPath("") + File.separator + "images" + File.separator + "fch1.png";
        
        pdf.add(Image.getInstance(logo));
        
        pdf.add(new Paragraph("Liste Clients", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new Color(1, 1, 0))));
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        pdf.add(new Phrase("Généré le: " + formato.format(new Date())));
    }



	public void ajouterVilApresValidation() {
		System.out.println("entr");

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		VilleService ser = new VilleService();
		 v = new Ville();
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
					//RequestContext.getCurrentInstance().update("f1");
					context.execute("PF('dialogmodif1').hide();");
					initialisationvil();

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
					RequestContext.getCurrentInstance().update("f1");
					initialisationvil();
				
						

				}else if(villeByNom.get(0).getIdville() == idville ){
					
					addValid = true;
					v.setDesignationVille(designationVille);
					v.setIdville(idville);
					ser.modifierVille(v);
					RequestContext.getCurrentInstance().update("f1");
					initialisationvil();
					
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
	
	
	
	
	public void ajouterCltApresValidation() {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		ClientService ser = new ClientService();
		Client c = new Client();
		VilleService serville = new VilleService();
		
		
		boolean addValid = false;
		
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		Boolean b =true;
		
		if(mailclt != null && mailclt.trim().length() > 0) 
		    b=mailclt.matches(EMAIL_REGEX);

		if (action.equals("Ajouter")) {

			if (nomclt == null || (nomclt.trim().length() == 0)) { // tester // si// nomclt // est// vide
			addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "client vide",
						"Invalid credentials"));	 
			}
			
			if (type == null || (type.trim().length() == 0)){
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "type vide",
						"Invalid credentials"));
			}

           if(!b){
        	   addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Format email invalide",
						"Invalid credentials"));
			}
					
           if (faces.getMessageList().size() == 0) {
        	   
        	   
        	
						addValid = true;
				if(idville != null){
				     Ville V = new Ville();
				     V=serville.rechercheParId(getIdville());
				     c.setVille(V);
				       		}
						
						c.setNomclt(nomclt);
						c.setMailclt(mailclt);
						c.setAdrclt(adrclt);
						c.setGsm1clt(gsm1clt);
						c.setGsm2clt(gsm2clt);
						c.setTelclt(telclt);
						c.setFaxclt(faxclt);
						c.setContact(contact);
						c.setType(type);
						
						
						ser.ajouterClient(c);
						
						
						context.execute("PF('dialogmodif1').hide();");
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Client ajouté avec succès",
								"Invalid credentials"));
						RequestContext.getCurrentInstance().update("f1");
						initialisation();
						}
	
		
		}

		if (action.equals("Modifier")) {
			if (nomclt == null || (nomclt.trim().length() == 0)) {// tester si clt vide
														           					
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "client vide",
						"Invalid credentials"));
			}
			
			if (type == null || (type.trim().length() == 0)){
				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "type vide",
						"Invalid credentials"));
			}

           if(!b){
        	   addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Format email invalide",
						"Invalid credentials"));
			}
			
			
           if (faces.getMessageList().size() == 0)  {
					
					addValid = true;
					
					if(idville != null){
					     Ville V = new Ville();
					     V=serville.rechercheParId(getIdville());
					     c.setVille(V);
					       		}
					c.setNomclt(nomclt);
					c.setIdclient(idclient);
					c.setMailclt(mailclt);
					c.setAdrclt(adrclt);
					c.setGsm1clt(gsm1clt);
					c.setGsm2clt(gsm2clt);
					c.setTelclt(telclt);
					c.setFaxclt(faxclt);
					c.setContact(contact);
					c.setType(type);
					
					ser.modifierClient(c);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Client modifié avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();
					
				}
		
		}

		context.addCallbackParam("addValid", addValid);

	}
	
	public void viewListeClient (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/clientRapport.jasper"));
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

	public void historiqueClt(Integer idcl){
		
		histAplClts.clear();
		idclient=idcl;
		Client cl= new Client();
		ClientService sercl= new ClientService();
		cl= sercl.rechercheParId(idcl);
		type=cl.getType();
		nomclt=cl.getNomclt();
		mailclt=cl.getMailclt();
		
	}
	
	public void detailapl(Appel apl){
		
		App_panneService serp = new App_panneService();
		App_statService serst = new App_statService();
		App_accService serac = new App_accService();
		ReparationService serRep= new ReparationService();
		Reg_tacheService reg_tachSer = new Reg_tacheService();
		Reg_pieceService reg_pceSer= new Reg_pieceService();
		Reparation rep= new Reparation();
		idApl=apl.getIdappel();
		date_entre=apl.getDate_entre();
		date_sortie=apl.getDate_sortie();
		etat=apl.getEtat().getDesignationEtat();
		mch=apl.getMachine();
		List<App_panne> l = serp.rechercheParAppel(idApl);
		pannes.clear();
		for (int i = 0; i < l.size(); i++)
			pannes.add(l.get(i).getPanne());
		
		List<App_stat> lst = serst.rechercheParAppel(idApl);
		status.clear();
		for (int i = 0; i < lst.size(); i++)
			status.add(lst.get(i).getStatut().getDesignationStat());
		
		List<App_acc> la = serac.rechercheParAppel(idApl);
		accessoires.clear();
		for (int i = 0; i < la.size(); i++)
			accessoires.add(la.get(i).getAccessoire().getDesignationAcc());
		
		List<Reparation> lRep= serRep.rechercheParAppel(idApl);
		rep=lRep.get(0);
		
		taches.clear();
		taches=reg_tachSer.rechercheParReparation(rep.getIdreparation());
		
		pieces.clear();
		pieces= reg_pceSer.rechercheParReparation(rep.getIdreparation());
		System.out.println("size de pieces===="+pieces.size());
		
	}
	
	
	public void viewHistoClt(ActionEvent actionEvent) throws SQLException,
	Exception {

Connection connection = (Connection) DriverManager.getConnection(
		"jdbc:mysql://localhost/sav", "root", "root");
File jasper = new File(FacesContext.getCurrentInstance()
		.getExternalContext()
		.getRealPath("/Report/histoClt.jasper"));

Map<String, Object> param = new HashMap<String, Object>();
param.put("idClt",idclient );
byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), param,
		connection);
HttpServletResponse response = (HttpServletResponse) FacesContext
		.getCurrentInstance().getExternalContext().getResponse();
response.setContentType("application/pdf");
response.setContentLength(bytes.length);
ServletOutputStream outStream = response.getOutputStream();
outStream.write(bytes, 0, bytes.length);
outStream.flush();
outStream.close();
FacesContext.getCurrentInstance().responseComplete();

	}
	

	public List<Reg_piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Reg_piece> pieces) {
		this.pieces = pieces;
	}

}
	
	


	
	

			