package com.creckett.service.impl;

import java.util.List;

import com.creckett.config.CreckettLiveMatchScore;
import com.creckett.config.CreckettLiveMatchScoreMap;
import com.creckett.dao.LiveMatchScoreDAO;
import com.creckett.dao.MatchMasterDAO;
import com.creckett.model.LiveMatchCommentary;
import com.creckett.model.LiveMatchScore;
import com.creckett.model.MatchMaster;
import com.creckett.service.LiveMatchScoreService;

public class LiveMatchScoreServiceImpl implements LiveMatchScoreService {

	private MatchMasterDAO matchMasterDAO;
	
	private LiveMatchScoreDAO liveMatchScoreDAO;
	
	private CreckettLiveMatchScore creckettLiveMatchScore;
	


	/**
	 * @return the creckettLiveMatchScore
	 */
	public CreckettLiveMatchScore getCreckettLiveMatchScore() {
		return creckettLiveMatchScore;
	}

	/**
	 * @param creckettLiveMatchScore the creckettLiveMatchScore to set
	 */
	public void setCreckettLiveMatchScore(
			CreckettLiveMatchScore creckettLiveMatchScore) {
		this.creckettLiveMatchScore = creckettLiveMatchScore;
	}

	@Override
	public int updateScore(Long matchId, Double over, Long run, Integer wicket,String battingSide) {
		MatchMaster match = matchMasterDAO.get(matchId);
		
		System.out.println("match:"+match);
		
		if( match != null ){
			LiveMatchScore liveMatchScore = new LiveMatchScore();
			liveMatchScore.setMatch(match);
			liveMatchScore.setOver(over);
			liveMatchScore.setRun(run);
			liveMatchScore.setWicket(wicket);
			liveMatchScore.setBattingSide(battingSide);
			
			creckettLiveMatchScore.updateScore(matchId, liveMatchScore);
			
//			System.out.println("liveMatchScore:"+liveMatchScore);

			return 1;
		}
		return 0;
	}
	
	@Override
	public int addCommentary(Long matchId, String commentary) {
		if( matchId != null  && commentary!=null){
			LiveMatchCommentary liveMatchCommentary = new LiveMatchCommentary();
			liveMatchCommentary.setCommentaryText(commentary);
			creckettLiveMatchScore.addCommentory(matchId, liveMatchCommentary);
			
//			System.out.println("liveMatchCommentary:"+liveMatchCommentary);
//			System.out.println(creckettLiveMatchScore.getCommentary(matchId).size());

			return 1;
		}
		return 0;
	}

	
	@Override
	public LiveMatchScore getLatestScore(Long matchId) {
		return creckettLiveMatchScore.getScoreForMatch(matchId);
	}

	@Override
	public List<LiveMatchCommentary> getCommentary(Long matchId,Long commentaryId) {
		List<LiveMatchCommentary> retList = null;
		List<LiveMatchCommentary> liveMatchCommentary = creckettLiveMatchScore.getCommentary(matchId);
		
		if(liveMatchCommentary!=null && liveMatchCommentary.size()>commentaryId){
			retList = liveMatchCommentary.subList(commentaryId.intValue(), liveMatchCommentary.size());
			
//			System.out.println(retList.get(0).getCommentaryId()+","+retList.get(0).getCommentaryText());
		}
		
		return retList;
	}

	@Override
	public int saveScore(Long matchId) {
		if( creckettLiveMatchScore.getScoreForMatch(matchId) != null ){
			liveMatchScoreDAO.saveMatchScore(creckettLiveMatchScore.getScoreForMatch(matchId));
			return 1;
		}
		return 0;
	}
	
	
	@Override
	public int saveCommentary(Long matchId) {
		if( creckettLiveMatchScore.getScoreForMatch(matchId) != null ){
			liveMatchScoreDAO.saveMatchCommentary(creckettLiveMatchScore.getCommentary(matchId));
			return 1;
		}
		return 0;
	}
	
	@Override
	@Deprecated
	public int addScore(Long matchId, Double over, String score,
			String commentary) {
		MatchMaster match = matchMasterDAO.get(matchId);
		if( match != null ){
			LiveMatchScore liveMatchScore = new LiveMatchScore();
			liveMatchScore.setMatch(match);
			liveMatchScore.setOver(over);
			liveMatchScore.setScore(score);
			liveMatchScore.setCommentaryText(commentary);
			CreckettLiveMatchScoreMap.getInstance().addScore(matchId, liveMatchScore);
			return 1;
		}
		return 0;
	}


	@Override
	@Deprecated
	public List<LiveMatchScore> getScore(Long matchId, Double over) {
		return CreckettLiveMatchScoreMap.getInstance().getSocreForMatchOver(matchId, over);
	}

	@Override
	@Deprecated
	public List<LiveMatchScore> getScore(Long matchId) {
		return CreckettLiveMatchScoreMap.getInstance().getScoreForMatch(matchId);
	}


	public MatchMasterDAO getMatchMasterDAO() {
		return matchMasterDAO;
	}

	public void setMatchMasterDAO(MatchMasterDAO matchMasterDAO) {
		this.matchMasterDAO = matchMasterDAO;
	}

	public LiveMatchScoreDAO getLiveMatchScoreDAO() {
		return liveMatchScoreDAO;
	}

	public void setLiveMatchScoreDAO(LiveMatchScoreDAO liveMatchScoreDAO) {
		this.liveMatchScoreDAO = liveMatchScoreDAO;
	}

	
	/**
	 * This method cleans up score and commentary
	 * this method will be invoked when match completes
	 *  
	 */
	public void cleanupScoreCommentary(){
		creckettLiveMatchScore.cleanupScoreCommentary();
	}
	
}
