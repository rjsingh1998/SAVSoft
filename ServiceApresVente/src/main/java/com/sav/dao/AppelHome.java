package com.sav.dao;

// Generated 22 janv. 2015 15:22:58 by Hibernate Tools 3.4.0.CR1


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.sav.persistance.Appel;
import com.sav.persistance.Detaille_Etat;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Appel.
 * @see com.stage.pfe.dao.Personne
 * @author Hibernate Tools
 */
public class AppelHome {

	private static Logger log = Logger.getLogger(AppelHome.class);

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

	public void persist(Appel transientInstance) {
		log.debug("persisting Appel instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Appel instance) {
		log.debug("attaching dirty Appel instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Appel instance) {
		log.debug("attaching clean Appel instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Appel persistentInstance) {
		log.debug("deleting Appel instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Appel merge(Appel detachedInstance) {
		log.debug("merging Appel instance");
		try {
			Appel result = (Appel) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Appel findById(java.lang.Integer id) {
		log.debug("getting Appel instance with id: " + id);
		try {
			Appel instance = (Appel) sessionFactory.getCurrentSession().get(
					Appel.class, id);
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
	

	
	
	public List<Appel> findByExample(Appel instance) {
		log.debug("finding Appel instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Appel> results = (List<Appel>) sessionFactory
					.getCurrentSession()
					.createCriteria(Appel.class)
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
	public List<Appel> findAll() {//select * from appel;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Appel.class).addOrder(Property.forName("idappel").desc());
		
		
		 List<Appel> l=crit.list();
		 System.out.println("home: "+l.size());
		return l;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Appel> findByNom(String n) {//select * from appel where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Appel.class).add(Restrictions.eq("nom", n));
		
		return crit.list();
	}
	
	

	
	@SuppressWarnings({ "unchecked"})
	public List<Appel> findAllWithFilter(String attribut,
			String valeurRecherche, Date db, Date df ,Integer n) {
		
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Appel.class,"apl")
				.createAlias("apl.client", "clt")
				.createAlias("apl.etat", "eta")
				.createAlias("apl.machine", "mch")
				.addOrder(Property.forName("idappel").desc());
		

		if (attribut!=(null) && attribut.equals("Client") && valeurRecherche!=(null) ){

				crit .add(Restrictions.like("clt.nomclt",valeurRecherche , MatchMode.ANYWHERE));	
		}

		if (attribut!=(null) && attribut.equals("S/N") && valeurRecherche!=(null)  )
		crit  .add(Restrictions.like("mch.num_serie",valeurRecherche , MatchMode.ANYWHERE));
		

		
		if (db!=null){
			 crit.add(Restrictions.ge("date_entre",db));
		}
		
		if (df!=null){
			
			Calendar c= Calendar.getInstance();
			c.setTime(df);
			c.add(Calendar.DATE,1);
			
			df=c.getTime();
			crit.add(Restrictions.le("date_entre",df));
		}
		if (n!=null ){
			
			crit .add(Restrictions.eq("eta.idetat", n));
		}
		
		
		return crit.list();
	}

@SuppressWarnings("unchecked")
	
	public List<Appel> findAllWithJoin(){
		try {
		
		List<Appel> results = (List<Appel>) sessionFactory
		.getCurrentSession()
		.createCriteria(Appel.class)
		.setFetchMode("client", FetchMode.JOIN)
		.setFetchMode("machine", FetchMode.JOIN)
		.setFetchMode("statut", FetchMode.JOIN)
		.setFetchMode("etat", FetchMode.JOIN)
		.setFetchMode("info_apl", FetchMode.JOIN)
		.addOrder(Property.forName("idappel").desc())
		.list();
		System.out.println("result   "+results.size());
		return results;
		} catch (RuntimeException re) {
		throw re;
		}
		}
@SuppressWarnings("unchecked")
public List<Appel> findByEtat(Integer n) {
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
			Appel.class)
			.setFetchMode("etat", FetchMode.JOIN)
			.add(Restrictions.eq("etat.idetat", n))
			.addOrder(Property.forName("idappel").desc());
	return crit.list();
}	
@SuppressWarnings("unchecked")
public List<Appel> findByIdclt(java.lang.Integer n) {//select * from client where cond;
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
			Appel.class)
			.setFetchMode("client", FetchMode.JOIN)
			.add(Restrictions.eq("client.idclient", n));
	return crit.list();
}
@SuppressWarnings("unchecked")
public List<Appel> findByIdmach(java.lang.Integer n) {//select * from client where cond;
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
			Appel.class)
			.setFetchMode("machine", FetchMode.JOIN)
			.add(Restrictions.eq("machine.idmachine", n))
			.addOrder(Property.forName("idappel").desc());
	return crit.list();
}


@SuppressWarnings("unchecked")
public List<Appel> findByIdcltwithJoin(java.lang.Integer n) {//select * from apl where cond;
	Criteria crit = sessionFactory.getCurrentSession()
			.createCriteria(Appel.class, "apl")
			.createAlias("apl.client","clt")
			.createAlias("apl.machine","mch")
		    .createAlias("apl.etat", "eta")
		    .createAlias("mch.type","typ")
		    .add(Restrictions.eq("client.idclient", n));
	return crit.list();
}


}

