package com.creckett.service;

import java.util.List;

import com.creckett.model.Market;
import com.creckett.model.User;

public interface UserService {

	public User createUser(User user);
	
	public User updateUser(User user);
	
	public User getPersistentUser(User user);
	
	public int resetMatchPlayingflag();
	
	public User getUserByEmailId( String emailId );

	public void resetMatchPlayingflagFor(List<Market> markets);


}
