package com.creckett.auth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.creckett.constant.AuthProvider;
import com.creckett.dto.FacebookUserFriend;
import com.creckett.exception.AuthenticationException;
import com.creckett.model.User;

public class FacebookAuthenticator implements Authenticator {

	private String secret;
	private String clientId;

	private String redirectURL;
	private String scope;

	private String userAuthenticatonURL;
	private String applicationAuthenticationURL;
	private String userDataAccessURL;
	private String userFriendListURL;
	private String userFriendProfilePhotoURL;

	private String accessToken = null;
	private Integer expires = null;

	@Override
	public AuthProvider getAuthProvider() {
		return AuthProvider.FACEBOOK;
	}

	@Override
	public String getLoginURL() {

		StringBuilder url = new StringBuilder();
		url.append(userAuthenticatonURL).append("client_id=").append(clientId)
				.append("&redirect_uri=").append(redirectURL).append("&scope=")
				.append(scope);
		return url.toString();
	}

	@Override
	public User getUserInformation(String authCode)
			throws AuthenticationException {

		try {

			URL url = new URL(getAppAuthURL(authCode));
			String result = readURL(url);

			String[] params = result.split("&");

			for (String pair : params) {
				String[] param = pair.split("=");

				if (param.length != 2) {
					throw new RuntimeException("Unexpected auth response");
				} else {

					if (param[0].equals("access_token")) {
						accessToken = param[1];
					}

					if (param[0].equals("expires")) {
						expires = Integer.valueOf(param[1]);
					}
				}
			}

			if (accessToken == null || expires == null)
				throw new RuntimeException("Access token and expires not found");

			return retrivetUserInformation(accessToken, expires);

		} catch (IOException ioe) {
			throw new AuthenticationException("Authentication failed.");
		}

	}

	
	private String readURL(URL url) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = url.openStream();
		int r;
		while ((r = is.read()) != -1) {
			baos.write(r);
		}
		return new String(baos.toByteArray());
	}

	private String getAppAuthURL(String authCode) {
		StringBuilder url = new StringBuilder();
		// url.append(applicationAuthenticationURL).append("client_id=")
		// .append(clientId).append("&amp;redirect_uri=").append(redirectURL)
		// .append("&amp;client_secret=").append(secret).append("&amp;code=")
		// .append(authCode);
		url.append(applicationAuthenticationURL).append("client_id=").append(
				clientId).append("&redirect_uri=").append(redirectURL).append(
				"&client_secret=").append(secret).append("&code=").append(
				authCode);
		return url.toString();
	}
	
	public User retrivetUserInformation(String accessToken, int expire)
			throws AuthenticationException {

		StringBuilder url = new StringBuilder();
		url.append(userDataAccessURL).append(accessToken);

		System.out.println( " Data url " + url.toString() );
		
		String firstName;
		String lastName;
		String emailId;
		String profileId;
		try {
			String response = readURL(new URL(url.toString()));
			System.out.println( " Data response " + response );
			JSONObject resp = new JSONObject(response);
			firstName = resp.getString("first_name");
			lastName = resp.getString("last_name");
			emailId = resp.getString("email");
			profileId = resp.getString("id");
			System.out.println( " Data firstName " + firstName + " : " + emailId );
		} catch (MalformedURLException mfe) {
			throw new AuthenticationException("URL is not corrent", mfe);
		} catch (JSONException jsone) {
			throw new AuthenticationException(
					"Error while parsing JSON string", jsone);
		} catch (IOException ioe) {
			throw new AuthenticationException(
					"Error while retrieving user information", ioe);
		}

		User user = new User();
		user.setEmailId(emailId);
		user.setName(firstName + " " + lastName);
		user.setProfileId(profileId);
		user.setAccessToken(accessToken);
		return user;
	}

	public ArrayList<FacebookUserFriend> getUserFriendList()
			throws AuthenticationException {
		return getUserFriendList(accessToken);
	}

	public ArrayList<FacebookUserFriend> getUserFriendList(String accessToken)
			throws AuthenticationException {
		ArrayList<FacebookUserFriend> userFriends = new ArrayList<FacebookUserFriend>();
		try {
			StringBuilder url = new StringBuilder();
			url.append(userFriendListURL).append(accessToken);
			String response = readURL(new URL(url.toString()));
			JSONObject jsonResponse = new JSONObject(response);
			if (jsonResponse != null) {
				JSONArray friendListArray = jsonResponse.getJSONArray("data");
				int length = friendListArray.length();
				for (int i = 0; i < length; i++) {
					JSONObject friend = friendListArray.getJSONObject(i);
					FacebookUserFriend userFriend = new FacebookUserFriend();
					userFriend.setFriendName((String) friend.get("name"));
					userFriend.setProfileId((String) friend.get("id"));
					userFriend
							.setProfilePhotoLink(this.userFriendProfilePhotoURL
									.replaceAll("#profile_id#", userFriend
											.getProfileId()));
					userFriends.add(userFriend);
				}
			}
		} catch (MalformedURLException mfe) {
			throw new AuthenticationException("URL is not corrent", mfe);
		} catch (JSONException jsone) {
			throw new AuthenticationException(
					"Error while parsing JSON string", jsone);
		} catch (IOException ioe) {
			throw new AuthenticationException(
					"Error while retrieving user information", ioe);
		}

		return userFriends;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public String getUserAuthenticatonURL() {
		return userAuthenticatonURL;
	}

	public void setUserAuthenticatonURL(String userAuthenticatonURL) {
		this.userAuthenticatonURL = userAuthenticatonURL;
	}

	public String getApplicationAuthenticationURL() {
		return applicationAuthenticationURL;
	}

	public void setApplicationAuthenticationURL(
			String applicationAuthenticationURL) {
		this.applicationAuthenticationURL = applicationAuthenticationURL;
	}

	public String getUserDataAccessURL() {
		return userDataAccessURL;
	}

	public void setUserDataAccessURL(String userDataAccessURL) {
		this.userDataAccessURL = userDataAccessURL;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUserFriendListURL() {
		return userFriendListURL;
	}

	public void setUserFriendListURL(String userFriendListURL) {
		this.userFriendListURL = userFriendListURL;
	}

	public String getUserFriendProfilePhotoURL() {
		return userFriendProfilePhotoURL;
	}

	public void setUserFriendProfilePhotoURL(String userFriendProfilePhotoURL) {
		this.userFriendProfilePhotoURL = userFriendProfilePhotoURL;
	}
}
