package com.creckett.dto;

import java.util.HashMap;
import java.util.Map;

public class ArchiveBet {

	private String over = "";
	
	private Map<String, String> userBets = new HashMap<String, String>();

	public String getOver() {
		return over;
	}

	public void setOver(String over) {
		this.over = over;
	}

	public Map<String, String> getUserBets() {
		return userBets;
	}

	public void setUserBets(Map<String, String> userBets) {
		this.userBets = userBets;
	}
	
}
