package edu.metrostate.ics425.reversi.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;

import edu.metrostate.ics425.reversi.model.Game;

/**
 * Application Lifecycle Listener implementation class AppListener
 *
 */
@WebListener
public class AppListener {
    /**
     * Default constructor. 
     */
    public AppListener() {
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	se.getSession().setAttribute("game", new Game());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    }
}
