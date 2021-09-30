package com.creckett.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.creckett.BaseTransactionalTestCase;
import com.creckett.dao.MatchMasterDAO;
import com.creckett.model.MatchMaster;
import com.creckett.util.DateUtils;

public class MatchMasterDaoImplTest extends BaseTransactionalTestCase {

	@Resource(name = "matchMasterDAO")
	private MatchMasterDAO matchMasterDAO;

	@Resource(name = "dateUtils")
	private DateUtils dateUtils;

	@Test
	public void save() {
		MatchMaster matchMaster = getMatchMaster();

		MatchMaster persistedMatchMaster = matchMasterDAO.save(matchMaster);

		Assert.assertNotNull(persistedMatchMaster);
		Assert.assertNotNull(persistedMatchMaster.getId());
	}

	@Test
	public void update() {
		MatchMaster matchMaster = getMatchMaster();
		MatchMaster persistedMatchMaster = matchMasterDAO.save(matchMaster);

		
		persistedMatchMaster.setMatchName("updatedMatchName");
		//persistedMatchMaster.setTeamOne("updatedTeamName1");
		//persistedMatchMaster.setTeamTwo("updatedTeamName2");
		persistedMatchMaster.setWinner("updatedWinner");
		matchMasterDAO.save(persistedMatchMaster);

		MatchMaster updatedMatchMaster = matchMasterDAO.get(persistedMatchMaster.getId());

		Assert.assertNotNull(updatedMatchMaster);
		Assert.assertEquals("updatedMatchName", updatedMatchMaster.getMatchName());
		//Assert.assertEquals("updatedTeamName1", updatedMatchMaster.getTeamOne());
		//Assert.assertEquals("updatedTeamName2", updatedMatchMaster.getTeamTwo());
		Assert.assertEquals("updatedWinner", updatedMatchMaster.getWinner());

	}

	@Test
	public void delete() {
		MatchMaster matchMaster = getMatchMaster();
		MatchMaster persistedMatchMaster = matchMasterDAO.save(matchMaster);

		Assert.assertNotNull(persistedMatchMaster);
		Assert.assertNotNull(persistedMatchMaster.getId());

		matchMasterDAO.delete(matchMaster);

		MatchMaster deletedMatchMaster = matchMasterDAO.get(persistedMatchMaster.getId());
		Assert.assertNull(deletedMatchMaster);
	}

	@Test
	public void getListOfUpcomingMatchesShouldReturnOnlyOneMatchMaster() {
		MatchMaster matchMaster1 = getMatchMaster();
		matchMaster1.setMatchDate(dateUtils.generateDateAfterNDaysUsingCalendar(2));
		matchMasterDAO.save(matchMaster1);

		MatchMaster matchMaster2 = getMatchMaster();
		matchMaster2.setMatchDate(dateUtils.generateDateAfterNDaysUsingCalendar(10));
		matchMasterDAO.save(matchMaster2);

		Date fromDate = dateUtils.generateTodaysDateUsingCalendar();
		Date toDate = dateUtils.generateDateAfterNDaysUsingCalendar(5);

		List<MatchMaster> listOfUpcomingMatches = matchMasterDAO.getListOfUpcomingMatches(fromDate, toDate);

		Assert.assertFalse(listOfUpcomingMatches.isEmpty());
		Assert.assertEquals(1, listOfUpcomingMatches.size());
	}
}
