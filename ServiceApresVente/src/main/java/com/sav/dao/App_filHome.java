package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.App_fil;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class App_fil.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class App_filHome {

	private static Logger log = Logger.getLogger(App_filHome.class);

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

	public void persist(App_fil transientInstance) {
		log.debug("persisting App_fil instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(App_fil instance) {
		log.debug("attaching dirty App_fil instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(App_fil instance) {
		log.debug("attaching clean App_fil instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(App_fil persistentInstance) {
		log.debug("deleting App_fil instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public App_fil merge(App_fil detachedInstance) {
		log.debug("merging App_fil instance");
		try {
			App_fil result = (App_fil) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public App_fil findById(java.lang.Integer id) {
		log.debug("getting App_fil instance with id: " + id);
		try {
			App_fil instance = (App_fil) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.App_fil", id);
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

	public List<App_fil> findByExample(App_fil instance) {
		log.debug("finding App_fil instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<App_fil> results = (List<App_fil>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.App_fil")
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
	public List<App_fil> findAll() {//select * from app_fil;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_fil.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<App_fil> findByNom(String n) {//select * from app_fil where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_fil.class).add(Restrictions.eq("nom", n));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<App_fil> findAllWithJoin() {//select * from app_panne;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_fil.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("fil_att", FetchMode.JOIN);
		return (List<App_fil>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<App_fil> findByAppel(Integer id) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_fil.class)
				.setFetchMode("appel", FetchMode.JOIN)
				.setFetchMode("fil_att", FetchMode.JOIN)
				.createCriteria("appel")
				.add(Restrictions.eq("idappel", id));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<App_fil> findByFil(Integer id) {//select * from app_fil where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_fil.class,"apf")
				.createAlias("apf.appel", "apl")
				.createAlias("apf.fil_att", "fil")
				
				.add(Restrictions.eq("fil.idfil_att", id))
				.addOrder(Property.forName("apf.priorite").asc())
				.addOrder(Property.forName("date_affect").asc());
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<App_fil> findByFilandDiag(Integer id) {//select * from app_fil where cond;
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				App_fil.class,"apf")
				.createAlias("apf.appel", "apl")
				.createAlias("apf.fil_att", "fil")
				.add(Restrictions.and(Restrictions.eq("fil.idfil_att", id),
						Restrictions.eq("apf.diagstiq", true)))
				.addOrder(Property.forName("apf.priorite").asc())
				.addOrder(Property.forName("date_affect").asc());
		return crit.list();
	}
}
