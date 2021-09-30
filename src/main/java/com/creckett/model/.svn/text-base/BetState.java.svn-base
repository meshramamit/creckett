package com.creckett.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BetState extends BusinessObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5449400062452795202L;

	@Column
	private Integer matchOver;

	@Column
	private Integer session;

	@Column
	private Integer runs;

	@Column
	private Integer runsBet;

	@Column
	private Integer wickets;

	@Column
	private Integer wicketsBet;

	@Column
	private String resultAmount;

	@Column
	private String timeFactor;
	
	@Column
	private boolean isRunsWinner;
	
	@Column
	private boolean isWicketsWinner;

	@ManyToOne
	private User user;

	@ManyToOne
	private Market market;

	@Column
	private boolean isWinner = false;
	
	public Integer getMatchOver()
	{
		return matchOver;
	}

	public void setMatchOver( Integer matchOver )
	{
		this.matchOver = matchOver;
	}

	public Integer getSession()
	{
		return session;
	}

	public void setSession( Integer session )
	{
		this.session = session;
	}

	public Integer getRuns()
	{
		return runs;
	}

	public void setRuns( Integer runs )
	{
		this.runs = runs;
	}

	public Integer getRunsBet()
	{
		return runsBet;
	}

	public void setRunsBet( Integer runsBet )
	{
		this.runsBet = runsBet;
	}

	public Integer getWickets()
	{
		return wickets;
	}

	public void setWickets( Integer wickets )
	{
		this.wickets = wickets;
	}

	public Integer getWicketsBet()
	{
		return wicketsBet;
	}

	public void setWicketsBet( Integer wicketsBet )
	{
		this.wicketsBet = wicketsBet;
	}

	public String getResultAmount()
	{
		return resultAmount;
	}

	public void setResultAmount( String resultAmount )
	{
		this.resultAmount = resultAmount;
	}

	public String getTimeFactor()
	{
		return timeFactor;
	}

	public void setTimeFactor( String timeFactor )
	{
		this.timeFactor = timeFactor;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser( User user )
	{
		this.user = user;
	}

	public Market getMarket()
	{
		return market;
	}

	public void setMarket( Market market )
	{
		this.market = market;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public boolean isRunsWinner() {
		return isRunsWinner;
	}

	public void setRunsWinner(boolean isRunsWinner) {
		this.isRunsWinner = isRunsWinner;
	}

	public boolean isWicketsWinner() {
		return isWicketsWinner;
	}

	public void setWicketsWinner(boolean isWicketsWinner) {
		this.isWicketsWinner = isWicketsWinner;
	}

}
