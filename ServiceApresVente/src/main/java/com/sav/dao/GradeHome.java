package com.sav.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Grade;


public class GradeHome {
	
	private static Logger log = Logger.getLogger(PanneHome.class);

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

	public void persist(Grade transientInstance) {
		log.debug("persisting Grade instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Grade instance) {
		log.debug("attaching dirty Grade instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Grade instance) {
		log.debug("attaching clean Grade instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Grade persistentInstance) {
		log.debug("deleting Grade instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Grade merge(Grade detachedInstance) {
		log.debug("merging Grade instance");
		try {
			Grade result = (Grade) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Grade findById(java.lang.Integer id) {
		log.debug("getting Grade instance with id: " + id);
		try {
			Grade instance = (Grade) sessionFactory.getCurrentSession().get(
					Grade.class, id);
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

	public List<Grade> findByExample(Grade instance) {
		log.debug("finding Grade instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Grade> results = (List<Grade>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.sav.dao.GradeHome")
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
	public List<Grade> findAll() {//select * from grade;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Grade.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Grade> findByNom(String n) {//select * from Grade where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Grade.class).add(Restrictions.eq("libelleGrd", n));
		return crit.list();
	}
	

	@SuppressWarnings("unchecked")
	public List<Grade> findAllWithFilter(String valeurRecherche) {
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Grade.class).add(Restrictions.like("libelleGrd", valeurRecherche,MatchMode.ANYWHERE));
		return crit.list() ;
	}
	

}
