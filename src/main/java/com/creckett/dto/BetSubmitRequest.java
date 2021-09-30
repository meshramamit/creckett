package com.creckett.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("userBet")
public class BetSubmitRequest {
	
	@XStreamAlias("uid")
	@XStreamAsAttribute
	private long userId;
	
	@XStreamAsAttribute
	private String userName;
	
	@XStreamAsAttribute
	private long marketId;
	
	@XStreamAlias("over")
	@XStreamAsAttribute
	private int overNumber;
	
	@XStreamAsAttribute
	private int betRuns;
	
	@XStreamAlias("betWkts")
	@XStreamAsAttribute
	private int betWickets;
	
	@XStreamAsAttribute
	private int session;
	
	@XStreamAsAttribute
	private int groupId;

	
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getBetRuns() {
		return betRuns;
	}

	public void setBetRuns(int betRuns) {
		this.betRuns = betRuns;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getMarketId() {
		return marketId;
	}

	public void setMarketId(long marketId) {
		this.marketId = marketId;
	}

	public int getOverNumber() {
		return overNumber;
	}

	public void setOverNumber(int overNumber) {
		this.overNumber = overNumber;
	}

	public int getBetWickets() {
		return betWickets;
	}

	public void setBetWickets(int betWickets) {
		this.betWickets = betWickets;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
	}
	
	
	
}
