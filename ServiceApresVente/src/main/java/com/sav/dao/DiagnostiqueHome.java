package com.sav.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Client;
import com.sav.persistance.Diagnostique;
import com.sav.persistance.Rep_Utl;
import com.sav.persistance.Reparation;


public class DiagnostiqueHome {
	
	private static Logger log = Logger.getLogger(ReparationHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			//return (SessionFactory) new InitialContext()
				//	.lookup("SessionFactory");
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Diagnostique transientInstance) {
		log.debug("persisting Diagnostique instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Diagnostique instance) {
		log.debug("attaching dirty Diagnostique instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Diagnostique instance) {
		log.debug("attaching clean Diagnostique instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Diagnostique persistentInstance) {
		log.debug("deleting Diagnostique instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Diagnostique merge(Diagnostique detachedInstance) {
		log.debug("merging Diagnostique instance");
		try {
			Diagnostique result = (Diagnostique) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	
	@SuppressWarnings("unchecked")
	public List<Diagnostique> findByUtilisateur(Integer id) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Diagnostique.class)
				.setFetchMode("utilisateur", FetchMode.JOIN)
				.createCriteria("utilisateur")
				.add(Restrictions.eq("idutilisateur", id));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Diagnostique> findAll() {//select * from Diagnostique;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Diagnostique.class);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Diagnostique> findByIdUtl(java.lang.Integer n) {//select * from diagnostique where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Diagnostique.class)
				.setFetchMode("utilisateur", FetchMode.JOIN)
				.add(Restrictions.eq("utilisateur.idutilisateur", n));
		return crit.list();
	}


}
