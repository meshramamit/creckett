package com.creckett.dao;

import java.util.ArrayList;
import java.util.List;

import com.creckett.dto.FacebookUserFriend;
import com.creckett.model.Market;
import com.creckett.model.MatchMaster;
import com.creckett.model.User;

public interface MarketDAO {

	public Market save(Market market);

	public Market get(Long id);

	public void delete(Market market);

	public List<Market> getMatchMarketsForUser(Long matchId, Long userId);

	public Market getMarketByToken(String marketToken);
	
	public void updateProcessedOver( Long id, Integer matchOver );
	
	public boolean ifUserHasMarketForMatch( User user, MatchMaster match );
	
	public Market update(Market market);

	public List<Market> getMatchInvites(Long matchId,
			ArrayList<FacebookUserFriend> friends);
	
	public boolean isMarketCreatedByModerator(Long matchId, Long moderatorId);

}
