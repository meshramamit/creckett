package com.creckett.core.betresult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/creckett-config.xml","/spring/applicationContext-datasource.xml","/spring/applicationContext.xml"})
public class BetResultCalculationStrategyTest {

	@Autowired
	private DefaultBetResultCalculationStrategy defaultBetResultCalculationStrategy;
	
	@Before
	public void setUp(){
		//defaultBetResultCalculationStrategy = new DefaultBetResultCalculationStrategy();
	}
	@Test
	public void testResultCalculationForMissedBet(){
		
	}
}
