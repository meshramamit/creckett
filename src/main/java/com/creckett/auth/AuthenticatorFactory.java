package com.creckett.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.creckett.constant.AuthProvider;

public class AuthenticatorFactory {

	private Map<String, Authenticator> authenticators;

	public Authenticator getAuthenticator(AuthProvider authProvider) {
		return authenticators.get(authProvider.toString());
	}
	
	public List<Authenticator> getListOfAllAuthenticators(){
		return new ArrayList<Authenticator>(authenticators.values());
	}

	public Map<String, Authenticator> getAuthenticators() {
		return authenticators;
	}

	public void setAuthenticators(Map<String, Authenticator> authenticators) {
		this.authenticators = authenticators;
	}

}
