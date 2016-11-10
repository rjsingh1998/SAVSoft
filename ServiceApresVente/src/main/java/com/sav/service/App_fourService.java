package com.sav.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sav.dao.App_fourHome;
import com.sav.dao.HibernateUtil;
import com.sav.persistance.App_four;


public class App_fourService {
	
	App_fourHome dao;
	
	public App_fourService() {
		dao = new App_fourHome();
	}

	
	public List<App_four> rechercheAplByFour(Integer n){
		List<App_four> liste = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {


			tx = session.beginTransaction();

			liste = dao.findByFour(n);
			
			tx.commit();

		} catch (RuntimeException ex) {

			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return (liste);
	}
	public void ajouterApp_four(App_four A) {
		Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx= null ; 
		try{
			
			tx=session.beginTransaction();
			dao.persist(A);
			tx.commit() ;
			}catch(RuntimeException ex){ 
				
				if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
			}
		
	}
	public List<App_four> rechercheParAppel(Integer id){
		List<App_four> liste = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();

			liste = dao.findByAppel(id);
			
			tx.commit();

		} catch (RuntimeException ex) {

			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return (liste);
	}
	
	public List<App_four> rechercheParExemple(App_four obj) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<App_four> d = null;
		try {
			tx = session.beginTransaction();

			d = (List<App_four>) dao.findByExample(obj);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return d;
	}

	public void modifierApp_four(App_four app_four)

	{
		
		Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx= null ; 
		try{
			
			tx=session.beginTransaction();
			dao.merge(app_four);
			tx.commit() ;
			}catch(RuntimeException ex){ 
				
				if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
			}
		}

}
