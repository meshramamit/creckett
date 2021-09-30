package com.creckett.dto;

import com.creckett.constant.CreckettConstant;
import com.creckett.model.BusinessObject;

public class UserBetState extends BusinessObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7295239838629296360L;

	private String userName;

	private Integer runs;

	private Integer runsBet;

	private Integer wickets;

	private Integer wicketsBet;

	private String resultAmount;

	private String timefactor;
	
	private String runsWinner;
	
	private String wicketsWinner;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName( String userName )
	{
		this.userName = userName;
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

	public String getTimefactor()
	{
		return timefactor;
	}

	public void setTimefactor( String timefactor )
	{
		this.timefactor = timefactor;
	}

	public String getRunsWinner() {
		return runsWinner;
	}

	public void setRunsWinner(String isRunsWinner) {
		this.runsWinner = isRunsWinner;
	}

	public String getWicketsWinner() {
		return wicketsWinner;
	}

	public void setWicketsWinner(String isWicketsWinner) {
		this.wicketsWinner = isWicketsWinner;
	}

	public String getRunsBetDisplay()
	{
		if(getRunsBet()==CreckettConstant.DEFAULT_SUBMIT_BET_VALUE){
			return CreckettConstant.DEFAULT_SUBMIT_BET;
		}else{
			return getRunsBet().toString();
		}
	}

	
}
