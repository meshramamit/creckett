/**
 * 
 */
package com.creckett.service.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.creckett.config.CreckettMarketCalculationMap;
import com.creckett.constant.CreckettConstant;
import com.creckett.core.betresult.BetResultAmount;
import com.creckett.core.betresult.BetResultCalculationStrategy;
import com.creckett.dao.AmountLeftDAO;
import com.creckett.dao.ArchivedAmountLeftDAO;
import com.creckett.dao.BetStateDAO;
import com.creckett.dao.MarketDAO;
import com.creckett.dao.MarketDetailChartDAO;
import com.creckett.dao.MatchMasterDAO;
import com.creckett.dao.MatchScoreDAO;
import com.creckett.dao.TeamDAO;
import com.creckett.dao.UserBetDAO;
import com.creckett.dao.UserDAO;
import com.creckett.dao.UserMatchAmountLeftDAO;
import com.creckett.dto.FacebookUserFriend;
import com.creckett.dto.MatchMasterWithMarketDTO;
import com.creckett.dto.ScoreUpdate;
import com.creckett.dto.UserBetState;
import com.creckett.dto.UserSessionBets;
import com.creckett.logger.CreckettLogger;
import com.creckett.messaging.application.MessagingAdaptor;
import com.creckett.model.AmountLeft;
import com.creckett.model.ArchivedAmountLeft;
import com.creckett.model.BetState;
import com.creckett.model.Market;
import com.creckett.model.MarketUser;
import com.creckett.model.MatchMaster;
import com.creckett.model.MatchScore;
import com.creckett.model.MatchStatus;
import com.creckett.model.User;
import com.creckett.model.UserBet;
import com.creckett.model.UserMatchAmountLeft;
import com.creckett.service.MatchService;
import com.creckett.service.UserService;
import com.creckett.service.bet.VirtualUserBetSubmissionStrategy;
import com.creckett.util.DateUtils;
import com.creckett.util.StringUtil;

/**
 * @author Dhaval
 * 
 */
public class MatchServiceImpl implements MatchService {

	private MatchMasterDAO matchMasterDAO;

	private MarketDAO marketDAO;

	private TeamDAO teamDAO;

	private MatchScoreDAO matchScoreDAO;

	private UserBetDAO userBetDAO;

	private BetStateDAO betStateDAO;

	private AmountLeftDAO amountLeftDAO;

	private ArchivedAmountLeftDAO archivedAmountLeftDAO;

	private UserDAO userDAO;
	
	private MarketDetailChartDAO marketDetailChartDAO;
	
	private UserService userService;

	private StringUtil stringUtil = new StringUtil();

	private NumberFormat numberFormater = NumberFormat.getNumberInstance();

	private Integer leftAmount;

	private CreckettLogger creckettLogger = CreckettLogger.getInstance();

	private BetResultCalculationStrategy betResultCalculationStrategy;

	private VirtualUserBetSubmissionStrategy virtualUserBetSubmissionStrategy;

	private UserMatchAmountLeftDAO userMatchAmountLeftDao;
	
	private MessagingAdaptor messagingAdaptor;
	
	
	public MarketDetailChartDAO getMarketDetailChartDAO() {
		return marketDetailChartDAO;
	}

	public void setMarketDetailChartDAO(MarketDetailChartDAO marketDetailChartDAO) {
		this.marketDetailChartDAO = marketDetailChartDAO;
	}

	public MessagingAdaptor getMessagingAdaptor() {
		return messagingAdaptor;
	}

	public void setMessagingAdaptor(MessagingAdaptor messagingAdaptor) {
		this.messagingAdaptor = messagingAdaptor;
	}

	public UserMatchAmountLeftDAO getUserMatchAmountLeftDao() {
		return userMatchAmountLeftDao;
	}

	public void setUserMatchAmountLeftDao(
			UserMatchAmountLeftDAO userMatchAmountLeftDao) {
		this.userMatchAmountLeftDao = userMatchAmountLeftDao;
	}

	public VirtualUserBetSubmissionStrategy getVirtualUserBetSubmissionStrategy() {
		return virtualUserBetSubmissionStrategy;
	}

	public void setVirtualUserBetSubmissionStrategy(
			VirtualUserBetSubmissionStrategy virtualUserBetSubmissionStrategy) {
		this.virtualUserBetSubmissionStrategy = virtualUserBetSubmissionStrategy;
	}

	public BetResultCalculationStrategy getBetResultCalculationStrategy() {
		return betResultCalculationStrategy;
	}

	public void setBetResultCalculationStrategy(
			BetResultCalculationStrategy betResultCalculationStrategy) {
		this.betResultCalculationStrategy = betResultCalculationStrategy;
	}

	@Override
	public List<MatchMaster> getListOfUpcomingMatches(Date fromDate, Date toDate) {
		List<MatchMaster> activeMatches = matchMasterDAO
				.getListOfUpcomingMatches(fromDate, toDate);
		List<MatchMaster> playingMatches = matchMasterDAO
				.getListOfPlayingMatches();
		if (playingMatches != null && !playingMatches.isEmpty()) {
			if (activeMatches == null) {
				return playingMatches;
			}
			activeMatches.addAll(playingMatches);
		}
		return activeMatches;
	}

	@Override
	public List<Market> getMatchMarketsForUser(Long matchId, Long userId) {

		List<Market> matchMarketsForUser = marketDAO.getMatchMarketsForUser(
				matchId, userId);

		if (matchMarketsForUser == null || matchMarketsForUser.isEmpty())
			return null;

		return matchMarketsForUser;
	}

	@Override
	public List<MatchMasterWithMarketDTO> getUpcomingMatchesWithMarketForUser(
			Long userId, Date fromDate, Date toDate) {

		List<MatchMasterWithMarketDTO> matchesWithMarket = new ArrayList<MatchMasterWithMarketDTO>();

		List<MatchMaster> matches = matchMasterDAO.getListOfUpcomingMatches(
				fromDate, toDate);

		for (MatchMaster matchMaster : matches) {
			List<Market> userMarketForMatch = marketDAO.getMatchMarketsForUser(
					matchMaster.getId(), userId);
			matchesWithMarket.add(new MatchMasterWithMarketDTO(matchMaster,
					userMarketForMatch));
		}
		return matchesWithMarket;
	}

	@Override
	public Market createMarketForUser(User userId, Long matchId,
			int groupOverValue, String difficultyLevel) throws Exception {
		if (marketDAO.isMarketCreatedByModerator(matchId, userId.getId())){
			throw new Exception( "You have already created challenge for this match..." );
		}
		MatchMaster matchMaster = matchMasterDAO.get(matchId);
		MatchScore latestMatchScore = getLatestMatchScore(matchId);

		MarketUser marketUser = new MarketUser();
		marketUser.setCreationDate(new Date());
		marketUser.setUserId(userId);

		List<MarketUser> marketUsers = new ArrayList<MarketUser>();
		marketUsers.add(marketUser);

		Market market = new Market();
		market.setCreationDate(new Date());
		market.setMarketCode(stringUtil.getUUID());
		market.setMarketUsers(marketUsers);
		market.setMatch(matchMaster);
		market.setModeratorId(userId);
		market.setProcessedOver(-1);
		market.setUpdateDate(new Date());
		market.setCreateDate(new Date());
		if (difficultyLevel != null && !difficultyLevel.isEmpty()) {
			market.setDifficultyLevel(difficultyLevel);
		}
		if (latestMatchScore != null) {
			market.setMatchSession(latestMatchScore.getSessionId());
		} else {
			market.setMatchSession(1);
		}
		market.setGroupOverValue(groupOverValue);

		List<Market> markets = matchMaster.getMarkets();

		if (markets == null)
			markets = new ArrayList<Market>();

		markets.add(market);
		matchMasterDAO.save(matchMaster);

		AmountLeft amountLeft = new AmountLeft();
		amountLeft.setMarketId(market);
		amountLeft.setLeftAmount(leftAmount);
		amountLeft.setMatchId(matchMaster);
		amountLeft.setOver(0);
		amountLeft.setSessionId(1);
		amountLeft.setUserId(userId);
		amountLeftDAO.save(amountLeft);

		return market;

	}

	private List<UserBet> getMarketUserBetsForOverAndSession(
			List<UserBet> allBets, int over, int session) {
		List<UserBet> toReturn = new ArrayList<UserBet>();
		for (UserBet userBet : allBets) {
			if (userBet.getMatchOver().equals(over)
					&& userBet.getMatchSession().equals(session)) {
				toReturn.add(userBet);
			}
		}
		return toReturn;
	}

	public List<BetState> getBetStatus(Long marketId, Integer matchOver,
			Integer session) {
		return betStateDAO.retrieveBetStatusForOvers(marketId, matchOver,
				session);
	}

	@Override
	public List<BetState> getBetStatus(Long marketId) {
		return betStateDAO.retrieveBetStatusForOvers(marketId);
	}

	@Override
	public List<UserBet> getUserBets(Long marketId, Integer sessionId,
			Integer over) {
		return userBetDAO.getAllUserBetsForMarket(marketId, sessionId, over);
	}

	@Override
	public MatchScore getMatchScoreForOver(Long matchId, int over,
			Integer matchSession) {
		return matchScoreDAO.getMatchScoreForOver(matchId, over, matchSession);
	}

	@Override
	public void saveUserBetState(BetState betState) {
		this.betStateDAO.save(betState);
	}

	@Override
	public Market getMarketById(Long marketId) {

		return marketDAO.get(marketId);
	}

	@Override
	public MatchScore getLatestInvalidatedOver(Long matchId) {

		return matchScoreDAO.getLatestInvalidatedOver(matchId);
	}

	@Override
	public MatchScore getLatestMatchScore(Long matchId) {
		return matchScoreDAO.getLatestMatchScore(matchId);
	}

	private void updateWinningBet(UserBet userBet, List<BetState> betStates,
			boolean runWinner, boolean wicketWinner, boolean betWinner) {
		BetState betState = getBetStateFromUserBet(userBet, betStates);
		if (betState != null && !betState.getResultAmount().isEmpty()) {
			betState.setRunsWinner(runWinner);
			betState.setWicketsWinner(wicketWinner);
			betState.setWinner(betWinner);
			saveUserBetState(betState);
		}
	}

	private void createBetState(BetState betState, Market market, int over,
			int session, MatchScore matchScore, String resultAmount,
			UserBet userBet, boolean isWinner, boolean isWicketWinner) {
		betState.setMarket(market);
		betState.setMatchOver(over);
		betState.setResultAmount(resultAmount);
		betState.setRuns(userBet.getRunBetAmount());
		betState.setRunsBet(userBet.getRunsBet());
		betState.setSession(session);
		betState.setTimeFactor(userBet.getTimeFactor());
		betState.setUser(userBet.getUser());
		betState.setWickets(userBet.getWicketBetAmount());
		betState.setWicketsBet(userBet.getWicketsBet());
		betState.setWinner(isWinner);
		betState.setWicketsWinner(isWicketWinner);
		betState.setRunsWinner(isWinner);
		saveUserBetState(betState);
	}

	public MatchMasterDAO getMatchMasterDAO() {
		return matchMasterDAO;
	}

	public void setMatchMasterDAO(MatchMasterDAO matchMasterDAO) {
		this.matchMasterDAO = matchMasterDAO;
	}

	public MarketDAO getMarketDAO() {
		return marketDAO;
	}

	public void setMarketDAO(MarketDAO marketDAO) {
		this.marketDAO = marketDAO;
	}

	@Override
	public MatchMaster createMatch(String team1, String team2, String overs,
			String matchDate, String matchTime) {
		try {
			int matchOvers = StringUtil.getIntFromString(overs);
			Date matchCombineDate = DateUtils.getDateFromStringDateTime(
					matchDate, matchTime);
			MatchMaster match = new MatchMaster();
			match.setMatchDate(matchCombineDate);
			match.setMatchName(team1 + " vs " + team2);
			match.setDescription(match.getMatchName());
			match.setOver(matchOvers);
			match.setTeam1(teamDAO.getTeamByName(team1));
			match.setTeam2(teamDAO.getTeamByName(team2));
			match.setStatus(MatchStatus.ACTIVE);
			matchMasterDAO.save(match);
			MatchScore matchScore = new MatchScore();
			matchScore.setInvalidate(true);
			matchScore.setMatchId(match);
			matchScore.setMatchOver(0);
			matchScore.setRuns(0);
			matchScore.setScore("0 for 0");
			matchScore.setSessionId(1);
			matchScore.setTimestamp(new Date());
			matchScore.setWickets(0);
			matchScoreDAO.save(matchScore);
			return match;
		} catch (ParseException e) {
			creckettLogger.error(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException("Not valid argument:"
					+ matchTime + ":" + matchDate);
		} catch (NumberFormatException e) {
			creckettLogger.error(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException("Not valid argument:" + overs);
		}
	}

	@Override
	public int deleteMatch(String matchId) {
		try {
			long deleteMatchId = StringUtil.getLongFromString(matchId);
			MatchMaster match = matchMasterDAO.get(deleteMatchId);
			if (match != null) {
				matchMasterDAO.delete(match);
				return 1;
			}
			return 0;
		} catch (NumberFormatException exception) {
			creckettLogger.error(exception.getMessage());
			exception.printStackTrace();
			throw new IllegalArgumentException("Not valid parameter:" + matchId);
		}
	}

	@Override
	public int updateMatch(String matchId, String team1, String team2,
			String overs, String matchDate, String matchTime, String winner) {
		try {
			long updateMatchId = StringUtil.getLongFromString(matchId);
			int matchOvers = StringUtil.getIntFromString(overs);
			Date matchCombineDate = DateUtils.getDateFromStringDateTime(
					matchDate, matchTime);
			MatchMaster match = matchMasterDAO.get(updateMatchId);
			if (match == null)
				return 0;
			match.setMatchDate(matchCombineDate);
			match.setMatchName(team1 + " vs " + team2);
			match.setDescription(match.getMatchName());
			match.setOver(matchOvers);
			match.setTeam1(teamDAO.getTeamByName(team1));
			match.setTeam2(teamDAO.getTeamByName(team2));
			if (winner != null && !winner.isEmpty()
					&& (match.getMatchDate().compareTo(new Date()) <= 0)) {
				match.setStatus(MatchStatus.FINISHED);
				insertMatchAmountLeft(match);
				match.setWinner(winner);
			} else {
				match.setStatus(MatchStatus.ACTIVE);
			}
			return (matchMasterDAO.save(match) != null) ? 1 : 0;
		} catch (ParseException e) {
			creckettLogger.error(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException("Not valid argument:"
					+ matchTime + ":" + matchDate);
		} catch (NumberFormatException e) {
			creckettLogger.error(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException("Not valid argument:" + overs
					+ ":" + matchId);
		}
	}

	private void insertMatchAmountLeft(MatchMaster match) {
		List<AmountLeft> amountLefts = marketDetailChartDAO.getMarketMatchDetails(match.getId());
		for (AmountLeft amountLeft : amountLefts) {
			UserMatchAmountLeft userMatchAmountLeft = new UserMatchAmountLeft();
			userMatchAmountLeft.setAmountLeft(amountLeft.getLeftAmount());
			userMatchAmountLeft.setUser(amountLeft.getUserId());
			userMatchAmountLeft.setMatchMaster(match);
			userMatchAmountLeftDao.save(userMatchAmountLeft);
		}

	}

	@Override
	public Map<String, List<MatchScore>> getMatchScore(String matchId) {
		try {
			List<MatchScore> matchScores = matchScoreDAO
					.getMatchScore(StringUtil.getLongFromString(matchId));
			if (matchScores != null) {
				Map<String, List<MatchScore>> sessionScoreMap = new HashMap<String, List<MatchScore>>();
				Iterator<MatchScore> scoreIterator = matchScores.iterator();
				while (scoreIterator.hasNext()) {
					MatchScore score = scoreIterator.next();
					if (sessionScoreMap.containsKey(score.getSessionId() + "")) {
						sessionScoreMap.get(score.getSessionId() + "").add(
								score);
					} else {
						List<MatchScore> scoreList = new ArrayList<MatchScore>();
						scoreList.add(score);
						sessionScoreMap.put(score.getSessionId() + "",
								scoreList);
					}
				}
				return sessionScoreMap;
			}
			return null;
		} catch (NumberFormatException exception) {
			creckettLogger.error(exception.getMessage());
			exception.printStackTrace();
			throw new IllegalArgumentException("Not valid parameter:" + matchId);
		}
	}

	@Override
	public int addNewScoreAndInvalidate(String matchId, String sessionId,
			String over) {
		try {
			long id = StringUtil.getLongFromString(matchId);
			MatchMaster matchMaster = matchMasterDAO.get(id);
			MatchScore matchScore = new MatchScore();
			matchScore.setMatchId(matchMaster);
			matchScore.setMatchOver(StringUtil.getIntFromString(over));
			matchScore.setSessionId(StringUtil.getIntFromString(sessionId));
			matchScore.setInvalidate(true);
			matchScore.setTimestamp(new Date());
			return (matchScoreDAO.save(matchScore) != null) ? 1 : 0;
		} catch (NumberFormatException exception) {
			creckettLogger.error(exception.getMessage());
			exception.printStackTrace();
			throw new IllegalArgumentException("Not valid parameter:" + matchId
					+ ":" + sessionId + ":" + over);
		}
	}

	@Override
	public int updateScore(String matchId, String sessionId, String over,
			String runs, String wickets, String score) {
		try {
			MatchScore matchScore = matchScoreDAO.get(
					StringUtil.getLongFromString(matchId),
					StringUtil.getIntFromString(sessionId),
					StringUtil.getIntFromString(over));
			if (matchScore != null) {
				matchScore.setRuns(StringUtil.getIntFromString(runs));
				matchScore.setScore(score);
				matchScore.setWickets(StringUtil.getIntFromString(wickets));
				if (StringUtil.getIntFromString(sessionId) == 1
						&& matchScore.getMatchId().getOver().intValue() == StringUtil
								.getIntFromString(over)) {
					MatchScore nextMatchScore = new MatchScore();
					nextMatchScore.setInvalidate(true);
					nextMatchScore.setMatchId(matchScore.getMatchId());
					nextMatchScore.setMatchOver(0);
					nextMatchScore.setRuns(0);
					nextMatchScore.setScore("0 for 0");
					nextMatchScore.setSessionId(2);
					nextMatchScore.setTimestamp(new Date());
					nextMatchScore.setWickets(0);
					matchScoreDAO.save(nextMatchScore);

				}
				return (matchScoreDAO.save(matchScore) != null) ? 1 : 0;
			} else {
				MatchScore nextMatchScore = new MatchScore();
				nextMatchScore.setInvalidate(true);
				MatchMaster matchMaster = matchMasterDAO.get(StringUtil
						.getLongFromString(matchId));
				nextMatchScore.setMatchId(matchMaster);
				nextMatchScore.setMatchOver(StringUtil.getIntFromString(over));
				nextMatchScore.setRuns(StringUtil.getIntFromString(runs));
				nextMatchScore.setScore(StringUtil.getIntFromString(runs)
						+ " for " + StringUtil.getIntFromString(wickets));
				nextMatchScore.setSessionId(StringUtil
						.getIntFromString(sessionId));
				nextMatchScore.setTimestamp(new Date());
				nextMatchScore.setWickets(StringUtil.getIntFromString(wickets));
				return (matchScoreDAO.save(nextMatchScore) != null) ? 1 : 0;
			}
		} catch (NumberFormatException exception) {
			creckettLogger.error(exception.getMessage());
			exception.printStackTrace();
			throw new IllegalArgumentException("Not valid parameter:" + matchId
					+ ":" + sessionId + ":" + over);
		}
	}

	public TeamDAO getTeamDAO() {
		return teamDAO;
	}

	public Integer getLeftAmount() {
		return leftAmount;
	}

	public void setLeftAmount(Integer leftAmount) {
		this.leftAmount = leftAmount;
	}

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

	public StringUtil getStringUtil() {
		return stringUtil;
	}

	public void setStringUtil(StringUtil stringUtil) {
		this.stringUtil = stringUtil;
	}

	public MatchScoreDAO getMatchScoreDAO() {
		return matchScoreDAO;
	}

	public void setMatchScoreDAO(MatchScoreDAO matchScoreDAO) {
		this.matchScoreDAO = matchScoreDAO;
	}

	@Override
	public int deleteMarket(Long marketId, User user) {

		Market market = marketDAO.get(marketId);
		if (market != null) {
			if (market.getModeratorId().getId().equals(user.getId())) {
				if (market.getMarketUsers().size() > 2) {
					return 0;
				} else {
					amountLeftDAO.deleteForMarket(market);
					marketDAO.delete(market);
					return 1;
				}
			} else {
				Iterator<MarketUser> marketUsers = market.getMarketUsers()
						.iterator();
				while (marketUsers.hasNext()) {
					MarketUser marketUser = marketUsers.next();
					if (marketUser.getUserId().getId().equals(user.getId())) {
						amountLeftDAO.deleteUserAmountForMarket(market,
								user.getId());
						marketUsers.remove();
						break;
					}
				}
				marketDAO.save(market);
				return 1;
			}
		} else {
			return 0;
		}
	}

	@Override
	public Market updateMarketForUser(User user, Long marketId,
			int groupOverValue, String difficultyLevel) {
		Market marketToUpdate = marketDAO.get(marketId);
		if (marketToUpdate != null) {
			marketToUpdate.setGroupOverValue(groupOverValue);
			if (difficultyLevel != null && !difficultyLevel.isEmpty()) {
				marketToUpdate.setDifficultyLevel(difficultyLevel);
			}
			marketDAO.save(marketToUpdate);
		}
		return marketToUpdate;
	}

	public UserBetDAO getUserBetDAO() {
		return userBetDAO;
	}

	public void setUserBetDAO(UserBetDAO userBetDAO) {
		this.userBetDAO = userBetDAO;
	}

	public BetStateDAO getUserBetStateDAO() {
		return betStateDAO;
	}

	public void setUserBetStateDAO(BetStateDAO betStateDAO) {
		this.betStateDAO = betStateDAO;
	}

	public BetStateDAO getBetStateDAO() {
		return betStateDAO;
	}

	public void setBetStateDAO(BetStateDAO betStateDAO) {
		this.betStateDAO = betStateDAO;
	}

	public AmountLeftDAO getAmountLeftDAO() {
		return amountLeftDAO;
	}

	public void setAmountLeftDAO(AmountLeftDAO amountLeftDAO) {
		this.amountLeftDAO = amountLeftDAO;
	}

	@Override
	public Map<String, String> getUserBlackListOvers(Long marketId, User user,
			MatchScore currentOver) {
		Map<String, String> toReturn = new HashMap<String, String>();
		List<UserBet> betStates = userBetDAO.getAllUserBetsForMarket(marketId,
				user);

		for (UserBet bet : betStates) {
			String overs = toReturn.get(bet.getMatchSession());
			if (overs == null) {
				toReturn.put(bet.getMatchSession().toString(), bet
						.getMatchOver().toString());
			} else {
				toReturn.put(bet.getMatchSession().toString(),
						overs.concat("," + bet.getMatchOver().toString()));
			}
		}
		return toReturn;
	}

	@Deprecated
	public Map<String, String> getUserBlackListOversOld(Long marketId,
			User user, MatchScore currentOver) {
		Map<String, String> toReturn = new HashMap<String, String>();
		List<UserBet> betStates = userBetDAO.getAllUserBetsForMarket(marketId,
				user);
		Iterator<UserBet> bets = betStates.iterator();
		while (bets.hasNext()) {
			UserBet bet = bets.next();
			StringBuffer stringBuffer = new StringBuffer("");
			if (bet.getUser().getId().equals(user.getId())) {
				if (toReturn.containsKey(currentOver.getSessionId().toString())) {
					if (!toReturn.get(currentOver.getSessionId().toString())
							.isEmpty()) {
						stringBuffer.append(toReturn.get(currentOver
								.getSessionId().toString()));
						stringBuffer.append(",");
					}
				}
				stringBuffer.append(bet.getMatchOver());
				toReturn.put(currentOver.getSessionId().toString(),
						stringBuffer.toString());
			}
		}
		return toReturn;
	}

	@Override
	public Map<String, UserSessionBets> getSessionWiseMarketBets(
			List<BetState> betStates) {
		Map<String, UserSessionBets> toReturn = new HashMap<String, UserSessionBets>();
		Iterator<BetState> bets = betStates.iterator();
		while (bets.hasNext()) {
			BetState bet = bets.next();
			com.creckett.dto.BetState sessionWiseBet = null;
			List<com.creckett.dto.BetState> betStats = null;
			UserSessionBets userSessionBets = null;
			if (toReturn.containsKey(bet.getSession().toString())) {
				userSessionBets = toReturn.get(bet.getSession().toString());
				betStats = userSessionBets.getBets();
			} else {
				userSessionBets = new UserSessionBets();
				betStats = new ArrayList<com.creckett.dto.BetState>();
				userSessionBets.setSession("session"
						+ bet.getSession().toString());
				userSessionBets.setBets(betStats);
				toReturn.put(bet.getSession().toString(), userSessionBets);
			}
			for (com.creckett.dto.BetState betState : betStats) {
				if (betState.getGroupOverId() == bet.getMatchOver()) {
					sessionWiseBet = betState;
					break;
				}
			}
			if (sessionWiseBet == null) {
				sessionWiseBet = new com.creckett.dto.BetState();
				sessionWiseBet.setGroupOverId(bet.getMatchOver());
				if (bet.getMarket().getGroupOverValue() == 1) {
					sessionWiseBet.setOver(bet.getMatchOver().toString());
					sessionWiseBet.setOverScore(com.creckett.dto.MatchScore
							.buildObjectFromModel(matchScoreDAO
									.getMatchScoreForOver(bet.getMarket()
											.getMatch().getId(), bet
											.getMatchOver().intValue(), bet
											.getSession())));
				} else {
					String over = ((bet.getMarket().getGroupOverValue() * (bet
							.getMatchOver() - 1)) + 1)
							+ "-"
							+ (bet.getMatchOver() * bet.getMarket()
									.getGroupOverValue());
					sessionWiseBet.setOver(over);
					sessionWiseBet.setOverScore(com.creckett.dto.MatchScore
							.buildObjectFromModel(matchScoreDAO
									.getMatchScoreForOver(bet.getMarket()
											.getMatch().getId(), (bet
											.getMatchOver() * bet.getMarket()
											.getGroupOverValue()), bet
											.getSession())));
				}
				if (betStats != null) {
					betStats.add(sessionWiseBet);
				}
			}
			UserBetState userBetState = new UserBetState();
			userBetState.setUserName(bet.getUser().getName());
			userBetState.setRuns(bet.getRuns());
			userBetState.setRunsBet(bet.getRunsBet());
			userBetState.setWickets(bet.getWickets());
			userBetState.setWicketsBet(bet.getWicketsBet());
			userBetState.setResultAmount(bet.getResultAmount());
			userBetState.setTimefactor(bet.getTimeFactor());
			userBetState.setRunsWinner(Boolean.toString(bet.isRunsWinner()));
			userBetState.setWicketsWinner(Boolean.toString(bet
					.isWicketsWinner()));
			if (bet.isWinner()) {
				sessionWiseBet.setWinningBet(userBetState);
				if (sessionWiseBet.getWinners() != null
						&& !sessionWiseBet.getWinners().isEmpty()) {
					sessionWiseBet.setWinners(sessionWiseBet.getWinners()
							+ ", " + bet.getUser().getName());
				} else {
					sessionWiseBet.setWinners(bet.getUser().getName());
				}
			}
			if (sessionWiseBet.getUserBetStates() == null) {
				sessionWiseBet.setUserBetStates(new ArrayList<UserBetState>());
			}
			sessionWiseBet.getUserBetStates().add(userBetState);
		}

		return toReturn;
	}

	@Override
	public int submitUserBet(User user, long marketId, int betRuns,
			int betRunsAmount, int betWickets, int betWicketsAmount,
			int session, int over) {

		Market market = marketDAO.get(marketId);
		if (!userBetDAO.isUserBetExist(marketId, user.getId(), session, over)) {
			AmountLeft amountLeft = amountLeftDAO
					.getAmountByMarketMatchMaximumSessionAndMaximumOver(market,
							user.getId());
			if (amountLeft != null
					&& amountLeft.getLeftAmount() >= (betRunsAmount + betWicketsAmount)) {
				UserBet userBet = new UserBet();
				userBet.setRunBetAmount(betRunsAmount);
				userBet.setWicketBetAmount(betWicketsAmount);
				userBet.setBetTime(new Date());
				userBet.setMarket(market);
				userBet.setMatchOver(over);
				userBet.setMatchSession(session);
				userBet.setRunsBet(betRuns);
				userBet.setUser(user);
				userBet.setWicketsBet(betWickets);
				userBetDAO.save(userBet);
				amountLeft.setLeftAmount(amountLeft.getLeftAmount()
						- betRunsAmount - betWicketsAmount);
				amountLeftDAO.save(amountLeft);
				BetState betState = new BetState();
				betState.setMarket(market);
				betState.setMatchOver(over);
				betState.setResultAmount("");
				betState.setRuns(betRuns);
				betState.setRunsBet(betRunsAmount);
				betState.setSession(session);
				betState.setTimeFactor("");
				betState.setUser(userBet.getUser());
				betState.setWickets(betWickets);
				betState.setWicketsBet(betWicketsAmount);
				betStateDAO.save(betState);
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int submitUserBet(User user, long marketId, int betRuns,
			int betWickets, int session, int over) {
		Market market = marketDAO.get(marketId);
		if (!userBetDAO.isUserBetExist(marketId, user.getId(), session, over)) {
			AmountLeft amountLeft = amountLeftDAO
					.getAmountByMarketMatchMaximumSessionAndMaximumOver(market,
							user.getId());
			if (amountLeft != null && amountLeft.getLeftAmount() >= 0) {
				UserBet userBet = new UserBet();
				// userBet.setRunBetAmount(betRunsAmount);
				// userBet.setWicketBetAmount(betWicketsAmount);
				userBet.setBetTime(new Date());
				userBet.setMarket(market);
				userBet.setMatchOver(over);
				userBet.setMatchSession(session);
				userBet.setRunsBet(betRuns);
				userBet.setUser(user);
				userBet.setWicketsBet(betWickets);
				userBetDAO.save(userBet);
				BetState betState = new BetState();
				betState.setMarket(market);
				betState.setMatchOver(over);
				betState.setResultAmount("");
				betState.setRunsBet(betRuns);
				betState.setSession(session);
				betState.setTimeFactor("");
				betState.setUser(userBet.getUser());
				betState.setWicketsBet(betWickets);
				betStateDAO.save(betState);
				return 1;
			}
		}
		return 0;
	}

	@Override
	public void calculateBetStatesWithOutBetAmount(Long marketId,
			List<BetState> betStates, List<AmountLeft> amountLefts) {

		// sync issue between users playing in same market
		if (CreckettMarketCalculationMap.getInstance().isMarketCalculating(
				marketId)) {
			return;
		}
		CreckettMarketCalculationMap.getInstance().addMarketEntry(marketId);
		// fetch market from market id passed in request
		Market market = getMarketById(marketId);
		// get user bets from database
		List<UserBet> userBets = userBetDAO.getAllUserBetsForMarket(marketId);
		// fetch latest match score
		MatchScore latestMatchScore = getLatestMatchScore(market.getMatch()
				.getId());

		if (market.getDifficultyLevel().equalsIgnoreCase(
				CreckettConstant.DIFFICULTY_LEVEL_EASY)) {
			calculateBetsByEasyMethod(market, userBets, betStates,
					latestMatchScore, amountLefts);
		}
	}

	private void calculateBetsByEasyMethod(Market market,
			List<UserBet> userBets, List<BetState> betStates,
			MatchScore latestMatchScore, List<AmountLeft> amountLefts) {

		for (UserBet userBet : userBets) {
			MatchScore matchScore = null;
			BetState userBetState = getBetStateFromUserBet(userBet, betStates);
			if ((userBetState == null)
					|| (userBetState != null && !userBetState.getResultAmount()
							.isEmpty())) {
				continue;
			} else if (userBetState != null
					&& userBetState.getResultAmount().isEmpty()) {
				if (market.getGroupOverValue() == 1) {
					if (userBetState.getSession().intValue() > latestMatchScore
							.getSessionId().intValue()) {
						continue;
					} else if (userBetState.getSession().intValue() < latestMatchScore
							.getSessionId().intValue()) {
						matchScore = matchScoreDAO.get(market.getMatch()
								.getId(), userBet.getMatchSession(), userBet
								.getMatchOver());
					} else {
						if (userBetState.getMatchOver().intValue() > latestMatchScore
								.getMatchOver().intValue()) {
							continue;
						} else if (userBetState.getMatchOver().intValue() < latestMatchScore
								.getMatchOver().intValue()) {
							matchScore = matchScoreDAO.get(market.getMatch()
									.getId(), userBet.getMatchSession(),
									userBet.getMatchOver());
						} else {
							matchScore = latestMatchScore;
						}
					}
				} else {
					int overValue = userBetState.getMatchOver().intValue();
					overValue = overValue * market.getGroupOverValue();
					if (overValue > latestMatchScore.getMatchOver().intValue()) {
						continue;
					} else {
						int minOver = overValue - market.getGroupOverValue()
								+ 1;
						int totalRuns = 0;
						int totalWickets = 0;
						List<MatchScore> scoreList = matchScoreDAO
								.getMatchScoreForOvers(market.getMatch()
										.getId(), minOver, overValue, userBet
										.getMatchSession());
						for (MatchScore score : scoreList) {
							totalRuns += score.getRuns().intValue();
							totalWickets += score.getWickets().intValue();
							if (score.getMatchOver().intValue() == overValue) {
								matchScore = score;
							}
						}
						matchScore.setRuns(totalRuns);
						matchScore.setWickets(totalWickets);
					}
				}

				if (matchScore == null) {
					return;
				}

				BetResultAmount betResultAmount = betResultCalculationStrategy
						.calculate(userBet, matchScore);
				int runAmount = betResultAmount.getRunsAmount();
				int wicketAmount = betResultAmount.getWicketAmount();
				userBet.setRunBetAmount(runAmount);
				userBet.setWicketBetAmount(wicketAmount);
				int finalAmount = runAmount + wicketAmount;
				String resultAmount = numberFormater.format((finalAmount));
				createBetState(userBetState, market, userBet.getMatchOver(),
						userBet.getMatchSession(), matchScore, resultAmount,
						userBet, false, false);
				AmountLeft amountLeft = getAmountLeft(amountLefts,
						market.getId(), userBet.getUser().getId());
				ArchivedAmountLeft archivedAmountLeft = ArchivedAmountLeft
						.build(amountLeft);
				archivedAmountLeftDAO.save(archivedAmountLeft);
				amountLeft.setMarketId(market);
				amountLeft.setMatchId(market.getMatch());
				amountLeft.setOver(matchScore.getMatchOver());
				amountLeft.setSessionId(matchScore.getSessionId());
				amountLeft.setUserId(userBet.getUser());
				amountLeft.setLeftAmount(amountLeft.getLeftAmount()
						+ finalAmount);
				amountLeftDAO.save(amountLeft);
				if (amountLeft.getLeftAmount() <= 0) {
					invalidateAllUserBets(userBets, userBet.getMatchOver(),
							userBet.getMatchSession(), userBet.getUser()
									.getId());
				}
			}

		}
		boolean haveAllUsersMissedTheBet = true;

		for (UserBet userBet : userBets) {
			if (userBet.getRunsBet().intValue() != CreckettConstant.DEFAULT_SUBMIT_BET_VALUE) {
				haveAllUsersMissedTheBet = false;
				break;
			}
		}

		// Dont decide winner if all users have missed the bet
		if (!haveAllUsersMissedTheBet) {
			for (UserBet userBet : userBets) {
				if (userBet.getMatchOver() > latestMatchScore.getMatchOver()) {
					continue;
				}
				long winningWicketAmount = -1;
				long winningRunsAmount = -1;
				long winningResultAmount = -1;
				// Calculate the winner per over
				List<UserBet> betsForWinnerPerOver = getMarketUserBetsForOverAndSession(
						userBets, userBet.getMatchOver(),
						userBet.getMatchSession());
				for (UserBet userBetForOver : betsForWinnerPerOver) {
					if (userBetForOver.getRunBetAmount() == null) {
						continue;
					}
					int runAmount = userBetForOver.getRunBetAmount();
					int wicketAmount = userBetForOver.getWicketBetAmount();
					int finalAmount = runAmount + wicketAmount;
					if (winningRunsAmount == -1) {
						winningRunsAmount = runAmount;
					} else {
						if (runAmount > winningRunsAmount) {
							winningRunsAmount = runAmount;
						}
					}
					if (winningWicketAmount == -1) {
						winningWicketAmount = wicketAmount;
					} else {
						if (wicketAmount > winningWicketAmount) {
							winningWicketAmount = wicketAmount;
						}
					}
					if (winningResultAmount == -1) {
						winningResultAmount = finalAmount;
					} else {
						if (finalAmount > winningResultAmount) {
							winningResultAmount = finalAmount;
						}
					}
				}
				for (UserBet betCalc : betsForWinnerPerOver) {
					boolean runWinner = false;
					boolean wicketWinner = false;
					boolean betWinner = false;
					if (betCalc.getRunBetAmount() == null) {
						continue;
					}
					if (betCalc.getRunBetAmount().longValue() == winningRunsAmount) {
						runWinner = true;
					}
					if (betCalc.getWicketBetAmount().longValue() == winningWicketAmount) {
						wicketWinner = true;
					}
					if ((betCalc.getWicketBetAmount().longValue() + betCalc
							.getRunBetAmount().longValue()) == winningResultAmount) {
						betWinner = true;
					}
					if (runWinner || wicketWinner || betWinner) {
						updateWinningBet(betCalc, betStates, runWinner,
								wicketWinner, betWinner);
					}
				}
			}
		}
	}

	private AmountLeft getAmountLeft(List<AmountLeft> amountLefts,
			Long marketId, Long userId) {
		for (AmountLeft amountLeft : amountLefts) {
			if (amountLeft.getMarketId().getId().longValue() == marketId
					.longValue()
					&& amountLeft.getUserId().getId().longValue() == userId
							.longValue()) {
				return amountLeft;
			}
		}
		return null;
	}

	private BetState getBetStateFromUserBet(UserBet userBet,
			List<BetState> betStates) {
		for (BetState betState : betStates) {
			if (betState.getSession().intValue() == userBet.getMatchSession()
					.intValue()
					&& betState.getMatchOver().intValue() == userBet
							.getMatchOver()
					&& betState.getMarket().getId() == userBet.getMarket()
							.getId()
					&& betState.getUser().getId() == userBet.getUser().getId()) {
				return betState;
			}
		}
		return null;
	}

	private void invalidateAllUserBets(List<UserBet> userBets, Integer over,
			Integer session, Long userId) {
		for (UserBet userBet : userBets) {
			if (userBet.getUser().getId().equals(userId)
					&& userBet.getMatchOver().intValue() > over.intValue()
					&& userBet.getMatchSession().intValue() >= session
							.intValue()) {
				userBetDAO.delete(userBet);
			}
		}
	}

	@Override
	public String autoJoinMarket(String marketToken, User user,
			ArrayList<FacebookUserFriend> friends) {
		Market market = marketDAO.getMarketByToken(marketToken);

		if (marketDAO.ifUserHasMarketForMatch(user, market.getMatch())) {
			return "You are alreay part of Market for Match";
		}

		if (!isUserModeratorFriend(market.getModeratorId().getProfileId(),
				friends)) {
			return "User is not Moderator's Friend.";
		}

		if (market.getMarketUsers() != null
				&& market.getMarketUsers().size() >= 6) {
			return "Market has already 6 players.";
		}

		joinUserInMarket(market, user);

		return "User successfully added to Market.";
	}

	private boolean isUserModeratorFriend(String profileId,
			ArrayList<FacebookUserFriend> friends) {
		for (FacebookUserFriend friend : friends) {
			if (friend.getProfileId().equalsIgnoreCase(profileId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Market joinMarket(String marketToken, User user) {

		Market market = marketDAO.getMarketByToken(marketToken);

		if (marketDAO.ifUserHasMarketForMatch(user, market.getMatch())) {
			return null;
		}

		joinUserInMarket(market, user);

		return market;
	}

	private void joinUserInMarket(Market market, User user) {

		MarketUser marketUser = new MarketUser();
		marketUser.setCreationDate(new Date());
		marketUser.setUserId(user);

		market.getMarketUsers().add(marketUser);

		AmountLeft amountLeft = new AmountLeft();
		amountLeft.setMarketId(market);
		amountLeft.setLeftAmount(leftAmount);
		amountLeft.setMatchId(market.getMatch());
		amountLeft.setOver(0);
		amountLeft.setSessionId(1);
		amountLeft.setUserId(user);
		amountLeftDAO.save(amountLeft);

	}

	@Override
	public Market getMarketFromMarketId(Long marketId) {
		return marketDAO.get(marketId);
	}

	public ArchivedAmountLeftDAO getArchivedAmountLeftDAO() {
		return archivedAmountLeftDAO;
	}

	public void setArchivedAmountLeftDAO(
			ArchivedAmountLeftDAO archivedAmountLeftDAO) {
		this.archivedAmountLeftDAO = archivedAmountLeftDAO;
	}

	@Override
	public int updateSsessionCompletion(String matchId, String sessionId) {
		MatchMaster match = matchMasterDAO.get(Long.valueOf(matchId));
		if (sessionId.equalsIgnoreCase("1")) {
			MatchScore matchScore = matchScoreDAO.get(
					StringUtil.getLongFromString(matchId), 2, 0);
			if (matchScore == null) {
				MatchScore nextMatchScore = new MatchScore();
				nextMatchScore.setInvalidate(true);
				nextMatchScore.setMatchId(match);
				nextMatchScore.setMatchOver(0);
				nextMatchScore.setRuns(0);
				nextMatchScore.setScore("0 for 0");
				nextMatchScore.setSessionId(2);
				nextMatchScore.setTimestamp(new Date());
				nextMatchScore.setWickets(0);
				matchScoreDAO.save(nextMatchScore);
			}
			return 1;
		} else if (sessionId.equalsIgnoreCase("2")) {
			match.setStatus(MatchStatus.FINISHED);
			matchMasterDAO.save(match);
			return 1;
		}
		return 0;
	}

	@Override
	public int updateMatchStatus(String matchId, MatchStatus matchStatus) {
		MatchMaster matchMaster = matchMasterDAO.get(Long.valueOf(matchId));
		if (matchMaster == null) {
			return 0;
		}
		matchMaster.setStatus(matchStatus);
		matchMasterDAO.save(matchMaster);
		return 1;
	}

	@Override
	public MatchMaster getMatchById(Long matchId) {
		return matchMasterDAO.get(matchId);
	}

	@Override
	public List<Market> getMarketInvitesForUser(Long matchId,
			ArrayList<FacebookUserFriend> friends) {
		if (friends != null && !friends.isEmpty()) {
			return marketDAO.getMatchInvites(matchId, friends);
		}
		return null;
	}

	public NumberFormat getNumberFormater() {
		return numberFormater;
	}

	public void setNumberFormater(NumberFormat numberFormater) {
		this.numberFormater = numberFormater;
	}

	@Override
	public int updateMarketWinners(Long marketId, String winners) {
		Market market = marketDAO.get(marketId);
		String winnerNames = "";
		if (market == null || winners == null || winners.isEmpty()) {
			return 0;
		}
		if (market.getMarketWinners() != null
				&& !market.getMarketWinners().isEmpty()) {
			return 1;
		}
		try {
			if (winners != null && !winners.isEmpty()) {
				String[] winnersArray = winners.split(",");
				int winnersLength = winnersArray.length;
				for (int index = 0; index < winnersLength; index++) {
					long userId = Long.parseLong(winnersArray[index]);
					if (!isUserValidMarketPlayer(market.getMarketUsers(),
							userId)) {
						return 0;
					}
					if (winnerNames != "") {
						winnerNames += ",";
					}
					winnerNames += userDAO.get(userId).getName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			creckettLogger.error(e.getMessage());
			return 0;
		}
		market.setMarketWinners(winnerNames);
		return marketDAO.save(market) != null ? 1 : 0;
	}

	private boolean isUserValidMarketPlayer(List<MarketUser> players,
			long userId) {
		for (MarketUser user : players) {
			if (user.getUserId().getId().longValue() == userId) {
				return true;
			}
		}
		return false;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void penaltyForUserToJoiningLate(User user, Long marketId,
			MatchScore latestScore) {
		// Return if match has not started or first over is yet to be bowled
		if (latestScore == null) {
			return;
		}
		Market market = getMarketById(marketId);
		int session = latestScore.getSessionId();
		int over = latestScore.getMatchOver();
		int noOfOversPassed = session * over;
		int penalty = betResultCalculationStrategy
				.getPaneltyForLateJoining(noOfOversPassed);
		AmountLeft amountLeft = amountLeftDAO
				.getAmountByMarketMatchMaximumSessionAndMaximumOver(market,
						user.getId());
		if (amountLeft.getOver().intValue() > 0) {
			return;
		}
		ArchivedAmountLeft archivedAmountLeft = ArchivedAmountLeft
				.build(amountLeft);
		archivedAmountLeftDAO.save(archivedAmountLeft);
		amountLeft.setMarketId(market);
		amountLeft.setMatchId(market.getMatch());
		amountLeft.setOver(latestScore.getMatchOver());
		amountLeft.setSessionId(latestScore.getSessionId());
		amountLeft.setUserId(user);
		amountLeft.setLeftAmount(amountLeft.getLeftAmount() + penalty);
		amountLeftDAO.save(amountLeft);

	}

	@Override
	public int submitUserBet(long userId, long marketId, int betRuns,
			int betWickets, int session, int over) {
		User user = userDAO.get(userId);
		return submitUserBet(user, marketId, betRuns, betWickets, session, over);
	}

	@Override
	public void calculateBetStatesWithoutBetAmountForLatestOver(Long marketId,
			MatchScore latestScore, List<BetState> betStates,
			List<UserBet> userBets, List<AmountLeft> amountLefts) {
		Market market = getMarketById(marketId);
		// get user bets from database
		calculateBetsByEasyMethod(market, userBets, betStates, latestScore,
				amountLefts);

	}

	@Override
	public Map<Long, List<BetState>> getBetStatusByMarket(Long matchId,
			Integer matchOver, Integer sessionId) {
		Map<Long, List<BetState>> betStatesByMarket = new HashMap<Long, List<BetState>>();
		List<BetState> betStatesForMatch = betStateDAO
				.retrieveBetStatesForMatch(matchId, matchOver, sessionId);
		for (BetState betState : betStatesForMatch) {

			List<BetState> betStates = betStatesByMarket.get(betState
					.getMarket().getId());
			if (betStates == null) {
				betStates = new ArrayList<BetState>();
			}
			betStates.add(betState);
			betStatesByMarket.put(betState.getMarket().getId(), betStates);

		}
		return betStatesByMarket;
	}

	@Override
	public Map<String, UserSessionBets> getSessionWiseMarketBets(
			List<BetState> betStates, MatchScore latestScore) {
		Map<String, UserSessionBets> toReturn = new HashMap<String, UserSessionBets>();
		Iterator<BetState> bets = betStates.iterator();
		while (bets.hasNext()) {
			BetState bet = bets.next();
			com.creckett.dto.BetState sessionWiseBet = null;
			List<com.creckett.dto.BetState> betStats = null;
			UserSessionBets userSessionBets = null;
			if (toReturn.containsKey(bet.getSession().toString())) {
				userSessionBets = toReturn.get(bet.getSession().toString());
				betStats = userSessionBets.getBets();
			} else {
				userSessionBets = new UserSessionBets();
				betStats = new ArrayList<com.creckett.dto.BetState>();
				userSessionBets.setSession("session"
						+ bet.getSession().toString());
				userSessionBets.setBets(betStats);
				toReturn.put(bet.getSession().toString(), userSessionBets);
			}
			for (com.creckett.dto.BetState betState : betStats) {
				if (betState.getGroupOverId() == bet.getMatchOver()) {
					sessionWiseBet = betState;
					break;
				}
			}
			if (sessionWiseBet == null) {
				sessionWiseBet = new com.creckett.dto.BetState();
				sessionWiseBet.setGroupOverId(bet.getMatchOver());
				if (bet.getMarket().getGroupOverValue() == 1) {
					sessionWiseBet.setOver(bet.getMatchOver().toString());
					sessionWiseBet.setOverScore(com.creckett.dto.MatchScore
							.buildObjectFromModel(latestScore));
				} else {
					String over = ((bet.getMarket().getGroupOverValue() * (bet
							.getMatchOver() - 1)) + 1)
							+ "-"
							+ (bet.getMatchOver() * bet.getMarket()
									.getGroupOverValue());
					sessionWiseBet.setOver(over);
					sessionWiseBet.setOverScore(com.creckett.dto.MatchScore
							.buildObjectFromModel(latestScore));
				}
				if (betStats != null) {
					betStats.add(sessionWiseBet);
				}
			}
			UserBetState userBetState = new UserBetState();
			userBetState.setUserName(bet.getUser().getName());
			userBetState.setRuns(bet.getRuns());
			userBetState.setRunsBet(bet.getRunsBet());
			userBetState.setWickets(bet.getWickets());
			userBetState.setWicketsBet(bet.getWicketsBet());
			userBetState.setResultAmount(bet.getResultAmount());
			userBetState.setTimefactor(bet.getTimeFactor());
			userBetState.setRunsWinner(Boolean.toString(bet.isRunsWinner()));
			userBetState.setWicketsWinner(Boolean.toString(bet
					.isWicketsWinner()));
			if (bet.isWinner()) {
				sessionWiseBet.setWinningBet(userBetState);
				if (sessionWiseBet.getWinners() != null
						&& !sessionWiseBet.getWinners().isEmpty()) {
					sessionWiseBet.setWinners(sessionWiseBet.getWinners()
							+ ", " + bet.getUser().getName());
				} else {
					sessionWiseBet.setWinners(bet.getUser().getName());
				}
			}
			if (sessionWiseBet.getUserBetStates() == null) {
				sessionWiseBet.setUserBetStates(new ArrayList<UserBetState>());
			}
			sessionWiseBet.getUserBetStates().add(userBetState);
		}

		return toReturn;

	}

	@Override
	public Map<Long, List<UserBet>> getUserBetsByMarket(long matchId,
			int sessionId, int matchOver) {
		Map<Long, List<UserBet>> userBetsByMarket = new HashMap<Long, List<UserBet>>();
		List<UserBet> betStatesForMatch = userBetDAO.getAllUserBetsForMatch(
				matchId, sessionId, matchOver);
		for (UserBet userBet : betStatesForMatch) {

			List<UserBet> userBets = userBetsByMarket.get(userBet.getMarket()
					.getId());
			if (userBets == null) {
				userBets = new ArrayList<UserBet>();
			}
			userBets.add(userBet);
			userBetsByMarket.put(userBet.getMarket().getId(), userBets);

		}
		return userBetsByMarket;
	}

	@Override
	public void autoJoinMarketForVirtualUser(Market market) {
		User user = userDAO.get(CreckettConstant.VIRTUAL_USER_ID);
		joinUserInMarket(market, user);
		marketDAO.update(market);
	}

	@Override
	public void submitBetForVirtualUser(ScoreUpdate scoreUpdate, int currentBall) {
		virtualUserBetSubmissionStrategy.submitBet(scoreUpdate, currentBall);
	}

	@Override
	public List<Market> getMarketsByMatch(long matchId) {
		MatchMaster matchMaster = matchMasterDAO.get(matchId);
		List<Market> markets = matchMaster.getMarkets();
		markets.size();
		return markets;

	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public int finishMatch(String matchId, String winner) {
		long updateMatchId = StringUtil.getLongFromString(matchId);
		MatchMaster match = matchMasterDAO.get(updateMatchId);
		if (match == null)
			return 0;
		if (winner != null && !winner.isEmpty()) {
			match.setStatus(MatchStatus.FINISHED);
			insertMatchAmountLeft(match);
			match.setWinner(winner);
			messagingAdaptor.sendWinner(updateMatchId, winner);
			userService.resetMatchPlayingflag();
		} else {
			match.setStatus(MatchStatus.ACTIVE);
		}
		return (matchMasterDAO.save(match) != null) ? 1 : 0;
	}

}
