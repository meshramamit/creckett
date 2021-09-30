package com.creckett.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.creckett.dao.UserDAO;
import com.creckett.dao.UserMatchAmountLeftDAO;
import com.creckett.dto.FacebookUserFriend;
import com.creckett.dto.UserMatchAmountLeftDTO;
import com.creckett.dto.UserMatchAmountLeftDTO.MatchAmountInfo;
import com.creckett.dto.UserMatchAmountLeftDTO.UserInfo;
import com.creckett.model.User;
import com.creckett.model.UserMatchAmountLeft;
import com.creckett.service.UserMatchAmountLeftService;

public class UserMatchAmountLeftServiceImpl implements
		UserMatchAmountLeftService {

	private UserDAO userDAO;

	private int noOfLastMatchesForBankAsset;

	private UserMatchAmountLeftDAO userMatchAmountLeftDao;
	
	

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public int getNoOfLastMatchesForBankAsset() {
		return noOfLastMatchesForBankAsset;
	}

	public void setNoOfLastMatchesForBankAsset(int noOfLastMatchesForBankAsset) {
		this.noOfLastMatchesForBankAsset = noOfLastMatchesForBankAsset;
	}

	public UserMatchAmountLeftDAO getUserMatchAmountLeftDao() {
		return userMatchAmountLeftDao;
	}

	public void setUserMatchAmountLeftDao(
			UserMatchAmountLeftDAO userMatchAmountLeftDao) {
		this.userMatchAmountLeftDao = userMatchAmountLeftDao;
	}

	@Override
	public List<UserMatchAmountLeftDTO> getMatchAmountLeft(long requestinUserId,
			List<FacebookUserFriend> friends) {
		List<String> profileIds = getProfileIds(friends);
		List<User> friendUsers = userDAO.getUsersByProfileIds(profileIds);
		List<Long> userIds = getUserIds(friendUsers);
		userIds.add(requestinUserId);
		List<UserMatchAmountLeft> userMatchAmountLefts = userMatchAmountLeftDao
				.getMatchAmountLeft(userIds);
		Map<Long, List<UserMatchAmountLeft>> amountLeftsByUser = getAmountLeftsByUser(userMatchAmountLefts);
		return getDTOs(amountLeftsByUser);

	}

	private List<UserMatchAmountLeftDTO> getDTOs(
			Map<Long, List<UserMatchAmountLeft>> amountLeftsByUser) {
		List<UserMatchAmountLeftDTO> userMatchAmountLeftDTOs = new ArrayList<UserMatchAmountLeftDTO>();
		Set<Long> userIds = amountLeftsByUser.keySet();
		for (Long userId : userIds) {
			List<UserMatchAmountLeft> userMatchAmountLefts = amountLeftsByUser
					.get(userId);
			UserMatchAmountLeftDTO userMatchAmountLeftDTO = new UserMatchAmountLeftDTO();
			UserInfo userInfo = userMatchAmountLeftDTO.new UserInfo();
			userInfo.setUserName(getUserName(userMatchAmountLefts));
			userInfo.setMatchesPlayed(userMatchAmountLefts.size());
			userInfo.setBankAsset(getBankAsset(userMatchAmountLefts));
			DecimalFormat twoDForm = new DecimalFormat("#.##");
			float average = ((float) userInfo.getBankAsset())
			/ userInfo.getMatchesPlayed();
			userInfo.setAverage(Float.valueOf(twoDForm.format(average)));
			userMatchAmountLeftDTO.setUserInfo(userInfo);
			userMatchAmountLeftDTO.setMatchAmountInfos(getMatchAmountInfo(
					userMatchAmountLefts, userMatchAmountLeftDTO));
			userMatchAmountLeftDTOs.add(userMatchAmountLeftDTO);
		}
		return userMatchAmountLeftDTOs;
	}

	private List<MatchAmountInfo> getMatchAmountInfo(
			List<UserMatchAmountLeft> userMatchAmountLefts,
			UserMatchAmountLeftDTO userMatchAmountLeftDTO) {
		List<MatchAmountInfo> matchAmountInfos = new ArrayList<UserMatchAmountLeftDTO.MatchAmountInfo>();
		int noOfMatches = noOfLastMatchesForBankAsset;
		if (noOfMatches > userMatchAmountLefts.size()) {
			noOfMatches = userMatchAmountLefts.size();
		}

		for (int i = 0; i < noOfMatches; i++) {
			MatchAmountInfo matchAmountInfo = userMatchAmountLeftDTO.new MatchAmountInfo();
			UserMatchAmountLeft userMatchAmountLeft = userMatchAmountLefts
					.get(i);
			matchAmountInfo.setMatchName(userMatchAmountLeft.getMatchMaster()
					.getDescription());
			matchAmountInfo
					.setMatchDate(new SimpleDateFormat("dd-MM-yyyy")
							.format(userMatchAmountLeft.getMatchMaster()
									.getMatchDate()));
			matchAmountInfo.setCrecks(userMatchAmountLeft.getAmountLeft());
			matchAmountInfos.add(matchAmountInfo);
		}
		return matchAmountInfos;
	}

	private int getBankAsset(List<UserMatchAmountLeft> userMatchAmountLefts) {
		int bankAsset = 0;
		for (UserMatchAmountLeft userMatchAmountLeft : userMatchAmountLefts) {
			bankAsset += userMatchAmountLeft.getAmountLeft();
		}
		return bankAsset;
	}

	private String getUserName(List<UserMatchAmountLeft> userMatchAmountLefts) {
		if (!userMatchAmountLefts.isEmpty()) {
			return userMatchAmountLefts.get(0).getUser().getName();
		}
		return null;
	}

	private Map<Long, List<UserMatchAmountLeft>> getAmountLeftsByUser(
			List<UserMatchAmountLeft> userMatchAmountLefts) {
		Map<Long, List<UserMatchAmountLeft>> amountLeftsByUser = new HashMap<Long, List<UserMatchAmountLeft>>();
		for (UserMatchAmountLeft userMatchAmountLeft : userMatchAmountLefts) {

			if (amountLeftsByUser.containsKey(userMatchAmountLeft.getUser()
					.getId())) {
				amountLeftsByUser.get(userMatchAmountLeft.getUser().getId())
						.add(userMatchAmountLeft);
			} else {
				List<UserMatchAmountLeft> amountLefts = new ArrayList<UserMatchAmountLeft>();
				amountLefts.add(userMatchAmountLeft);
				amountLeftsByUser.put(userMatchAmountLeft.getUser().getId(),
						amountLefts);
			}
		}
		return amountLeftsByUser;
	}

	private List<Long> getUserIds(List<User> friendUsers) {
		List<Long> userIds = new ArrayList<Long>();
		for (User user : friendUsers) {
			userIds.add(user.getId());
		}
		return userIds;
	}

	private List<String> getProfileIds(List<FacebookUserFriend> friends) {
		List<String> profileIds = new ArrayList<String>();
		for (FacebookUserFriend facebookUserFriend : friends) {
			profileIds.add(facebookUserFriend.getProfileId());
		}
		return profileIds;
	}

}
