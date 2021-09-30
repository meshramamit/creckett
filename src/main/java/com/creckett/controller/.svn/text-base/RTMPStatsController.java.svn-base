package com.creckett.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.creckett.messaging.application.MessagingAdaptor;
import com.creckett.messaging.application.rtmp.red5.RTMPConnectionStatsSummary;
import com.creckett.messaging.application.rtmp.red5.RTMPRSOStat;
import com.creckett.messaging.application.rtmp.red5.RTMPStatsSummary;

public class RTMPStatsController extends MultiActionController {
	
	private MessagingAdaptor messagingAdaptor;
	
	
	public MessagingAdaptor getMessagingAdaptor() {
		return messagingAdaptor;
	}


	public void setMessagingAdaptor(MessagingAdaptor messagingAdaptor) {
		this.messagingAdaptor = messagingAdaptor;
	}


	public ModelAndView rtmpStats(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RTMPStatsSummary rtmpStatsSummary = messagingAdaptor.getRTMPStatsSummary();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("summary", rtmpStatsSummary);
		return new ModelAndView("rtmpStats", model);
		
	}

	public ModelAndView rtmpConnectionStats(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RTMPConnectionStatsSummary rtmpConnectionStatsSummary = messagingAdaptor.getRTMPConnectionStats();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("summary", rtmpConnectionStatsSummary);
		return new ModelAndView("rtmpConnectionStats", model);
		
	}
	
	public ModelAndView rtmpRSOStats(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<RTMPRSOStat> rtmpRSOStats = messagingAdaptor.getRTMPRSOStats();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("rsoStats", rtmpRSOStats);
		return new ModelAndView("rtmpRSOStats", model);
		
	}
}
