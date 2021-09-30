package com.creckett.dao;

import java.util.List;

import com.creckett.model.BetState;

public interface BetStateDAO {
    public BetState save(BetState betState);

    public BetState get(Long id);

    public void delete(BetState betState);

    public List<BetState> retrieveBetStatusForOvers(Long marketId,
	    Integer matchOver, Integer session);

    public List<BetState> retrieveBetStatusForOvers(Long marketId);

    public BetState retrieveBetStatusForUserMarketSessionOver(
	    Integer over, Integer session, Long userId, Long marketId);

	public List<BetState> retrieveBetStatesForMatch(Long matchId,
			Integer matchOver, Integer sessionId);

}
