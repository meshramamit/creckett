package com.creckett.model.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.springframework.test.util.ReflectionTestUtils;

import com.creckett.model.Market;
import com.creckett.model.MatchMaster;

@SuppressWarnings("unused")
public class MatchMasterBuilder {

	private Long id;

	private String matchName;

	private String description;

	private Date matchDate;

	private String teamOne;

	private String teamOneAlias;

	private String teamTwo;

	private String teamTwoAlias;

	private Integer over;

	private String winner;

	private List<Market> markets;

	public MatchMaster build() {
		try {
			Class<MatchMaster> matchMasterClass = MatchMaster.class;
			Constructor<?> constructor = matchMasterClass.getDeclaredConstructor(new Class[0]);
			constructor.setAccessible(true);
			MatchMaster matchMaster = (MatchMaster) constructor.newInstance();
			Field[] fields = MatchMaster.class.getDeclaredFields();
			for (Field field : fields) {
				ReflectionTestUtils.setField(matchMaster, field.getName(),
						ReflectionTestUtils.getField(this, field.getName()));
			}
			constructor.setAccessible(false);
			matchMaster.setId(id);
			return matchMaster;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public MatchMasterBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public MatchMasterBuilder withMatchName(String matchName) {
		this.matchName = matchName;
		return this;
	}

	public MatchMasterBuilder withMatchDataAndTime(Date matchDate) {
		this.matchDate = matchDate;
		return this;
	}

	public MatchMasterBuilder withTeamOne(String teamOne) {
		this.teamOne = teamOne;
		return this;
	}

	public MatchMasterBuilder withTeamTwo(String teamTwo) {
		this.teamTwo = teamTwo;
		return this;
	}

	public MatchMasterBuilder withWinner(String winner) {
		this.winner = winner;
		return this;
	}

	public MatchMasterBuilder withDescription(String description) {
		this.description = description;
		return this;
	}

	public MatchMasterBuilder withMarkets(List<Market> markets) {
		this.markets = markets;
		return this;
	}

	public MatchMasterBuilder withTeamOneAlias(String teamOneAlias) {
		this.teamOneAlias = teamOneAlias;
		return this;
	}

	public MatchMasterBuilder withTeamTwoAlias(String teamTwoAlias) {
		this.teamTwoAlias = teamTwoAlias;
		return this;
	}

	public MatchMasterBuilder withOver(Integer over) {
		this.over = over;
		return this;
	}
}
