package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;



import com.sav.persistance.Fil_att;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Accessoire.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class Fil_attHome {

	private static Logger log = Logger.getLogger(Fil_attHome.class);

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

	public void persist(Fil_att transientInstance) {
		log.debug("persisting Fil_att instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Fil_att instance) {
		log.debug("attaching dirty Fil_att instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Fil_att instance) {
		log.debug("attaching clean Fil_att instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Fil_att persistentInstance) {
		log.debug("deleting Fil_att instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fil_att merge(Fil_att detachedInstance) {
		log.debug("merging Fil_att instance");
		try {
			Fil_att result = (Fil_att) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Fil_att findById(java.lang.Integer id) {
		log.debug("getting Fil_att instance with id: " + id);
		try {
			Fil_att instance = (Fil_att) sessionFactory.getCurrentSession().get(
					Fil_att.class, id);
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

	public List<Fil_att> findByExample(Fil_att instance) {
		log.debug("finding Fil_att instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Fil_att> results = (List<Fil_att>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Fil_att")
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
	public List<Fil_att> findAll() {//select * from filAtt;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Fil_att.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Fil_att> findByNom(String n) {//select * from ville where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Fil_att.class).add(Restrictions.eq("idfil_att", n));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Fil_att> findAllWithFilter(Integer n) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Fil_att.class)
				.setFetchMode("service", FetchMode.JOIN)
				.add(Restrictions.eq("service.idservice", n));
		return crit.list();
	}	

@SuppressWarnings("unchecked")
public List<Fil_att> findByIdService(java.lang.Integer n) {//select * from client where cond;
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
			Fil_att.class)
			.setFetchMode("service", FetchMode.JOIN)
			.add(Restrictions.eq("service.idservice", n));
	return crit.list();
}

@SuppressWarnings("unchecked")
public List<Fil_att> findAllWithJoin() {
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
			Fil_att.class)
			.setFetchMode("service", FetchMode.JOIN);
			
	return crit.list();
}	




}
