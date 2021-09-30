package com.creckett.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.creckett.BaseTransactionalTestCase;
import com.creckett.dao.MatchMasterDAO;
import com.creckett.dto.MatchMasterWithMarketDTO;
import com.creckett.model.Market;
import com.creckett.model.MarketUser;
import com.creckett.model.MatchMaster;
import com.creckett.model.User;
import com.creckett.service.MatchService;
import com.creckett.util.DateUtils;

public class MatchServiceImplTest extends BaseTransactionalTestCase {

	@Resource(name = "matchMasterDAO")
	private MatchMasterDAO matchMasterDAO;

	@Resource(name = "matchService")
	private MatchService matchService;

	@Resource(name = "dateUtils")
	private DateUtils dateUtils;

	private Long userId = 12345l;

	private MatchMaster persistedMatchMaster;

	@Before
	public void populateMatchMasterTestData() {

		Market market1 = getMarket(1111l);
		Market market2 = getMarket(1111l);
		Market market3 = getMarket(userId);

		List<Market> markets = new ArrayList<Market>();
		markets.add(market1);
		markets.add(market2);
		markets.add(market3);

		MatchMaster matchMaster1 = getMatchMaster(markets);
		matchMaster1.setMatchDate(dateUtils.generateDateAfterNDaysUsingCalendar(2));
		persistedMatchMaster = matchMasterDAO.save(matchMaster1);

		MatchMaster matchMaster2 = getMatchMaster();
		matchMaster2.setMatchDate(dateUtils.generateDateAfterNDaysUsingCalendar(10));
		matchMasterDAO.save(matchMaster2);

	}

	@Test
	public void getListOfUpcomingMatches() {

		Date fromDate = dateUtils.generateTodaysDateUsingCalendar();
		Date toDate = dateUtils.generateDateAfterNDaysUsingCalendar(5);

		List<MatchMaster> listOfUpcomingMatches = matchService.getListOfUpcomingMatches(fromDate, toDate);

		Assert.assertFalse(listOfUpcomingMatches.isEmpty());
		Assert.assertEquals(1, listOfUpcomingMatches.size());

	}

	@Test
	public void getUpcomingMatchesWithMarketForUser() {

		Date fromDate = dateUtils.generateTodaysDateUsingCalendar();
		Date toDate = dateUtils.generateDateAfterNDaysUsingCalendar(5);

		List<MatchMasterWithMarketDTO> upcomingMatchesWithMarket = matchService
				.getUpcomingMatchesWithMarketForUser(userId, fromDate, toDate);

		Assert.assertNotNull(upcomingMatchesWithMarket);

		Iterator<MatchMasterWithMarketDTO> iterator = upcomingMatchesWithMarket.iterator();

		while (iterator.hasNext()) {
			MatchMasterWithMarketDTO matchMasterWithMarketDTO = iterator.next();
			MatchMaster matchMaster = matchMasterWithMarketDTO.getMatchMaster();
			List<Market> markets = matchMasterWithMarketDTO.getMarkets();

			Assert.assertFalse(markets.isEmpty());
			Assert.assertNotNull(matchMaster);
			Assert.assertEquals(userId, markets.get(0).getModeratorId());
		}

	}

	@Test
	public void getMatchMarketsForUser() {

		List<Market> matchMarketsForUser = matchService.getMatchMarketsForUser(persistedMatchMaster.getId(),
				userId);

		Assert.assertEquals(1, matchMarketsForUser.size());
	}

	@Test
	public void createMarketForUser() throws Exception {

		Market newMarket = matchService.createMarketForUser(getUser(), persistedMatchMaster.getId(),1,"1");

		List<MarketUser> marketUsers = newMarket.getMarketUsers();
		Assert.assertEquals(1, marketUsers.size());

		Assert.assertNotNull(newMarket.getMarketCode());
		Assert.assertEquals(userId, newMarket.getModeratorId());

		List<Market> markets = matchMasterDAO.get(persistedMatchMaster.getId()).getMarkets();
		Assert.assertEquals(4, markets.size());

	}

	public void joinMarket() {
		Market market = persistedMatchMaster.getMarkets().get(0);

		User user = getUser();
		Market updatedMarket = matchService.joinMarket(market.getMarketCode(), user);

		Assert.assertNotNull(updatedMarket.getMarketUsers());

	}
}
