package com.sav.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private HibernateUtil() {
		
	}

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null)
			try {
				sessionFactory = new Configuration().configure()
						.buildSessionFactory();
			} catch (Throwable ex) {
				throw new ExceptionInInitializerError(ex);
			}

		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
