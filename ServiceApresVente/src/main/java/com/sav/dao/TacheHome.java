package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Tache;


import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tache.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class TacheHome {

	private static Logger log = Logger.getLogger(TacheHome.class);

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

	public void persist(Tache transientInstance) {
		log.debug("persisting Tache instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tache instance) {
		log.debug("attaching dirty Tache instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Tache instance) {
		log.debug("attaching clean Tache instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tache persistentInstance) {
		log.debug("deleting Tache instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tache merge(Tache detachedInstance) {
		log.debug("merging Tache instance");
		try {
			Tache result = (Tache) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tache findById(java.lang.Integer id) {
		log.debug("getting Tache instance with id: " + id);
		try {
			Tache instance = (Tache) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Tache", id);
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

	public List<Tache> findByExample(Tache instance) {
		log.debug("finding Tache instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Tache> results = (List<Tache>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Tache")
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
	public List<Tache> findAll() {//select * from tache;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Tache.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Tache> findByNom(String n) {//select * from tache where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Tache.class).add(Restrictions.eq("designationTach", n));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tache> findAllWithFilter(String valeurRecherche,String attribut) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Tache.class);
				if (attribut.equals("Designation"))
					crit = crit.add(Restrictions.like("designationTach",
							valeurRecherche, MatchMode.ANYWHERE));
				if (attribut.equals("Prix"))
					crit = crit.add(Restrictions.like("prixTach",
							valeurRecherche, MatchMode.ANYWHERE));
		
		return crit.list() ;
	}
	

}
