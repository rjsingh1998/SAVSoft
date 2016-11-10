package com.sav.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;



import com.sav.dao.HibernateUtil;
import com.sav.dao.Rep_UtlHome;
import com.sav.persistance.Diagnostique;
import com.sav.persistance.Rep_Utl;



public class Rep_UtlService {
	
	public Rep_UtlService (){
		dao=new Rep_UtlHome();
	}
	Rep_UtlHome dao; 
	
public List<Rep_Utl> rechercheTousRep_Utl(){
	List<Rep_Utl> liste = null;
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




public List<Rep_Utl> rechercheParNom(){
	List<Rep_Utl> liste = null;
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
public void  supprimerRep_Utl(Integer idrep_utl)
{
	
	
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		Rep_Utl cl=new Rep_Utl();
		cl.setIdrep_utl(idrep_utl);
		
		dao.delete(cl);
					
		
		tx.commit() ;
		}catch(RuntimeException ex){ 
		if(tx!= null) tx.rollback();
		ex.printStackTrace() ; 
			}
}

public void modifierRep_Utl(Rep_Utl rep_utl)

{
	
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		dao.merge(rep_utl);
		tx.commit() ;
		}catch(RuntimeException ex){ 
			
			if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
		}
	}




public void ajouterRep_Utl(Rep_Utl aa) {
	Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx= null ; 
	try{
		
		tx=session.beginTransaction();
		dao.persist(aa);
		tx.commit() ;
		}catch(RuntimeException ex){ 
			
			if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
		}
	
}
public List<Rep_Utl> rechercheParUtilisateur(Integer id){
	List<Rep_Utl> liste = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		liste = dao.findByUtilisateur(id);
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (liste);
}
public List<Rep_Utl> rechercheParRep_Utl(Integer idutl,Integer idrep){
	List<Rep_Utl> liste = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	try {

		tx = session.beginTransaction();

		liste = dao.findByRep_Utl(idutl,idrep);
		
		tx.commit();

	} catch (RuntimeException ex) {

		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return (liste);
}

public List<Rep_Utl> rechercheRep_UtlByIdUtl( Integer idUtl) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction tx = null;
	List<Rep_Utl> d = null;
	try {
		tx = session.beginTransaction();

		d = (List<Rep_Utl>) dao.findRegUtlByIdUtl(idUtl);
		tx.commit();
	} catch (RuntimeException ex) {
		if (tx != null)
			tx.rollback();
		ex.printStackTrace();
	}
	return d;
}

}
