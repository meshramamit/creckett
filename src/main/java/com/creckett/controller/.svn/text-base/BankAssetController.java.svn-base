package com.creckett.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.constant.SessionAttributes;
import com.creckett.dto.FacebookUserFriend;
import com.creckett.dto.UserMatchAmountLeftDTO;
import com.creckett.exception.AuthenticationException;
import com.creckett.model.User;
import com.creckett.service.UserMatchAmountLeftService;

public class BankAssetController implements Controller{

	private UserMatchAmountLeftService userMatchAmountLeftService;
	
	
	public UserMatchAmountLeftService getUserMatchAmountLeftService() {
		return userMatchAmountLeftService;
	}

	public void setUserMatchAmountLeftService(
			UserMatchAmountLeftService userMatchAmountLeftService) {
		this.userMatchAmountLeftService = userMatchAmountLeftService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<FacebookUserFriend> friends = getFriendsList(request);
		User requestinguUser = (User) request.getSession().getAttribute(SessionAttributes.USER);
		if (requestinguUser == null) {
			throw new AuthenticationException("INVALID USER");
		}
		List<UserMatchAmountLeftDTO> dtos =userMatchAmountLeftService.getMatchAmountLeft(requestinguUser.getId(),friends);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userMatchAmountLeftDTOs", dtos);
		return new ModelAndView("bankAssetResponse", model);
		
	}

	private List<FacebookUserFriend> getFriendsList(HttpServletRequest request) {
		return (List<FacebookUserFriend>) request.getSession().getAttribute(
				SessionAttributes.USER_FRIENDS_LIST);
	}

}
