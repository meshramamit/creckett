package com.creckett.service.bet;

import com.creckett.dto.ScoreUpdate;

public interface VirtualUserBetSubmissionStrategy {

	void submitBet(ScoreUpdate scoreUpdate, int ballNumber);
}
