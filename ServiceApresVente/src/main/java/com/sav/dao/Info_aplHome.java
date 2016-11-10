package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;







import com.sav.persistance.Info_apl;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Info_apl.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class Info_aplHome {

	private static Logger log = Logger.getLogger(Info_aplHome.class);

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

	public void persist(Info_apl transientInstance) {
		log.debug("persisting Info_apl instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Info_apl instance) {
		log.debug("attaching dirty Info_apl instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Info_apl instance) {
		log.debug("attaching clean Info_apl instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Info_apl persistentInstance) {
		log.debug("deleting Info_apl instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Info_apl merge(Info_apl deetatdInstance) {
		log.debug("merging Info_apl instance");
		try {
			Info_apl result = (Info_apl) sessionFactory.getCurrentSession()
					.merge(deetatdInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Info_apl findById(java.lang.Integer id) {
		log.debug("getting Info_aplinstance with id: " + id);
		try {
			Info_apl instance = (Info_apl) sessionFactory.getCurrentSession().get(
					Info_apl.class, id);
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

	public List<Info_apl> findByExample(Info_apl instance) {
		log.debug("finding Info_apl instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Info_apl> results = (List<Info_apl>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Info_apl")
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
	public List<Info_apl> findAll() {//select * from Info_apl;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Info_apl.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Info_apl> findByNom(String n) {//select * from Info_apl where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Info_apl.class).add(Restrictions.eq("designationinfo_apl", n));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Info_apl> findAllWithFilter(String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Info_apl.class)
				.add(Restrictions.like("designationinfo_apl", valeurRecherche, MatchMode.ANYWHERE));
		return crit.list() ;
	}
	
	@SuppressWarnings("unchecked")
	public List<Info_apl> findInfo_aplById( Integer n) {//select * from machine where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Info_apl.class).add(Restrictions.eq("idinfo_apl", n));
		return crit.list();
	}

}
