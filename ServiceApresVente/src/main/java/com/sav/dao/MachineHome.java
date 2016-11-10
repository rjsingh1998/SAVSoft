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
import com.sav.persistance.Machine;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Machine.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class MachineHome {

	private static Logger log = Logger.getLogger(MachineHome.class);

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

	public void persist(Machine transientInstance) {
		log.debug("persisting Machine instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Machine instance) {
		log.debug("attaching dirty Machine instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Machine instance) {
		log.debug("attaching clean Machine instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Machine persistentInstance) {
		log.debug("deleting Machine instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Machine merge(Machine detachedInstance) {
		log.debug("merging Machine instance");
		try {
			Machine result = (Machine) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Machine findById(java.lang.Integer id) {
		log.debug("getting Machine instance with id: " + id);
		try {
			Machine instance = (Machine) sessionFactory.getCurrentSession()
					.get("com.stage.pfe.dao.Machine", id);
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

	public List<Machine> findByExample(Machine instance) {
		log.debug("finding Machine instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Machine> results = (List<Machine>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Machine")
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
	public List<Machine> findAll() {//select * from machine;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Machine.class);
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Machine> findByNom(String n) {//select * from machine where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Machine.class).add(Restrictions.eq("num_serie", n))
				.setFetchMode("marque", FetchMode.JOIN)
		        .setFetchMode("type", FetchMode.JOIN);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Machine> findAllWithFilter(String attribut,
			String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Machine.class)
		        .setFetchMode("type", FetchMode.JOIN)
		        .setFetchMode("marque", FetchMode.JOIN);
				
		
		
		if (attribut.equals("N°serie"))
			crit = crit.add(Restrictions.like("num_serie",
					valeurRecherche, MatchMode.ANYWHERE));
		
		
		
		if (attribut.equals("Model"))
			crit = crit.add(Restrictions.like("model",
					valeurRecherche, MatchMode.ANYWHERE));
		

		if (attribut.equals("Type"))
			crit = crit.createCriteria("type")
			            .add(Restrictions.like("designationType",valeurRecherche , MatchMode.ANYWHERE));
		
		if (attribut.equals("Marque"))
			crit = crit.createCriteria("marque")
			            .add(Restrictions.like("designationMarq",valeurRecherche , MatchMode.ANYWHERE));

		return crit.list();
	}
@SuppressWarnings("unchecked")
	public List<Machine> findAllWithJoin(){
   	Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Machine.class)
				.setFetchMode("marque", FetchMode.JOIN)
		        .setFetchMode("type", FetchMode.JOIN);
   	
   	return (List<Machine>) crit.list();
}
   


@SuppressWarnings("unchecked")
public List<Machine> findByIdTyp(java.lang.Integer n) {//select * from Machine where cond;
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
			Machine.class)
			.setFetchMode("type", FetchMode.JOIN)
			.add(Restrictions.eq("type.idtype", n));
	return crit.list();
}


@SuppressWarnings("unchecked")
public List<Machine> findByIdMarq(java.lang.Integer n) {//select * from Machine where cond;
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
			Machine.class)
			.setFetchMode("marque", FetchMode.JOIN)
			.add(Restrictions.eq("marque.idmarque", n));
	return crit.list();
}



public Machine findByIdWithJoin(Integer idMach){
	Criteria crit = sessionFactory.getCurrentSession()
			.createCriteria(Machine.class)
			.setFetchMode("marque", FetchMode.JOIN)
	        .setFetchMode("type", FetchMode.JOIN)
	        .add(Restrictions.eq("idmachine", idMach));
	
	return (Machine) crit.uniqueResult();
}


}
