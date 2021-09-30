/**
 * 
 */
package com.creckett.web;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.creckett.constant.SessionAttributes;
import com.creckett.model.User;
import com.creckett.service.UserService;

/**
 * @author Latesh
 *
 */
public class CreckettSessionListner implements HttpSessionListener {

	
	private UserService userService;
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {

		HttpSession session = sessionEvent.getSession();
		User user = (User) session.getAttribute(SessionAttributes.USER);
		if( user != null && user.isPlaying() ){
			user.setPlaying(false);
			user.setMarket(null);
			if( userService == null ){
		          ApplicationContext ctx = 
		                WebApplicationContextUtils.
		                      getWebApplicationContext(session.getServletContext());
		 
		          userService = (UserService) ctx.getBean("userService");
			}
			userService.updateUser(user);
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
