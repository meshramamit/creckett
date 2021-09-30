package com.creckett.core.betresult;

import com.creckett.model.MatchScore;
import com.creckett.model.UserBet;

public interface BetResultCalculationStrategy {
	BetResultAmount calculate(UserBet userBet, MatchScore matchScore);

	int getPaneltyForLateJoining(int noOfOversPassed);
}
