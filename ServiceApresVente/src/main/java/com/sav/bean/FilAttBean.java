package com.sav.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;

import com.sav.persistance.App_fil;
import com.sav.persistance.Client;
import com.sav.persistance.Fil_att;
import com.sav.persistance.Statut;
import com.sav.service.App_filService;
import com.sav.service.ClientService;
import com.sav.service.Fil_attService;


@ManagedBean(name = "filAttBean")
@SessionScoped

public class FilAttBean {
	private TabView v;
	private Tab tb;
	
	private List<Fil_att> fils = new ArrayList<Fil_att>(0);
	private List<App_fil> aplFils = new ArrayList<App_fil>(0);
	private Integer idFil;
	
	private List<String> title = new ArrayList<String>(0);
	private List<Boolean> tab = new ArrayList<Boolean>(0);
	private List<List> table = new ArrayList<List>(0);

	
	private String type;
	
	
	
	public List<List> getTable() {
		return table;
	}

	public void setTable(List<List> table) {
		this.table = table;
	}

	public List<String> getTitle() {
		
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public List<Boolean> getTab() {
		return tab;
	}

	public void setTab(List<Boolean> tab) {
		this.tab = tab;
	}

	public Integer getIdFil() {
		return idFil;
	}

	public void setIdFil(Integer idFil) {
		this.idFil = idFil;
	}

	public List<Fil_att> getFils() {
		
		/*Fil_attService filser = new Fil_attService();
		fils= filser.rechercheWithJoin();
		System.out.println("get in getFils==>fils size==="+fils.size());*/
		return fils;
	}
	
	public void setFils(List<Fil_att> fils) {
		this.fils = fils;
	}

	public List<App_fil> getAplFils() {
		
		
		
		return aplFils;
	}

	public void setAplFils(List<App_fil> aplFils) {
		this.aplFils = aplFils;
	}
	
	
	
    public void init() {
    	
    	App_filService appFilser = new App_filService();
		
    	Fil_attService filser = new Fil_attService();
    	fils= filser.rechercheWithJoin();
		System.out.println("size fils=="+fils.size());
		
		for(int i=0; i<fils.size(); i++) {
			System.out.println("i=="+i);
			System.out.println("DesignationSer==="+fils.get(i).getService().getDesignationSer());
		
			aplFils=appFilser.rechercheParFil(fils.get(i).getIdfil_att());
			System.out.println("id filAtt==="+fils.get(i).getIdfil_att());
			table.add(i,aplFils);
		title.add(i, fils.get(i).getService().getDesignationSer());
		tab.add(i, true);
		
		}
		
		
	
		for(int j=fils.size(); j<8; j++) {
			System.out.println("j=="+j);
			//aplFils.clear();	
		title.add(j, "");
		tab.add(j, false);
		table.add(j, aplFils);
			
		}
		
	}
	
	
	public String  getingType(App_fil apllFil){
		
		String type;
		if(apllFil.isDiagstiq()){
			type="diag";
		}else{
			type="rep";
		}
		
		return type;
	}
	
	

	/*public List<Tab> getLstTab() {
		return lstTab;
	}

	public void setLstTab(List<Tab> lstTab) {
		this.lstTab = lstTab;
	}

	private List<Tab> lstTab = new ArrayList<>();
	private List<Client> clients = new ArrayList<Client>(0);
	
	 @PostConstruct
	    public void init() {
		 ClientService ser = new ClientService();
		 clients = ser.rechercheTousClientAvecJointure();
	
		 for(int i=0 ; i<3 ; i++)
		 {
			 DataTable dataTable = new DataTable();
			 
			 Tab tab = new Tab();
			 tab.setTitle("Tab"+i);
			 
			 dataTable.setValue(clients);
			 
			 Column nomColumn = new Column();
			 nomColumn.setHeaderText("Nom");
			
			 
			 
			 lstTab.add(tab);
			 
		 }		 
		
		
	
	
	 }*/

}
