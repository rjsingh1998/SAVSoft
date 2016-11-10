package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Statut;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Statut.
 * 
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class StatutHome {

	private static Logger log = Logger.getLogger(StatutHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			// return (SessionFactory) new InitialContext()
			// .lookup("SessionFactory");
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Statut transientInstance) {
		log.debug("persisting Statut instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Statut instance) {
		log.debug("attaching dirty Statut instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Statut instance) {
		log.debug("attaching clean Statut instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Statut persistentInstance) {
		log.debug("deleting Statut instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Statut merge(Statut destatutdInstance) {
		log.debug("merging Statut instance");
		try {
			Statut result = (Statut) sessionFactory.getCurrentSession().merge(
					destatutdInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Statut findById(java.lang.Integer id) {
		log.debug("getting statut instance with id: " + id);
		try {
			Statut instance = (Statut) sessionFactory.getCurrentSession().get(
					Statut.class, id);
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

	public List<Statut> findByExample(Statut instance) {
		log.debug("finding Statut instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Statut> results = (List<Statut>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Statut")
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
	public List<Statut> findAll() {// select * from statut;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Statut.class);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Statut> findByNom(String n) {// select * from statut where cond;
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Statut.class)
				.add(Restrictions.eq("designationStat", n));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Statut> findAllWithFilter(String valeurRecherche) {
		Criteria crit = sessionFactory
				.getCurrentSession()
				.createCriteria(Statut.class)
				.add(Restrictions.like("designationStat", valeurRecherche,
						MatchMode.ANYWHERE));
		return crit.list();

	}

	public Statut findByIdstat(Integer id) {

		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Statut.class)
				.add(Restrictions.eq("idstatut", id));

		return (Statut) crit.uniqueResult();
	}

}
