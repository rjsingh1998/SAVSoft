package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;



import com.sav.persistance.Service;









import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Service.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class ServiceHome {

	private static Logger log = Logger.getLogger(ServiceHome.class);

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

	public void persist(Service transientInstance) {
		log.debug("persisting Service instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Service instance) {
		log.debug("attaching dirty Service instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Service instance) {
		log.debug("attaching clean Service instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Service persistentInstance) {
		log.debug("deleting Service instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Service merge(Service detachedInstance) {
		log.debug("merging Service instance");
		try {
			Service result = (Service) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Service findById(java.lang.Integer id) {
		log.debug("getting Service instance with id: " + id);
		try {
			Service instance = (Service) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Service", id);
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

	public List<Service> findByExample(Service instance) {
		log.debug("finding Service instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Service> results = (List<Service>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Service")
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
	public List<Service> findAll() {//select * from service;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Service.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Service> findByNom(String n) {//select * from service where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Service.class).add(Restrictions.eq("designationSer", n));
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Service> findAllWithFilter(String valeurRecherche) {
		
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Service.class)
				.add(Restrictions.like("designationSer", valeurRecherche, MatchMode.ANYWHERE));
		return crit.list() ;
	}

	@SuppressWarnings("unchecked")
	public List<Service> findServiceById( Integer n) {//select * from Service where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Service.class).add(Restrictions.eq("idservice", n));
		return crit.list();
}
}
