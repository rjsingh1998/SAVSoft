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
import com.sav.persistance.Panne;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Panne.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class PanneHome {

	private static Logger log = Logger.getLogger(PanneHome.class);

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

	public void persist(Panne transientInstance) {
		log.debug("persisting Panne instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Panne instance) {
		log.debug("attaching dirty Panne instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Panne instance) {
		log.debug("attaching clean Panne instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Panne persistentInstance) {
		log.debug("deleting Panne instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Panne merge(Panne detachedInstance) {
		log.debug("merging Machine instance");
		try {
			Panne result = (Panne) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Panne findById(java.lang.Integer id) {
		log.debug("getting Panne instance with id: " + id);
		try {
			Panne instance = (Panne) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Panne", id);
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

	public List<Panne> findByExample(Panne instance) {
		log.debug("finding Panne instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Panne> results = (List<Panne>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Panne")
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
	public List<Panne> findAll() {//select * from panne;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Panne.class).addOrder(Property.forName("idpanne").asc());
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Panne> findByNom(String n) {//select * from Panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Panne.class).add(Restrictions.eq("designationPan", n)) .setFetchMode("service", FetchMode.JOIN)
				.addOrder(Property.forName("idpanne").asc());
		return crit.list();
	}
	

	@SuppressWarnings("unchecked")
	public List<Panne> findAllWithFilter(String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Panne.class)
				.add(Restrictions.like("designationPan", valeurRecherche, MatchMode.ANYWHERE))
				.addOrder(Property.forName("idpanne").asc());
		return crit.list() ;
	}
	
	@SuppressWarnings("unchecked")
	public List<Panne> findAllWithFilter(String attribut , String valeurRecherche) {
		
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Panne.class)
		        .setFetchMode("service", FetchMode.JOIN);
		
		if (attribut.equals("Panne"))
			crit = crit.add(Restrictions.like("designationPan", valeurRecherche, MatchMode.ANYWHERE))
					.addOrder(Property.forName("idpanne").desc());
		
		if (attribut.equals("Service"))
		{
			crit = crit.createCriteria("service")
			            .add(Restrictions.like("designationSer",valeurRecherche , MatchMode.ANYWHERE))
			            .addOrder(Property.forName("idpanne").desc());
		               
			
		}
		
		return crit.list() ;
	}
	


	
	@SuppressWarnings("unchecked")
	public List<Panne> findAllWithJoin() {

		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Panne.class)
				.setFetchMode("service", FetchMode.JOIN)
				.addOrder(Property.forName("idpanne").desc());
		
		return (List<Panne>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Panne> findByIdSvce(java.lang.Integer n) {//select * from panne where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Panne.class)
				.setFetchMode("service", FetchMode.JOIN)
				.add(Restrictions.eq("service.idservice", n));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Panne> findbyIdPanWithJoin(Integer idPan){
   	Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Panne.class)
				.setFetchMode("service", FetchMode.JOIN)
				.add(Restrictions.eq("idpanne", idPan));
		       
   	
   	return (List<Panne>) crit.list();
}
	
}
