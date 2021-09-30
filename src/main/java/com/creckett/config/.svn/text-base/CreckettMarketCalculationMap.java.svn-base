/**
 * 
 */
package com.creckett.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Latesh Dulani
 * 
 */
public class CreckettMarketCalculationMap {

	private static CreckettMarketCalculationMap creckettMarketCalculationMap = new CreckettMarketCalculationMap();

	private Map<Long, String> marketCalculationMap = new HashMap<Long, String>();

	// private constructor for singleton
	private CreckettMarketCalculationMap() {

	}

	public static CreckettMarketCalculationMap getInstance() {
		if (creckettMarketCalculationMap == null) {
			creckettMarketCalculationMap = new CreckettMarketCalculationMap();
		}
		return creckettMarketCalculationMap;
	}

	public synchronized void addMarketEntry(Long marketId) {
		this.marketCalculationMap.put(marketId, "true");
	}

	public synchronized void addMarketEntry(Long marketId, String message) {
		this.marketCalculationMap.put(marketId, message);
	}

	public synchronized void removeMarketEntry(Long marketId) {
		this.marketCalculationMap.remove(marketId);
	}

	public synchronized boolean isMarketCalculating(Long marketId) {
		return this.marketCalculationMap.containsKey(marketId);
	}

}
