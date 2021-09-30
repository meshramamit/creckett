package com.creckett.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.creckett.BaseTransactionalTestCase;
import com.creckett.dao.MarketDAO;
import com.creckett.dao.MatchMasterDAO;
import com.creckett.model.Market;
import com.creckett.model.MatchMaster;
import com.creckett.util.DateUtils;

public class MarketDAOImplTest extends BaseTransactionalTestCase {

	@Resource(name = "marketDAO")
	private MarketDAO marketDAO;

	@Resource(name = "matchMasterDAO")
	private MatchMasterDAO matchMasterDAO;

	@Resource(name = "dateUtils")
	private DateUtils dateUtils;

	@Test
	@Rollback(false)
	public void getMatchMarketsForUser() {

		Long userId = 12345l;
		Market market1 = getMarket(1111l);
		Market market2 = getMarket(1111l);
		Market market3 = getMarket(userId);

		List<Market> markets = new ArrayList<Market>();
		markets.add(market1);
		markets.add(market2);
		markets.add(market3);

		MatchMaster matchMaster1 = getMatchMaster(markets);
		matchMaster1.setMatchDate(dateUtils.generateDateAfterNDaysUsingCalendar(2));
		MatchMaster persistedMatchMaster = matchMasterDAO.save(matchMaster1);

		MatchMaster matchMaster2 = getMatchMaster();
		matchMaster2.setMatchDate(dateUtils.generateDateAfterNDaysUsingCalendar(10));
		matchMasterDAO.save(matchMaster2);

		List<Market> userMarketForMatch = marketDAO.getMatchMarketsForUser(persistedMatchMaster.getId(),
				userId);

		Assert.assertFalse(userMarketForMatch.isEmpty());
		Assert.assertEquals(1, userMarketForMatch.size());
		Market market = userMarketForMatch.get(0);
		Assert.assertEquals(userId, market.getModeratorId());

	}
}
