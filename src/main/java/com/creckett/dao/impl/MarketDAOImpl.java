package com.creckett.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.creckett.dao.MarketDAO;
import com.creckett.dto.FacebookUserFriend;
import com.creckett.model.Market;
import com.creckett.model.MatchMaster;
import com.creckett.model.User;

@SuppressWarnings("unchecked")
public class MarketDAOImpl extends BaseHibernateDAO<Market> implements
		MarketDAO {

	public List<Market> getMatchMarketsForUser(Long matchId, Long userId) {
		Query query = this.getSession().getNamedQuery(
				"getMarketsByMatchIdAndUserId");
		query.setParameter("moderatorId", userId);
		query.setParameter("matchId", matchId);
		return query.list();
	}

	@Override
	public Market get(Long id) {
		return super.get(Market.class, id);
	}

	public void updateProcessedOver(Long id, Integer matchOver) {
		Market market = this.get(id);
		market.setProcessedOver(matchOver);
		this.save(market);
	}

	@Override
	public Market getMarketByToken(String marketToken) {
		if (marketToken != null && !marketToken.isEmpty()) {
			Query query = this.getSession().createQuery(
					" from Market where marketCode=:marketToken ");
			query.setParameter("marketToken", marketToken);
			return (Market) query.uniqueResult();
		}
		return null;
	}

	@Override
	public boolean ifUserHasMarketForMatch(User user, MatchMaster match) {

		Query query = this
				.getSession()
				.createQuery(
						"select market from Market market where market.match.id = :matchId and "
								+ "exists ( select 1 from market.marketUsers marketuser where marketuser.userId.id = :userId )");
		query.setParameter("userId", user.getId());
		query.setParameter("matchId", match.getId());
		List markets = query.list();
		if (markets != null && !markets.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public List<Market> getMatchInvites(Long matchId,
			ArrayList<FacebookUserFriend> friends) {
		ArrayList<String> profiles = getProfileIdFromUser(friends);
		Query query = this.getSession().createQuery(" from Market market where market.match.id = :matchId" +
				" and market.moderatorId.profileId in ( :profiles ) ");
		query.setParameter("matchId", matchId);
		query.setParameterList("profiles", profiles);
//		Criteria criteria = this.getSession().createCriteria(Market.class).add(
//				Expression.eq("match.id", matchId)).add(
//				Expression.in("profiles", profiles));
		return query.list();
	}

	private ArrayList<String> getProfileIdFromUser(
			ArrayList<FacebookUserFriend> friends) {
		ArrayList<String> toReturn = new ArrayList<String>();
		if (friends != null) {
			for (FacebookUserFriend friend : friends) {
				toReturn.add(friend.getProfileId());
			}
		}
		return toReturn;
	}
	
	@Override
	public Market update(Market businessObject) {
		// TODO Auto-generated method stub
		return super.update(businessObject);
	}

	@Override
	public boolean isMarketCreatedByModerator(Long matchId, Long moderatorId) {
		Query query = this.getSession().createQuery(" from Market market where market.match.id = :matchId" +
				" and market.moderatorId.id = :moderatorId ");
		query.setParameter("moderatorId", moderatorId);
		query.setParameter("matchId", matchId);
		List<Market> markets = query.list();
		return !markets.isEmpty();
	}
}
