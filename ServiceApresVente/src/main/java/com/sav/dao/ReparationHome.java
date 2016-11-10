package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.App_panne;
import com.sav.persistance.Reparation;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Reparation.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class ReparationHome {

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

	public void persist(Reparation transientInstance) {
		log.debug("persisting Reparation instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Reparation instance) {
		log.debug("attaching dirty Reparation instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Reparation instance) {
		log.debug("attaching clean Reparation instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Reparation persistentInstance) {
		log.debug("deleting Reparation instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Reparation merge(Reparation detachedInstance) {
		log.debug("merging Reparation instance");
		try {
			Reparation result = (Reparation) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Reparation findById(java.lang.Integer id) {
		log.debug("getting Reparation instance with id: " + id);
		try {
			Reparation instance = (Reparation) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Reparation", id);
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

	public List<Reparation> findByExample(Reparation instance) {
		log.debug("finding Reparation instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Reparation> results = (List<Reparation>) sessionFactory
					.getCurrentSession()
					.createCriteria(Reparation.class)
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
	public List<Reparation> findAll() {//select * from Reparation;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Reparation.class).setFetchMode("utilisateur", FetchMode.JOIN);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Reparation> findByAppel(Integer id) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Reparation.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.createCriteria("appel")
				.add(Restrictions.eq("idappel", id));
		return crit.list();
	}
	@SuppressWarnings("unchecked")
	public List<Reparation> findAllWithJoin(){
   	Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Reparation.class)
				.setFetchMode("appel", FetchMode.JOIN);
		        
   	
   	return (List<Reparation>) crit.list();
}
   


	






}
