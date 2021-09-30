package com.creckett.service.impl;

import com.creckett.dao.UserPrefererenceDAO;
import com.creckett.model.User;
import com.creckett.model.UserPreference;
import com.creckett.service.UserPreferenceService;
import com.creckett.util.StringUtil;

public class UserPreferenceServiceImpl implements UserPreferenceService {

	private UserPrefererenceDAO userPreferenceDAO;

	/**
	 * @return the userPreferenceDAO
	 */
	public UserPrefererenceDAO getUserPreferenceDAO() {
		return userPreferenceDAO;
	}

	/**
	 * @param userPreferenceDAO the userPreferenceDAO to set
	 */
	public void setUserPreferenceDAO(UserPrefererenceDAO userPreferenceDAO) {
		this.userPreferenceDAO = userPreferenceDAO;
	}
	
	/**
	 * @param user
	 * @param showVideoTutorial
	 * @param showAssistant
	 * @return
	 */
	public UserPreference saveUserPreference(User user,String showVideoTutorial,String showAssistant,String termsAccepted){
		UserPreference userPreference = null;
		userPreference = userPreferenceDAO.getUserPreference(user);
		
		if(userPreference==null){
			userPreference = new UserPreference();
			userPreference.setUser(user);
			userPreference.setShowVideoTutorial(StringUtil.getBooleanValueFromString(showVideoTutorial,true));
			userPreference.setShowAssistant(StringUtil.getBooleanValueFromString(showAssistant,true));
			userPreference.setTermsAccepted(StringUtil.getBooleanValueFromString(termsAccepted, false));
		}else{
			userPreference.setShowVideoTutorial(StringUtil.getBooleanValueFromString(showVideoTutorial,userPreference.getShowVideoTutorial()));
			userPreference.setShowAssistant(StringUtil.getBooleanValueFromString(showAssistant,userPreference.getShowAssistant()));
			userPreference.setTermsAccepted(StringUtil.getBooleanValueFromString(termsAccepted, userPreference.getTermsAccepted()));
		}
		
		userPreference = userPreferenceDAO.save(userPreference);
		
		return userPreference;
		
	}
	
	/* (non-Javadoc)
	 * @see com.creckett.service.UserPreferenceService#getUserPreferernce(com.creckett.model.User)
	 */
	public UserPreference getUserPreference(User user){
		UserPreference userPreference = null;
		userPreference = userPreferenceDAO.getUserPreference(user);
		return userPreference;
	}
	
}
