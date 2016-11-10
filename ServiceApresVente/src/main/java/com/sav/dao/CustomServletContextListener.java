package com.sav.dao;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Instanciates a HashMap for holding references to session objects, and
 * binds it to context scope.
 * Also instanciates the mock database (UserDB) and binds it to 
 * context scope.
 * @author Ben Souther; ben@souther.us
 * @since Sun May  8 18:57:10 EDT 2005
 */
public class CustomServletContextListener implements ServletContextListener{

    public void contextInitialized(ServletContextEvent event){
        ServletContext context = event.getServletContext();

        //
        // instanciate a map to store references to all the active
        // sessions and bind it to context scope.
        //
        HashMap activeUsers = new HashMap();
        context.setAttribute("activeUsers", activeUsers);
    }

    /**
     * Needed for the ServletContextListener interface.
     */
    public void contextDestroyed(ServletContextEvent event){
        // To overcome the problem with losing the session references
        // during server restarts, put code here to serialize the
        // activeUsers HashMap.  Then put code in the contextInitialized
        // method that reads and reloads it if it exists...
    }
}
