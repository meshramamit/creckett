package com.creckett.dao;

import java.util.List;

import com.creckett.model.MatchScore;

public interface MatchScoreDAO
{

	public List< MatchScore > getMatchScore( Long matchId );

	public MatchScore save( MatchScore matchScore );

	public MatchScore get( long matchId, int sessionId, int over );

	public MatchScore getLatestInvalidatedOver( Long matchId );

	public MatchScore getLatestMatchScore( Long matchId );

	public MatchScore getMatchScoreForOver( Long matchId, int over, Integer matchSession );
	
	public List<MatchScore> getMatchScoreForOvers( Long matchId, int minOver, int maxOver, Integer matchSession );

}
