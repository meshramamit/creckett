package com.creckett.view;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.creckett.BaseSimpleTestCase;
import com.creckett.model.MatchMaster;
import com.creckett.util.DateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class IndexViewTest extends BaseSimpleTestCase {

	@Resource(name = "configuration")
	private Configuration configuration;

	@Resource(name = "dateUtils")
	private DateUtils dateUtils;

	@Test
	public void getHomeScreen() throws IOException, TemplateException {

		String expectedResult = getFileContent("view/Index.xml");

		List<MatchMaster> listOfMatchMasters = new ArrayList<MatchMaster>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 14);
		calendar.set(Calendar.MONTH, 2);
		calendar.set(Calendar.YEAR, 2011);
		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.HOUR, 10);
		calendar.set(Calendar.MINUTE, 10);

		MatchMaster matchMaster = getMatchMaster();
		matchMaster.setId(1l);
		matchMaster.setMatchDate(calendar.getTime());

		listOfMatchMasters.add(matchMaster);
		listOfMatchMasters.add(matchMaster);
		listOfMatchMasters.add(matchMaster);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listOfUpcomingMatches", listOfMatchMasters);
		model.put("dateUtils", dateUtils);
		model.put("today", new Date());

		Template template = configuration.getTemplate("Index.ftl");
		Assert.assertNotNull(template);

		StringWriter out = new StringWriter();
		template.process(model, out);
		String actualResult = out.toString();

		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getHomeScreen_TodaysMatches() throws IOException, TemplateException {

		String expectedResult = getFileContent("view/Index-TodaysMatch.xml");

		List<MatchMaster> listOfMatchMasters = new ArrayList<MatchMaster>();

		MatchMaster matchMaster = getMatchMaster();
		matchMaster.setId(1l);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.HOUR, 10);
		calendar.set(Calendar.MINUTE, 10);

		matchMaster.setMatchDate(calendar.getTime());

		listOfMatchMasters.add(matchMaster);
		listOfMatchMasters.add(matchMaster);
		listOfMatchMasters.add(matchMaster);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listOfUpcomingMatches", listOfMatchMasters);
		model.put("dateUtils", dateUtils);
		model.put("today", new Date());

		Template template = configuration.getTemplate("Index.ftl");
		Assert.assertNotNull(template);

		StringWriter out = new StringWriter();
		template.process(model, out);
		String actualResult = out.toString();

		Assert.assertEquals(expectedResult, actualResult);
	}
}
