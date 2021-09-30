package com.creckett.dao;

import java.util.List;

import com.creckett.model.BetState;
import com.creckett.model.Market;
import com.creckett.model.User;

public interface ArchiveDAO {

	public List<Market> getArchivedMatchListForUser( User user );
	
	public List<BetState> getArchivedBetsForMarket(Long marketId);
	
}
