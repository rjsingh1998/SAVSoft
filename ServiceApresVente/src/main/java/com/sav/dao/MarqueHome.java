package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Marque;


import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Marque.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class MarqueHome {

	private static Logger log = Logger.getLogger(MarqueHome.class);

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

	public void persist(Marque transientInstance) {
		log.debug("persisting Marque instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Marque instance) {
		log.debug("attaching dirty Marque instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Marque instance) {
		log.debug("attaching clean Marque instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Marque persistentInstance) {
		log.debug("deleting Marque instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Marque merge(Marque demarquedInstance) {
		log.debug("merging Marque instance");
		try {
			Marque result = (Marque) sessionFactory.getCurrentSession()
					.merge(demarquedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Marque findById(java.lang.Integer id) {
		log.debug("getting Marque instance with id: " + id);
		try {
			Marque instance = (Marque) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Marque", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Marque> findByExample(Marque instance) {
		log.debug("finding Marque instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Marque> results = (List<Marque>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Marque")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Marque> findAll() {//select * from marque;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Marque.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Marque> findByNom(String n) {//select * from marque where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Marque.class).add(Restrictions.eq("designationMarq", n));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Marque> findAllWithFilter(String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Marque.class)
				.add(Restrictions.like("designationMarq", valeurRecherche, MatchMode.ANYWHERE));
		return crit.list() ;
	}
	
	@SuppressWarnings("unchecked")
	public List<Marque> findMarqueById( Integer n) {//select * from machine where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Marque.class).add(Restrictions.eq("idmarque", n));
		return crit.list();
	}
	
	
	public Marque finduniqMarqueById( Integer n) {//select * from machine where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Marque.class).add(Restrictions.eq("idmarque", n));
		return (Marque) crit.uniqueResult();
	}

}
