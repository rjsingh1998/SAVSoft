package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.App_acc;






import com.sav.persistance.App_panne;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class App_acc.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class App_accHome {

	private static Logger log = Logger.getLogger(App_accHome.class);

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

	public void persist(App_acc transientInstance) {
		log.debug("persisting App_acc instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(App_acc instance) {
		log.debug("attaching dirty App_acc instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(App_acc instance) {
		log.debug("attaching clean App_acc instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(App_acc persistentInstance) {
		log.debug("deleting App_acc instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public App_acc merge(App_acc detachedInstance) {
		log.debug("merging App_acc instance");
		try {
			App_acc result = (App_acc) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public App_acc findById(java.lang.Integer id) {
		log.debug("getting App_acc instance with id: " + id);
		try {
			App_acc instance = (App_acc) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.App_acc", id);
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

	public List<App_acc> findByExample(App_acc instance) {
		log.debug("finding App_acc instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<App_acc> results = (List<App_acc>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.App_acc")
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
	public List<App_acc> findAll() {//select * from app_acc;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_acc.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<App_acc> findByNom(String n) {//select * from app_acc where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_acc.class).add(Restrictions.eq("nom", n));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<App_acc> findAllWithJoin() {//select * from app_panne;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_acc.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("accessoire", FetchMode.JOIN);
		return (List<App_acc>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<App_acc> findByAppel(Integer id) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_acc.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("accessoire", FetchMode.JOIN)
				.createCriteria("appel")
				.add(Restrictions.eq("idappel", id));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<App_acc> findByAcc(Integer id) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_acc.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("accessoire", FetchMode.JOIN)
				.createCriteria("accessoire")
				.add(Restrictions.eq("idaccessoire", id));
		return crit.list();
	}
	
}
