/**
 * 
 */
package com.creckett.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.creckett.logsmgmt.LoggerInstancesManager;

/**
 * @author Dhaval
 *
 */
public class HTTPResponseInterceptor implements HandlerInterceptor {

	private static final Logger LOG = LoggerInstancesManager.getLogger(HTTPResponseInterceptor.class);
	/**
	 * 
	 */
	public HTTPResponseInterceptor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView modelAndView) throws Exception {
		LOG.info("Entering postHandle") ;
		
		if ( modelAndView != null ) {
			LOG.info("View obtained : " + modelAndView.getViewName() ) ;
			if ( "homescreen".equalsIgnoreCase(modelAndView.getViewName() ) ) {
				Map model = modelAndView.getModel() ;
				LOG.info("homescreen Interceptor's map : " + model ) ;
			}
		}
		LOG.info("Leaving postHandle") ;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		LOG.info("Entering preHandle") ;
		LOG.info("Leaving preHandle") ;
		return true;
	}

}
