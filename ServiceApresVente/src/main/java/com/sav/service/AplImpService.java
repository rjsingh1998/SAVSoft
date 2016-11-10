package com.sav.service;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sav.dao.AplImpHome;
import com.sav.dao.HibernateUtil;
import com.sav.persistance.AplImp;

public class AplImpService {

	AplImpHome dao; 
	
	public AplImpService(){
		dao=new AplImpHome();
	}
	
	
	public void  supprimerAplImp(Integer idAplImp)
	{
		
		
		Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx= null ; 
		try{
			
			tx=session.beginTransaction();
			AplImp aplImp=new AplImp();
			aplImp.setIdAplImp(idAplImp);
			
			dao.delete(aplImp);
						
			
			tx.commit() ;
			}catch(RuntimeException ex){ 
			if(tx!= null) tx.rollback();
			ex.printStackTrace() ; 
				}
	}
	
	public void ajouterAplImp(AplImp aplImp) {
		Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx= null ; 
		try{
			
			tx=session.beginTransaction();
			dao.persist(aplImp);
			tx.commit() ;
			}catch(RuntimeException ex){ 
				
				if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
			}
		
	}

	
}
