package com.creckett;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creckett.model.Market;
import com.creckett.model.MatchMaster;
import com.creckett.model.User;
import com.creckett.model.builder.MarketBuilder;
import com.creckett.model.builder.MatchMasterBuilder;
import com.creckett.model.builder.UserBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/iBet-config.xml","classpath:spring/applicationContext-datasource.xml",
		"classpath:spring/applicationContext.xml" })
public abstract class BaseSimpleTestCase {

	protected MatchMaster getMatchMaster() {
		return new MatchMasterBuilder().withMatchDataAndTime(new Timestamp(System.currentTimeMillis()))
				.withMatchName("matchName1").withTeamOne("KKR").withTeamOneAlias("Kolkatta Knight Riders")
				.withTeamTwo("DD").withTeamTwoAlias("Delhi Daredevils").withOver(20).withWinner("KKR")
				.withDescription("TeamOne Vs TeamTwo").build();
	}

	protected String getFileContent(String fileName) throws IOException {
		BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(ClassLoader
				.getSystemResource(fileName).getPath()));
		byte[] content = new byte[inputStream.available()];
		inputStream.read(content);
		return new String(content);

	}

	protected User getUser() {
		return new UserBuilder().withEmailId("amitmeshram@hotmail.com").withId(123L).withName("amitmeshram")
				.withPlaying(true).build();
	}

	protected Market getMarket(Long id) {
		return new MarketBuilder().withCreationDate(new Date()).withMarketCode("ABCD-12").withModeratorId(id)
				.withId(id).build();
	}
}
