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
import com.sav.persistance.Piece;
import com.sav.persistance.Reg_piece;
import com.sav.persistance.Reg_tache;
import com.sav.service.PieceService;
import com.sav.service.Reg_pieceService;
import com.sav.service.Reg_tacheService;



@ManagedBean(name = "pieceBean")
@SessionScoped
public class PieceBean {

	private Integer idpiece;
	private String designationPce;
	private String prixPce;
	private String action;
	private String valeurRecherche;
	private String attribut;
	
	
	private List<Piece> pieces = new ArrayList<Piece>(0);
	private List<String> listesRecherches = new ArrayList<String>();
	private List<Piece> pieceByNom = new ArrayList<Piece>(0);
	
	
	public String getAttribut() {
		return attribut;
	}
	public void setAttribut(String attribut) {
		this.attribut = attribut;
	}
	public List<String> getListesRecherches() {
		listesRecherches.clear();
		listesRecherches.add("--Selectionner--");
		listesRecherches.add("Designation");
		listesRecherches.add("Prix");
		
		return listesRecherches;
	}
	public void setListesRecherches(List<String> listesRecherches) {
		this.listesRecherches = listesRecherches;
	}
	
	
	public String getValeurRecherche() {
		return valeurRecherche;
	}


	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}


	public Integer getIdpiece() {
		return idpiece;
	}


	public String getPrixPce() {
		return prixPce;
	}

	public void setPrixPce(String prixPce) {
		this.prixPce = prixPce;
	}




	public void setIdpiece(Integer idpiece) {
		this.idpiece = idpiece;
	}




	public String getDesignationPce() {
		return designationPce;
	}




	public void setDesignationPce(String designationPce) {
		this.designationPce = designationPce;
	}



	public List<Piece> getPieces() {
		PieceService ser = new PieceService();
		if ((valeurRecherche!=null) && (attribut!=null))
			pieces = ser.rechercheFiltre(valeurRecherche,attribut);
		else
		pieces = ser.rechercheTousPiece();
		return pieces;
		
	}




	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}

	
	
	public void modifierPiece (Piece p) {
		action="Modifier";
		System.out.println("id  "+idpiece);
		idpiece = p.getIdpiece();
		designationPce = p.getDesignationPce();
		prixPce = p.getPrixPce();
	
	}
		
		
		
	public void validation() {
		PieceService ser = new PieceService();
		Piece p = new Piece();
		p.setDesignationPce(designationPce);
		p.setPrixPce(prixPce);
		
		System.out.println(action);	
		
		if(action.equals("Modifier"))
		   {
			System.out.println("c'est une modification");
			p.setIdpiece(idpiece);
			ser.modifierPiece(p);
		   }
	if (action.equals("Ajouter"))
	
		
	
	   ser.ajouterPiece(p);
	   initialisation();
	}
	
	
	public void initialisation (){
		idpiece = null;
		designationPce = null;
		prixPce = null;
		
	}
	
	public void ajouterPiece(){
	action="Ajouter";
	initialisation();
	
	}

	public void Supprimer(Integer id) {
		PieceService ser = new PieceService();
		
		FacesContext faces = FacesContext.getCurrentInstance();
		Reg_pieceService regTachSer = new Reg_pieceService();
		List<Reg_piece> listeAppPiece = regTachSer.rechercheRegByPce(id);
		
		if(listeAppPiece.size()>0  ){
			System.out.println("suppression pce impossible, pce utilisée");
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "suppression piece impossible, piece utilisée",
					"Invalid credentials"));
		}
		else{
			ser.supprimerPiece(id);
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Pièce supprimée  avec succès",
					"Invalid credentials"));
		    RequestContext.getCurrentInstance().update("f1");
		
		}
	}
	
	
	
	
	
	
	public void annulerRecherche() {
		valeurRecherche = null;
		attribut="--Selectionner--";
	}
	
	
	
	public void ajouterPceApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		PieceService ser = new PieceService();
		Piece p = new Piece();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationPce == null || (designationPce.trim().length() == 0)) {// tester
																					// si
																					// sevice
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Piece vide",
						"Invalid credentials"));
			}
			
			if(prixPce!= null ){
				
				try{
					double prip=  Double.parseDouble(prixPce);
				}catch(NumberFormatException ex){
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Prix Pièce invalide",
							"Invalid credentials"));
					}
			}
			
			if(designationPce != null &&  faces.getMessageList().size() == 0 ){
				
			// tester si ce Piece existe déjà
			
				pieceByNom = ser.rechercheParNom(designationPce);
				

				
				if (pieceByNom.size() == 0  ) { // sevice n'existe pas
					addValid = true;
					
					p.setDesignationPce(designationPce);
					p.setPrixPce(prixPce);
					ser.ajouterPiece(p);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Pièce ajoutée  avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Piece existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationPce == null || (designationPce.trim().length() == 0)) {// tester
																					// si
																					// sevice
																					// est
																					// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Piece vide",
						"Invalid credentials"));
			} 
			
         if(prixPce!= null ){
				
				try{
					double prip=  Double.parseDouble(prixPce);
				}catch(NumberFormatException ex){
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Prix Pièce invalide",
							"Invalid credentials"));
					}
			}
         if(designationPce != null &&  faces.getMessageList().size() == 0 ){ // tester si cette ville existe déjà
			
				pieceByNom = ser.rechercheParNom(designationPce);
				
				
				
				if (pieceByNom.size() == 0 ) { //  n'existe pas
					addValid = true;
					p.setDesignationPce(designationPce);
					p.setIdpiece(idpiece);
					p.setPrixPce(prixPce);
					ser.modifierPiece(p);
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Pièce modifiée  avec succès",
							"Invalid credentials"));
					RequestContext.getCurrentInstance().update("f1");
					initialisation();

				}
				 else if(pieceByNom.get(0).getIdpiece() == idpiece  ){
						
						addValid = true;
						p.setDesignationPce(designationPce);
						p.setIdpiece(idpiece);
						p.setPrixPce(prixPce);
						ser.modifierPiece(p);
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Pièce modifiée  avec succès",
								"Invalid credentials"));
						RequestContext.getCurrentInstance().update("f1");
						initialisation();
				 }
				
				
				
				
				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "piece existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}
	
	
	public void viewListePiece (ActionEvent actionEvent) throws SQLException, Exception{
		  
		
		Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sav","root","root");
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Report/pieceRapport.jasper"));
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

	

	



	