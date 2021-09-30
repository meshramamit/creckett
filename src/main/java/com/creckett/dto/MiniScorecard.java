package com.creckett.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;


public class MiniScorecard {

	private String team1Score;
	
	private String team2Score;
	
	private BatsmanScore batsman1Score;
	
	private BatsmanScore batsman2Score;

	private String partnership;
	
	@XStreamAlias("bowler1")
	private BowlerPerformance  bowler1Performance;
	
	@XStreamAlias("bowler2")
	private BowlerPerformance bowler2Performance;
	
	private double crr;
	
	private String lastBatsman;
	
	private String status;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BowlerPerformance getBowler1Performance() {
		return bowler1Performance;
	}

	public void setBowler1Performance(BowlerPerformance bowler1Performance) {
		this.bowler1Performance = bowler1Performance;
	}

	public BowlerPerformance getBowler2Performance() {
		return bowler2Performance;
	}

	public void setBowler2Performance(BowlerPerformance bowler2Performance) {
		this.bowler2Performance = bowler2Performance;
	}

	public String getPartnership() {
		return partnership;
	}

	public void setPartnership(String partnership) {
		this.partnership = partnership;
	}

	public double getCrr() {
		return crr;
	}

	public void setCrr(double crr) {
		this.crr = crr;
	}

	public String getLastBatsman() {
		return lastBatsman;
	}

	public void setLastBatsman(String lastBatsman) {
		this.lastBatsman = lastBatsman;
	}

	public String getTeam1Score() {
		return team1Score;
	}

	public void setTeam1Score(String team1Score) {
		this.team1Score = team1Score;
	}

	public String getTeam2Score() {
		return team2Score;
	}

	public void setTeam2Score(String team2Score) {
		this.team2Score = team2Score;
	}

	public BatsmanScore getBatsman1Score() {
		return batsman1Score;
	}

	public void setBatsman1Score(BatsmanScore batsman1Score) {
		this.batsman1Score = batsman1Score;
	}

	public BatsmanScore getBatsman2Score() {
		return batsman2Score;
	}

	public void setBatsman2Score(BatsmanScore batsman2Score) {
		this.batsman2Score = batsman2Score;
	}
	
	
	
	
}
