package com.sav.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sav.dao.Detaille_EtatHome;
import com.sav.dao.HibernateUtil;
import com.sav.persistance.Appel;
import com.sav.persistance.Detaille_Etat;



public class Detaille_EtatService {
	
	public Detaille_EtatService (){
		dao=new Detaille_EtatHome();
	}
	Detaille_EtatHome dao; 
	
public List<Detaille_Etat> rechercheTousDetaille_Etat(){
	List<Detaille_Etat> liste = null;
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




public List<Detaille_Etat> rechercheParNom(String n){
	List<Detaille_Etat> liste = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		liste = dao.findByNom(n);
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (liste);
}

public Detaille_Etat rechercheParId(Integer id){
	Detaille_Etat c =new Detaille_Etat();
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

public List<Detaille_Etat>rechercheParAppel(Integer id){
	List<Detaille_Etat> c =null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		c = dao.findByAppel(id);
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (c);
}

public void  supprimerDetaille_Etat(Integer iddetaille_etat)
{
	
	
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		Detaille_Etat cl=new Detaille_Etat();
		cl.setIddetaille_etat(iddetaille_etat);
		
		dao.delete(cl);
					
		
		tx.commit() ;
		}catch(RuntimeException ex){ 
		if(tx!= null) tx.rollback();
		ex.printStackTrace() ; 
			}
}

public void modifierDetaille_Etat(Detaille_Etat detaille_etat)

{
	
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		dao.merge(detaille_etat);
		tx.commit() ;
		}catch(RuntimeException ex){ 
			
			if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
		}
	}




public void ajouterDetaille_Etat(Detaille_Etat C) {
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
public List<Detaille_Etat> rechercheFiltre(String valeurRecherche) {
	List<Detaille_Etat> liste =  null;
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		
		liste = dao.findAllWithFilter(valeurRecherche);
		tx.commit() ;
		
		
		}catch(RuntimeException ex){ 
			
			if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
		}
	return(liste);
}

public Detaille_Etat rechercheParIdWithJoin(Integer idA,Integer idS, String typApl  ){
	Detaille_Etat a =new Detaille_Etat();
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		a = dao.findByIdAplWithJoin(idA, idS, typApl);
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (a);
}

public List<Detaille_Etat> rechercheTousDetaille_EtatByIdapl( Integer id){
	List<Detaille_Etat> liste = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		liste = dao.findAllByIdAplWithJoin(id);
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (liste);
}

}
