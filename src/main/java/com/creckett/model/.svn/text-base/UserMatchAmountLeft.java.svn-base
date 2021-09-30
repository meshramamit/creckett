package com.creckett.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class UserMatchAmountLeft extends BusinessObject {
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "MATCH_ID")
	private MatchMaster matchMaster;
	
	private Integer amountLeft;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MatchMaster getMatchMaster() {
		return matchMaster;
	}

	public void setMatchMaster(MatchMaster matchMaster) {
		this.matchMaster = matchMaster;
	}

	public Integer getAmountLeft() {
		return amountLeft;
	}

	public void setAmountLeft(Integer amountLeft) {
		this.amountLeft = amountLeft;
	}
	
}
