package com.creckett.view;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.creckett.BaseSimpleTestCase;
import com.creckett.constant.SessionAttributes;
import com.creckett.model.Market;
import com.creckett.model.User;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MarketCreatedScreenViewTest extends BaseSimpleTestCase {

	@Resource(name = "configuration")
	private Configuration configuration;

	@Test
	public void getMarketCreatedScreen() throws Exception {

		String expectedResult = getFileContent("view/CreateMarket-Success.xml");

		Market market = getMarket(1234l);
		User user = getUser();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("market", market);
		model.put(SessionAttributes.USER, user);

		Template template = configuration.getTemplate("MarketCreatedScreen.ftl");
		Assert.assertNotNull(template);

		StringWriter out = new StringWriter();
		template.process(model, out);
		String actualResult = out.toString();

		Assert.assertEquals(expectedResult, actualResult);
	}
}
