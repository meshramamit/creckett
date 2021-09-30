/**
 * 
 */
package com.creckett.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.creckett.dto.FacebookUserFriend;
import com.creckett.dto.MatchMasterWithMarketDTO;
import com.creckett.dto.ScoreUpdate;
import com.creckett.dto.UserSessionBets;
import com.creckett.model.AmountLeft;
import com.creckett.model.BetState;
import com.creckett.model.Market;
import com.creckett.model.MatchMaster;
import com.creckett.model.MatchScore;
import com.creckett.model.MatchStatus;
import com.creckett.model.User;
import com.creckett.model.UserBet;

public interface MatchService {

    public List<MatchMaster> getListOfUpcomingMatches(Date fromDate, Date toDate);

    public List<MatchMasterWithMarketDTO> getUpcomingMatchesWithMarketForUser(
	    Long userId, Date fromDate, Date toDate);

    public List<Market> getMatchMarketsForUser(Long matchId, Long userId);

    public Market createMarketForUser(User user, Long matchId,
	    int groupOverValue, String level) throws Exception;

    public Market updateMarketForUser(User user, Long marketId,
	    int groupOverValue, String level);

    public Market joinMarket(String marketToken, User user);

    public MatchMaster createMatch(String team1, String team2, String overs,
	    String matchDate, String matchTime);

    public int updateMatch(String matchId, String team1, String team2,
	    String overs, String matchDate, String matchTime, String winner);

    public int finishMatch(String matchId, String winner);
    public int deleteMatch(String matchId);

    public Map<String, List<MatchScore>> getMatchScore(String matchId);

    public int addNewScoreAndInvalidate(String matchId, String sessionId,
	    String over);

    public int updateScore(String matchId, String sessionId, String over,
	    String runs, String wickets, String score);

    public int deleteMarket(Long marketId, User user);

    public MatchScore getLatestInvalidatedOver(Long matchId);

    public MatchScore getLatestMatchScore(Long matchId);

    public Market getMarketById(Long marketId);

    public List<UserBet> getUserBets(Long marketId, Integer sessionId,
	    Integer over);

    public MatchScore getMatchScoreForOver(Long marketId, int over,
	    Integer matchSession);

    public void saveUserBetState(BetState betState);

     public List<BetState> getBetStatus(Long marketId, Integer matchOver, Integer session);

    public List<BetState> getBetStatus(Long marketId);

    public Map<String, String> getUserBlackListOvers(Long marketId, User user,
	    MatchScore currentOver);

    public Map<String, UserSessionBets> getSessionWiseMarketBets(
	    List<BetState> betStates);

    public int submitUserBet(User user, long marketId, int betRuns,
	    int betRunsAmount, int betWickets, int betWicketsAmount,
	    int session, int over);
    
    public int submitUserBet(User user, long marketId, int betRuns, int betWickets,
    	    int session, int over);
    
    public int submitUserBet(long userId, long marketId, int betRuns, int betWickets,
    	    int session, int over);
    
    public String autoJoinMarket(String marketToken, User user, ArrayList<FacebookUserFriend> friends);
    
    public Market getMarketFromMarketId( Long marketId );

	public int updateSsessionCompletion(String matchId, String sessionId);
	
	public MatchMaster getMatchById( Long matchId );

	public int updateMatchStatus(String matchId, MatchStatus matchStatus);

	public List<Market> getMarketInvitesForUser(Long matchId,
			ArrayList<FacebookUserFriend> friends);
	
	public int updateMarketWinners( Long marketId, String winners );
	
	public void penaltyForUserToJoiningLate(User user, Long marketId, MatchScore latestScore);

	public void calculateBetStatesWithoutBetAmountForLatestOver(
			Long marketId, MatchScore latestScore, List<BetState> betStates, List<UserBet> userBets, List<AmountLeft> amountLefts);

	public Map<Long, List<BetState>> getBetStatusByMarket(Long matchId,
			Integer matchOver, Integer sessionId);

	public Map<String, UserSessionBets> getSessionWiseMarketBets(
			List<BetState> betStates, MatchScore latestScore);

	Map<Long, List<UserBet>> getUserBetsByMarket(long matchId, int sessionId,
			int matchOver);

	void calculateBetStatesWithOutBetAmount(Long marketId,
			List<BetState> betStates, List<AmountLeft> amountLefts);
	
	void autoJoinMarketForVirtualUser(Market market);

	void submitBetForVirtualUser(ScoreUpdate scoreUpdate, int currentBall); 
	
	List<Market> getMarketsByMatch(long matchId);
}
