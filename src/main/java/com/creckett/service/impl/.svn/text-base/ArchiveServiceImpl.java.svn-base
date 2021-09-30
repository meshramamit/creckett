package com.creckett.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creckett.dao.ArchiveDAO;
import com.creckett.dao.ArchivedAmountLeftDAO;
import com.creckett.dto.ArchiveBet;
import com.creckett.dto.SessionWiseArchiveBets;
import com.creckett.model.ArchivedAmountLeft;
import com.creckett.model.BetState;
import com.creckett.model.Market;
import com.creckett.model.MarketUser;
import com.creckett.model.User;
import com.creckett.service.ArchiveService;

public class ArchiveServiceImpl implements ArchiveService {

	private ArchiveDAO archiveDAO;
	
	private ArchivedAmountLeftDAO amountLeftDAO;
	
	@Override
	public List<Market> getArchivedMatchListForUser(User user) {
		List<Market> markets = archiveDAO.getArchivedMatchListForUser(user);
		if( markets == null ){
			markets = new ArrayList<Market>();
		}
		return markets;
	}

	@Override
	public Collection<SessionWiseArchiveBets> getArchivedSessionWiseBets(
			List<ArchivedAmountLeft> bets) {
		Map<Integer, SessionWiseArchiveBets> archiveBets = new HashMap<Integer, SessionWiseArchiveBets>();
		
		for( ArchivedAmountLeft bet : bets ){
			SessionWiseArchiveBets sessionBet = null;
			ArchiveBet archiveBet = null;
			if( archiveBets.containsKey( bet.getSessionId() ) ){
				sessionBet = archiveBets.get(bet.getSessionId());
			}else{
				sessionBet = new SessionWiseArchiveBets();
				sessionBet.setSession(bet.getSessionId());
				int i = 0;
				for( MarketUser user : bet.getMarketId().getMarketUsers() ){
					i++;
					sessionBet.getUsers().put(user.getUserId().getName(), "user"+i);
				}
				archiveBets.put(bet.getSessionId(), sessionBet);
			}
			String over = "";
			if( bet.getMarketId().getGroupOverValue() == 1 ){
				over = bet.getOver().toString();
			}else{
				over = ((bet.getMarketId().getGroupOverValue() * (bet
						.getOver() - 1)) + 1)
						+ "-"
						+ (bet.getOver() * bet.getMarketId()
								.getGroupOverValue());
			}
			
			if( sessionBet.getBets().containsKey(over) ){
				archiveBet = sessionBet.getBets().get(over);
			}else{
				archiveBet = new ArchiveBet();
				archiveBet.setOver(over);
				sessionBet.getBets().put(over, archiveBet);
			}
			if( bet.getLeftAmount() != null ){
				archiveBet.getUserBets().put(sessionBet.getUsers().get(bet.getUserId().getName()), bet.getLeftAmount().toString());
			}else{
				archiveBet.getUserBets().put(sessionBet.getUsers().get(bet.getUserId().getName()), "0");
			}
			
		}
		return (Collection<SessionWiseArchiveBets>) archiveBets.values();
	}
	
	@Override
	public List<BetState> getArchivedBetsForMarket(Long marketId) {
		List<BetState> bets = archiveDAO.getArchivedBetsForMarket(marketId);
		return bets;
	}
	
	@Override
	public List<ArchivedAmountLeft> getArchivedAmountLeftForMarket(Long marketId) {
		List<ArchivedAmountLeft> amounts = amountLeftDAO.getAmountLeftForMarket(marketId);
		return amounts;
	}
	
	public ArchiveDAO getArchiveDAO() {
		return archiveDAO;
	}

	public void setArchiveDAO(ArchiveDAO archiveDAO) {
		this.archiveDAO = archiveDAO;
	}

	public ArchivedAmountLeftDAO getAmountLeftDAO() {
		return amountLeftDAO;
	}

	public void setAmountLeftDAO(ArchivedAmountLeftDAO amountLeftDAO) {
		this.amountLeftDAO = amountLeftDAO;
	}

}
