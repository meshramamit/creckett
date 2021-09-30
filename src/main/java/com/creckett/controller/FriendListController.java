package com.creckett.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.constant.SessionAttributes;
import com.creckett.dto.FacebookUserFriend;
import com.creckett.exception.AuthenticationException;
import com.creckett.model.User;

public class FriendListController implements Controller {

	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute(
				SessionAttributes.USER);
		if( user != null ){
			Map<String, Object> model = new HashMap<String, Object>();
			ArrayList<FacebookUserFriend> friends  = (ArrayList<FacebookUserFriend>) request.getSession().getAttribute(
					SessionAttributes.USER_FRIENDS_LIST);
			model.put("friends", friends);
			return new ModelAndView("FriendsListScreen", model);
		}else{
			throw new AuthenticationException("Not a valid User.");
		}
	}

}
