package com.creckett.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.creckett.model.LiveMatchScore;

/**
 * @author Latesh Dulani
 * 
 */

public class CreckettLiveMatchScoreMap {

	private static CreckettLiveMatchScoreMap creckettLiveMatchScoreMap = new CreckettLiveMatchScoreMap();
	
	private ConcurrentHashMap<Long, List<LiveMatchScore>> liveMatchScoreMap = new ConcurrentHashMap<Long, List<LiveMatchScore>>();

	// private constructor for singleton
	private CreckettLiveMatchScoreMap() {

	}

	public static CreckettLiveMatchScoreMap getInstance() {
		if (creckettLiveMatchScoreMap == null) {
			creckettLiveMatchScoreMap = new CreckettLiveMatchScoreMap();
		}
		return creckettLiveMatchScoreMap;
	}

	public void addScore( Long matchId, LiveMatchScore score ){
		if( this.liveMatchScoreMap.containsKey(matchId) ){
			this.liveMatchScoreMap.get(matchId).add(score);
		}else{
			List<LiveMatchScore> scores = new ArrayList<LiveMatchScore>();
			scores.add(score);
			this.liveMatchScoreMap.put(matchId, scores);
		}
	}
	
	public List<LiveMatchScore> getScoreForMatch( Long matchId ){
		return this.liveMatchScoreMap.get(matchId);
	}
	
	public List<LiveMatchScore> getSocreForMatchOver( Long matchId, Double over ){
		List<LiveMatchScore> toReturn = new ArrayList<LiveMatchScore>();
		if( this.liveMatchScoreMap.containsKey(matchId) ){
			int len = this.liveMatchScoreMap.get(matchId).size();
			for( int i = len - 1; i >= 0; i--  ){
				LiveMatchScore matchScore = this.liveMatchScoreMap.get(matchId).get(i);
				if( matchScore.getOver().compareTo(over) >= 0){
					toReturn.add(matchScore);
				}else{
					break;
				}
			}
		}
		return toReturn;
	}
	
	public LiveMatchScore getLatestMatchScore( Long matchId ){
		if( this.liveMatchScoreMap.containsKey(matchId) ){
			return this.liveMatchScoreMap.get(matchId).get(this.liveMatchScoreMap.get(matchId).size()-1);
		}
		return null;
	}
	
}
