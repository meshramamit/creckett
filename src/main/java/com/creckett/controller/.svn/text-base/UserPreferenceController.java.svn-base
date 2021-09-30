package com.creckett.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.creckett.constant.SessionAttributes;
import com.creckett.exception.AuthenticationException;
import com.creckett.logsmgmt.LoggerInstancesManager;
import com.creckett.model.User;
import com.creckett.model.UserPreference;
import com.creckett.service.UserPreferenceService;

/**
 * @author vipul
 * 
 */
public class UserPreferenceController extends MultiActionController {

	private static final Logger LOG = LoggerInstancesManager.getLogger(UserPreferenceController.class);
	
	private UserPreferenceService userPreferenceService;
	
	/**
	 * @return the userPreferenceService
	 */
	public UserPreferenceService getUserPreferenceService() {
		return userPreferenceService;
	}

	/**
	 * @param userPreferenceService the userPreferenceService to set
	 */
	public void setUserPreferenceService(UserPreferenceService userPreferenceService) {
		this.userPreferenceService = userPreferenceService;
	}

	
	/**
	 * This method creates the feedback as per provided parameters
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveUserPreference(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		User user = (User) request.getSession().getAttribute(
				SessionAttributes.USER);

//		User user = getHardCodedUser();
		// TESTING

		Map<String, Object> model = new HashMap<String, Object>();
		if (user != null) {
			try {
					String showVideoTutorial = request.getParameter("show_video_tutorials");
					String showAssistant = request.getParameter("show_assistant");
					String termsAccepted = request.getParameter("terms_accepted");

					UserPreference userPreference = userPreferenceService.saveUserPreference(user, showVideoTutorial, showAssistant,termsAccepted);

					model.put("status", userPreference != null ? 1 : 0);
					model.put("userPreference", userPreference);

				} catch (Exception e) {
					model.put("status", 0);
				}
		} else {
			throw new AuthenticationException("USER NOT VALID");
		}
		
		return new ModelAndView("userPreference", model);
	}


	/**
	 * @return
	 */
	public static User getHardCodedUser() {
		User user = new User();
		user.setEmailId("vipul.parekh@abc.com");
		user.setId(1L);
		user.setName("vipul parekh");
		user.setPlaying(true);
		return user;
	}
}
