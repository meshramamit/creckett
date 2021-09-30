package com.creckett.dao;

import java.util.List;

import com.creckett.model.LiveMatchCommentary;
import com.creckett.model.LiveMatchScore;

public interface LiveMatchScoreDAO {

	public void saveMatchScore(LiveMatchScore score);
	
	public void saveMatchCommentary( List<LiveMatchCommentary> commantaryList );
	
	@Deprecated
	public void saveMatchScores( List<LiveMatchScore> scores );
	
}
