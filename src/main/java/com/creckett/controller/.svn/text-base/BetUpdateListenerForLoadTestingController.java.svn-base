package com.creckett.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.messaging.application.MessagingAdaptor;

public class BetUpdateListenerForLoadTestingController implements Controller {

	private MessagingAdaptor messagingAdaptor;
	
	
	public MessagingAdaptor getMessagingAdaptor() {
		return messagingAdaptor;
	}


	public void setMessagingAdaptor(MessagingAdaptor messagingAdaptor) {
		this.messagingAdaptor = messagingAdaptor;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long matchId = Integer.valueOf(request.getParameter("matchId"));
		long marketId = Integer.valueOf(request.getParameter("marketId"));
		messagingAdaptor.addListnerForBetUpdate(matchId, marketId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", 1);
		return new ModelAndView("genericStatus", model);
		
	}

}
