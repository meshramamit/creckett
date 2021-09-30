package com.creckett.model.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.springframework.test.util.ReflectionTestUtils;

import com.creckett.model.Market;
import com.creckett.model.MarketUser;
import com.creckett.model.MatchMaster;

@SuppressWarnings("unused")
public class MarketBuilder {

	private Long id;

	private String marketCode;

	private Long moderatorId;

	private Date creationDate;

	private MatchMaster match;

	private List<MarketUser> marketUsers;

	public Market build() {
		try {
			Class<Market> punchOutBuyerClass = Market.class;
			Constructor<?> constructor = punchOutBuyerClass.getDeclaredConstructor(new Class[0]);
			constructor.setAccessible(true);
			Market market = (Market) constructor.newInstance();
			Field[] fields = Market.class.getDeclaredFields();
			for (Field field : fields) {
				ReflectionTestUtils.setField(market, field.getName(),
						ReflectionTestUtils.getField(this, field.getName()));
			}
			constructor.setAccessible(false);
			market.setId(id);
			return market;
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

	public MarketBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public MarketBuilder withMarketCode(String marketCode) {
		this.marketCode = marketCode;
		return this;
	}

	public MarketBuilder withModeratorId(Long moderatorId) {
		this.moderatorId = moderatorId;
		return this;
	}

	public MarketBuilder withCreationDate(Date creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public MarketBuilder withMarketUsers(List<MarketUser> marketUsers) {
		this.marketUsers = marketUsers;
		return this;
	}

	public MarketBuilder withMatch(MatchMaster match) {
		this.match = match;
		return this;
	}
}
