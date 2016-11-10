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
import com.sav.persistance.Fournisseur;
import com.sav.persistance.Utilisateur;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Fournisseur.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class FournisseurHome {

	private static Logger log = Logger.getLogger(FournisseurHome.class);

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

	public void persist(Fournisseur transientInstance) {
		log.debug("persisting Fournisseur instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Fournisseur instance) {
		log.debug("attaching dirty Fournisseur instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Fournisseur instance) {
		log.debug("attaching clean Fournisseur instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Fournisseur persistentInstance) {
		log.debug("deleting Fournisseur instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fournisseur merge(Fournisseur detachedInstance) {
		log.debug("merging Fournisseur instance");
		try {
			Fournisseur result = (Fournisseur) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Fournisseur findById(java.lang.Integer id) {
		log.debug("getting Fournisseur instance with id: " + id);
		try {
			Fournisseur instance = (Fournisseur) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Fournisseur", id);
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

	public List<Fournisseur> findByExample(Fournisseur instance) {
		log.debug("finding Fournisseur instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Fournisseur> results = (List<Fournisseur>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Fournisseur")
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
	public List<Fournisseur> findAll() {//select * from fournisseur;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Fournisseur.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Fournisseur> findByNom(String n) {//select * from fournisseur where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Fournisseur.class).add(Restrictions.eq("nom", n));
		return crit.list();
	}
	

	@SuppressWarnings("unchecked")
	public List<Fournisseur> findByVille(String n) {//select * from fournisseur where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Fournisseur.class).add(Restrictions.eq("ville", n)).setFetchMode("ville", FetchMode.JOIN);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Fournisseur> findAllWithFilter(String attribut,
			String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Fournisseur.class).setFetchMode("ville", FetchMode.JOIN)
				.setFetchMode("type_four", FetchMode.JOIN);

		if (attribut.equals("Fournisseur"))
			crit = crit.add(Restrictions.like("nomfour",
					valeurRecherche, MatchMode.ANYWHERE));
		
		
		
		if (attribut.equals("Gsm"))
			crit = crit.add(Restrictions.or(Restrictions.like("gsm1four",
					valeurRecherche, MatchMode.ANYWHERE), Restrictions.like(
					"gsm2four", valeurRecherche, MatchMode.ANYWHERE)));
		
		
		if (attribut.equals("Tel"))
			crit = crit.add(Restrictions.like("telfour",
					valeurRecherche, MatchMode.ANYWHERE));
		
		return crit.list();
	}

@SuppressWarnings("unchecked")
	
	public List<Fournisseur> findAllWithJoin(){
		try {
		
		List<Fournisseur> results = (List<Fournisseur>) sessionFactory
		.getCurrentSession()
		.createCriteria(Fournisseur.class)
		.setFetchMode("ville", FetchMode.JOIN)
		.setFetchMode("type_four", FetchMode.JOIN)
		.list();
		return results;
		} catch (RuntimeException re) {
		throw re;

		}	

		}

@SuppressWarnings("unchecked")
public List<Fournisseur> findByIdVil(java.lang.Integer n) {//select * from Fournisseur where cond;
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
			Fournisseur.class)
			.setFetchMode("ville", FetchMode.JOIN)
			.add(Restrictions.eq("ville.idville", n));
	return crit.list();
}

@SuppressWarnings("unchecked")
public List<Fournisseur> findByIdTypFour(java.lang.Integer n) {//select * from Fournisseur where cond;
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
			Fournisseur.class)
			.setFetchMode("type_four", FetchMode.JOIN)
			.add(Restrictions.eq("type_four.idtype_four", n));
	return crit.list();
}

}
