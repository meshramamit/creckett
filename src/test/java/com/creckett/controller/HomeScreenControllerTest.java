package com.creckett.controller;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.creckett.BaseSimpleTestCase;
import com.creckett.constant.SessionAttributes;
import com.creckett.dto.MatchMasterWithMarketDTO;
import com.creckett.model.Market;
import com.creckett.model.MatchMaster;
import com.creckett.model.User;
import com.creckett.service.MatchService;
import com.creckett.util.DateUtils;

@SuppressWarnings("unchecked")
public class HomeScreenControllerTest extends BaseSimpleTestCase {

	private HomeScreenController homeScreenController;
	private MatchService matchService;

	private MockHttpServletRequest httpServletRequest;
	private MockHttpServletResponse httpServletResponse;
	private MockHttpSession httpSession;

	@Before
	public void setup() {
		matchService = mock(MatchService.class);
		homeScreenController = new HomeScreenController();
		homeScreenController.setMatchService(matchService);
		homeScreenController.setInterval(7);
		homeScreenController.setDateUtils(new DateUtils());
		httpServletRequest = new MockHttpServletRequest();
		httpServletResponse = new MockHttpServletResponse();
		httpSession = new MockHttpSession();

		httpServletRequest.setSession(httpSession);

		User user = new User();
		user.setId(12345l);
		httpSession.setAttribute(SessionAttributes.USER, user);

	}

	@Test
	public void handleRequest() throws Exception {
		List<MatchMaster> expectedListOfUpcomingMatches = getListOfUpcomingMatches();
		List<Market> markets = getListOfMarkets();

		when(matchService.getListOfUpcomingMatches((Date) anyObject(), (Date) anyObject())).thenReturn(
				expectedListOfUpcomingMatches);
		when(matchService.getMatchMarketsForUser((Long) anyObject(), (Long) anyObject())).thenReturn(markets);

		ModelAndView modelAndView = homeScreenController.handleRequest(httpServletRequest,
				httpServletResponse);
		List<MatchMasterWithMarketDTO> upcomingMatchesWithMarketForUser = (List<MatchMasterWithMarketDTO>) modelAndView
				.getModelMap().get("matches");
		DateUtils dateUtils = (DateUtils) modelAndView.getModelMap().get("dateUtils");
		Date date = (Date) modelAndView.getModelMap().get("today");
		User user = (User) modelAndView.getModelMap().get(SessionAttributes.USER);

		Assert.assertNotNull(upcomingMatchesWithMarketForUser);
		MatchMasterWithMarketDTO matchMasterWithMarketDTO = upcomingMatchesWithMarketForUser.get(0);

		Assert.assertNotNull(matchMasterWithMarketDTO.getMatchMaster());
		Assert.assertNotNull(matchMasterWithMarketDTO.getMarkets());

		Assert.assertEquals(4, matchMasterWithMarketDTO.getMarkets().size());

		Assert.assertNotNull(dateUtils);
		Assert.assertNotNull(date);

		Assert.assertEquals(12345l, user.getId().longValue());
	}

	private List<Market> getListOfMarkets() {
		List<Market> markets = new ArrayList<Market>();
		markets.add(getMarket(1234l));
		markets.add(getMarket(1111l));
		markets.add(getMarket(2222l));
		markets.add(getMarket(3333l));
		return markets;
	}

	private List<MatchMaster> getListOfUpcomingMatches() {

		List<MatchMaster> matches = new ArrayList<MatchMaster>();
		MatchMaster matchMaster = getMatchMaster();
		matchMaster.setId(1l);

		matches.add(matchMaster);
		matches.add(matchMaster);
		matches.add(matchMaster);

		return matches;
	}
}
