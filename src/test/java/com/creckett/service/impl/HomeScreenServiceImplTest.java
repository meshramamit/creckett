package com.creckett.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.creckett.BaseTransactionalTestCase;
import com.creckett.dao.MatchMasterDAO;
import com.creckett.model.MatchMaster;
import com.creckett.service.MatchService;
import com.creckett.util.DateUtils;

public class HomeScreenServiceImplTest extends BaseTransactionalTestCase {

	@Resource(name = "matchMasterDAO")
	private MatchMasterDAO matchMasterDAO;

	@Resource(name = "matchService")
	private MatchService matchService;

	@Resource(name = "dateUtils")
	private DateUtils dateUtils;

	@Before
	public void populateMatchMasterTestData() {
		MatchMaster matchMaster1 = getMatchMaster();
		matchMaster1.setMatchDate(dateUtils.generateDateAfterNDaysUsingCalendar(2));
		matchMasterDAO.save(matchMaster1);

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

}
