package com.ibm.ebuy.servlet;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class EBuyActionListener
 * @author QiaoXi
 */
@WebListener
public class EBuyActionListener implements HttpSessionListener,
		HttpSessionAttributeListener, HttpSessionActivationListener,
		HttpSessionBindingListener {
//	@EJB
//	CounterBean counterBean;

	/**
	 * Default constructor.
	 */
	public EBuyActionListener() {
	}
	public void sessionDestroyed(HttpSessionEvent se) {
	}

	/**
	 * Called when a session be created
	 */
	public void sessionCreated(HttpSessionEvent se) {
	}

	public void sessionDidActivate(HttpSessionEvent arg0) {
	}

	public void sessionWillPassivate(HttpSessionEvent arg0) {
	}

	public void valueBound(HttpSessionBindingEvent arg0) {
	}

	public void valueUnbound(HttpSessionBindingEvent arg0) {
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
	}

	public void attributeAdded(HttpSessionBindingEvent arg0) {
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {

	}

}
