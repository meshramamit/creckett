package com.creckett.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "USER_MATCH_VOTE", uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_ID","MATCH_ID" }) })
public class UserMatchVote extends BusinessObject {

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "MATCH_ID")
	private MatchMaster matchMaster;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private TeamMaster userVote;

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

	public TeamMaster getUserVote() {
		return userVote;
	}

	public void setUserVote(TeamMaster userVote) {
		this.userVote = userVote;
	}
	
	
}
