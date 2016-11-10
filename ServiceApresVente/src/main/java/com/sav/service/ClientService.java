package com.sav.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.sav.dao.ClientHome;
import com.sav.dao.HibernateUtil;
import com.sav.persistance.Client;

public class ClientService {
	
	public ClientService (){
		dao=new ClientHome();
	}
	ClientHome dao; 
	
public List<Client> rechercheTousClient(){
	List<Client> liste = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		liste = dao.findAll();
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (liste);
}




public List<Client> rechercheParNom(String c){
	List<Client> liste = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		liste = dao.findByNom(c);
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (liste);
}

public Client rechercheParId(Integer id){
	Client c =new Client();
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		c = dao.findById(id);
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (c);
}

public void  supprimerClient(Integer idclient)
{
	
	
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		Client cl=new Client();
		cl.setIdclient(idclient);
		
		dao.delete(cl);
					
		
		tx.commit() ;
		}catch(RuntimeException ex){ 
		if(tx!= null) tx.rollback();
		ex.printStackTrace() ; 
			}
}

public void modifierClient(Client client)

{
	
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		dao.merge(client);
		tx.commit() ;
		}catch(RuntimeException ex){ 
			
			if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
		}
	}




public void ajouterClient(Client C) {
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		dao.persist(C);
		tx.commit() ;
		}catch(RuntimeException ex){ 
			
			if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
		}
	
}


public List<Client> rechercheTousClientAvecJointure() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	List<Client> d = null;
	try {
		tx = session.beginTransaction();

		d = (List<Client>) dao.findAllWithJoin();
		System.out.println("sise="+d.size());
		
		/*for(int i=0; i< d.size();i++){
			System.out.println(i);
			if(d.get(i).getVille()==null){
				System.out.println("vilee===="+d.get(i).getVille());
				Ville v=new Ville();
				v.setDesignationVille("");
				d.get(i).setVille(v);
			}
		}*/
		tx.commit();
	} catch (RuntimeException ex) {
		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return d;
}





public List<Client> rechercheFiltre(String attribut, String valeurRecherche) {
	List<Client> liste =  null;
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		tx=session.beginTransaction();
		liste = dao.findAllWithFilter(attribut,valeurRecherche);
		tx.commit() ;
		}catch(RuntimeException ex){ 
			
			if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
		}
	
	return(liste);
	
}


public List<Client> rechercheCltParVille(Integer n){
	List<Client> liste = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		liste = dao.findByIdVil(n);
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (liste);
}


}
