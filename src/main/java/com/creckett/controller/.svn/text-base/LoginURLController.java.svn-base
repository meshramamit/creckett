package com.creckett.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.auth.AuthenticatorFactory;

public class LoginURLController implements Controller {

	private AuthenticatorFactory authenticatorFactory;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("authenticators", authenticatorFactory.getListOfAllAuthenticators());

		return new ModelAndView("LoginUrls", model);
	}

	public AuthenticatorFactory getAuthenticatorFactory() {
		return authenticatorFactory;
	}

	public void setAuthenticatorFactory(AuthenticatorFactory authenticatorFactory) {
		this.authenticatorFactory = authenticatorFactory;
	}

}
