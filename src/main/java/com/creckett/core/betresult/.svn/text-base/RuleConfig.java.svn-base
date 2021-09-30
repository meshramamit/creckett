package com.creckett.core.betresult;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("ruleConfig")
public class RuleConfig {
	@XStreamAlias("missed_bet_penalty")
	private int missedBetPenalty;
	
	@XStreamAlias("wkt_bonus")
	private int wktBonus;
	
	@XStreamAlias("runs_definition")
	private List<RunsDefinition> runsDefinitions;

	public RunsDefinition getRunsDefinitionFor(int delta){
		if (runsDefinitions == null || runsDefinitions.isEmpty()){
			return null;
		}
		for(RunsDefinition runsDefinition : runsDefinitions){
			if (delta == runsDefinition.getDelta()){
				return runsDefinition;
			}
		}
		return null;
	}
	public int getMissedBetPenalty() {
		return missedBetPenalty;
	}

	public void setMissedBetPenalty(int missedBetPenalty) {
		this.missedBetPenalty = missedBetPenalty;
	}

	public int getWktBonus() {
		return wktBonus;
	}

	public void setWktBonus(int wktBonus) {
		this.wktBonus = wktBonus;
	}

	public List<RunsDefinition> getRunsDefinitions() {
		return runsDefinitions;
	}

	public void setRunsDefinitions(List<RunsDefinition> runsDefinitions) {
		this.runsDefinitions = runsDefinitions;
	}
	
	
}

@XStreamAlias("def")
class RunsDefinition{
	
	public RunsDefinition(){
		
	}
	@XStreamAsAttribute
	private int delta;
	
	@XStreamAsAttribute
	private int bonus;

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
}
