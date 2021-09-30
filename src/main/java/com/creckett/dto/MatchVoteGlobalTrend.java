package com.creckett.dto;

public class MatchVoteGlobalTrend {

	private long team1Id;
	
	private String team1Name;
	
	private int numberOfVotesForTeam1;
	
	private long team2Id;
	
	private String team2Name;
	
	private int numberOfVotesForTeam2;
	
	private int numberOfVotesForDraw;
	
	private String userVote;

	
	public int getNumberOfVotesForDraw() {
		return numberOfVotesForDraw;
	}

	public void setNumberOfVotesForDraw(int numberOfVotesForDraw) {
		this.numberOfVotesForDraw = numberOfVotesForDraw;
	}

	public long getTeam1Id() {
		return team1Id;
	}

	public void setTeam1Id(long team1Id) {
		this.team1Id = team1Id;
	}

	public String getTeam1Name() {
		return team1Name;
	}

	public void setTeam1Name(String team1Name) {
		this.team1Name = team1Name;
	}

	public int getNumberOfVotesForTeam1() {
		return numberOfVotesForTeam1;
	}

	public void setNumberOfVotesForTeam1(int numberOfVotesForTeam1) {
		this.numberOfVotesForTeam1 = numberOfVotesForTeam1;
	}

	public long getTeam2Id() {
		return team2Id;
	}

	public void setTeam2Id(long team2Id) {
		this.team2Id = team2Id;
	}

	public String getTeam2Name() {
		return team2Name;
	}

	public void setTeam2Name(String team2Name) {
		this.team2Name = team2Name;
	}

	public int getNumberOfVotesForTeam2() {
		return numberOfVotesForTeam2;
	}

	public void setNumberOfVotesForTeam2(int numberOfVotesForTeam2) {
		this.numberOfVotesForTeam2 = numberOfVotesForTeam2;
	}

	public String getUserVote() {
		return userVote;
	}

	public void setUserVote(String userVote) {
		this.userVote = userVote;
	}

	
}
