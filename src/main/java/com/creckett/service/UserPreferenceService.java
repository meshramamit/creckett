package com.creckett.service;

import com.creckett.model.User;
import com.creckett.model.UserPreference;

public interface UserPreferenceService {

	public UserPreference saveUserPreference(User user,String showVideoTutorial,String showAssistant,String termsAccepted);
	
	public UserPreference getUserPreference(User user);
}
