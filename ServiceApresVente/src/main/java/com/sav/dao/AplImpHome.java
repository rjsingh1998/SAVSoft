package com.sav.dao;


import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import com.sav.persistance.AplImp;


/**
 * Home object for domain model class AplImp.
 * @see com.sav.dao.AplImp
 * @author Hibernate Tools
 */

public class AplImpHome {
	
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
	
	public void persist(AplImp aplimp) {
		log.debug("persisting AplImp instance");
		try {
			sessionFactory.getCurrentSession().persist(aplimp);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	
	public void delete(AplImp persistentInstance) {
		log.debug("deleting AplImp instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AplImp merge(AplImp detachedInstance) {
		log.debug("merging AplImp instance");
		try {
			AplImp result = (AplImp) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

}
