package com.creckett.dao.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.creckett.BaseTransactionalTestCase;
import com.creckett.dao.MarketDAO;
import com.creckett.dao.MarketDetailChartDAO;
import com.creckett.dao.MatchMasterDAO;
import com.creckett.dao.UserDAO;
import com.creckett.model.AmountLeft;
import com.creckett.model.Market;
import com.creckett.model.MarketUser;
import com.creckett.model.MatchMaster;
import com.creckett.model.User;
import com.creckett.model.builder.MarketBuilder;
import com.creckett.model.builder.MatchMasterBuilder;
import com.creckett.model.builder.UserBuilder;

public class MarketDetailChartDAOImplTest extends BaseTransactionalTestCase {

	@Resource(name = "marketDetailChartDAO")
	private MarketDetailChartDAO marketDetailChartDAO;

	@Resource(name = "userDAO")
	private UserDAO userDAO;

	@Resource(name = "marketDAO")
	private MarketDAO marketDAO;

	@Resource(name = "matchMasterDAO")
	private MatchMasterDAO matchMasterDAO;

	@Test
	public void getMarketMatchDetails() {
		User moderatorUser = getUser("latesh");
		Market market = getMarket(moderatorUser.getId(),
				"CHART_DETAIL_TEST_MARKET");
		MatchMaster matchMaster = getMatchMaster(Arrays.asList(market),
				"INDIA", "WEST INDIES");
		for (int i = 0; i < 10; i++) {
			AmountLeft amountLeft = getMarketMatchUserAmount(moderatorUser
					.getId(), market, matchMaster, "XYZ" + i);
			marketDetailChartDAO.save(amountLeft);
		}
		List<AmountLeft> outputList = marketDetailChartDAO
				.getMarketMatchDetails(market.getId(), matchMaster.getId());
		System.out.println(" LIST IS ::: " + outputList.size());
		Assert.assertNotNull(outputList);
		Assert.assertFalse(outputList.isEmpty());
		Assert.assertEquals(10, outputList.size());
	}

	public MarketDetailChartDAO getMarketDetailChartDAO() {
		return marketDetailChartDAO;
	}

	public void setMarketDetailChartDAO(
			MarketDetailChartDAO marketDetailChartDAO) {
		this.marketDetailChartDAO = marketDetailChartDAO;
	}

	protected User getUser(String name) {
		User user = new UserBuilder().withEmailId(name + "@hotmail.com")
				.withName(name).withPlaying(true).build();
		userDAO.save(user);
		return user;
	}

	protected Market getMarket(Long id, String code) {
		Market market = new MarketBuilder().withId(getRandomId())
				.withCreationDate(new Date()).withMarketCode(code)
				.withModeratorId(id).build();
		marketDAO.save(market);
		return market;
	}

	protected MatchMaster getMatchMaster(List<Market> markets, String team1,
			String team2) {
		MatchMaster matchMaster = new MatchMasterBuilder()
				.withId(getRandomId()).withMatchDataAndTime(
						new Timestamp(System.currentTimeMillis()))
				.withMatchName(team1 + " with " + team2).withTeamOne(team1)
				.withTeamOneAlias(team2.substring(0, 3)).withTeamTwo(team2)
				.withTeamTwoAlias(team2.substring(0, 3)).withOver(20)
				.withWinner(team1).withDescription(team1 + " Vs " + team2)
				.withMarkets(markets).build();
		matchMasterDAO.save(matchMaster);
		return matchMaster;
	}

	protected MarketUser getMarketUser(long userId) {
		MarketUser marketUser = new MarketUser();
		marketUser.setCreationDate(new Date());
		marketUser.setId(getRandomId());
		marketUser.setUserId(new User());
		return marketUser;
	}

	protected AmountLeft getMarketMatchUserAmount(long userId, Market market,
			MatchMaster matchId, String name) {
		AmountLeft amountLeft = new AmountLeft();
		amountLeft.setId(getRandomId());
		amountLeft.setLeftAmount((int) getRandomnumberInRange(10000));
		amountLeft.setOver((int) getRandomnumberInRange(20));
		amountLeft.setSessionId((int) getRandomnumberInRange(2));
		amountLeft.setMarketId(market);
		amountLeft.setMatchId(matchId);
		User user = getUser(name);
		getMarketUser(user.getId());
		amountLeft.setUserId(user);
		return amountLeft;
	}

	public long getRandomId() {
		int i = 100000;
		return getRandomnumberInRange(i);
	}

	public long getRandomnumberInRange(int i) {
		return ((long) ((Math.random() * i) % i));
	}
}
