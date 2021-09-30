package com.creckett.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creckett.constant.CreckettConstant;
import com.creckett.dao.MarketDetailChartDAO;
import com.creckett.dto.MarketMatchDetailDTO;
import com.creckett.dto.MarketMatchSessionDetail;
import com.creckett.model.AmountLeft;
import com.creckett.service.MarketDetailChartService;

public class MarketDetailChartServiceImpl implements MarketDetailChartService {

	MarketDetailChartDAO marketDetailChartDAO;

	@Override
	public List<AmountLeft> getMarketMatchDetails(Long marketId, Long matchId) {
		List<AmountLeft> matchList = marketDetailChartDAO
				.getMarketMatchDetails(marketId, matchId);
		return matchList;
	}

	@Override
	public List<AmountLeft> getMarketPlayersAmountDetails(Long marketId,
			Long matchId) {
		List<AmountLeft> matchList = marketDetailChartDAO
				.getMarketMatchDetails(marketId, matchId);
		return matchList;
	}

	@Override
	public Map<Integer, MarketMatchSessionDetail> getSessionWiseMarketMatchDetail(
			List<AmountLeft> amountList) {
		Map<Integer, MarketMatchSessionDetail> outputData = new HashMap<Integer, MarketMatchSessionDetail>();
		// Map<Integer, List<MarketMatchDetailDTO>> outputData = new
		// HashMap<Integer, List<MarketMatchDetailDTO>>();
		if (amountList != null && amountList.size() > 0) {
			for (AmountLeft amountLeft : amountList) {
				boolean isOverExists = false;
				if (outputData.containsKey(amountLeft.getSessionId())) {
					MarketMatchSessionDetail marketMatchSessionDetail = outputData
							.get(amountLeft.getSessionId());
					List<MarketMatchDetailDTO> marketMatchDetailDTOs = marketMatchSessionDetail
							.getMarketUserAmountDetail();
					if (marketMatchDetailDTOs != null
							&& marketMatchDetailDTOs.size() > 0) {
						for (MarketMatchDetailDTO marketMatchDetailDTO : marketMatchDetailDTOs) {
							if (marketMatchDetailDTO.getOver() == amountLeft
									.getOver()) {
								if (!marketMatchSessionDetail
										.getMarketUserDetail().containsKey(
												amountLeft.getUserId())) {
									int nextKey = marketMatchSessionDetail
											.getMarketUserDetail().keySet()
											.size() + 1;
									marketMatchSessionDetail
											.getMarketUserDetail().put(
													amountLeft.getUserId(),
													"user" + nextKey);
								}
								marketMatchDetailDTO
										.getUserAmountDetail()
										.put(marketMatchSessionDetail
												.getMarketUserDetail().get(
														amountLeft.getUserId()),
												amountLeft.getLeftAmount());
								isOverExists = true;
								break;
							}
						}
						if (!isOverExists) {
							MarketMatchDetailDTO marketMatchDetailDTO = new MarketMatchDetailDTO();
							marketMatchDetailDTO.setOver(amountLeft.getOver());
							if (!marketMatchSessionDetail.getMarketUserDetail()
									.containsKey(amountLeft.getUserId())) {
								int nextKey = marketMatchSessionDetail
										.getMarketUserDetail().keySet().size() + 1;
								marketMatchSessionDetail.getMarketUserDetail()
										.put(amountLeft.getUserId(),
												"user" + nextKey);
							}
							marketMatchDetailDTO.getUserAmountDetail().put(
									marketMatchSessionDetail
											.getMarketUserDetail().get(
													amountLeft.getUserId()),
									amountLeft.getLeftAmount());
							marketMatchDetailDTOs.add(marketMatchDetailDTO);
						}
					}
				} else {
					List<MarketMatchDetailDTO> dtoList = new ArrayList<MarketMatchDetailDTO>();
					MarketMatchSessionDetail marketMatchSessionDetail = new MarketMatchSessionDetail();
					MarketMatchDetailDTO marketMatchDetailDTO = new MarketMatchDetailDTO();
					marketMatchDetailDTO.setOver(amountLeft.getOver());
					if (!marketMatchSessionDetail.getMarketUserDetail()
							.containsKey(amountLeft.getUserId())) {
						int nextKey = marketMatchSessionDetail
								.getMarketUserDetail().keySet().size() + 1;
						marketMatchSessionDetail.getMarketUserDetail().put(
								amountLeft.getUserId(), "user" + nextKey);
					}
					marketMatchDetailDTO.getUserAmountDetail().put(
							marketMatchSessionDetail.getMarketUserDetail().get(
									amountLeft.getUserId()),
							amountLeft.getLeftAmount());
					dtoList.add(marketMatchDetailDTO);
					marketMatchSessionDetail.getMarketUserAmountDetail().add(
							marketMatchDetailDTO);
					marketMatchSessionDetail.setSessionId("session"
							+ amountLeft.getSessionId());
					outputData.put(amountLeft.getSessionId(),
							marketMatchSessionDetail);
				}
			}
		}
		return outputData;
	}

	public MarketDetailChartDAO getMarketDetailChartDAO() {
		return marketDetailChartDAO;
	}

	public void setMarketDetailChartDAO(
			MarketDetailChartDAO marketDetailChartDAO) {
		this.marketDetailChartDAO = marketDetailChartDAO;
	}

	@Override
	public Map<Long,List<AmountLeft>> getMarketPlayersAmountDetails(Long matchId) {
		List<AmountLeft> matchList = marketDetailChartDAO
				.getMarketMatchDetails(matchId);
		Map<Long,List<AmountLeft>> amountLeftByMarket = new HashMap<Long, List<AmountLeft>>();
		for (AmountLeft amountLeft : matchList) {
				List<AmountLeft> amountLefts = amountLeftByMarket.get(amountLeft.getMarketId().getId());
				if (amountLefts == null){
					amountLefts = new ArrayList<AmountLeft>();
				}
				amountLefts.add(amountLeft);
				amountLeftByMarket.put(amountLeft.getMarketId().getId(), amountLefts);
		}
		return amountLeftByMarket;
	}

}
