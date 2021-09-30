package com.creckett.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.creckett.util.DateUtils;

@Entity
public class Market extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2638522673596996186L;

	@Column
	private String marketCode;

	@OneToOne
	@JoinColumn(name = "MODERATOR_ID")
	private User moderatorId;

	@Column
	private Date creationDate;

	@ManyToOne
	@JoinColumn(name = "MATCH_MASTER_ID")
	private MatchMaster match;

	@Column
	private int groupOverValue;

	@Column
	private Integer processedOver;

	@Column
	private Integer matchSession;
	
	@Column
	private String marketWinners = "";
	
	@Column
	private String difficultyLevel = "1";

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "MARKET_ID")
	private List<MarketUser> marketUsers;

	@Transient
	private int noOfUsers;

	@Transient
	private String userTooltip = "";

	@Transient
	private String timeStartString = "";
	
	@Transient
	private Long timeRemain;

	public String getMarketCode() {
		return marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	public User getModeratorId() {
		return moderatorId;
	}

	public void setModeratorId(User moderatorId) {
		this.moderatorId = moderatorId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<MarketUser> getMarketUsers() {
		return marketUsers;
	}

	public void setMarketUsers(List<MarketUser> marketUsers) {
		this.marketUsers = marketUsers;
	}

	public MatchMaster getMatch() {
		return match;
	}

	public void setMatch(MatchMaster match) {
		this.match = match;
	}

	public int getGroupOverValue() {
		return groupOverValue;
	}

	public void setGroupOverValue(int groupOverValue) {
		this.groupOverValue = groupOverValue;
	}

	public Integer getProcessedOver() {
		return processedOver;
	}

	public void setProcessedOver(Integer processedOver) {
		this.processedOver = processedOver;
	}

	/**
	 * TODO: What this method will return?
	 * 
	 * @return the matchSession
	 */
	public Integer getMatchSession() {
		return matchSession;
	}

	/**
	 * TODO: How the method parameter will be used?
	 * 
	 * @param matchSession
	 *            the matchSession to set
	 */
	public void setMatchSession(Integer matchSession) {
		this.matchSession = matchSession;
	}

	public int getNoOfUsers() {
		if (marketUsers != null) {
			noOfUsers = marketUsers.size();
		}
		return noOfUsers;
	}

	public String getUserTooltip() {
		StringBuilder buffer = new StringBuilder();
		if (marketUsers != null && userTooltip.isEmpty()) {
			for (MarketUser marketUser : marketUsers) {
				buffer.append(marketUser.getUserId().getName());
				buffer.append(",");
			}
			buffer.deleteCharAt(buffer.length() - 1);
			userTooltip = buffer.toString();
		}
		return userTooltip;
	}

	public String getTimeStartString() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.getMatch().getMatchDate());
		calendar.add(Calendar.MINUTE, -30);
		timeStartString = calendar.get(Calendar.YEAR) + " - "
				+ calendar.get(Calendar.MONTH) + " - "
				+ calendar.get(Calendar.DATE) + " - "
				+ calendar.get(Calendar.HOUR_OF_DAY) + " - "
				+ calendar.get(Calendar.MINUTE);
		return timeStartString;
	}

	public Long getTimeRemain() {
		timeRemain = new DateUtils().getTimeRemainingInMarketOpen(match.getMatchDate(),30);
		return timeRemain;
	}

	public String getMarketWinners() {
		return marketWinners;
	}

	public void setMarketWinners(String marketWinners) {
		this.marketWinners = marketWinners;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String level) {
		this.difficultyLevel = level;
	}
	
}
