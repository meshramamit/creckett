package com.creckett.service;

import java.util.Collection;
import java.util.List;

import com.creckett.dto.SessionWiseArchiveBets;
import com.creckett.model.ArchivedAmountLeft;
import com.creckett.model.BetState;
import com.creckett.model.Market;
import com.creckett.model.User;

public interface ArchiveService {

	public List<Market> getArchivedMatchListForUser( User user );
	
	public List<BetState> getArchivedBetsForMarket( Long marketId );
	
	public Collection<SessionWiseArchiveBets> getArchivedSessionWiseBets(
			List<ArchivedAmountLeft> bets);
	
	public List<ArchivedAmountLeft> getArchivedAmountLeftForMarket( Long marketId );
	
}
