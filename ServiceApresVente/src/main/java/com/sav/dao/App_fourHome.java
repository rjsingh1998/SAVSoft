package com.sav.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.App_four;


public class App_fourHome {
	
	
	private static Logger log = Logger.getLogger(App_fourHome.class);

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

	public void persist(App_four transientInstance) {
		log.debug("persisting App_four instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(App_four instance) {
		log.debug("attaching dirty App_four instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(App_four instance) {
		log.debug("attaching clean App_four instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(App_four persistentInstance) {
		log.debug("deleting App_four instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public App_four merge(App_four detachedInstance) {
		log.debug("merging App_four instance");
		try {
			App_four result = (App_four) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public App_four findById(java.lang.Integer id) {
		log.debug("getting App_four instance with id: " + id);
		try {
			App_four instance = (App_four) sessionFactory.getCurrentSession()
					.get("com.sav.dao.App_four", id);
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

	public List<App_four> findByExample(App_four instance) {
		log.debug("finding App_four instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<App_four> results = (List<App_four>) sessionFactory
					.getCurrentSession()
					.createCriteria(App_four.class)
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
	public List<App_four> findAll() {//select * from App_four;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_four.class);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<App_four> findByFour(Integer id) {//select * from app_four where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_four.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("fournisseur", FetchMode.JOIN)
				.createCriteria("fournisseur")
				.add(Restrictions.eq("idfournisseur", id));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<App_four> findByAppel(Integer id) {//select * from app_four where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_four.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("fournisseur", FetchMode.JOIN)
				.createCriteria("appel")
				.add(Restrictions.eq("idappel", id)).addOrder(Property.forName("idappel").asc());
		return crit.list();
	}

}
