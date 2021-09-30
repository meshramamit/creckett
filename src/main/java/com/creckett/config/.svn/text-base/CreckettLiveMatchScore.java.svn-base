package com.creckett.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.creckett.model.LiveMatchCommentary;
import com.creckett.model.LiveMatchScore;

/**
 * @author vipul
 *
 */
public class CreckettLiveMatchScore {

	private static CreckettLiveMatchScore creckettLiveMatchScoreMap = new CreckettLiveMatchScore();

	private ConcurrentHashMap<Long, LiveMatchScore> latestMatchScore = new ConcurrentHashMap<Long, LiveMatchScore>();

	private ConcurrentHashMap<Long, List<LiveMatchCommentary>> liveMatchCommentary = new ConcurrentHashMap<Long, List<LiveMatchCommentary>>();
	

	// private constructor for singleton
	private CreckettLiveMatchScore() {

	}

	/**
	 * Factory Method to create object
	 * 
	 * @return
	 */
	public static CreckettLiveMatchScore getInstance() {
		return creckettLiveMatchScoreMap;
	}

	/**
	 * Update Match for provided matchId
	 * 
	 * @param matchId
	 * @param score
	 */
	public void updateScore(Long matchId, LiveMatchScore score) {
		this.latestMatchScore.put(matchId, score);
	}

	/**
	 * Add commentary for given matchId
	 * 
	 * @param matchId
	 * @param commentory
	 */
	public void addCommentory(Long matchId, LiveMatchCommentary commentary) {
		if (this.liveMatchCommentary.containsKey(matchId)) {
			this.liveMatchCommentary.get(matchId).add(commentary);
		} else {
			List<LiveMatchCommentary> commentaryList = new ArrayList<LiveMatchCommentary>();
			commentaryList.add(commentary);
			this.liveMatchCommentary.put(matchId, commentaryList);
		}
	}

	
	/**
	 * Get Latest score the given Match
	 * 
	 * @param matchId
	 * @return
	 */
	public LiveMatchScore  getScoreForMatch(Long matchId) {
		return this.latestMatchScore.get(matchId);
	}

	
	
	/**
	 * Returns Commentary List for given matchId where commentaryId is greater than or equal to provided commentaryId
	 * 
	 * @param matchId
	 * @param commentaryId
	 * @return
	 */
	public List<LiveMatchCommentary> getCommentaryById(Long matchId, Long commentaryId) {
		List<LiveMatchCommentary> toReturn = new ArrayList<LiveMatchCommentary>();
		if (this.liveMatchCommentary.containsKey(matchId)) {
			int len = this.liveMatchCommentary.get(matchId).size();
			for (int i = len - 1; i >= 0; i--) {
				LiveMatchCommentary matchCommentary = this.liveMatchCommentary.get(matchId).get(i);
				if (matchCommentary.getCommentaryId() >=commentaryId) {
					toReturn.add(matchCommentary);
				} else {
					break;
				}
			}
		}
		return toReturn;
	}

	
	/**
	 * Returns Commentary List for given matchId
	 * 
	 * @param matchId
	 * @param commentaryId
	 * @return
	 */
	public List<LiveMatchCommentary> getCommentary(Long matchId) {
		List<LiveMatchCommentary> toReturn = new ArrayList<LiveMatchCommentary>();
		if (this.liveMatchCommentary.containsKey(matchId)) {
			toReturn = this.liveMatchCommentary.get(matchId);
		}
		return toReturn;
	}	

	
	/**
	 * This method cleans up score and commentary
	 * this method will be invoked when match completes
	 *  
	 */
	public void cleanupScoreCommentary(){
		this.liveMatchCommentary.clear();
		this.latestMatchScore.clear();
		LiveMatchCommentary.resetCommentaryCn();
	}
	
}
