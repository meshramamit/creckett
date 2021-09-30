package com.creckett.service.impl;

import java.util.Date;
import java.util.List;

import com.creckett.dao.FeedbackDAO;
import com.creckett.model.Feedback;
import com.creckett.model.User;
import com.creckett.service.FeedbackService;

public class FeedbackServiceImpl implements FeedbackService {

	private FeedbackDAO feedbackDAO;
	/**
	 * @return the feedbackDAO
	 */
	public FeedbackDAO getFeedbackDAO() {
		return feedbackDAO;
	}
	/**
	 * @param feedbackDAO the feedbackDAO to set
	 */
	public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}
	@Override
	public Feedback createFeedback(User user,String feedbackType,String feedbackMsg) {
		Feedback feedback = new Feedback();
		
		feedback.setUser(user);
		feedback.setFeedbackType(feedbackType);
		feedback.setFeedbackMsg(feedbackMsg);
		feedback.setCreateDate(new Date(System.currentTimeMillis()));
		
		return feedbackDAO.save(feedback);
	}

	
	public List<Feedback> getFeedBackListByDate(Date fromDate,Date toDate){
		return feedbackDAO.getFeedBackListByDate(fromDate, toDate);
	}
}
