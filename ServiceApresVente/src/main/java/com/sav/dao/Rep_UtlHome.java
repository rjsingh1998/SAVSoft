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
import com.sav.persistance.Rep_Utl;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Rep_Utl.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class Rep_UtlHome {

	private static Logger log = Logger.getLogger(Rep_UtlHome.class);

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

	public void persist(Rep_Utl transientInstance) {
		log.debug("persisting Rep_Utl instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Rep_Utl instance) {
		log.debug("attaching dirty Rep_Utl instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Rep_Utl instance) {
		log.debug("attaching clean Rep_Utl instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Rep_Utl persistentInstance) {
		log.debug("deleting Rep_Utl instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rep_Utl merge(Rep_Utl detachedInstance) {
		log.debug("merging Rep_Utl instance");
		try {
			Rep_Utl result = (Rep_Utl) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Rep_Utl findById(java.lang.Integer id) {
		log.debug("getting Rep_Utl instance with id: " + id);
		try {
			Rep_Utl instance = (Rep_Utl) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Rep_Utl", id);
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

	public List<Rep_Utl> findByExample(Rep_Utl instance) {
		log.debug("finding Rep_Utl instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Rep_Utl> results = (List<Rep_Utl>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Rep_Utl")
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
	public List<Rep_Utl> findAll() {//select * from app_acc;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Rep_Utl.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Rep_Utl> findByNom(String n) {//select * from app_acc where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Rep_Utl.class).add(Restrictions.eq("nom", n));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Rep_Utl> findAllWithJoin() {//select * from app_panne;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Rep_Utl.class)
				.setFetchMode("reparation", FetchMode.JOIN)
				.setFetchMode("utilisateur", FetchMode.JOIN);
		return (List<Rep_Utl>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Rep_Utl> findByUtilisateur(Integer id) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Rep_Utl.class)
				.setFetchMode("reparation", FetchMode.JOIN)
				.setFetchMode("utilisateur", FetchMode.JOIN)
				.createCriteria("utilisateur")
				.add(Restrictions.eq("idutilisateur", id));
		return crit.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Rep_Utl> findByRep_Utl(Integer idutl,Integer idrep) {//select * from app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Rep_Utl.class,"reputl")
				.createAlias("reputl.reparation", "rep")
				.createAlias("reputl.utilisateur", "utl")
				
				.add(Restrictions.and(Restrictions.eq("rep.idreparation", idrep),Restrictions.eq("utl.idutilisateur", idutl)));
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Rep_Utl> findRegUtlByIdUtl(java.lang.Integer n) {//select * from Rep_Utl where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Rep_Utl.class)
				.setFetchMode("utilisateur", FetchMode.JOIN)
				.add(Restrictions.eq("utilisateur.idutilisateur", n));
		return crit.list();
	}
	
	
}
