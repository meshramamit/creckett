package com.creckett.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.creckett.constant.SessionAttributes;
import com.creckett.exception.AuthenticationException;
import com.creckett.logger.CreckettLogger;
import com.creckett.model.Feedback;
import com.creckett.model.User;
import com.creckett.service.FeedbackService;
import com.creckett.util.DateUtils;

/**
 * @author vipul
 * 
 */
public class FeedbackController extends MultiActionController {

	//private static final Logger LOG = LoggerInstancesManager.getLogger(FeedbackController.class);
	private CreckettLogger creckettLogger = CreckettLogger.getInstance();
	private FeedbackService feedbackService;

	public FeedbackService getFeedbackService() {
		return feedbackService;
	}

	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	/**
	 * This method creates the feedback as per provided parameters
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView createFeedback(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		User user = (User) request.getSession().getAttribute(
				SessionAttributes.USER);

//		user = getHardCodedUser();
		// TESTING

		Map<String, Object> model = new HashMap<String, Object>();
		if (user != null) {
			if (request.getParameter("feedbackType") != null
					&& request.getParameter("feedbackMsg") != null) {
				try {
					String feedbackType = request.getParameter("feedbackType");
					String feedbackMsg = request.getParameter("feedbackMsg");

					Feedback feedback = feedbackService.createFeedback(user,
							feedbackType, feedbackMsg);
					model.put("status", feedback != null ? 1 : 0);
					model.put("feedback", feedback);

				} catch (Exception e) {
					model.put("status", 0);
				}
			} else {
				throw new IllegalArgumentException("Not valid arguments."
						+ "	Expected:<feedbackType><feedbackMsg>");
			}
		} else {
			throw new AuthenticationException("USER NOT VALID");
		}
		return new ModelAndView("feedbackStatus", model);
	}

	public ModelAndView listFeedback(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String isAdmin = (String) request.getSession().getAttribute("isAdmin");
		
//		isAdmin = "true"; 
				
		
		if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
			try {
				Date fromDate = null;
				Date toDate = null;
				if (request.getParameter("fromDate") != null) {
					fromDate = DateUtils.getDateFromStringDateTime(
							request.getParameter("fromDate"), "0000");
				}

				if (request.getParameter("toDate") != null) {
					toDate = DateUtils.getDateFromStringDateTime(
							request.getParameter("toDate"), "2359");
				}
				
				List<Feedback> feedbackList = feedbackService
						.getFeedBackListByDate(fromDate, toDate);
				
				creckettLogger.info(">>getFeedbackList>> feedbackList:"+feedbackList.size());
				
				model.put("status", 1);
				model.put("feedbacklist", feedbackList);
				
			} catch (Exception e) {
				creckettLogger.error("Feedback"+e.getMessage());
				model.put("status", 0);
			}
		} else {
			throw new AuthenticationException("Invalid user");
		}
		return new ModelAndView("feedbackList", model);
	}

	/**
	 * @return
	 */
	private User getHardCodedUser() {
		User user = new User();
		user.setEmailId("abc.def@gmail.com");
		user.setId(1L);
		user.setName("vipul parekh");
		user.setPlaying(true);
		return user;
	}
}
