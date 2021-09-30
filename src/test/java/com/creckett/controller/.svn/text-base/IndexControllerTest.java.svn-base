package com.creckett.controller;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.creckett.BaseSimpleTestCase;
import com.creckett.model.MatchMaster;
import com.creckett.service.MatchService;
import com.creckett.util.DateUtils;

@SuppressWarnings("unchecked")
public class IndexControllerTest extends BaseSimpleTestCase {

	private IndexController indexController;
	private MatchService matchService;

	private MockHttpServletRequest httpServletRequest;
	private MockHttpServletResponse httpServletResponse;

	@Before
	public void setup() {
		matchService = mock(MatchService.class);
		indexController = new IndexController();
		indexController.setMatchService(matchService);
		indexController.setInterval(7);
		indexController.setDateUtils(new DateUtils());
		httpServletRequest = new MockHttpServletRequest();
		httpServletResponse = new MockHttpServletResponse();
	}

	@Test
	public void handleRequest() throws Exception {
		List<MatchMaster> expectedListOfUpcomingMatches = getListOfUpcomingMatches();
		when(matchService.getListOfUpcomingMatches((Date) anyObject(), (Date) anyObject())).thenReturn(
				expectedListOfUpcomingMatches);

		ModelAndView modelAndView = indexController.handleRequest(httpServletRequest,
				httpServletResponse);

		List<MatchMaster> actualListOfUpcomingMatches = (List<MatchMaster>) modelAndView.getModelMap().get(
				"listOfUpcomingMatches");
		DateUtils dateUtils = (DateUtils) modelAndView.getModelMap().get("dateUtils");
		Date date = (Date) modelAndView.getModelMap().get("today");

		Assert.assertEquals("index", modelAndView.getViewName());
		Assert.assertFalse(actualListOfUpcomingMatches.isEmpty());
		Assert.assertEquals(3, actualListOfUpcomingMatches.size());
		Assert.assertNotNull(dateUtils);
		Assert.assertNotNull(date);

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
