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
import com.sav.persistance.Detaille_Etat;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class App_panne.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class App_panneHome {

	private static Logger log = Logger.getLogger(App_panneHome.class);

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

	public void persist(App_panne transientInstance) {
		log.debug("persisting App_panne instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(App_panne instance) {
		log.debug("attaching dirty App_panne instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(App_panne instance) {
		log.debug("attaching clean App_panne instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(App_panne persistentInstance) {
		log.debug("deleting App_panne instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public App_panne merge(App_panne detachedInstance) {
		log.debug("merging App_panne instance");
		try {
			App_panne result = (App_panne) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public App_panne findById(java.lang.Integer id) {
		log.debug("getting App_panne instance with id: " + id);
		try {
			App_panne instance = (App_panne) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.App_panne", id);
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

	public List<App_panne> findByExample(App_panne instance) {
		log.debug("finding App_panne instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<App_panne> results = (List<App_panne>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.App_panne")
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
	public List<App_panne> findAll() {//select * from app_panne;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_panne.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<App_panne> findByNom(String n) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_panne.class).add(Restrictions.eq("nom", n));
		return crit.list();
	}
	@SuppressWarnings("unchecked")
	public List<App_panne> findAllWithJoin() {//select * from app_panne;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_panne.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("panne", FetchMode.JOIN);
		return (List<App_panne>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<App_panne> findByAppel(Integer id) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_panne.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("panne", FetchMode.JOIN)
				.createCriteria("appel")
				.add(Restrictions.eq("idappel", id));
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<App_panne> findByPan(Integer id) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_panne.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("panne", FetchMode.JOIN)
				.createCriteria("panne")
				.add(Restrictions.eq("idpanne", id));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<App_panne> findByIdaplWithJoin(Integer idapl) {

		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(App_panne.class)
				.createAlias("appel", "app")
				.createAlias("panne", "pan")
				.createAlias("pan.service", "ser")
				.add(Restrictions.eq("app.idappel", idapl));

		return crit.list();

	}

	
}
