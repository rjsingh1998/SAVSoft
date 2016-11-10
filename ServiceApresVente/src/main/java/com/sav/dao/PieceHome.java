package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Piece;


import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Piece.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class PieceHome {

	private static Logger log = Logger.getLogger(PieceHome.class);

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

	public void persist(Piece transientInstance) {
		log.debug("persisting Piece instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Piece instance) {
		log.debug("attaching dirty Piece instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Piece instance) {
		log.debug("attaching clean Piece instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Piece persistentInstance) {
		log.debug("deleting Piece instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Piece merge(Piece detachedInstance) {
		log.debug("merging Piece instance");
		try {
			Piece result = (Piece) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Piece findById(java.lang.Integer id) {
		log.debug("getting Piece instance with id: " + id);
		try {
			Piece instance = (Piece) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Piece", id);
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

	public List<Piece> findByExample(Piece instance) {
		log.debug("finding Piece instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Piece> results = (List<Piece>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Piece")
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
	public List<Piece> findAll() {//select * from piece;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Piece.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Piece> findByNom(String n) {//select * from Piece where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Piece.class).add(Restrictions.eq("designationPce", n));
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Piece> findAllWithFilter(String valeurRecherche,String attribut) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Piece.class);
				if (attribut.equals("Designation"))
					crit = crit.add(Restrictions.like("designationPce",
							valeurRecherche, MatchMode.ANYWHERE));
				if (attribut.equals("Prix"))
					crit = crit.add(Restrictions.like("prixPce",
							valeurRecherche, MatchMode.ANYWHERE));
		return crit.list() ;
	}

}
