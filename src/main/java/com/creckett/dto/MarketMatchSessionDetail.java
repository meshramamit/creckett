package com.creckett.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creckett.model.User;

public class MarketMatchSessionDetail {
	
	private String sessionId;

	private List<MarketMatchDetailDTO> marketUserAmountDetail = new ArrayList<MarketMatchDetailDTO>();
	
	private Map<User, String> marketUserDetail = new HashMap<User, String>();

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<MarketMatchDetailDTO> getMarketUserAmountDetail() {
		return marketUserAmountDetail;
	}

	public void setMarketUserAmountDetail(
			List<MarketMatchDetailDTO> marketUserAmountDetail) {
		this.marketUserAmountDetail = marketUserAmountDetail;
	}

	public Map<User, String> getMarketUserDetail() {
		return marketUserDetail;
	}

	public void setMarketUserDetail(Map<User, String> marketUserDetail) {
		this.marketUserDetail = marketUserDetail;
	}
	
	public String getMarketUserDetailValue(User user) {
		return this.marketUserDetail.get(user);
	}
	
}
