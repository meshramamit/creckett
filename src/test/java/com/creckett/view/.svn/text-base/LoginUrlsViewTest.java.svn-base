package com.creckett.view;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.creckett.BaseSimpleTestCase;
import com.creckett.auth.AuthenticatorFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class LoginUrlsViewTest extends BaseSimpleTestCase {

	@Resource(name = "configuration")
	private Configuration configuration;

	@Resource(name = "authenticatorFactory")
	private AuthenticatorFactory authenticatorFactory;

	@Test
	public void getAuthProviderUrls() throws IOException, TemplateException {

		String expectedResult = getFileContent("view/LoginUrls.xml");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("authenticators", authenticatorFactory.getListOfAllAuthenticators());

		Template template = configuration.getTemplate("LoginUrls.ftl");
		Assert.assertNotNull(template);

		StringWriter out = new StringWriter();
		template.process(model, out);
		String actualResult = out.toString();

		Assert.assertEquals(expectedResult, actualResult);
	}

}
