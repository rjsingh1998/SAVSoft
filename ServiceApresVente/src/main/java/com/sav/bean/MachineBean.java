package com.sav.bean;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sav.persistance.App_stat;
import com.sav.persistance.Appel;
import com.sav.persistance.MachHisto;
import com.sav.persistance.Marque;
import com.sav.persistance.Panne;
import com.sav.persistance.Reg_piece;
import com.sav.persistance.Reg_tache;
import com.sav.persistance.Reparation;
import com.sav.persistance.Type;
import com.sav.persistance.Machine;
import com.sav.service.App_panneService;
import com.sav.service.App_statService;
import com.sav.service.AppelService;
import com.sav.service.MachHistoService;
import com.sav.service.MachineService;
import com.sav.service.MarqueService;
import com.sav.service.PanneService;
import com.sav.service.Reg_pieceService;
import com.sav.service.Reg_tacheService;
import com.sav.service.ReparationService;
import com.sav.service.TypeService;

@ManagedBean(name = "machineBean")
@SessionScoped
public class MachineBean {

	private List<Marque> marques = new ArrayList<Marque>(0);
	private List<Type> types = new ArrayList<Type>(0);

	private Integer idmachine;
	private String num_serie;

	private String marqueMach;
	private String typeMach;

	private String model;
	private String valeurRecherche;
	private String attribut;
	private String designationMarq;
	private String designationType;
	private Marque marque;
	private Type type;
	private Integer idmarque;
	private Integer idtype;
	Type ty = null;
	Marque m = null;
	private List<Type> typmachByNom = new ArrayList<Type>(0);
	private List<Machine> machines = new ArrayList<Machine>(0);
	private List<Marque> mrqByNom = new ArrayList<Marque>(0);
	private List<Machine> machByNom = new ArrayList<Machine>(0);
	private List<MachHisto> machHistos = new ArrayList<MachHisto>(0);

	public String getMarqueMach() {
		return marqueMach;
	}

	public void setMarqueMach(String marqueMach) {
		this.marqueMach = marqueMach;
	}

	public String getTypeMach() {
		return typeMach;
	}

	public void setTypeMach(String typeMach) {
		this.typeMach = typeMach;
	}

	public List<MachHisto> getMachHistos() {
		return machHistos;
	}

	public void setMachHistos(List<MachHisto> machHistos) {
		this.machHistos = machHistos;
	}

	public String getDesignationType() {
		return designationType;
	}

	public void setDesignationType(String designationType) {
		this.designationType = designationType;
	}

	public Integer getIdmarque() {
		if (m != null) {
			idmarque = m.getIdmarque();
			m = null;
		}
		return idmarque;
	}

	public void setIdmarque(Integer idmarque) {
		this.idmarque = idmarque;
	}

	public Integer getIdtype() {
		if (ty != null) {
			idtype = ty.getIdtype();
			ty = null;
		}
		return idtype;

	}

	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}

	public List<Marque> getMarques() {
		MarqueService ser = new MarqueService();
		marques = ser.rechercheTousMarque();
		return marques;
	}

	public void setMarques(List<Marque> marques) {
		this.marques = marques;
	}

	public List<Type> getTypes() {
		TypeService ser = new TypeService();
		types = ser.rechercheTousType();
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public String getDesignationMarq() {
		return designationMarq;
	}

	public void setDesignationMarq(String designationMarq) {
		this.designationMarq = designationMarq;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	private List<String> listesRecherches = new ArrayList<String>();
	private String action;

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

	public List<String> getListesRecherches() {

		listesRecherches.clear();
		listesRecherches.add("--Selectionner--");
		listesRecherches.add("Designation");
		listesRecherches.add("N°serie");
		listesRecherches.add("Model");

		listesRecherches.add("Type");
		listesRecherches.add("Marque");

		return listesRecherches;

	}

	public void setListesRecherches(List<String> listesRecherches) {
		this.listesRecherches = listesRecherches;
	}

	public List<Machine> getMachines() {
		MachineService ser = new MachineService();

		if ((valeurRecherche != null) && (attribut != null))
			machines = ser.rechercheFiltre(attribut, valeurRecherche);
		else
			// machines = ser.rechercheTousMachine();
			machines = ser.rechercheTousMachineAvecJointure();
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}

	public Integer getIdmachine() {
		return idmachine;
	}

	public void setIdmachine(Integer idmachine) {
		this.idmachine = idmachine;
	}

	public String getNum_serie() {

		System.out.println("get de num seir=====>>>>" + num_serie);
		return num_serie;
	}

	public void setNum_serie(String num_serie) {

		System.out.println("set de num seri======>" + num_serie);
		this.num_serie = num_serie;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void modifierMachine(Machine m) {
		action = "Modifier";
		System.out.println("id  " + idmachine);
		idmachine = m.getIdmachine();

		num_serie = m.getNum_serie();
		model = m.getModel();

		if (m.getMarque() != null)
			idmarque = m.getMarque().getIdmarque();

		if (m.getType() != null)
			idtype = m.getType().getIdtype();

	}

	public void validation() {
		MachineService ser = new MachineService();

		TypeService sertype = new TypeService();
		Type t = new Type();
		t = sertype.rechercheParId(getIdtype());

		MarqueService sermarque = new MarqueService();
		Marque M = new Marque();
		M = sermarque.rechercheParId(getIdmarque());

		Machine m = new Machine();

		m.setNum_serie(num_serie);
		m.setModel(model);
		m.setType(t);
		m.setMarque(M);

		System.out.println(action);
		if (action.equals("Modifier")) {
			System.out.println("c'est une modification");
			m.setIdmachine(idmachine);
			ser.modifierMachine(m);
		}
		if (action.equals("Ajouter"))
			ser.ajouterMachine(m);
		initialisation();
	}

	public void initialisation() {
		idmachine = null;
		num_serie = null;

		model = null;
		idtype = null;
		idmarque = null;

	}

	public void ajouterMachine() {
		action = "Ajouter";
		initialisation();

	}

	public void modifierMachine() {
		action = "Modifier";

	}

	public void supprimer(Integer id) {
		FacesContext faces = FacesContext.getCurrentInstance();
		MachineService ser = new MachineService();
		AppelService serapp = new AppelService();
		List<Appel> listeapp = serapp.rechercheAppParMach(id);
		if (listeapp.size() > 0) {
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Suppression impossible; machine utilisée",
					"Invalid credentials"));

		} else {
			ser.supprimerMachine(id);
			RequestContext.getCurrentInstance().update("f1");
			faces.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Machine supprimée avec succès",
					"Invalid credentials"));
		}
	}

	public void initialisationmqe() {
		idmarque = null;
		designationMarq = null;

	}

	public void ajouterMarque() {
		action = "Ajouter";
		initialisationmqe();

	}

	public void initialisationtyp() {
		idtype = null;
		designationType = null;

	}

	public void ajouterType() {
		action = "Ajouter";
		initialisationtyp();

	}

	public void annulerRecherche() {
		valeurRecherche = null;
		attribut = "--Selectionner--";
		valtype = null;
		valmarque = null;
	}

	public void ajouterTypmachApresValidation(ActionEvent event) {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		TypeService ser = new TypeService();
		ty = new Type();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationType == null
					|| (designationType.trim().length() == 0)) {// tester
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
						+ typmachByNom.size());
				if (typmachByNom.size() == 0) { // typ_mach n'existe pas
					addValid = true;
					ty.setDesignationType(designationType);

					ser.ajouterType(ty);
					// RequestContext.getCurrentInstance().update("f1");
					context.execute("PF('dialogmodif1').hide();");
					initialisationtyp();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Type Machine existe déja", "Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationType == null
					|| (designationType.trim().length() == 0)) {// tester
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
				if (typmachByNom.size() == 0) { // typ_mach n'existe pas
					addValid = true;
					ty.setDesignationType(designationType);
					ty.setIdtype(idtype);
					ser.modifierType(ty);
					RequestContext.getCurrentInstance().update("f1");
					initialisationtyp();

				} else if (typmachByNom.get(0).getIdtype() == idtype) {
					addValid = true;
					ty.setDesignationType(designationType);
					ty.setIdtype(idtype);
					ser.modifierType(ty);
					RequestContext.getCurrentInstance().update("f1");
					initialisationtyp();

				}

				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Type Machine existe déja", "Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	public void ajouterMrqApresValidation(ActionEvent event) {

		System.out.println("get in ajouter marque");
		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		MarqueService ser = new MarqueService();
		m = new Marque();
		boolean addValid = false;

		if (action.equals("Ajouter")) {

			if (designationMarq == null
					|| (designationMarq.trim().length() == 0)) {// tester
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
					// RequestContext.getCurrentInstance().update("f1");
					context.execute("PF('dialogmodif2').hide();");
					initialisationmqe();

				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Marque existe déja",
							"Invalid credentials"));
				}
			}

		}

		if (action.equals("Modifier")) {
			if (designationMarq == null
					|| (designationMarq.trim().length() == 0)) {// tester
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
					RequestContext.getCurrentInstance().update("f1");
					initialisationmqe();

				} else if (mrqByNom.get(0).getIdmarque() == idmarque) {
					addValid = true;
					m.setDesignationMarq(designationMarq);
					m.setIdmarque(idmarque);
					ser.modifierMarque(m);
					RequestContext.getCurrentInstance().update("f1");
					initialisationmqe();

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

	public void ajoutermachApresValidation() {

		FacesContext faces = FacesContext.getCurrentInstance();
		RequestContext context = RequestContext.getCurrentInstance();
		MachineService ser = new MachineService();
		Machine m = new Machine();
		MarqueService serm = new MarqueService();
		Marque M = new Marque();
		TypeService sert = new TypeService();
		Type t = new Type();
		M = serm.rechercheuniqueMarqParId(getIdmarque());
		t = sert.rechercheuniquetypParId(getIdtype());

		boolean addValid = false;

		if (action.equals("Ajouter")) {
			System.out.println("ajout mach");
			System.out.println("num_serie==" + num_serie);
			System.out.println("model==" + model);
			if (num_serie == null || (num_serie.trim().length() == 0)) {// tester//
																		// si //
																		// num_serie
																		// //
																		// est
																		// //
																		// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Machine vide",
						"Invalid credentials"));

				if (idtype == null) {// tester
					// si// type// est// vide
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Type vide",
							"Invalid credentials"));
				}
				if (idmarque == null) {// tester
					// si// marque// est// vide
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "  Marque vide",
							"Invalid credentials"));
				}

			} else // tester si ce machine existe déjà
			{
				machByNom = ser.rechercheParNom(num_serie);
				System.out.println("size liste recherche client=="
						+ machByNom.size());
				if (machByNom.size() == 0) {
					if ((idtype == null) && (idmarque != null)) {// tester
						// si// type// est// vide
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"type obligatoire ", "Invalid credentials"));
					}

					else if ((idtype != null) && (idmarque == null)) {// tester
						// si// type// est// vide
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"marquee obligatoire ", "Invalid credentials"));
					} else if ((idtype == null) && (idmarque == null)) {// tester
						// si// type// est// vide
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"marquee  obligatoire ", "Invalid credentials"));
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								" type obligatoire ", "Invalid credentials"));
					}

					else {
						addValid = true;
						m.setNum_serie(num_serie);
						m.setModel(model);
						m.setType(t);
						m.setMarque(M);
						ser.ajouterMachine(m);
						
						RequestContext.getCurrentInstance().update("f1");
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Machine ajoutée avec succès",
								"Invalid credentials"));
						context.execute("PF('dialogmodif1').hide();");

						initialisation();
					}
				} else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "machine existe déja",
							"Invalid credentials"));

					if ((idtype == null) && (idmarque != null)) {// tester
						// si// type// est// vide
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"type obligatoire ", "Invalid credentials"));
					}

					else if ((idtype != null) && (idmarque == null)) {// tester
						// si// type// est// vide
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"marquee obligatoire ", "Invalid credentials"));
					} else if ((idtype == null) && (idmarque == null)) {// tester
						// si// type// est// vide
						addValid = false;
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"marquee  obligatoire ", "Invalid credentials"));
						faces.addMessage(null, new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								" type obligatoire ", "Invalid credentials"));
					}

				}
			}

		}

		if (action.equals("Modifier")) {
			System.out.println("modif mach");
			if (num_serie == null || (num_serie.trim().length() == 0)) {// tester
																		// si
																		// sevice
																		// est
																		// vide

				addValid = false;
				faces.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Machine vide",
						"Invalid credentials"));
			} else // tester si cette machine existe déjà
			{
				machByNom = ser.rechercheParNom(num_serie);
				if (machByNom.size() == 0) { // machine n'existe pas
					addValid = true;
					m.setIdmachine(idmachine);
					m.setNum_serie(num_serie);
					m.setIdmachine(idmachine);
					m.setModel(model);
					m.setType(t);
					m.setMarque(M);

					ser.modifierMachine(m);
					
					RequestContext.getCurrentInstance().update("f1");
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Machine modifiée avec succès",
							"Invalid credentials"));
					initialisation();

				} else if (machByNom.get(0).getIdmachine() == idmachine) {

					addValid = true;
					m.setIdmachine(idmachine);
					m.setNum_serie(num_serie);
					m.setModel(model);
					m.setType(t);
					m.setMarque(M);
					ser.modifierMachine(m);
					
					RequestContext.getCurrentInstance().update("f1");
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Machine modifiée avec succès",
							"Invalid credentials"));
					initialisation();

				}

				else {
					addValid = false;
					faces.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Machine existe déja",
							"Invalid credentials"));
				}
			}
		}

		context.addCallbackParam("addValid", addValid);

	}

	private String valtype;

	public String getValtype() {
		return valtype;
	}

	public void setValtype(String valtype) {
		this.valtype = valtype;
	}

	public void onTypeChange() {
		valeurRecherche = valtype;
	}

	private String valmarque;

	public String getValmarque() {
		return valmarque;
	}

	public void setValmarque(String valmarque) {
		this.valmarque = valmarque;
	}

	public void onMarqueChange() {
		valeurRecherche = valmarque;

	}

	private boolean actiftype;
	private boolean actifmarque;

	public boolean isActiftype() {
		if ((attribut != null) && (attribut.equals("Type")))
			actiftype = false;
		else
			actiftype = true;
		return actiftype;
	}

	public void setActiftype(boolean actiftype) {
		this.actiftype = actiftype;
	}

	public boolean isActifmarque() {
		if ((attribut != null) && (attribut.equals("Marque")))
			actifmarque = false;
		else
			actifmarque = true;
		return actifmarque;
	}

	public void setActifmarque(boolean actifmarque) {
		this.actifmarque = actifmarque;
	}

	public void onselectchange(AjaxBehaviorEvent e) {
		if ((attribut != null) && (attribut.equals("Type"))) {
			actiftype = false;
			valeurRecherche = null;
			valmarque = null;
		} else if ((attribut != null) && (attribut.equals("Marque"))) {
			actifmarque = false;
			valeurRecherche = null;
			valtype = null;
		}

		else if ((attribut != null) && !(attribut.equals("Marque"))
				&& !(attribut.equals("Type"))) {

			valeurRecherche = null;
			valtype = null;
			valmarque = null;
		}
	}

	public void viewListeMachine(ActionEvent actionEvent) throws SQLException,
			Exception {

		Connection connection = (Connection) DriverManager.getConnection(
				"jdbc:mysql://localhost/sav", "root", "root");
		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/Report/rapportMachines.jasper"));
		byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null,
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

	public void historiqueMach(Integer idmach) {

		machHistos.clear();
		
		idmachine=idmach;
		Machine m = new Machine();

		AppelService aplSer = new AppelService();
		MachineService machSer = new MachineService();
		App_statService apl_statSer = new App_statService();
		App_panneService app_panSer = new App_panneService();
		ReparationService repSer = new ReparationService();
		Reg_tacheService reg_tachSer = new Reg_tacheService();
		Reg_pieceService reg_pceser = new Reg_pieceService();
		MachHistoService machHSer = new MachHistoService();

		List<Appel> aplByMach = new ArrayList<Appel>(0);
		List<App_panne> aplpanByApl = new ArrayList<App_panne>(0);
		List<Reparation> repByApl = new ArrayList<Reparation>(0);
		List<Reg_tache> reptachByrep = new ArrayList<Reg_tache>(0);
		List<Reg_piece> reppceByrep = new ArrayList<Reg_piece>(0);
		List<App_stat> aplstatByApl = new ArrayList<App_stat>(0);

		m = machSer.rechercheByIdWithJoin(idmach);
		model = m.getModel();
		num_serie = m.getNum_serie();
		marqueMach = m.getMarque().getDesignationMarq();
		typeMach = m.getType().getDesignationType();

		aplByMach = aplSer.rechercheAppParMach(idmach);// liste des apl de la
														// machine de l'id
														// "idmach"

		for (int i = 0; i < aplByMach.size(); i++) {
			

			aplstatByApl = apl_statSer.rechercheParAppel(aplByMach.get(i)
					.getIdappel());

			aplpanByApl = app_panSer.rechercheAplpanByaplwithJoin(aplByMach
					.get(i).getIdappel());

			repByApl = repSer.rechercheParAppel(aplByMach.get(i).getIdappel());

			String stat = "";
			for (int u = 0; u < aplstatByApl.size(); u++) {
				if (stat.length() == 0) {
					stat += aplstatByApl.get(u).getStatut()
							.getDesignationStat();
				} else {
					stat += "**"
							+ aplstatByApl.get(u).getStatut()
									.getDesignationStat();
				}

			}
			

			for (int j = 0; j < aplpanByApl.size(); j++) {

				

				MachHisto mh = new MachHisto();

				mh.setIdapl(aplByMach.get(i).getIdappel());
				mh.setDate(aplByMach.get(i).getDate_entre());
				mh.setType(aplpanByApl.get(j).getPanne().getDesignationPan());
				mh.setService(aplpanByApl.get(j).getPanne().getService()
						.getDesignationSer());
				mh.setStatut(stat);

				machHistos.add(mh);

			}

			for (int k = 0; k < repByApl.size(); k++) {// parcourir liste des
														// reparation filtré
														// selon idapl

				

				reptachByrep = reg_tachSer.rechercheRegTachByreg(repByApl
						.get(k).getIdreparation());

			}

			for (int q = 0; q < reptachByrep.size(); q++) {

				/*
				 * System.out.println("**idreg=="+reptachByrep.get(q).getReparation
				 * ().getIdreparation());
				 * System.out.println("**idapl=="+reptachByrep
				 * .get(q).getReparation().getAppel().getIdappel());
				 * System.out.println
				 * ("**tache=="+reptachByrep.get(q).getTache().
				 * getDesignationTach());
				 * System.out.println("**idutl=="+reptachByrep
				 * .get(q).getUtilisateur().getNomUtl());
				 * System.out.println("**service=="
				 * +reptachByrep.get(q).getUtilisateur
				 * ().getService().getIdservice());
				 */
				MachHisto mh = new MachHisto();
				mh.setIdapl(reptachByrep.get(q).getReparation().getAppel()
						.getIdappel());
				mh.setDate(reptachByrep.get(q).getReparation().getAppel()
						.getDate_entre());
				mh.setType(reptachByrep.get(q).getTache().getDesignationTach());
				mh.setService(reptachByrep.get(q).getUtilisateur().getService()
						.getDesignationSer());
				mh.setUser(reptachByrep.get(q).getUtilisateur().getNomUtl()
						+ " "
						+ reptachByrep.get(q).getUtilisateur().getPrenomUtl());
				mh.setStatut(stat);
				machHistos.add(mh);
			}

			for (int t = 0; t < repByApl.size(); t++) {

				reppceByrep = reg_pceser.rechercheRegpceByRep(repByApl.get(t)
						.getIdreparation());

			}

			for (int t = 0; t < reppceByrep.size(); t++) {

				/*
				 * System.out.println("idrep========>"+reppceByrep.get(t).
				 * getReparation().getIdreparation());
				 * System.out.println("idpce========>"
				 * +reppceByrep.get(t).getPiece().getIdpiece());
				 */

				MachHisto mh = new MachHisto();
				mh.setIdapl(reppceByrep.get(t).getReparation().getAppel()
						.getIdappel());
				mh.setDate(reppceByrep.get(t).getReparation().getAppel()
						.getDate_entre());
				mh.setType(reppceByrep.get(t).getPiece().getDesignationPce());
				mh.setService(reppceByrep.get(t).getUtilisateur().getService()
						.getDesignationSer());
				mh.setUser(reppceByrep.get(t).getUtilisateur().getNomUtl()
						+ " "
						+ reptachByrep.get(t).getUtilisateur().getPrenomUtl());
				mh.setQte(reppceByrep.get(t).getQte());
				mh.setStatut(stat);
				machHistos.add(mh);

			}

		}

		

	}

	

	public void viewHistoMachine(ActionEvent actionEvent) throws SQLException,
	Exception {
		
		MachHistoService machHSer = new MachHistoService();
		for (int f = 0; f < machHistos.size(); f++) {
			System.out.println("----------i===-------" + f + "  "
					+ machHistos.get(f));

			machHSer.ajouterMachHisto(machHistos.get(f));

		}

Connection connection = (Connection) DriverManager.getConnection(
		"jdbc:mysql://localhost/sav", "root", "root");
File jasper = new File(FacesContext.getCurrentInstance()
		.getExternalContext()
		.getRealPath("/Report/machHisto.jasper"));

Map<String, Object> param = new HashMap<String, Object>();
param.put("idMach",idmachine );
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



for (int f = 0; f < machHistos.size(); f++) {
	

	machHSer.supprimerMachHisto(machHistos.get(f).getIdMachHisto());

}


}

	public void numSeriChange() {
		setNum_serie(num_serie);
	}

}
