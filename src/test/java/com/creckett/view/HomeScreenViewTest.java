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
import com.creckett.constant.SessionAttributes;
import com.creckett.dto.MatchMasterWithMarketDTO;
import com.creckett.model.Market;
import com.creckett.model.MatchMaster;
import com.creckett.util.DateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HomeScreenViewTest extends BaseSimpleTestCase {

	@Resource(name = "configuration")
	private Configuration configuration;

	@Resource(name = "dateUtils")
	private DateUtils dateUtils;

	@Test
	public void getHomeScreen() throws IOException, TemplateException {

		String expectedResult = getFileContent("view/HomeScreen-Success.xml");

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
		
		MatchMaster matchMaster2 = getMatchMaster();
		matchMaster2.setId(1l);
		matchMaster2.setMatchDate(calendar.getTime());

		List<Market> markets = new ArrayList<Market>();
		markets.add(getMarket(1234l));

		List<MatchMasterWithMarketDTO> matches = new ArrayList<MatchMasterWithMarketDTO>();
		matches.add(new MatchMasterWithMarketDTO(matchMaster, markets));
		matches.add(new MatchMasterWithMarketDTO(matchMaster2));
		

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("matches", matches);
		model.put("dateUtils", dateUtils);
		model.put("today", new Date());
		model.put(SessionAttributes.USER, getUser());

		Template template = configuration.getTemplate("HomeScreen.ftl");
		Assert.assertNotNull(template);

		StringWriter out = new StringWriter();
		template.process(model, out);
		String actualResult = out.toString();

		Assert.assertEquals(expectedResult, actualResult);
	}
}
