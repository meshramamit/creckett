package com.creckett.dto;

import java.util.List;

import com.creckett.model.Market;
import com.creckett.model.MatchMaster;

public class MatchMasterWithMarketDTO {

	private MatchMaster matchMaster;

	private List<Market> markets;
	
	private List<Market> invites;

	public MatchMasterWithMarketDTO(MatchMaster matchMaster) {
		this.matchMaster = matchMaster;
	}

	public MatchMasterWithMarketDTO(MatchMaster matchMaster, List<Market> markets) {
		this.matchMaster = matchMaster;
		this.markets = markets;
	}
	
	public MatchMasterWithMarketDTO(MatchMaster matchMaster, List<Market> markets, List<Market> invites) {
		this.matchMaster = matchMaster;
		this.markets = markets;
		this.invites = invites;
	}

	public MatchMaster getMatchMaster() {
		return matchMaster;
	}

	public List<Market> getMarkets() {
		return markets;
	}

	public List<Market> getInvites() {
		return invites;
	}

}
