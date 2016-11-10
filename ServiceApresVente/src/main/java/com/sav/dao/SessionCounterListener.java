package com.sav.dao;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



public class SessionCounterListener implements HttpSessionListener {

  private static int totalActiveSessions;
  
  
  
  public void init(ServletConfig config){
  }

  /**
   * Adds sessions to the context scoped HashMap when they begin.
   */
  
	
  public static int getTotalActiveSession(){
	return totalActiveSessions;
  }
	
  @Override
  public void sessionCreated(HttpSessionEvent event) {
	totalActiveSessions++;
	
	System.out.println("Current Session created :" + event.getSession().getId() + " at "+ new Date());
	
	System.out.println("sessionCreated - add one session into counter "+totalActiveSessions);
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent event) {
	  
	  HttpSession session = event.getSession();
	  System.out.println("Current Session destroyed :" + session.getId() + " Logging out user…");
	  
	totalActiveSessions--;
	System.out.println("sessionDestroyed - deduct one session from counter "+totalActiveSessions);
  }	
}