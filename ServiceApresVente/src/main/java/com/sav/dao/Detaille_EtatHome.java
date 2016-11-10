package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Detaille_Etat;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Detaille_Etat.
 * 
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class Detaille_EtatHome {

	private static Logger log = Logger.getLogger(Detaille_EtatHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			// return (SessionFactory) new InitialContext()
			// .lookup("SessionFactory");
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Detaille_Etat transientInstance) {
		log.debug("persisting Detaille_Etat instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Detaille_Etat instance) {
		log.debug("attaching dirty Detaille_Etat instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Detaille_Etat instance) {
		log.debug("attaching clean Detaille_Etat instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Detaille_Etat persistentInstance) {
		log.debug("deleting Detaille_Etat instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Detaille_Etat merge(Detaille_Etat dedetaille_etatdInstance) {
		log.debug("merging Detaille_Etat instance");
		try {
			Detaille_Etat result = (Detaille_Etat) sessionFactory
					.getCurrentSession().merge(dedetaille_etatdInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Detaille_Etat findById(java.lang.Integer id) {
		log.debug("getting detaille_etat instance with id: " + id);
		try {
			Detaille_Etat instance = (Detaille_Etat) sessionFactory
					.getCurrentSession().get(Detaille_Etat.class, id);
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

	public List<Detaille_Etat> findByExample(Detaille_Etat instance) {
		log.debug("finding Detaille_Etat instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Detaille_Etat> results = (List<Detaille_Etat>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Detaille_Etat")
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
	public List<Detaille_Etat> findAll() {// select * from detaille_etat;
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Detaille_Etat.class)
				.addOrder(Property.forName("iddetaille_etat").asc());
		;
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Detaille_Etat> findByNom(String n) {// select * from
													// detaille_etat where cond;
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Detaille_Etat.class)
				.add(Restrictions.eq("designationDetaille_Etat", n))
				.addOrder(Property.forName("iddetaille_etat").asc());
		;
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Detaille_Etat> findAllWithFilter(String valeurRecherche) {
		Criteria crit = sessionFactory
				.getCurrentSession()
				.createCriteria(Detaille_Etat.class)
				.add(Restrictions.like("designationDetaille_Etat",
						valeurRecherche, MatchMode.ANYWHERE))
				.addOrder(Property.forName("iddetaille_etat").asc());
		;
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Detaille_Etat> findDetaille_EtatById(Integer n) {// select *
																	// from
																	// machine
																	// where
																	// cond;
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Detaille_Etat.class)
				.add(Restrictions.eq("iddetaille_etat", n));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Detaille_Etat> findByAppel(Integer id) {// select * from
														// app_panne where cond;
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Detaille_Etat.class)
				.createAlias("appel", "app")
				.add(Restrictions.eq("app.idappel", id));
		return crit.list();
	}

	public Detaille_Etat findByIdAplWithJoin(Integer idApl, Integer idSer, String typApl) {

		Criteria crit = sessionFactory
				.getCurrentSession()
				.createCriteria(Detaille_Etat.class)
				.createAlias("appel", "app")
				.createAlias("etat", "eta")
				.createAlias("service", "ser")
	            .add(Restrictions.and(Restrictions.eq("app.idappel", idApl),
				Restrictions.eq("ser.idservice", idSer),
				Restrictions.eq("typeApl",typApl)));

		return (Detaille_Etat) crit.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<Detaille_Etat> findAllByIdAplWithJoin(Integer idApl) {

		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Detaille_Etat.class)
				.createAlias("appel", "app")
				.createAlias("etat", "eta")
				.createAlias("service", "ser")
				.add(Restrictions.eq("app.idappel", idApl));

		return crit.list();

	}

}
