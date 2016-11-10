package com.sav.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sav.dao.DiagnostiqueHome;
import com.sav.dao.HibernateUtil;
import com.sav.dao.ReparationHome;
import com.sav.persistance.Diagnostique;
import com.sav.persistance.Rep_Utl;
import com.sav.persistance.Reparation;


public class DiagnostiqueService {
	
	public DiagnostiqueService (){
		dao=new DiagnostiqueHome();
	}
	DiagnostiqueHome dao; 
	





public void  supprimerDiagnostique(Integer idDiagnostique)
{
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		Diagnostique d=new Diagnostique();
		d.setIddiag(idDiagnostique);
		dao.delete(d);
		tx.commit() ;
		
	}catch(RuntimeException ex){ 
		if(tx!= null) tx.rollback();
		ex.printStackTrace() ; 
			}
}

public void modifieridDiagnostique(Diagnostique diagnostique)

{
	
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		dao.merge(diagnostique);
		tx.commit() ;
		}catch(RuntimeException ex){ 
			
			if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
		}
	}




public void ajouteridDiagnostique(Diagnostique d) {
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		dao.merge(d);
		tx.commit() ;
		}catch(RuntimeException ex){ 
			
			if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
		}
	
}

public List<Diagnostique> rechercheDiagParUtilisateur(Integer idutl){
	List<Diagnostique> liste = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		liste = dao.findByUtilisateur(idutl);
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (liste);
}

public List<Diagnostique> rechercheTousDiagnostique() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	List<Diagnostique> d = null;
	try {
		tx = session.beginTransaction();

		d = (List<Diagnostique>) dao.findAll();
		tx.commit();
	} catch (RuntimeException ex) {
		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return d;
}

public List<Diagnostique> rechercheDiagByIdUtl( Integer idUtl) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	List<Diagnostique> d = null;
	try {
		tx = session.beginTransaction();

		d = (List<Diagnostique>) dao.findByIdUtl(idUtl);
		tx.commit();
	} catch (RuntimeException ex) {
		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return d;
}

}
