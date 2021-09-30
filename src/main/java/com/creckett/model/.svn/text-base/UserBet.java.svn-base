package com.creckett.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "USER_BET", uniqueConstraints = { @UniqueConstraint(columnNames = {
	"matchOver", "matchSession", "USER", "MARKET" }) })
public class UserBet extends BusinessObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7187844294765817775L;

	@ManyToOne
    @JoinColumn(name = "MARKET")
    private Market market;

    @ManyToOne
    @JoinColumn(name = "USER")
    private User user;

    @Column
    private Integer matchSession;

    @Column
    private Integer matchOver;

    @Column
    private Integer runsBet;

    @Column
    private Integer wicketsBet;

    @Column
    private Date betTime;

    @Column
    private Integer wicketBetAmount;

    @Column
    private Integer runBetAmount;

    @Transient
    private String timeFactor = "";

    @Transient
    private Integer calculatedWicketBetAmount;

    public Integer getCalculatedWicketBetAmount() {
	return calculatedWicketBetAmount;
    }

    public void setCalculatedWicketBetAmount(Integer calculatedWicketBetAmount) {
	this.calculatedWicketBetAmount = calculatedWicketBetAmount;
    }

    public Market getMarket() {
	return market;
    }

    public void setMarket(Market market) {
	this.market = market;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Integer getMatchSession() {
	return matchSession;
    }

    public void setMatchSession(Integer matchSession) {
	this.matchSession = matchSession;
    }

    public Integer getMatchOver() {
	return matchOver;
    }

    public void setMatchOver(Integer matchOver) {
	this.matchOver = matchOver;
    }

    public Integer getRunsBet() {
	return runsBet;
    }

    public void setRunsBet(Integer runsBet) {
	this.runsBet = runsBet;
    }

    public Integer getWicketsBet() {
	return wicketsBet;
    }

    public void setWicketsBet(Integer wicketsBet) {
	this.wicketsBet = wicketsBet;
    }

    public Date getBetTime() {
	return betTime;
    }

    public void setBetTime(Date betTime) {
	this.betTime = betTime;
    }

    public Integer getWicketBetAmount() {
	return wicketBetAmount;
    }

    public void setWicketBetAmount(Integer wicketBetAmount) {
	this.wicketBetAmount = wicketBetAmount;
    }

    public Integer getRunBetAmount() {
	return runBetAmount;
    }

    public void setRunBetAmount(Integer runBetAmount) {
	this.runBetAmount = runBetAmount;
    }

    public String getTimeFactor() {
	return timeFactor;
    }

    public void setTimeFactor(String timeFactor) {
	this.timeFactor = timeFactor;
    }

}
