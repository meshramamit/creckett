package com.creckett.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ARCHIVED_AMOUNT_LEFT")
public class ArchivedAmountLeft extends BusinessObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4221644307654741435L;

	@ManyToOne
	@JoinColumn(name = "MATCH_ID")
	private MatchMaster matchId;

	@ManyToOne
	@JoinColumn(name = "MARKET_ID")
	private Market marketId;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User userId;

	@Column
	private Integer sessionId;

	@Column(name="MATCH_OVER")
	private Integer over;

	@Column
	private Integer leftAmount;

	public static ArchivedAmountLeft build( AmountLeft amountLeft ){
		ArchivedAmountLeft archivedAmountLeft = new ArchivedAmountLeft();
		if( amountLeft != null ){
			archivedAmountLeft.setLeftAmount(amountLeft.getLeftAmount());
			archivedAmountLeft.setMarketId(amountLeft.getMarketId());
			archivedAmountLeft.setMatchId(amountLeft.getMatchId());
			archivedAmountLeft.setOver(amountLeft.getOver());
			archivedAmountLeft.setSessionId(amountLeft.getSessionId());
			archivedAmountLeft.setUserId(amountLeft.getUserId());
		}
		return archivedAmountLeft;
	}
	
	public MatchMaster getMatchId() {
		return matchId;
	}

	public void setMatchId(MatchMaster matchId) {
		this.matchId = matchId;
	}

	public Market getMarketId() {
		return marketId;
	}

	public void setMarketId(Market marketId) {
		this.marketId = marketId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getOver() {
		return over;
	}

	public void setOver(Integer over) {
		this.over = over;
	}

	public Integer getLeftAmount() {
		return leftAmount;
	}

	public void setLeftAmount(Integer leftAmount) {
		this.leftAmount = leftAmount;
	}
}
