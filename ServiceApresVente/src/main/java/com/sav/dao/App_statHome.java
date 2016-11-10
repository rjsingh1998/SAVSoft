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
import com.sav.persistance.App_stat;






import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class App_stat.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class App_statHome {

	private static Logger log = Logger.getLogger(App_statHome.class);

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

	public void persist(App_stat transientInstance) {
		log.debug("persisting App_stat instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(App_stat instance) {
		log.debug("attaching dirty App_stat instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(App_stat instance) {
		log.debug("attaching clean App_stat instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(App_stat persistentInstance) {
		log.debug("deleting App_stat instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public App_stat merge(App_stat detachedInstance) {
		log.debug("merging App_stat instance");
		try {
			App_stat result = (App_stat) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public App_stat findById(java.lang.Integer id) {
		log.debug("getting App_stat instance with id: " + id);
		try {
			App_stat instance = (App_stat) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.App_stat", id);
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

	public List<App_stat> findByExample(App_stat instance) {
		log.debug("finding App_stat instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<App_stat> results = (List<App_stat>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.App_stat")
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
	public List<App_stat> findAll() {//select * from app_stat;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_stat.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<App_stat> findByNom(String n) {//select * from app_stat where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_stat.class).add(Restrictions.eq("nom", n));
		return crit.list();
	}
	@SuppressWarnings("unchecked")
	public List<App_stat> findAllWithJoin() {//select * from app_stat;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_stat.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("stat", FetchMode.JOIN);
		return (List<App_stat>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<App_stat> findByAppel(Integer id) {//select * from app_stat where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_stat.class)
				.createAlias("appel", "app")
				.createAlias("statut", "stat")
				.add(Restrictions.eq("app.idappel", id));
		return crit.list();
	}
	

	
	@SuppressWarnings("unchecked")
	public List<App_stat> findByStat(Integer id) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_stat.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("statut", FetchMode.JOIN)
				.createCriteria("statut")
				.add(Restrictions.eq("idstatut", id));
		return crit.list();
	}
	
}
