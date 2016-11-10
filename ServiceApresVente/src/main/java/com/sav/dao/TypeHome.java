package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


import com.sav.persistance.Type;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Type.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class TypeHome {

	private static Logger log = Logger.getLogger(TypeHome.class);

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

	public void persist(Type transientInstance) {
		log.debug("persisting Type instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Type instance) {
		log.debug("attaching dirty Type instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Type instance) {
		log.debug("attaching clean Type instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Type persistentInstance) {
		log.debug("deleting Type instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Type merge(Type detypedInstance) {
		log.debug("merging Type instance");
		try {
			Type result = (Type) sessionFactory.getCurrentSession()
					.merge(detypedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Type findById(java.lang.Integer id) {
		log.debug("getting Type instance with id: " + id);
		try {
			Type instance = (Type) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Type", id);
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

	public List<Type> findByExample(Type instance) {
		log.debug("finding Type instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Type> results = (List<Type>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Type")
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
	public List<Type> findAll() {//select * from type;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Type.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Type> findByNom(String n) {//select * from type where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Type.class).add(Restrictions.eq("designationType", n));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Type> findAllWithFilter(String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Type.class)
				.add(Restrictions.like("designationType", valeurRecherche, MatchMode.ANYWHERE));
		return crit.list() ;
	}

	
	@SuppressWarnings("unchecked")
	public List<Type> findTypeById( Integer n) {//select * from type where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Type.class).add(Restrictions.eq("idtype", n));
		return crit.list();
}
	
	public Type finduniqueTypeById( Integer n) {//select * from type where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Type.class).add(Restrictions.eq("idtype", n));
		return (Type) crit.uniqueResult();
}



}
