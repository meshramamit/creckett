package com.creckett.auth;

import java.util.ArrayList;

import com.creckett.constant.AuthProvider;
import com.creckett.dto.FacebookUserFriend;
import com.creckett.exception.AuthenticationException;
import com.creckett.model.User;

public interface Authenticator {

	public String getLoginURL();

	public User getUserInformation(String authCode) throws AuthenticationException;
	
	public User retrivetUserInformation(String accessToken, int expire) throws AuthenticationException;

	public ArrayList<FacebookUserFriend> getUserFriendList(String accessToken) throws AuthenticationException;
	
	public ArrayList<FacebookUserFriend> getUserFriendList() throws AuthenticationException;
	
	public AuthProvider getAuthProvider();
}
