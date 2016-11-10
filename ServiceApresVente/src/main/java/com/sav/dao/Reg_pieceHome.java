package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Diagnostique;
import com.sav.persistance.Reg_piece;
import com.sav.persistance.Reg_tache;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Reg_piece.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class Reg_pieceHome {

	private static Logger log = Logger.getLogger(Reg_pieceHome.class);

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

	public void persist(Reg_piece transientInstance) {
		log.debug("persisting Reg_piece instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Reg_piece instance) {
		log.debug("attaching dirty Reg_piece instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Reg_piece instance) {
		log.debug("attaching clean Reg_piece instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Reg_piece persistentInstance) {
		log.debug("deleting Reg_piece instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Reg_piece merge(Reg_piece detachedInstance) {
		log.debug("merging Reg_piece instance");
		try {
			Reg_piece result = (Reg_piece) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Reg_piece findById(java.lang.Integer id) {
		log.debug("getting Reg_piece instance with id: " + id);
		try {
			Reg_piece instance = (Reg_piece) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Reg_piece", id);
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

	public List<Reg_piece> findByExample(Reg_piece instance) {
		log.debug("finding Reg_piece instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Reg_piece> results = (List<Reg_piece>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Reg_piece")
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
	public List<Reg_piece> findAll() {//select * from app_acc;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Reg_piece.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Reg_piece> findByNom(String n) {//select * from app_acc where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Reg_piece.class).add(Restrictions.eq("nom", n));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Reg_piece> findAllWithJoin() {//select * from app_panne;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Reg_piece.class)
				.setFetchMode("reparation", FetchMode.JOIN)
				.setFetchMode("piece", FetchMode.JOIN);
		return (List<Reg_piece>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Reg_piece> findByReparation(Integer id) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Reg_piece.class)
				.setFetchMode("reparation", FetchMode.JOIN)
				.setFetchMode("piece", FetchMode.JOIN)
				.createCriteria("reparation")
				.add(Restrictions.eq("idreparation", id));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Reg_piece> findByUtilisateur(Integer id) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Reg_piece.class)
				.setFetchMode("reparation", FetchMode.JOIN)
				.setFetchMode("piece", FetchMode.JOIN)
				.createCriteria("utilisateur")
				.add(Restrictions.eq("idutilisateur", id));
		return crit.list();
	}
	@SuppressWarnings("unchecked")
	public List<Reg_piece> findByRep_Utl(Integer idutl,Integer idrep) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Reg_piece.class,"reputl")
				.createAlias("reputl.reparation", "rep")
				.createAlias("reputl.utilisateur", "utl")
				
				.add(Restrictions.and(Restrictions.eq("rep.idreparation", idrep),Restrictions.eq("utl.idutilisateur", idutl)));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Reg_piece> findByPce(Integer id) {//select * from reg_pce where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Reg_piece.class)
				.setFetchMode("reglement", FetchMode.JOIN)
				.setFetchMode("piece", FetchMode.JOIN)
				.createCriteria("piece")
				.add(Restrictions.eq("idpiece", id));
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Reg_piece> findByIdrepWithJoin(Integer idrep) {

		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Reg_piece.class)
				.createAlias("reparation", "rep")
				.createAlias("piece", "pce")
				.createAlias("utilisateur", "utl")
				.createAlias("rep.appel", "apl")
				.createAlias("utl.service", "ser")
				.add(Restrictions.eq("rep.idreparation", idrep));

		return crit.list();

	}
	
	@SuppressWarnings("unchecked")
	public List<Reg_piece> findByIdUtl(java.lang.Integer n) {//select * from Reg_piece where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Reg_piece.class)
				.setFetchMode("utilisateur", FetchMode.JOIN)
				.add(Restrictions.eq("utilisateur.idutilisateur", n));
		return crit.list();
	}
	
}
