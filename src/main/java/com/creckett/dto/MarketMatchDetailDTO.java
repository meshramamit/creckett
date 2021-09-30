package com.creckett.dto;

import java.util.HashMap;
import java.util.Map;

public class MarketMatchDetailDTO {

	private int over;

	private Map<String, Integer> userAmountDetail = new HashMap<String, Integer>();

	public int getOver() {
		return over;
	}

	public void setOver(int over) {
		this.over = over;
	}

	public Map<String, Integer> getUserAmountDetail() {
		return userAmountDetail;
	}

	public void setUserAmountDetail(Map<String, Integer> userAmountDetail) {
		this.userAmountDetail = userAmountDetail;
	}

}
