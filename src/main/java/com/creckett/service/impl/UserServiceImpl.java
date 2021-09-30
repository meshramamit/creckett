package com.creckett.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.creckett.dao.UserDAO;
import com.creckett.model.Market;
import com.creckett.model.MarketUser;
import com.creckett.model.User;
import com.creckett.service.UserPreferenceService;
import com.creckett.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	private UserPreferenceService userPreferenceService;

	public User createUser(User user) {

		User persistedUser = userDAO.getUserByEmailId(user.getEmailId());
		if (persistedUser != null) {
			// VP (1/31/13): increment the login count
			if (persistedUser.getLoginCount() == null) {
				persistedUser.setLoginCount(1L);
			} else {
				persistedUser.setLoginCount(persistedUser.getLoginCount() + 1);
			}
			
			//update the login count
			updateUser(persistedUser);
			return persistedUser;
		}
		//VP (1/31/13): first time login
		user.setLoginCount(1L);
		User retUser = userDAO.save(user);
		
		//save user preference to their default values while creating user ( Null - default values)
		userPreferenceService.saveUserPreference(retUser, null,null,null);
		
		return retUser;

	}
	
	public User updateUser(User user) {

		if( user != null ){
			User persistedUser = userDAO.getUserByEmailId(user.getEmailId());
			persistedUser.setPlaying(user.isPlaying());
			persistedUser.setProfileId(user.getProfileId());
			persistedUser.setName(user.getName());
			persistedUser.setMarket(user.getMarket());
			//VP (1/31/13): Login count 
			persistedUser.setLoginCount(user.getLoginCount());
			return userDAO.save(persistedUser);
		}
		return user;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	
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

	@Override
	public User getPersistentUser(User user) {
		User persistedUser = userDAO.getUserByEmailId(user.getEmailId());
		if (persistedUser != null)
			return persistedUser;
		return user;
	}
	
	@Override
	public int resetMatchPlayingflag() {
		int status = 0;
	    status = userDAO.restMatchPlayingFlag();	
		return status;
	}

	@Override
	public User getUserByEmailId(String emailId) {
		User persistedUser = userDAO.getUserByEmailId(emailId);
		if (persistedUser != null)
			return persistedUser;
		return null;
	}

	@Override
	public void resetMatchPlayingflagFor(List<Market> markets) {
		List<User> users = new ArrayList<User>();
		for (Market market : markets){
			for (MarketUser marketUser : market.getMarketUsers()){
			users.add(marketUser.getUserId());
			}
		}
		userDAO.restMatchPlayingFlag(users);
	}

}
