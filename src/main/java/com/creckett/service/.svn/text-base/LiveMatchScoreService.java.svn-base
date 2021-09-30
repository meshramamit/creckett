package com.creckett.service;

import java.util.List;

import com.creckett.model.LiveMatchCommentary;
import com.creckett.model.LiveMatchScore;

public interface LiveMatchScoreService {

	
	
	public int updateScore(Long matchId, Double over, Long run, Integer wicket,String battingSide);
	
	public int addCommentary(Long matchId, String commentary);

	public int saveCommentary(Long matchId);
	
	public LiveMatchScore getLatestScore(Long matchId);
	
	public List<LiveMatchCommentary> getCommentary(Long matchId,Long commentaryId);

	public int saveScore(Long matchId);
	
	@Deprecated
	public int addScore(Long matchId, Double over, String score,
			String commentary);
  
	@Deprecated
	public List<LiveMatchScore> getScore(Long matchId, Double over);

	@Deprecated
	public List<LiveMatchScore> getScore(Long matchId);
	
	/**
	 * This method cleans up score and commentary
	 * this method will be invoked when match completes
	 *  
	 */
public void cleanupScoreCommentary();

}
