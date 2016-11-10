package com.sav.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.sav.persistance.Client;
import com.sav.persistance.MachHisto;


/**
 * Home object for domain model class MachHisto.
 * @see com.sav.dao.Personne
 * @author Hibernate Tools
 */

public class MachHistoHome {
	
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
	
	public void persist(MachHisto mchHis) {
		log.debug("persisting MachHisto instance");
		try {
			sessionFactory.getCurrentSession().persist(mchHis);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	
	public void delete(MachHisto persistentInstance) {
		log.debug("deleting MachHisto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MachHisto merge(MachHisto detachedInstance) {
		log.debug("merging MachHisto instance");
		try {
			MachHisto result = (MachHisto) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

}
