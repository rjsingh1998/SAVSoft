package com.sav.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sav.dao.HibernateUtil;
import com.sav.dao.MachHistoHome;
import com.sav.persistance.MachHisto;

public class MachHistoService {

	MachHistoHome dao; 
	
	public MachHistoService(){
		dao=new MachHistoHome();
	}
	
	
	public void  supprimerMachHisto(Integer idMachHisto)
	{
		
		
		Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx= null ; 
		try{
			
			tx=session.beginTransaction();
			MachHisto mchHisto=new MachHisto();
			mchHisto.setIdMachHisto(idMachHisto);
			
			dao.delete(mchHisto);
						
			
			tx.commit() ;
			}catch(RuntimeException ex){ 
			if(tx!= null) tx.rollback();
			ex.printStackTrace() ; 
				}
	}
	
	public void ajouterMachHisto(MachHisto mchHisto) {
		Session	session=HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx= null ; 
		try{
			
			tx=session.beginTransaction();
			dao.persist(mchHisto);
			tx.commit() ;
			}catch(RuntimeException ex){ 
				
				if(tx!= null) tx.rollback(); ex.printStackTrace() ; 
			}
		
	}

	
}
