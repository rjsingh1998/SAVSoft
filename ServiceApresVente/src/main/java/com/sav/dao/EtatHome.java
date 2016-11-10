package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;



import com.sav.persistance.Etat;




import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Etat.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class EtatHome {

	private static Logger log = Logger.getLogger(EtatHome.class);

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

	public void persist(Etat transientInstance) {
		log.debug("persisting Etat instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Etat instance) {
		log.debug("attaching dirty Etat instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Etat instance) {
		log.debug("attaching clean Etat instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Etat persistentInstance) {
		log.debug("deleting Etat instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Etat merge(Etat deetatdInstance) {
		log.debug("merging Etat instance");
		try {
			Etat result = (Etat) sessionFactory.getCurrentSession()
					.merge(deetatdInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Etat findById(java.lang.Integer id) {
		log.debug("getting etat instance with id: " + id);
		try {
			Etat instance = (Etat) sessionFactory.getCurrentSession().get(
					Etat.class, id);
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

	public List<Etat> findByExample(Etat instance) {
		log.debug("finding Etat instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Etat> results = (List<Etat>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Etat")
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
	public List<Etat> findAll() {//select * from etat;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Etat.class).addOrder(Property.forName("idetat").asc());;
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Etat> findByNom(String n) {//select * from etat where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Etat.class).add(Restrictions.eq("designationEtat", n))
				.addOrder(Property.forName("idetat").asc());;
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Etat> findAllWithFilter(String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Etat.class)
				.add(Restrictions.like("designationEtat", valeurRecherche, MatchMode.ANYWHERE))
				.addOrder(Property.forName("idetat").asc());;
		return crit.list() ;
	}
	
	@SuppressWarnings("unchecked")
	public List<Etat> findEtatById( Integer n) {//select * from machine where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Etat.class).add(Restrictions.eq("idetat", n));
		return crit.list();
	}

}
