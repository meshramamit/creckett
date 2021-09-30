package com.creckett;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.creckett.model.Market;
import com.creckett.model.MarketUser;
import com.creckett.model.MatchMaster;
import com.creckett.model.User;
import com.creckett.model.builder.MarketBuilder;
import com.creckett.model.builder.MatchMasterBuilder;
import com.creckett.model.builder.UserBuilder;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/iBet-config.xml","classpath:spring/applicationContext-datasource.xml",
		"classpath:spring/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public abstract class BaseTransactionalTestCase extends AbstractTransactionalDataSourceSpringContextTests {

	protected MatchMaster getMatchMaster() {
		return this.getMatchMaster(null);
	}

	protected MatchMaster getMatchMaster(List<Market> markets) {
		return new MatchMasterBuilder().withMatchDataAndTime(new Timestamp(System.currentTimeMillis()))
				.withMatchName("matchName1").withTeamOne("KKR").withTeamOneAlias("Kolkatta Knight Riders")
				.withTeamTwo("DD").withTeamTwoAlias("Delhi Daredevils").withOver(20).withWinner("team1")
				.withDescription("TeamOne Vs TeamTwo").withMarkets(markets).build();
	}

	protected Market getMarket(Long id) {

		return new MarketBuilder().withCreationDate(new Date()).withMarketCode("ABCD-12").withModeratorId(id)
				.build();
	}

	protected User getUser() {
		return new UserBuilder().withEmailId("amitmeshram@hotmail.com").withId(123L).withName("amitmeshram")
				.withPlaying(true).build();
	}

	protected MarketUser getMarketUser() {
		MarketUser marketUser = new MarketUser();
		marketUser.setCreationDate(new Date());
		marketUser.setId(123l);
		marketUser.setUserId(new User());

		return marketUser;
	}

}
