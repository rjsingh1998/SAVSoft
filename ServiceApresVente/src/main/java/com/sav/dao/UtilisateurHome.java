package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Utilisateur;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Utilisateur.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class UtilisateurHome {

	private static Logger log = Logger.getLogger(UtilisateurHome.class);

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

	public void persist(Utilisateur transientInstance) {
		log.debug("persisting Utilisateur instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Utilisateur instance) {
		log.debug("attaching dirty Utilisateur instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Utilisateur instance) {
		log.debug("attaching clean Utilisateur instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Utilisateur persistentInstance) {
		log.debug("deleting Utilisateur instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Utilisateur merge(Utilisateur detachedInstance) {
		log.debug("merging Utilisateur instance");
		try {
			Utilisateur result = (Utilisateur) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Utilisateur findById(java.lang.Integer id) {
		log.debug("getting Utilisateur instance with id: " + id);
		try {
			Utilisateur instance = (Utilisateur) sessionFactory.getCurrentSession()
					.get("com.sav.dao.Utilisateur", id);
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
	
	
	public Utilisateur findByIdWIthJoin(java.lang.Integer id) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Utilisateur.class)
				.add(Restrictions.eq("idutilisateur",id))
				.setFetchMode("ville", FetchMode.JOIN)
				.setFetchMode("grade", FetchMode.JOIN)
				.setFetchMode("service", FetchMode.JOIN);

		return (Utilisateur) crit.uniqueResult();
	}

	

	public List<Utilisateur> findByExample(Utilisateur instance) {
		log.debug("finding Utilisateur instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Utilisateur> results = (List<Utilisateur>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.vav.dao.Utilisateur")
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
	public List<Utilisateur> findAll() {//select * from utilisateur;
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Utilisateur.class);
		return crit.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findAllWithJoin() {

		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Utilisateur.class)
				.setFetchMode("grade", FetchMode.JOIN)
		        .setFetchMode("ville", FetchMode.JOIN)
		        .setFetchMode("service", FetchMode.JOIN);  
		
			
		
		return (List<Utilisateur>) crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByNom(String n) {//select * from utilisateur where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Utilisateur.class).add(Restrictions.eq("nom", n));
		return crit.list();
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByCin(String cin) {//select * from utilisateur where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Utilisateur.class).add(Restrictions.eq("cinUtl", cin));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByLogin(String login) {//select * from utilisateur where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Utilisateur.class).add(Restrictions.eq("login", login));
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findAllWithFilter(String attribut,
			String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Utilisateur.class)
		        .setFetchMode("grade", FetchMode.JOIN)
		        .setFetchMode("ville", FetchMode.JOIN)
		        .setFetchMode("service", FetchMode.JOIN);  

				
		
		if (attribut.equals("Utilisateur"))
			crit = crit.add(Restrictions.or(Restrictions.like("nomUtl",
					valeurRecherche, MatchMode.ANYWHERE), Restrictions.like(
					"prenomUtl", valeurRecherche, MatchMode.ANYWHERE)));

		if (attribut.equals("CIN"))
			crit  = crit.add(Restrictions.like("cinUtl", valeurRecherche, MatchMode.ANYWHERE));
		
		
		if (attribut.equals("Grade"))
			crit  = crit.createCriteria("grade")
			.add(Restrictions.like("libelleGrd", valeurRecherche, MatchMode.ANYWHERE));
			

		if (attribut.equals("Tel"))
			crit = crit.add(Restrictions.or(Restrictions.like("telUtl",
					valeurRecherche, MatchMode.ANYWHERE), Restrictions.like(
					"gsm1Utl", valeurRecherche, MatchMode.ANYWHERE),
					Restrictions.like("gsm2Utl", valeurRecherche,
							MatchMode.ANYWHERE)));

		return crit.list();
	}
	
	
	public Utilisateur findByLogMotPass(String l, String m) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Utilisateur.class)
				.add(Restrictions.and(Restrictions.eq("login", l),Restrictions.eq("motPass",m)));
		
		return (Utilisateur)crit.uniqueResult();
	}
	

	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByIdVil(java.lang.Integer n) {//select * from utilisateur where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Utilisateur.class)
				.setFetchMode("ville", FetchMode.JOIN)
				.add(Restrictions.eq("ville.idville", n));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByIdGrd(java.lang.Integer n) {//select * from utilisateur where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Utilisateur.class)
				.setFetchMode("grade", FetchMode.JOIN)
				.add(Restrictions.eq("grade.idGrade", n));
		return crit.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByMotPass(String m) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Utilisateur.class)
				.add(Restrictions.eq("motPass",m));
		
		return crit.list();
	}

	
}
