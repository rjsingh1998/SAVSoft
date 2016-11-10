
package com.sav.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sav.dao.App_panneHome;
import com.sav.dao.Diag_panneHome;
import com.sav.dao.HibernateUtil;
import com.sav.persistance.Diag_panne;
import com.sav.persistance.Appel;
import com.sav.persistance.Utilisateur;

public class Diag_panneService {

	public Diag_panneService() {
		dao = new Diag_panneHome();
	}

	Diag_panneHome dao;

	public List<Diag_panne> rechercheTousDiag_panne() {
		List<Diag_panne> liste = null;
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

	

	public void supprimerDiag_panne(Integer iddiag_panne) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			Diag_panne dp = new Diag_panne();
			dp.setIddiag_panne(iddiag_panne);

			dao.delete(dp);

			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
	}

	public void modifierDiag_panne(Diag_panne diag_panne)

	{

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			dao.merge(diag_panne);
			tx.commit();
		} catch (RuntimeException ex) {

			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
	}

	public void ajouterDiag_panne(Diag_panne dp) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.persist(dp);
			tx.commit();
		} catch (RuntimeException ex) {

			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}

	}

	public List<Diag_panne> rechercheParDiag(Integer id) {
		List<Diag_panne> liste = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();

			liste = dao.findByDiag(id);

			tx.commit();

		} catch (RuntimeException ex) {

			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return (liste);
	}
	
	
	public List<Diag_panne> rechercheAplByPan(Integer n){
		List<Diag_panne> liste = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {


			tx = session.beginTransaction();

			liste = dao.findByPan(n);
			
			tx.commit();

		} catch (RuntimeException ex) {

			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		}
		return (liste);
	}
	
	
}
