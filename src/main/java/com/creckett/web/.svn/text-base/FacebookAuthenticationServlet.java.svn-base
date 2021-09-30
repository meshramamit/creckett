package com.creckett.web;

import static com.creckett.constant.CreckettConstant.FACEBOOK_FRIEND_LIMIT;
import static com.creckett.constant.SessionAttributes.USER;
import static com.creckett.constant.SessionAttributes.USER_ACCESS_TOKEN;
import static com.creckett.constant.SessionAttributes.USER_FRIENDS_LIST;
import static com.creckett.constant.SessionAttributes.LIMIT_USER_FRIENDS_LIST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import com.creckett.auth.Authenticator;
import com.creckett.dto.FacebookUserFriend;
import com.creckett.exception.AuthenticationException;
import com.creckett.model.User;
import com.creckett.service.UserService;

public class FacebookAuthenticationServlet implements HttpRequestHandler {

	private UserService userService;

	private Authenticator authenticator;

	private String homePageUrl;
	
	private String facebookHomePage;
	
	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String code = request.getParameter("code");
		String accessToken = request.getParameter("accessToken");
		boolean isNewUser = false;
		try {
			if (code != null && code.trim().length() > 0) {
				User user = authenticator.getUserInformation(code);
				request.getSession().setAttribute(
						USER_ACCESS_TOKEN,
						user.getAccessToken());
				if( userService.getUserByEmailId(user.getEmailId()) == null ){
					isNewUser = true;
				}
				user = userService.createUser(user);
				request.getSession().setAttribute(USER, user);
				request.getSession().setAttribute(
						USER_FRIENDS_LIST,
						authenticator.getUserFriendList());
				limitFriendListSize(request);
				if(isNewUser){
					response.sendRedirect(facebookHomePage);
				}else{
					response.sendRedirect(homePageUrl);
				}

			} else if (accessToken != null && !accessToken.isEmpty()) {
				// String expires = request.getParameter("expires");
				User user = authenticator.retrivetUserInformation(accessToken,
						-1);
				user = userService.createUser(user);
				request.getSession().setAttribute(USER, user);
				request.getSession().setAttribute(
						USER_FRIENDS_LIST,
						authenticator.getUserFriendList(accessToken));
				limitFriendListSize(request);
				response.sendRedirect("/creckett/service/homeScreen.do");
			}
		} catch (AuthenticationException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	@SuppressWarnings("unchecked")
	public void limitFriendListSize(HttpServletRequest request){
		if( request.getSession().getAttribute(USER_FRIENDS_LIST) != null){
			ArrayList<FacebookUserFriend> friends = (ArrayList<FacebookUserFriend>) request.getSession().
													getAttribute(USER_FRIENDS_LIST);
			int limitCount = FACEBOOK_FRIEND_LIMIT;
			int size = friends.size();
			StringBuilder limitedFriends = new StringBuilder("");
			if( size <= limitCount ){
				for( int i=0;i<size;i++ ){
					if( !limitedFriends.toString().equals("") ){
						limitedFriends.append(",");
					}
					limitedFriends.append(friends.get(i).getFriendName());
				}
			}else{
				Random random = new Random();
				int factor = ((int)size/limitCount);
				for( int i=0;i<limitCount;i++ ){
					int index = random.nextInt(factor);
					index = (factor*(i+1))+index-1;
					if( !limitedFriends.toString().equals("") ){
						limitedFriends.append(",");
					}
					if( index > (size-1) ){
						limitedFriends.append(friends.get(size-1).getFriendName());
					}else{
						limitedFriends.append(friends.get(index).getFriendName());
					}
				}
			}
			request.getSession().setAttribute(
					LIMIT_USER_FRIENDS_LIST, limitedFriends.toString());
		}
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Authenticator getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

	public String getFacebookHomePage() {
		return facebookHomePage;
	}

	public void setFacebookHomePage(String facebookHomePage) {
		this.facebookHomePage = facebookHomePage;
	}

}
