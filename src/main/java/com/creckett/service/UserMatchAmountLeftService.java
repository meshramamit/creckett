package com.creckett.service;

import java.util.List;

import com.creckett.dto.FacebookUserFriend;
import com.creckett.dto.UserMatchAmountLeftDTO;

public interface UserMatchAmountLeftService {
	List<UserMatchAmountLeftDTO> getMatchAmountLeft(long requestinUserId,
			List<FacebookUserFriend> friends);
}
