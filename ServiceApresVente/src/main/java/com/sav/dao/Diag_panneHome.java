
package com.sav.dao;

//Generated 10 oct 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Diag_panne;

import static org.hibernate.criterion.Example.create;

/**
* Home object for domain model class Diag_panne.
* @see com.stage.pfe.dao.Personne
* @author Hibernate Tools
*/
public class Diag_panneHome {

	private static Logger log = Logger.getLogger(Diag_panneHome.class);

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

	public void persist(Diag_panne transientInstance) {
		log.debug("persisting Diag_panne instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Diag_panne instance) {
		log.debug("attaching dirty Diag_panne instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Diag_panne instance) {
		log.debug("attaching clean Diag_panne instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Diag_panne persistentInstance) {
		log.debug("deleting Diag_panne instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Diag_panne merge(Diag_panne detachedInstance) {
		log.debug("merging Diag_panne instance");
		try {
			Diag_panne result = (Diag_panne) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Diag_panne findById(java.lang.Integer id) {
		log.debug("getting Diag_panne instance with id: " + id);
		try {
			Diag_panne instance = (Diag_panne) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Diag_panne", id);
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

	public List<Diag_panne> findByExample(Diag_panne instance) {
		log.debug("finding Diag_panne instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Diag_panne> results = (List<Diag_panne>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.sav.dao.Diag_panne")
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
	public List<Diag_panne> findAll() {//select * from Diag_panne;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Diag_panne.class);
		return crit.list();
	}
	
	

	@SuppressWarnings("unchecked")
	public List<Diag_panne> findAllWithJoin() {//select * from Diag_panne;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Diag_panne.class)
				.setFetchMode("diagnostique", FetchMode.JOIN)
				.setFetchMode("panne", FetchMode.JOIN);
		return (List<Diag_panne>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Diag_panne> findByDiag(Integer id) {//select * from Diag_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Diag_panne.class)
				.setFetchMode("diagnostique", FetchMode.JOIN)
				.setFetchMode("panne", FetchMode.JOIN)
				.createCriteria("diagnostique")
				.add(Restrictions.eq("iddiag", id));
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Diag_panne> findByPan(Integer id) {//select * from Diag_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Diag_panne.class)
				.setFetchMode("diagnostique", FetchMode.JOIN)
				.setFetchMode("panne", FetchMode.JOIN)
				.createCriteria("panne")
				.add(Restrictions.eq("idpanne", id));
		return crit.list();

	}
	
}
