package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Accessoire;


import com.sav.persistance.Client;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Accessoire.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class AccessoireHome {

	private static Logger log = Logger.getLogger(AccessoireHome.class);

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

	public void persist(Accessoire transientInstance) {
		log.debug("persisting Accessoire instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Accessoire instance) {
		log.debug("attaching dirty Accessoire instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Accessoire instance) {
		log.debug("attaching clean Accessoire instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Accessoire persistentInstance) {
		log.debug("deleting Accessoire instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Accessoire merge(Accessoire detachedInstance) {
		log.debug("merging Accessoire instance");
		try {
			Accessoire result = (Accessoire) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Accessoire findById(java.lang.Integer id) {
		log.debug("getting accessoire instance with id: " + id);
		try {
			Accessoire instance = (Accessoire) sessionFactory.getCurrentSession().get(
					Accessoire.class, id);
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

	public List<Accessoire> findByExample(Accessoire instance) {
		log.debug("finding Accessoire instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Accessoire> results = (List<Accessoire>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Accessoire")
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
	public List<Accessoire> findAll() {//select * from accessoire;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Accessoire.class).addOrder(Property.forName("idaccessoire").asc());
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Accessoire> findByNom(String n) {//select * from ville where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Accessoire.class).add(Restrictions.eq("designationAcc", n))
				.addOrder(Property.forName("idaccessoire").asc());
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Accessoire> findAllWithFilter(String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Accessoire.class)
				.add(Restrictions.like("designationAcc", valeurRecherche, MatchMode.ANYWHERE))
				.addOrder(Property.forName("idaccessoire").asc());
		return crit.list() ;
	}	
	

}
