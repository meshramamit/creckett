package com.creckett.core.betresult;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.creckett.constant.CreckettConstant;
import com.creckett.core.codec.CodecStrategy;
import com.creckett.model.MatchScore;
import com.creckett.model.UserBet;

public class DefaultBetResultCalculationStrategy implements BetResultCalculationStrategy,InitializingBean {

	private RuleConfig ruleConfig;
	
	private CodecStrategy codecStrategy;
	
	@Override
	public BetResultAmount calculate(UserBet userBet, MatchScore matchScore) {
		int runAmount = calculateRunAmount(userBet, matchScore);
		int wicketAmount = calculateWicketAmount(userBet, matchScore);
		BetResultAmount betResultAmount = new BetResultAmount();
		betResultAmount.setRunsAmount(runAmount);
		betResultAmount.setWicketAmount(wicketAmount);
		return betResultAmount;
	}

	private int calculateWicketAmount(UserBet userBet, MatchScore matchScore) {
		int wicketAmount = 0;
		// If user has put a value for wicket
		if (userBet.getWicketsBet() != null
				&& userBet.getWicketsBet() >= 0) {
			
			if (userBet.getWicketsBet().equals(
					matchScore.getWickets())) {
				// add the wicket amount for as many as matched wickets 
				if (userBet.getWicketsBet().equals(0)) {
					wicketAmount = ruleConfig.getWktBonus();
				} else {
					wicketAmount = ruleConfig.getWktBonus()
							* userBet.getWicketsBet().intValue();
				}
			} else {
				// deduct the wicket amount for as many as delta wickets
				wicketAmount = -this.ruleConfig.getWktBonus()
						* Math.abs((userBet.getWicketsBet()
								.intValue() - matchScore
								.getWickets()));
			}
		} else {
			// If user did not put a value for wicket
			wicketAmount = 0;
		}
		return wicketAmount;
	}

	private int calculateRunAmount(UserBet userBet, MatchScore matchScore) {
		int runAmount;
		// Deduct the amount if user missed the bet
		if (userBet.getRunsBet().equals(
				CreckettConstant.DEFAULT_SUBMIT_BET_VALUE)) {
			runAmount = ruleConfig.getMissedBetPenalty();
		} else {
			// Calculate the delta between runs from userBet and actual score
			int runsDelta = Math.abs(userBet.getRunsBet() - matchScore.getRuns());
			RunsDefinition runsDefinition = ruleConfig.getRunsDefinitionFor(runsDelta);
			if (runsDefinition!=null){
				// Assign the bonus corresponding to the delta
				runAmount = runsDefinition.getBonus();
			}else{
				// If delta is not configured in rulesConfig.xml , then amount is deducted equal to delta
				runAmount = -runsDelta;
			}
		}
		return runAmount;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource path = resourceLoader.getResource("rulesConfig.xml");
		File ruleFile = path.getFile();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(ruleFile);
			byte[] fileBytes = new byte[(int) ruleFile.length()];  
			fis.read(fileBytes);
			ruleConfig =  (RuleConfig) codecStrategy.decode(new String(fileBytes));
		}finally{
			if (fis!=null){
				fis.close();
			}
		}
	}

	public CodecStrategy getCodecStrategy() {
		return codecStrategy;
	}

	public void setCodecStrategy(CodecStrategy codecStrategy) {
		this.codecStrategy = codecStrategy;
	}

	@Override
	public int getPaneltyForLateJoining(int noOfOversPassed) {
		return noOfOversPassed * ruleConfig.getMissedBetPenalty();
	}
	
	
	
}
