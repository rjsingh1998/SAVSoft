package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Type_four;


import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Type_four.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class Type_fourHome {

	private static Logger log = Logger.getLogger(Type_fourHome.class);

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

	public void persist(Type_four transientInstance) {
		log.debug("persisting Type_four instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Type_four instance) {
		log.debug("attaching dirty Type_four instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Type_four instance) {
		log.debug("attaching clean Type_four instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Type_four persistentInstance) {
		log.debug("deleting Type_four instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Type_four merge(Type_four detype_fourdInstance) {
		log.debug("merging Type_four instance");
		try {
			Type_four result = (Type_four) sessionFactory.getCurrentSession()
					.merge(detype_fourdInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Type_four findById(java.lang.Integer id) {
		log.debug("getting Type_four instance with id: " + id);
		try {
			Type_four instance = (Type_four) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Type_four", id);
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

	public List<Type_four> findByExample(Type_four instance) {
		log.debug("finding Type_four instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Type_four> results = (List<Type_four>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Type_four")
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
	public List<Type_four> findAll() {//select * from type_four;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Type_four.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Type_four> findByNom(String n) {//select * from type_four where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Type_four.class).add(Restrictions.eq("nomtype", n));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Type_four> findAllWithFilter(String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Type_four.class)
				.add(Restrictions.like("nomtype", valeurRecherche, MatchMode.ANYWHERE));
		return crit.list() ;
	}
	@SuppressWarnings("unchecked")
	public List<Type_four> findType_fourById( Integer n) {//select * from Type_four where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Type_four.class).add(Restrictions.eq("idtype_four", n));
		return crit.list();

}
}