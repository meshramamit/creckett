package com.creckett.service;

import java.util.List;

import com.creckett.dto.BetResultResponse;

public interface BetResultService {

	BetResultResponse getBetResult(Long matchId, Long marketId,
			boolean isHistoryRequired);

	List<BetResultResponse> onOverCompleted(long matchId);

	void pushBetResults(List<BetResultResponse> betResultResponses);
}
