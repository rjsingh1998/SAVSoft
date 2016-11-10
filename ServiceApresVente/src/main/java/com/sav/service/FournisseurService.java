package com.sav.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.sav.dao.HibernateUtil;
import com.sav.dao.FournisseurHome;
import com.sav.persistance.Client;
import com.sav.persistance.Fournisseur;



public class FournisseurService {
	
	public FournisseurService (){
		dao=new FournisseurHome();
	}
	FournisseurHome dao; 
	
public List<Fournisseur> rechercheTousFournisseur(){
	List<Fournisseur> liste = null;
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

public List<Fournisseur> rechercheFourParVille(Integer n){
	List<Fournisseur> liste = null;
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



public List<Fournisseur> rechercheParNom(){
	List<Fournisseur> liste = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		liste = dao.findByNom("");
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (liste);
}
public void  supprimerFournisseur(Integer idfournisseur)
{
	
	
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		Fournisseur cl=new Fournisseur();
		cl.setIdfournisseur(idfournisseur);
		
		dao.delete(cl);
					
		tx.commit() ;
		}catch(RuntimeException ex){ 
		if(tx!= null) tx.rollback();
		ex.printStackTrace() ; 
			}
}

public void modifierFournisseur(Fournisseur fournisseur)

{
	
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		dao.merge(fournisseur);
		tx.commit() ;
		}catch(RuntimeException ex){ 
			
			if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
		}
	}




public void ajouterFournisseur(Fournisseur f) {
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		dao.persist(f);
		tx.commit() ;
		}catch(RuntimeException ex){ 
			
			if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
		}
	
}


public List<Fournisseur> rechercheTousFournisseurAvecJointure() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	List<Fournisseur> d = null;
	try {
		tx = session.beginTransaction();

		d = (List<Fournisseur>) dao.findAllWithJoin();
		tx.commit();
	} catch (RuntimeException ex) {
		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return d;
}


public List<Fournisseur> rechercheFiltre(String attribut, String valeurRecherche) {
	List<Fournisseur> liste =  null;
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


public List<Fournisseur> rechercheFourParTypFour(Integer n){
	List<Fournisseur> liste = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		liste = dao.findByIdTypFour(n);

		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (liste);
}

}
