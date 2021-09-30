package com.creckett.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FEEDBACK")
public class Feedback extends BusinessObject{

	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name = "USER")
    private User user;
	
	@Column
	private String feedbackType;
	
	@Column
	private String feedbackMsg;
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the feedbackType
	 */
	public String getFeedbackType() {
		return feedbackType;
	}
	/**
	 * @param feedbackType the feedbackType to set
	 */
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	/**
	 * @return the feedbackMsg
	 */
	public String getFeedbackMsg() {
		return feedbackMsg;
	}
	/**
	 * @param feedbackMsg the feedbackMsg to set
	 */
	public void setFeedbackMsg(String feedbackMsg) {
		this.feedbackMsg = feedbackMsg;
	}
}
