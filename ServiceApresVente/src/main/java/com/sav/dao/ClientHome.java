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
import com.sav.persistance.Client;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Client.
 * @see com.sav.dao.Personne
 * @author Hibernate Tools
 */
public class ClientHome {

	private static Logger log = Logger.getLogger(ClientHome.class);

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

	public void persist(Client transientInstance) {
		log.debug("persisting Client instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Client instance) {
		log.debug("attaching dirty Client instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Client instance) {
		log.debug("attaching clean Client instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Client persistentInstance) {
		log.debug("deleting Client instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Client merge(Client detachedInstance) {
		log.debug("merging Client instance");
		try {
			Client result = (Client) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	
		public Client findById(java.lang.Integer id) {
			log.debug("getting Ville instance with id: " + id);
			try {
				Client instance = (Client) sessionFactory.getCurrentSession().get(
						Client.class, id);
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
	public List<Client> findByExample(Client instance) {
		log.debug("finding Client instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Client> results = (List<Client>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.stage.pfe.dao.Client")
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
	public List<Client> findAll() {//select * from client;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Client.class).addOrder(Property.forName("idclient").asc());
		return crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Client> findByNom(String n) {//select * from client where cond;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Client.class).add(Restrictions.eq("nom", n))
				.addOrder(Property.forName("idclient").asc());
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Client> findAllWithFilter(String attribut,
			String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Client.class)
				.setFetchMode("ville", FetchMode.JOIN)
				.addOrder(Property.forName("idclient").asc());

		
		
		if (attribut.equals("Client"))
			crit = crit.add(Restrictions.or(Restrictions.like("nomclt",
					valeurRecherche, MatchMode.ANYWHERE)));
		
		
		if (attribut.equals("GSM"))
			crit = crit.add(Restrictions.or(Restrictions.like("gsm1clt",
					valeurRecherche, MatchMode.ANYWHERE), Restrictions.like(
					"gsm2clt", valeurRecherche, MatchMode.ANYWHERE)));
		
		
		if (attribut.equals("Tel"))
			crit = crit.add(Restrictions.like("telclt",
					valeurRecherche, MatchMode.ANYWHERE));
		


		return crit.list();

	}
	
    @SuppressWarnings("unchecked")
	public List<Client> findAllWithJoin(){
    	Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Client.class)
				.setFetchMode("ville", FetchMode.JOIN)
				.addOrder(Property.forName("idclient").asc());
    	
    	return (List<Client>) crit.list();
}
     


public Client findByIdWIthJoin(java.lang.Integer id) {
	Criteria crit = sessionFactory.getCurrentSession()
			.createCriteria(Client.class)
			.add(Restrictions.eq("idclient",id))
			.setFetchMode("ville", FetchMode.JOIN);
	
	

	return (Client) crit.uniqueResult() ;
}

@SuppressWarnings("unchecked")
public List<Client> findByIdVil(java.lang.Integer n) {//select * from client where cond;
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
			Client.class)
			.setFetchMode("ville", FetchMode.JOIN)
			.add(Restrictions.eq("ville.idville", n));
	return crit.list();
}



}
